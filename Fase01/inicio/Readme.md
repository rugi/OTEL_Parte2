
## Pasos
-------
Tenemos clara la meta, así que, comencemos con la construcción de la Fase 1.
Recuerda que puedes revisar la carpeta final si tienes alguna duda concreta o si quieres ver todo funcionando.

Para esta fase 1, requeriremos 3 componentes:

* Una aplicación java (crearemos una con Springboot y expondrá un servicio simple).
* Un Collector, un OpenTelemetry Collector, será el encargado de recibir las métricas que genere nuestra app-java
* Jaeger, un server Jaeger, será nuestro front de consulta, con Jaeger inspeccionaremos nuestro Collector.

Y, agregaremos un componente principal: Docker.

Tenemos entonces:

### Componentes

#### Docker 101.
* <a href="componentes/Docker.md">Docker 101</a>
#### Aplicacion Java.
#### Otel-Collector.
#### Jaeger.
-------

### Juntemos todo.

LLegado a este punto, ya todos los contenedores están funcionando, ahora lo que sigue es, ponerlos a trabajar en conjunto, para ello, realizaremos los siguientes ajustes:

-------

### Ultimos detalles.

Tenemos todo listo, entonces, hagamos una ultima validación antes de ponernos a analizar lo que podemos obtener de nuestro stack inicial de observabilidad.

-------

### Ahora a analizar.
Listo.

Revisemos Jaeger.
-------

