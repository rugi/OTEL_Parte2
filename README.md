# Laboratorio Técnico de Observabilidad con OpenTelemetry

¡Bienvenido a este laboratorio técnico progresivo sobre observabilidad! 

Aquí encontrarás una guía paso a paso que explora cómo integrar **trazas, logs y métricas** en un entorno local usando tecnologías modernas como **OpenTelemetry, Loki, Promtail, Fluent Bit, Grafana, Tempo y MinIO**, todo orquestado desde `docker-compose`.

## 🧭 Propósito del Proyecto

Este laboratorio tiene varios objetivos clave:

- Aprender haciendo: mientras construyo, aprendo; y comparto lo aprendido para que otras personas también puedan beneficiarse.
- Ofrecer una base realista para quienes desean entender cómo construir un stack completo de observabilidad sin depender de soluciones propietarias.
- Respaldar con material técnico mis próximas charlas en 2026.

## ✍️ Filosofía del Contenido

- Todo está redactado en tono **didáctico** y **amigable**, sin pretensiones de ser contenido académico, pero tampoco superficial.
- Si buscas una referencia rápida, es mejor un TikTok o un reel. Si buscas profundidad académica, busca mejor un libro. Este laboratorio está en medio: **serio pero accesible**.
- Si ves algo mejorable, **por favor abre un issue**. Estoy abierto a feedback y mejoras.
- Este proyecto se entrega **"as is"**, sin garantía de finalización, pero con mucha buena voluntad y (deseos de) consistencia.

## 📚 Material Complementario

Habrá contenido 101 que acompañe este laboratorio, ahora mismo hay uno de docker, se integran justo en el momento que considero adecuado, al final de todas las fases, pondré un listado final de todos los 101 dentro de todo este material. Otros temas básicos tendrán su espacio más adelante.

## 🧱 Fases del Laboratorio
 
| Fase     | Objetivo                                                                 | Stack principal                                                | Estatus       |
|----------|--------------------------------------------------------------------------|----------------------------------------------------------------|---------------|
| Fase 01  | Instrumentar trazas usando agente automático de OpenTelemetry           | OpenTelemetry Collector, app-java, Jaeger                      | ✅ Completa   |
| Fase 02  | Recolección y visualización de logs desde contenedores Docker           | Loki, Fluent Bit, Grafana                                      | ✅ Completa   |
| Fase 02b | Agregar etiquetas útiles en logs y migrar recolección con Promtail      | Promtail, Loki, Grafana                                        | 🔄 En revisión |
| Fase 03  | Integrar métricas con Prometheus y visualizarlas en Grafana             | Prometheus, OpenTelemetry Collector, Grafana                   | 🚧 Pendiente  |
| Fase 04  | Correlación e integración avanzada entre trazas, logs y métricas        | OTel Collector, Loki, Prometheus, Grafana, TraceQL, LogsQL     | 🚧 Pendiente  |
| Fase 05  | Bonus: Dashboards como código utilizando PERSES                         | PERSES, Grafana, OTel, Loki, Prometheus                        | 🚧 Pendiente  |
| Fase 06  | Persistencia distribuida de logs                                         | Loki + MinIO (S3-like backend), Docker volumes                 | 🚧 Pendiente  |
| Fase 07  | Persistencia distribuida de trazas                                       | Tempo + MinIO, Jaeger, OTel Collector                          | 🚧 Pendiente  |
 

## 🎤 Próximas Charlas

Este laboratorio servirá de base para varias charlas en 2026. Si me ves en alguna, **salúdame, me encantará saber que llegaste aquí**.

* `🟩` EVENTO: [https://2025.jconf.gt/es] (JConf Guatemala. 2026)
    * Charlas:
        * OpenTelemetry para todos.
        * 3 JEPs que debemos conocer.


## 📹 ¿Quieres videos?

Levanta un issue y dime qué parte del laboratorio te gustaría ver grabada o explicada en video.

## 🔖 Licencia

Este repositorio se publica bajo la **MIT License**. Eres libre de usarlo, copiarlo o adaptarlo, siempre que mantengas la atribución.

---

**Gracias por visitar este proyecto**. Espero te sea útil.

> _RuGI_
