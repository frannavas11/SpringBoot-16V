# Proyecto Demo - Spring Boot REST API

Este es un proyecto de demostración desarrollado con **Spring Boot** y **Java 17**, diseñado como una API RESTful. Implementa un modelo de datos relacional para gestionar entidades como Personas, Autores, Libros, Localidades y Domicilios. Además, cuenta con auditoría de entidades utilizando Hibernate Envers.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot** (Web MVC, Data JPA, Data REST)
- **Hibernate Envers** (Para auditoría y versionado de registros)
- **Lombok** (Para reducir el código boilerplate: getters, setters, constructores, etc.)
- **MySQL** (Base de datos relacional)
- **Gradle** (Herramienta de construcción y gestión de dependencias)

## Estructura del Proyecto

El proyecto sigue una arquitectura en capas estándar de Spring Boot, utilizando genéricos (`Base`) para evitar la repetición de código en las operaciones CRUD básicas:

- `entities/`: Modelos de la base de datos (`Persona`, `Autor`, `Libro`, `Localidad`, `Domicilio`, `Base`). Incluye también el subpaquete `audit` para la gestión de auditorías.
- `repositories/`: Interfaces que extienden `JpaRepository` (y `BaseRepository`) para el acceso a datos.
- `services/`: Interfaces y sus implementaciones (`ServiceImpl`) con la lógica de negocio, centralizadas mediante un `BaseService`.
- `controllers/`: Controladores REST que exponen los endpoints HTTP, extendiendo de un `BaseControllerImpl`.
- `config/`: Configuraciones de la aplicación, como `CustomRevisionListener` para Envers.

## Requisitos Previos

- **Java Development Kit (JDK) 17** o superior.
- **MySQL Server** ejecutándose localmente en el puerto `3306`.
- IDE recomendado: IntelliJ IDEA, Eclipse o VS Code.

## Configuración y Ejecución

### 1. Base de Datos
El proyecto está configurado para conectarse a una base de datos MySQL local. Debes asegurarte de tener un servidor MySQL corriendo y crear una base de datos llamada `pruebaspring`.

Por defecto, la configuración en `src/main/resources/application.properties` espera las siguientes credenciales:
- **URL**: `jdbc:mysql://localhost:3306/pruebaspring`
- **Usuario**: `root`
- **Contraseña**: *(vacía)*

Si tienes credenciales diferentes, modifica el archivo `application.properties`.

*Nota: La propiedad `spring.jpa.hibernate.ddl-auto=update` se encarga de crear o actualizar automáticamente las tablas de la base de datos al iniciar la aplicación.*

### 2. Ejecutar la Aplicación

Puedes ejecutar la aplicación desde la terminal utilizando el wrapper de Gradle incluido en la raíz del proyecto:

En Windows:
```bash
gradlew bootRun
```

En Linux/Mac:
```bash
./gradlew bootRun
```

La aplicación se iniciará por defecto en el puerto **9000** (configurado mediante `server.port=9000`).

## Endpoints Principales

Debido a que el proyecto expone controladores que heredan de una implementación base (`BaseControllerImpl`), todas las entidades principales cuentan con operaciones CRUD estándar. Por ejemplo, para la entidad `Persona` (en la ruta `/personas` o la que se haya mapeado en su respectivo controlador), dispones de:

- **GET** `/ruta` : Obtiene todos los registros.
- **GET** `/ruta/{id}` : Obtiene un registro por su ID.
- **POST** `/ruta` : Crea un nuevo registro.
- **PUT** `/ruta/{id}` : Actualiza un registro existente.
- **DELETE** `/ruta/{id}` : Elimina un registro.

*(Aplica a `/autores`, `/localidades`, etc., dependiendo de los `@RequestMapping` configurados en cada controlador).*
