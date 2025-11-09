# Laboratorio Técnico de Observabilidad con OpenTelemetry

¡Bienvenido a este laboratorio técnico progresivo, didàctico, posicionador sobre observabilidad! 

Aquí encontrarás una guía paso a paso que explora cómo integrar **trazas, logs y métricas** en un entorno local usando tecnologías modernas como **OpenTelemetry, Loki, Promtail, Fluent Bit, Grafana, Tempo y MinIO**, todo orquestado desde `docker-compose`.
(Esto para que le sea util a la mayor cantidad de personas).

## 🧭 Propósito del Proyecto

Este laboratorio tiene varios objetivos clave:

- Aprender haciendo: mientras construyo, aprendo; y comparto lo aprendido para que otras personas también puedan beneficiarse.
- Ofrecer una base funcional para quienes desean entender cómo construir un stack completo de observabilidad usando preferencialmente proyectos opensource.
- Respaldar con material técnico comprobable mis próximas charlas en 2026 (y el último jalón del 2025).
- Reforzar mi perfil técnico como Solutions Architect/Software engineer y en proceso Observability Focus (OpenTelemetry, CNCF).

## ✍️ Filosofía del Contenido

- Todo está redactado en tono **didáctico** y **amigable**, sin pretensiones de ser contenido académico, pero tampoco superficial.
- Si buscas una referencia rápida, es mejor un TikTok o un reel. Si buscas profundidad académica, busca mejor un libro. Este laboratorio está en medio: **serio pero accesible**.
- Si ves algo mejorable, **por favor abre un issue**. Estoy abierto a feedback y mejoras.
- Este proyecto se entrega **"as is"**, sin garantía de finalización, pero con mucha buena voluntad y (deseos de) consistencia.

## 📚 Material Complementario

Habrá contenido 101 que acompañe este laboratorio, ahora mismo hay uno de docker, se integran justo en el momento que considero adecuado, al final de todas las fases, pondré un listado final de todos los 101 dentro de todo este material. Otros temas básicos tendrán su espacio más adelante.

## 🧱 Fases del Laboratorio
 
| Fase     | Objetivo                                                                 | Stack principal                                                | Estatus       | Liberado en:|
|----------|--------------------------------------------------------------------------|----------------------------------------------------------------|---------------|---------------|
| Fase 01  | Instrumentar trazas usando agente automático de OpenTelemetry           | OpenTelemetry Collector, app-java, Jaeger                      | ✅ Completado   | JConf Guatemala 2025. |
| Fase 02  | Recolección y visualización de logs desde contenedores Docker           | Loki, Fluent Bit, Grafana                                      | 🔄 En revisión   | Pendiente |
| Fase 02b | Agregar etiquetas útiles en logs y migrar recolección con Promtail      | Promtail, Loki, Grafana                                        | 🔄 En revisión | Pendiente |
| Fase 03  | Integrar métricas con Prometheus y visualizarlas en Grafana             | Prometheus, OpenTelemetry Collector, Grafana                   | 🚧 Pendiente  | Pendiente |
| Fase 04  | Correlación e integración avanzada entre trazas, logs y métricas        | OTel Collector, Loki, Prometheus, Grafana, TraceQL, LogsQL     | 🚧 Pendiente  | Pendiente |
| Fase 05  | Bonus: Dashboards como código utilizando PERSES                         | PERSES, Grafana, OTel, Loki, Prometheus                        | 🚧 Pendiente  | Pendiente |
| Fase 06  | Persistencia distribuida de logs                                         | Loki + MinIO (S3-like backend), Docker volumes                 | 🚧 Pendiente  | Pendiente |
| Fase 07  | Persistencia distribuida de trazas                                       | Tempo + MinIO, Jaeger, OTel Collector                          | 🚧 Pendiente  | Pendiente |
 

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
