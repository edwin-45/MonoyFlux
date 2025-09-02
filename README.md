# Proyecto de Demostración de Spring WebFlux y Project Reactor<br>
Este es un proyecto simple de Spring Boot diseñado para ilustrar los conceptos básicos de la programación reactiva utilizando Spring WebFlux y Project Reactor (Mono y Flux). El proyecto expone varios endpoints REST que demuestran el uso de diferentes operadores reactivos.


# Tecnologías Utilizadas
Java 17<br>
Spring Boot 3.2.5<br>
Spring WebFlux<br>
Project Reactor<br>
Gradle<br>
# Estructura del Proyecto<br>
El proyecto sigue una arquitectura simple de dos capas:<br>


## ReactorController: Contiene los endpoints REST que exponen la funcionalidad reactiva.<br>
## ReactorService: Contiene la lógica de negocio y la implementación de los flujos reactivos con Mono y Flux.<br>
## Cómo Ejecutar el Proyecto<br>
### Clona el repositorio.<br>


Abre una terminal en el directorio raíz del proyecto.<br>


Ejecuta el siguiente comando para iniciar la aplicación:<br>


./gradlew bootRun<br>
La aplicación se iniciará en http://localhost:8080.<br>


## Endpoints Disponibles<br>
A continuación se describen los endpoints expuestos por la aplicación.<br>


## 1. Filter y Map<br>
Demuestra el uso de los operadores filter y map en un Flux.<br>


URL: GET /reactor/filter-map<br>
Descripción: Devuelve un flujo (Flux) de números. Comienza con un rango de 1 a 10, filtra los números pares, los multiplica por 2 y los emite como un text/event-stream.
Ejemplo con curl:<br>
curl -N http://localhost:8080/reactor/filter-map<br>
## 2. Reduce<br>
Demuestra el uso del operador reduce para agregar los elementos de un Flux en un Mono.<br>


URL: GET /reactor/reduce<br>
Descripción: Devuelve un Mono que contiene la suma de los números del 1 al 10.<br>
Ejemplo con curl:<br>
curl http://localhost:8080/reactor/reduce
## 3. Manejo de Errores (onErrorResume)<br>
Demuestra cómo manejar errores en un flujo reactivo.<br>


URL: GET /reactor/on-error<br>
Descripción: Intenta una operación que falla (división por cero) y, en lugar de lanzar una excepción, devuelve un valor de respaldo (-1) gracias al operador onErrorResume.<br>
Ejemplo con curl:
curl http://localhost:8080/reactor/on-error<br>
## 4. Flujo Complejo<br>
Muestra una cadena de operadores más compleja que incluye manejo de errores, filtrado y transformaciones asíncronas.<br>
URL: GET /reactor/explain<br>
Descripción:
Emite números del 1 al 10.<br>
Lanza una excepción para números pares, que es capturada para emitir un 0.<br>
Filtra los números para mantener solo aquellos mayores que 5.<br>
Multiplica los números restantes por 2 y los emite con un pequeño retardo.
Ejemplo con curl:
curl -N http://localhost:8080/reactor/explain
