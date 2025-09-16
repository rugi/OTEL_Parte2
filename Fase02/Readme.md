
# 📘 Fase 2 – Captura y Visualización de Logs

## 🎯 Objetivo
Capturar logs desde contenedores Docker y visualizarlos en Grafana usando Loki como backend. Esta fase incorpora **Promtail y Fluent Bit** para mostrar ambas opciones de recolección de logs.

---

## 🟩 Alcance técnico mínimo

- [ ] **Agregar Loki al `docker-compose.yml`**
- [ ] **Agregar Promtail y Fluent Bit como servicios** (ambos coexistiendo, uno puede estar desactivado)
- [ ] **Configurar Promtail para leer logs de contenedores Docker**
- [ ] **Configurar Fluent Bit como alternativa de lectura/exportación**
- [ ] **Visualizar logs de `app-java` en Grafana**
- [ ] **Verificar etiquetas (`container`, `job`, etc.) y búsqueda funcional**
- [ ] **Preparar correlación futura con `trace_id`** (pero no implementarla aún)

---

## 🧩 Alcance opcional / avanzado

- [ ] Usar **solo Fluent Bit** si se requiere mayor flexibilidad (filtros, enrouting, enriquecimiento)
- [ ] Emitir logs **estructurados (JSON)** desde `app-java` con `logback` + `JsonEncoder`
- [ ] Inyectar `trace_id` y `span_id` en logs usando **MDC (Mapped Diagnostic Context)** o bridge con SDK de OpenTelemetry
- [ ] Crear **paneles de logs críticos** en Grafana (por nivel, mensaje, job, etc.)
- [ ] Comparar **Promtail vs Fluent Bit** en capacidad de etiquetas, rendimiento y facilidad de uso

---

## 🔍 Validación de resultados

| Validación técnica                                 | Cómo comprobarlo                                                                 |
|----------------------------------------------------|----------------------------------------------------------------------------------|
| Ver logs de `app-java` en Grafana (tiempo real)    | Abrir Grafana → ir a *Explore* → seleccionar *Loki* → visualizar por `container` |
| Logs tienen etiquetas útiles                       | Usar `{job="docker", container="app-java"}` para filtrar                         |
| Logs muestran mensajes INFO, WARN, ERROR           | Filtrar por `level` si se usan logs estructurados o buscar por texto            |
| Promtail está funcionando                          | `docker-compose logs promtail` → debe mostrar logs capturados                   |
| Fluent Bit está funcionando                        | `docker-compose logs fluent-bit` → debe mostrar conexión con Loki               |
| Grafana puede consultar Loki sin errores           | Panel Explore debe responder sin errores                                         |
| Loki muestra retención temporal                    | Buscar logs de hace minutos para comprobar retención                            |
| Preparar búsqueda por `trace_id`                   | Simular trazas con ID y revisar si aparecen en logs como texto plano            |

---

## 🧠 ¿Por qué incluir ambos?

| Promtail                      | Fluent Bit                         |
|------------------------------|------------------------------------|
| Sencillo de configurar       | Mucho más flexible y escalable     |
| Integración nativa con Loki  | Compatible con múltiples backends  |
| Ideal para comenzar rápido   | Ideal para producción compleja     |

> En este laboratorio, ambos estarán disponibles para que el participante active uno u otro según el caso.

---

