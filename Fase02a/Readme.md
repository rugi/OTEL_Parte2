🔁 Fase 2a – Etiquetado y transición de Fluent Bit a Promtail
🎯 Objetivo general:

Mejorar la calidad de los logs recolectados mediante etiquetado enriquecido, y reemplazar Fluent Bit por Promtail como agente de recolección principal en esta etapa del laboratorio.

✅ Actividades clave

Agregar etiquetas útiles (labels) en Fluent Bit

Incluir: container_name, app, env, etc.

Objetivo: permitir búsquedas como {container_name="otel_parte2-app-java"} en Grafana/Loki.

Validar búsqueda por etiquetas

Confirmar en Loki y en Grafana que los logs se pueden consultar utilizando los nuevos labels.

Desactivar Fluent Bit (sin eliminar configuración)

Parar el servicio para evitar duplicidad.

Mantenerlo disponible como alternativa futura o comparativa.

Agregar Promtail al docker-compose.yml

Configurarlo para leer los logs de los contenedores Docker.

Integrarlo con Loki como destino.

Validar que Promtail levanta correctamente

Revisar con docker-compose logs promtail que el servicio arranca sin errores.

Verificar conexión con Loki.

Promtail toma el lugar de Fluent Bit

Confirmar que los logs de app-java y otros contenedores están siendo recolectados por Promtail.

Validar en Grafana que todo sigue funcionando

Confirmar que:

Se siguen viendo los logs.

Se pueden hacer búsquedas con labels.

No hay pérdida de logs o regresión de funcionalidad.