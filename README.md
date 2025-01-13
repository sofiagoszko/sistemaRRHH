# Sistema de gestión de Recursos Humanos

Aplicación para la gestión de empleados junto con sus operaciones CRUD (create, read, update y delete).

## Tecnologías Utilizadas
Este proyecto utiliza las siguientes tecnologías:

- **Frontend:** React.js
- **Backend:** Java con Spring Boot
- **Base de Datos:** MySQL

## Para correrlo en tu máquina
En caso de querer ejecutar este código en tu máquina, despues de clonarlo incorporá en rrhh-spring-boot, src, main, la carpeta de resources con su archivo applitacion.properties y copiá lo siguiente (adaptalo a tu puerto y tus credenciales):

```
spring.application.name=rrhh

server.port=8080
spring.datasource.url=jdbc:mysql://localhost:tu_puerto/nombre_de_tu_base_de_datos
spring.datasource.username=tu usuario
spring.datasource.password=tu contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

