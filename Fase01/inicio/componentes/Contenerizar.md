# Contenerización de aplicación java.
## Introducción.
En este punto, crearemos una imagen de nuestra aplicación java. La "contenerizaremos", si es que existe ese verbo.
Lo mejor es decir: "mi aplicación tiene una imagen y se puede usar como contenedor".

Crear una imagen es un ejercicio de abstracción, no es algo complicado, solamente requiere que tengamos claro, mientras creamos la imagen que tenemos 2 espacios, el espacio de creación (nuestro entorno local) y el espacio destino (el paquete que estamos armando). Ambos con un sistema operativo abajo, no siempre el linux del espacio de trabajo destino es el linux local, eso a veces confunde, pero, mientras tengamos separados los espacios en nuestra mente, todo es más sencillo.


Podemos verlo así:
```mermaid
flowchart TB
    subgraph SO_Base["Sistema Operativo (Linux)"]
    end

    subgraph Origen["Espacio de Creación\n(Entorno Local)"]
    end

    subgraph Destino["Espacio Destino\n(Imagen / Paquete)"]
    end

    Origen --> SO_Base
    Destino --> SO_Base

```

```mermaid
flowchart TB
    subgraph BaseLocal["Linux Local"]
    end

    subgraph BaseDestino["Linux dentro de la Imagen"]
    end

    subgraph Origen["Espacio de Creación\n(Entorno Local)"]
    end

    subgraph Destino["Espacio Destino\n(Imagen/Contenedor)"]
    end

    Origen --> BaseLocal
    Destino --> BaseDestino

    style BaseLocal fill:#e8f5e9,stroke:#2e7d32
    style BaseDestino fill:#e3f2fd,stroke:#1565c0
```
