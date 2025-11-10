# Aplicación java

# Preparando el código
En este apartado, crearemos una aplicación java sencilla, posteriormente la empaquetaremos en una imagen para poder usarla con docker, pero, por ahora nos concentraremos solo en esta parte:
```
!
```
Crear la aplicación java y validar que funciona perfectamente.
En mi experiencia, ver que algo ya funciona, te mantiene motivado a seguir, así que, ya funcionando iremos haciendo ajustes.

Crearemos una aplicación java, utilizando Springboot:
* Una clase controlador: `HelloController.java`
* Una clase aplicación: `AppJavaApplication.jav`
* Una clase para meter test: `AppJavaApplicationTests.java`
* Un archivo .properties para cumplir con el standar: `application.properties` 
* Y por supuesto, requeriremos nuestro: `pom.mxl`

Nuestro controlador implementará un endpoint con el método GET:
```shell
/pago
```

De momento no recibe nada, nos interesa de momento tener el esqueleto, conforme avancemos iremos implementando más cosas y corrigiendo algunas cosas, meter un logger, adopdar algunas convenciones en los nombres,etc.
El objetivo es, echarlo andar lo más sencillo posible. Cuando ya todo compile y ejecute correctamente, repasaremos con algo de detalle lo realizado.

Así que, manos a la obra.

### Crear la estructura de carpetas.
Primero vamos a crear una estructura de carpetas:
```shell
│workarea
├───app-java
│   ├───.mvn
│   │   └───wrapper
│   └───src
│       ├───main
│       │   ├───java
│       │   │   └───com
│       │   │       └───example
│       │   │           └───app_java
│       │   │               └───controller
│       │   └───resources
│       │       ├───static
│       │       └───templates
│       └───test
│           └───java
│               └───com
│                   └───example
│                       └───app_java
```

### Agrega el archivo properties

### Agregar la clase controlador

### Agregar la clase de applicacion Spring boot

### Agregar la clase de Test.

### Agregar el pom.xml

### Validar que compile sin problemas

### Validar que ejecute sin problemas.

## Revisemos lo realizado.

## Maven

## Spring 

## Spring boot

## Spring boot. Los controladores

## Spring boot. La clase de arranque

## El archivo de propiedades.

## La clase de Testing

## Probemos que todo esté OK.

----
Podemos continuar.

Regresa al índice de la Fase 01, y continua con el siguiente paso: Contenerizar esta app: <a href="../Readme.md">Fase 01.</a>

¡¡Adelante!!
