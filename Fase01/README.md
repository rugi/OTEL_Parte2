# 🟢 Fase 1 – Captura de Trazas con OpenTelemetry

Este módulo representa la **primera fase** del laboratorio de observabilidad distribuida basado en OpenTelemetry.  
Aquí se construye un stack mínimo pero funcional que permite capturar, recolectar y visualizar trazas distribuidas generadas desde un microservicio Java.

---

## 🎯 Objetivo

> Demostrar cómo capturar trazas de una aplicación Java instrumentada automáticamente, enviarlas a un OpenTelemetry Collector y visualizarlas en Jaeger usando el protocolo Zipkin.

---

## 📦 Stack utilizado

| Componente           | Descripción                                     |
|----------------------|-------------------------------------------------|
| `app-java`           | Microservicio Spring Boot con agente OpenTelemetry Java |
| `otel-collector`     | OpenTelemetry Collector con receptor OTLP y exportador Zipkin |
| `jaeger`             | Visualizador de trazas                          |
| `docker-compose`     | Orquestación de los contenedores                |

---

## 🧱 Estructura

```
fase-1-tracing/
├── app-java/                  # Proyecto Java instrumentado
├── otel-collector/            # Configuración del Collector
│   └── config.yaml
├── docker-compose.yml         # Orquestador de servicios
├── lab-operaciones.md         # Guía para levantar, detener y explorar el laboratorio
├── scripts/
│   └── generar_trafico.sh     # Script para generar trazas
└── README.md                  # Este documento
```

---

## 🚀 Resultado esperado

- Ejecutar `curl http://localhost:8080/pago`
- Ver las trazas reflejadas en [http://localhost:16686](http://localhost:16686)
- Seleccionar el servicio `app-java` en Jaeger y explorar los spans generados

---

## 🔍 Conceptos aplicados

- Instrumentación automática (Java Agent)
- Recolección con OTLP gRPC
- Exportación vía protocolo Zipkin
- Visualización distribuida con Jaeger
- Separación de componentes (Collector desacoplado)
- Multi-stage Docker build

---

## 📘 Próxima fase (Fase 2)

> Integración de logs distribuidos y correlación de trazas con herramientas como Loki o Fluent Bit.

---
