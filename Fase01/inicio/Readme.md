# Fase 1.

## Bienvenido.

## Introducción
Este módulo representa la **primera fase** del laboratorio de observabilidad distribuida basado en OpenTelemetry.  
Aquí se construye un stack mínimo pero funcional que permite capturar, recolectar y visualizar trazas distribuidas generadas desde un microservicio Java existente.

Es decir, asumimos que tienes un microservicio en java funcionando y quieres comenzar a utilizar opentelemetry.
En esta **primera fase**, utilizaremos jaeger para visualizar las trazas de los request y los responses, requeriremos otel-collector, básicamente es el servidor que recolectará toda la información,
es el puente entre lo que genera nuestra app en java y lo que queremos visualizar.

Esta es la forma menos intrusiva para utilizar opentelemetry, el código java "no se toca", solo adjuntamos un jar al momento de ejecutar nuestro microservicio.

Utilizaremos Docker para facilitar las cosas, sino sabes Docker, no te preocupes, la redacción será amigable y tratará de irte aclarando dudas,
si ya sabes Docker, solo verás redundante esa perte, pero te la puedes saltar.

Así que, manos  a la obra.

Asumiremos que partimos desde cero.
