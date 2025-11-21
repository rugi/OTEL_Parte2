# Contenerización de aplicación java.
## Introducción.
En este punto, crearemos una imagen de nuestra aplicación java. La "contenerizaremos", si es que existe ese verbo.
Lo mejor es decir: "mi aplicación tiene una imagen y se puede usar como contenedor".

 

Crear una imagen es un ejercicio de abstracción. No es complicado: solo requiere tener claro que existen dos espacios diferentes durante el proceso.

El espacio de creación (tu entorno local) y el espacio destino (la imagen o paquete que estás armando).

Ambos tienen un sistema operativo debajo —y no siempre el Linux del destino es el mismo Linux de tu máquina. Esa diferencia suele confundir, pero cuando separas ambos espacios mentalmente, todo se vuelve mucho más sencillo.


Podemos verlo así:
 

```mermaid
flowchart TB
    %% Bloque inferior genérico
    subgraph LinuxGenerico["Linux Genérico\n(Concepto Base Común)"]
    end

    %% Bases específicas
    subgraph BaseLocal["Linux Local"]
    end

    subgraph BaseDestino["Linux dentro de la Imagen"]
    end

    %% Espacios
    subgraph Origen["Espacio de Creación\n(Entorno Local)"]
    end

    subgraph Destino["Espacio Destino\n(Imagen / Contenedor)"]
    end

    %% Relaciones
    Origen --> BaseLocal
    Destino --> BaseDestino

    BaseLocal --> LinuxGenerico
    BaseDestino --> LinuxGenerico

    %% Estilos opcionales (colors)
    style LinuxGenerico fill:#f3e5f5,stroke:#7b1fa2
    style BaseLocal fill:#e8f5e9,stroke:#2e7d32
    style BaseDestino fill:#e3f2fd,stroke:#1565c0

```
