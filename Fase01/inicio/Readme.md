# Fase 01.

## Introducción
-------
Tenemos clara la <a href="../Readme.md">meta</a>, así que, comencemos con la construcción de la Fase 1.
Recuerda que puedes revisar la carpeta <a href="../final/">final</a> si tienes alguna duda concreta o si quieres ver todo funcionando.

Para esta fase 1, requeriremos 3 componentes:

* Una aplicación java (crearemos una con Springboot y expondrá un servicio simple).
* Un Collector, un OpenTelemetry Collector, será el encargado de recibir las métricas que genere nuestra app-java
* Jaeger, un server Jaeger, será nuestro front de consulta, con Jaeger inspeccionaremos nuestro Collector.

Y, agregaremos un componente principal: Docker.
Si ya estás familiarizado con Docker, puedes saltarte ese paso e ir directamente a la construcción de la app en java, 
sino, en Docker 101 tendrás todo el conocimiento necesario para concluir este y todos los ejercicios.

Para mantener separados los componentes y tener organizados mejor nuestro proyecto (además de que és más didactico),
crearemos esta estructura de carpetas, puedes crear una carpeta principal llamada: *workarea*, y dentro hay crear 3 carpetas:
```shell
├ workarea
├───app-java
├───jaeger
├───otel-collector
```

Tenemos entonces:

### Componentes

#### Docker 101.
* <a href="componentes/Docker.md">Docker 101</a>. Todo lo que debes saber de Docker para completar este y los siguientes ejercicios.
#### Aplicacion Java.
* <a href="componentes/Java.md">Aplicación java</a>. La aplicación java que usaremos de ejemplo.
#### Docker. Siguientes pasos.  
* Contenerizacion. Crear un contenedor para nuestra aplicación java.
* Docker compose. Ahora, aprenderemos a usar docker compose.
#### Otel-Collector.
* <a href="componentes/OtelCollector.md">OTEL Collector</a>. La configuración del servidor Collector.
#### Jaeger.
* <a href="componentes/Jaeger.md">Jaeger</a>. La configuración del servidor de Jaeger.
-------

### Juntemos todo.

LLegado a este punto, ya todos los contenedores están funcionando en aislado y has validado con Docker que todo está OK,
estamos usando Docker compose y, todo está ya funcionando.

Ahora lo que sigue es, ponerlos a trabajar en conjunto, para ello, realizaremos los siguientes ajustes:

* Conectando todo.
-------

### Ultimos detalles.

Tenemos todo listo, entonces, hagamos una ultima validación antes de ponernos a analizar lo que podemos obtener de nuestro stack inicial de observabilidad.

* Smoke test.

-------

### Ahora a analizar.
Listo.

Revisemos Jaeger.
* Revisión de trazas.
-------


Si todo está bien, compara tu resultado con lo presentado en la carpeta <a href="../final/Readme.md">final</a>.

