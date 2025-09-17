# 🧪 Operaciones del laboratorio `otel-vision-completa`

Guía práctica para iniciar, detener, inspeccionar y controlar los contenedores que componen este laboratorio basado en Docker Compose.

---

## 🚀 1. Iniciar todo el stack

Desde la raíz del proyecto:

```bash
docker-compose up --build
```

Esto:
- Construye el microservicio Java (`app-java`) usando su `Dockerfile`
- Descarga e inicia los servicios: `otel-collector`, `jaeger`, etc.
- Reconstruye imágenes si hay cambios en el código
- Mapea los puertos definidos en `docker-compose.yml`

> 🌐 Accede a:
> - Microservicio: http://localhost:8080/pago
> - Jaeger UI: http://localhost:16686

---

## 🛑 2. Detener el laboratorio

### 🔸 Opción A: Detener sin eliminar nada

```bash
docker-compose down
```

- Detiene todos los contenedores
- Conserva volúmenes, redes e imágenes

> ✅ Ideal para pausar sin perder datos

---

### 🔸 Opción B: Detener y limpiar completamente

```bash
docker-compose down --volumes --remove-orphans
```

Esto:
- Elimina contenedores
- Borra volúmenes (por ejemplo, almacenamiento de Jaeger o Collector)
- Elimina contenedores huérfanos (no listados en el archivo)

> ⚠️ Ideal cuando haces cambios grandes o tienes errores de red/nombres

---

## 🛠️ 3. Comandos útiles con Docker Compose

### ▶️ Levantar solo un servicio

```bash
docker-compose up <nombre-del-servicio>
```

Ejemplo:

```bash
docker-compose up otel-collector
```

> Útil para debuggear un servicio específico (como collector)

---

### 🔄 Reiniciar un solo servicio

```bash
docker-compose restart <nombre-del-servicio>
```

Ejemplo:

```bash
docker-compose restart app-java
```

> Forza a reiniciar el servicio sin tocar los demás

---

### 🛑 Detener solo un servicio

```bash
docker-compose stop <nombre-del-servicio>
```

Ejemplo:

```bash
docker-compose stop jaeger
```

> El contenedor sigue existiendo, solo se detiene

---

### 🗑 Eliminar un solo servicio

```bash
docker-compose rm <nombre-del-servicio>
```

Ejemplo:

```bash
docker-compose rm app-java
```

> Se borra el contenedor; puede requerir confirmación

---

## 🕵️ 4. Ver los logs de los servicios

### 🔍 Ver logs de todos los servicios (en tiempo real)

```bash
docker-compose logs -f
```

### 🔍 Ver logs de un solo servicio

```bash
docker-compose logs otel-collector
```

> ✅ Muy útil para ver por qué un servicio no arranca correctamente

---

## 🐚 5. Entrar a un contenedor en ejecución

Primero lista los contenedores:

```bash
docker ps
```

Luego entra con:

```bash
docker exec -it <nombre-del-contenedor> sh
```

Ejemplo:

```bash
docker exec -it otel-vision-completa-app-java-1 sh
```

---

## 🧪 6. ¿Se puede hacer `ping` entre contenedores?

✅ **Cierto**

Todos los contenedores del mismo `docker-compose.yml` que comparten red pueden comunicarse por nombre de servicio.

Ejemplo desde dentro de `app-java`:

```bash
ping otel-collector
```

> 🧠 Si falla, revisa que todos los servicios estén en la misma red (`observabilidad-net`)

---
