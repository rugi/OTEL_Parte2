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



Diagrama de componentes:
``` mermaid
graph TD
    A[💻 Linux Host] --> B[🐳 Docker Engine]
    B --> C[📦 Docker Compose]

    %% Contenedores encima del Compose
    subgraph Containers [Contenedores en ejecución]
        C --> D1[app-java Puerto: 8080]
        C --> D2[otel-collector Puertos: 4317, 55681, 13133, 8888]
        C --> D3[jaeger Puertos: 16686, 14250, 9411]
    end

 

 

    %% Estilo visual
    style A fill:#2f3542,stroke:#fff,stroke-width:1px,color:#fff
    style B fill:#57606f,stroke:#fff,color:#fff
    style C fill:#70a1ff,stroke:#fff,color:#fff
    style D1 fill:#7bed9f,stroke:#2f3542
    style D2 fill:#fffa65,stroke:#2f3542
    style D3 fill:#ff6b81,stroke:#2f3542
    style Network fill:#dfe4ea,stroke:#57606f,stroke-dasharray:3 3
```
