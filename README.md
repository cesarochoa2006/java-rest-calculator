# Java rest calculator

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/summary/overall?id=cesarochoa2006_java-rest-calculator)

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)]()

Proyecto de calculadora con servicios rest, construido sobre spring boot y spring webflux

- Suma
- Resta
- Multiplicación
- División
- Potenciación

## Uso
### Flujo básico
1. Obtener un token
   Peticion GET al path `calculadora/token`. Puedes generar tantos tokens como quieras. Para cada token puedes realizar un número ilimitado de operaciones y puedes agregar tantos operandos como desees.
2. Agregar operando(u operandos)
   Puedes agregar uno o varios operandos realizando peticiones POST al path `/calculadora/agregar` de la siguiente manera:

   `{
   "datos":"100",
   "token":"mi token generado"
   }`
   o
   `{
   "datos":"100; 200; 300",
   "token":"mi token generado"
   }`

   Puedes agregar operandos enteros positivos y negativos y decimales con punto

3. Realizar operaciones
   Puedes realizar operaciones enviando peticiones POST al path `/calculadora/calcular` con: 'suma', 'resta', 'multiplicacion', 'division', 'potenciacion' de la siguiente manera:

   `{
   "operacion":"suma",
   "token":"mi token generado"
   }`

   Notas:
    - Cuando se realiza una operación exitosamente, el resultado de la misma queda almacenado como único operando de la misma, por lo que puedes realizar operaciones en cadena utilizando el resultado de la última operación ejecutada
    - Si la operación enviada devuelve un número demasiado grande o demasiado pequeño y se desborda (infinito o menos ininito), debes generar un nuevo token.

### Documentación adicional.

Puedes encontrar ejemplos de consumo de los servicios en la colección de postman del proyecto [ver archivo](https://github.com/cesarochoa2006/java-rest-calculator/blob/master/JavaRestCalculator.postman_collection.json).

## Sobre el proyecto
### Desarrollo y generalidades
Proyecto construido sobre Java 11, Maven y Spring Boot, con enfoque hacia 'DDD' mediante 3 módulos: Utilitarios (**app-util**), Lógica de negocio (**services**) y aplicación principal (**main-app**); cumpliendo con generalidades de principios SOLID, bajo acoplamiento, alta cohesión, y mantenibilidad en niveles viables.

Para lanzar el proyecto basta con ejecutar el jar generado en el módulo de aplicación principal **main-app-1.0.0.jar**.
### Patrones de diseño aplicados
- Para los comandos se utilizaron controladores rest de Spring, aplicando una combinación de patrones **fábrica** mediante la fábrica de beans de Spring junto a patrón **comando** para cada uno de los servicios. Escalable mediante la creación de nuevos comandos interoperables con el contexto de la aplicación.

- Para las operaciones matemáticas definidas se implementa una combinación de patrones **estrategia** junto a un servicio spring **singleton** decorado con la etiqueta service, que mediante **java reflection** realiza una búsqueda de todas las clases que hereden a '***Operator***' (ver **CalculatorService**) una vez iniciado el proyecto. Para la escalabilidad del mismo se sugiere simplemente agregar nuevas operaciones como subclases de ***Operator***.

### Testing unitario
El proyecto cuenta con testing unitario mediante JUnit, Mockito y Spring tests. Los tests están nombrados con la nomenclatura *Test.

### Despliegue y CI
- Este proyecto tiene soporte *Docker* (revisar Dockerfile). Para compilar una nueva imagen de Docker simplemente ejecute `docker build . -t java-rest-calculator` sobre la carpeta raíz del proyecto.
- Este proyecto tiene un pipeline de integración continua que ejecuta mediante github actions sobre la rama master:
    1. Análisis estático de codigo "*code quality*" con sonar cloud.
    2. Despliegue de imagenes de contenedor hacia Docker Hub [ver](https://hub.docker.com/r/cesarochoa2006/java-rest-calculator)

### Trade offs

- Este proyecto se construyó sobre una base de datos H2 'in memory' por simplicidad del mismo. Puede reemplazarse con una base de datos relacional robusta ya que se construyó sobre un orm e implementa JPA. También se puede implementar sobre una base de datos no relacional si se requiere una mayor velocidad de ejecución ya que al menos en versiones iniciales no hay complejidad alguna en la capa de persistencia, y se utiliza solo para gestionar los tokens generados por los usuarios.
- Los números operables están limitados por la capacidad de los primitivos de java **double** por simplicidad y velocidad de desarrollo del mvp, pero por la misma razón se pueden generar errores de presición y errores de desbordamiento.  Si se desea escalar esta funcionalidad una buena opción es cambiar las operaciones para utilizar BigDecimal.
- Aunque modular, este proyecto es *monolítico*!.  Un monolito es ideal en proyectos pequeños como este, pero a mediana escala pueden surgir problemas abiertamente conocidos. Si se piensa en escalabilidad a mediano nivel, lo mejor sería optimizarlo mediante **microservicios**. 

