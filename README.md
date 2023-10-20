# Prueba técnica SpringBoot

## _Modelos/Entidades_

- Superhero: id(long), name(string), description(string), gender(string), skill(string), status(string)
- User: user(string), pwd(string), token(string)

## _Documentación API (Endpoints/Ejemplos)_

- Autenticación con usuario, POST, hay que enviar user y password: http://localhost:8080/user
Esto devuelve un token que luego se tiene utilizar para realizar las demás peticiones a la API. Se tiene que añadir en el header de cada petición con el nombre Authorization y el token como valor.
- Consultar toda la lista de superhéroes, GET: http://localhost:8080/superheroes
- Consultar superhéroes por ID, GET:  http://localhost:8080/superheroes/1
- Consultar superhéroes por nombre, GET: http://localhost:8080/superheroes/getByName?name=man
- Editar superhéroes por ID, hay que enviar el objeto SuperHero, PUT: http://localhost:8080/superheroes/1
- Borrar superhéroes por ID, DELETE: http://localhost:8080/superheroes/1

## _Base de datos_

H2 Database: Se encuentra en la carpeta resources, he creado el script para que se inserten dos superhéroes al iniciar el servidor y poder trabajar y probar las peticiones.

## _Puntos opcionales_
- Se han utilizado los principios SOLID y TDD.
- Cache
- Manejo de errores centralizado en GlobalExceptionHandler
- Anotación para obtener el tiempo que tarda cada método en ejecutarse
- JWT y Spring Security para seguridad de la API
- Tests unitarios y de integración
- Para hacer el build y ejecutar la versión dockerizada he utilizado los comandos:
  sudo docker build -t spring-boot-docker . y sudo docker run -p 8080:8080 spring-boot-docker:latest
  La he subido a Docker Hub, se puede consultar en el siguiente enlace:
  https://hub.docker.com/repository/docker/sergidiazbocanegra/spring-boot-docker/general

## _Dependencias de Maven_
- spring-boot-starter-data-jpa
- spring-boot-starter-cache
- spring-boot-starter-web
- spring-boot-starter-webflux
- com.h2database
- org.projectlombok
- spring-boot-starter-test
- spring-boot-starter-security
- spring-boot-starter-aop
- spring-boot-starter-tomcat

