# Fase 1.

## Bienvenido.

## Introducción
Este módulo presenta la **primera fase** del laboratorio de observabilidad distribuida basado en OpenTelemetry.  
Aquí se construye un stack mínimo pero funcional que permite capturar, recolectar y visualizar trazas distribuidas generadas desde un microservicio Java existente.

Es decir, asumimos que tienes un microservicio en java funcionando y quieres comenzar a utilizar opentelemetry.
En esta **primera fase**, utilizaremos:

* Jaeger para visualizar las trazas de los request y los responses, acá veremos la actividad.
* Requeriremos otel-collector, básicamente es el servidor que recolectará toda la información, es el puente entre lo que genera nuestra app en java y lo que queremos visualizar.
* Por supuesto, una aplicación java, usaremos una construida con Spring boot,
  
Esta es la forma menos intrusiva para utilizar opentelemetry, el código java "no se toca", solo adjuntamos un jar al momento de ejecutar nuestro microservicio.
A esto en OpenTelemetry lo conocemos como "instrumentación automática", el jar se mantiene tal cual, y solo se "adjunta" un agente (otro jar, el de opentelemetry) al momento de arrancar.

Utilizaremos Docker para facilitar las cosas, sino sabes Docker, no te preocupes, la redacción será amigable y tratará de irte aclarando dudas,
si ya sabes Docker, solo verás redundante esa parte, pero te la puedes saltar.

Asumiremos que partimos desde cero, que solo tenemos una app en java funcionando.
``` mermaid
graph TD
    %% Contenedores encima del Compose
    subgraph Containers [App Java]
        D1[app-java]
    end
    %% Estilo visual
    style D1 fill:#7bed9f,stroke:#2f3542
``` 

Diagrama de componentes que aprenderemos a utilizar:
``` mermaid
graph TD
    A[💻 Linux Host] --> B[🐳 Docker Engine]
    B --> C[📦 Docker Compose]

    %% Contenedores encima del Compose
    subgraph Containers [Contenedores en ejecución]
        C --> D1[app-java]
        C --> D2[otel-collector]
        C --> D3[jaeger]
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

El objetivo es llegar a esto:

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

    %% Conexiones de red internas
    D1 ---|OTLP gRPC| D2
    D2 ---|Zipkin Exporter| D3

    %% Red
    subgraph Network [🌐 observabilidad-net -bridge-]
        D1
        D2
        D3
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
Para continuar, veras 2 carpetas:

* <a href="inicio/Readme.md">inicio.</a>
* <a href="final/README.md">final.</a>

La carpeta inicio es nuestro punto de partida, a partir de ahí, junto con la guía, podrás llegar a lo que contiene la carpeta final.

La carpeta final tiene ya todo terminado, puedes usarla de referencia o, puedes ir directo a ella para ver todo ya terminado.
Si tu intención es aprender todo el proceso, te recomiendo seguir la guía desde la carpeta inicio, si solo quieres tener referencias o ver todo en acción,
la carpeta final es para tí.

Así que, manos  a la obra.

----
Si ya terminaste esta Fase 01, puede avanzar a la <a href="../Fase02/Readme.md">Fase 02</a>


¡¡Suerte!!
---
@RuGI
