# Laboratorio Técnico de Observabilidad con OpenTelemetry

¡Bienvenido a este laboratorio técnico progresivo, didàctico, posicionador sobre observabilidad! 

Aquí encontrarás una guía paso a paso que explora cómo integrar **trazas, logs y métricas** en un entorno local usando tecnologías modernas como **OpenTelemetry, Loki, Promtail, Fluent Bit, Grafana, Tempo y MinIO**, todo orquestado desde `docker-compose`.
(Esto para que le sea util a la mayor cantidad de personas).

## 🧭 Propósito del Proyecto

Este laboratorio tiene varios objetivos clave:

- Aprender haciendo: mientras construyo, aprendo; y comparto lo aprendido para que otras personas también puedan beneficiarse.
- Ofrecer una base funcional para quienes desean entender cómo construir un stack completo de observabilidad usando preferencialmente proyectos opensource.
- Respaldar con material técnico comprobable mis próximas charlas en 2026 (y el último jalón del 2025).

## ✍️ Filosofía del Contenido

- Todo está redactado en tono **didáctico** y **amigable**, sin pretensiones de ser contenido académico, pero tampoco superficial.
- Si buscas una referencia rápida, es mejor un TikTok o un reel. Si buscas profundidad académica, busca mejor un libro. Este laboratorio está en medio: **serio pero accesible**.
- Si ves algo mejorable, **por favor abre un issue**. Estoy abierto a feedback y mejoras.
- Este proyecto se entrega **"as is"**, sin garantía de finalización, pero con mucha buena voluntad y (deseos de) consistencia.

## 📚 Material Complementario

Mi idea es tener contenido 101 para este laboratorio,  mi idea tambien es que estos 101 se integren conforme se vayan requiriendo; ahora mismo hay uno de docker.
También hay videos cortos, dirigidos principalmente a personas que apenas inician con el uso de este tipo de ambientes. 
Su objetivo es reforzar algunas acciones de validación.
Habrá mucho de eso, puntos de validación, es algo que me agrada de los tutoriales que tomo, así que, replicaré esa estraregia.

Al final de todas las fases, pondré un listado final todo este material complementario.

## 🧱 Fases del Laboratorio
Las fases aun no completadas podrán cambiar de stack sin aviso, el objetivo es cumplir todas las fases con un stack opensource.
 
| Fase     | Objetivo                                                                 | Stack principal                                                | Estatus       | Liberado en:|
|----------|--------------------------------------------------------------------------|----------------------------------------------------------------|---------------|---------------|
| <a href="Fase01/Readme.md">Fase 01</a>  | Instrumentar trazas usando agente automático de OpenTelemetry           | OpenTelemetry Collector, app-java, Jaeger                      | 🔄 En revisión  | JConf Guatemala 2025. |
| Fase 02  | Recolección y visualización de logs desde contenedores Docker           | Loki, Fluent Bit, Grafana                                      | 🔄 En revisión   | Pendiente |
| Fase 02b | Agregar etiquetas útiles en logs y migrar recolección con Promtail      | Promtail, Loki, Grafana                                        | 🔄 En revisión | Pendiente |
| Fase 03  | Integrar métricas con Prometheus y visualizarlas en Grafana             | Prometheus, OpenTelemetry Collector, Grafana                   | 🚧 Pendiente  | Pendiente |
| Fase 04  | Correlación e integración avanzada entre trazas, logs y métricas        | OTel Collector, Loki, Prometheus, Grafana, TraceQL, LogsQL     | 🚧 Pendiente  | Pendiente |
| Fase 05  | Bonus: Dashboards como código utilizando PERSES                         | PERSES, Grafana, OTel, Loki, Prometheus                        | 🚧 Pendiente  | Pendiente |
| Fase 06  | Persistencia distribuida de logs                                         | Loki + MinIO (S3-like backend), Docker volumes                 | 🚧 Pendiente  | Pendiente |
| Fase 07  | Persistencia distribuida de trazas                                       | Tempo + MinIO, Jaeger, OTel Collector                          | 🚧 Pendiente  | Pendiente |
| Fase 08 | Persistencia distribuida de métricas                                      | Prometheus + Thanos + MinIO                                    | 🚧 Pendiente | Pendiente |

 

## 🎤 Próximas Charlas

Este laboratorio servirá de base para varias charlas en 2026. Si me ves en alguna, **salúdame, me encantará saber que llegaste aquí**.

### 2026
#### Abril.
##### KCD Guadalajara 2026., México México.
###### Evitando el vendor lock-in en el almacenamiento de tu observabilidad: OpenTelemetry & amigos. Charla.


## 📹 ¿Quieres videos?

Levanta un issue y dime qué parte del laboratorio te gustaría ver grabada o explicada en video.

## 🔖 Licencia

Este repositorio se publica bajo la **MIT License**. Eres libre de usarlo, copiarlo o adaptarlo, siempre que mantengas la atribución.

---

**Gracias por visitar este proyecto**. Espero te sea útil.

> _RuGI_
