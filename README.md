# Prueba técnica 4 HackaBoss Softtek

Prueba técnica final del Bootcamp. Desarrollo API con SpringBoot.

## Características

La API está desarrollada para reservar vuelos y habitaciones a traves de una agencia de turismo.
Se realizan operaciones de Crud para los vuelos, hoteles y reservas. Se incluye seguridad con Spring Security y documentación con Swagger.
Aplicado un test unitario con Spring Security.

## Requisitos

Se necesitará tener instalado java 17 y Apache Maven. Se incluye una base de datos SQL llamada "agency" con el archivo de la base de datos alojada en /resources.
En la carpeta config se encuentra el usuario y contraseña para acceder a las rutas protegidas y la base de datos.Tambien se encuentra el config del modelMapper.

## Instalación

El proyecto esta desarrollado en IntelliJ. Se debe descargar el proyecto y abrirlo en el IDE. Después, abrir el xampp u otro gestor de base de datos y añadir la base de datos (archivo sql) que se encuentra en /resources.
Se incluye lista para Postman con las peticiones. El archivo se encuentra en /resources.


## Supuestos

Creación de una tabla de clientes en la base de datos.
JSON modificados para una ajuste de los datos.
