# Notes App (Backend)

API para aplicación de notas con la característica de compartir y editar notas en conjunto con otros colaboradores.

![Estado](https://img.shields.io/badge/estado-en%20construcci%C3%B3n-yellow)

## Tabla de Contenidos

- [Instalación](#instalación)
- [Uso](#uso)
- [Configuración](#configuración)
- [Roadmap](#roadmap)
- [Contacto](#contacto)

## Instalación

### Requisitos Previos
- Java JDK 17 o superior.
- Maven 3.6+ (para construir y ejecutar el proyecto).
- MySQL 8.0+ (base de datos relacional).
- Git (para clonar el repositorio).

### Pasos de Instalación
1. Clona el repositorio desde GitHub:
   ```
   git clone https://github.com/tu-usuario/notes-app-backend.git
   ```
2. Navega al directorio del proyecto:
   ```
   cd notes-app-backend
   ```
3. Construye el proyecto con Maven:
   ```
   mvn clean install
   ```
4. Configura la conexión a la base de datos (ver sección [Configuración](#configuración)).

## Uso

Una vez instalado y configurado, puedes ejecutar la aplicación localmente. La API expone endpoints para crear, compartir y editar notas colaborativamente.

Para más detalles, consulta la documentación de endpoints en `/swagger-ui` (Pendiente).

## Configuración

La configuración principal se realiza en el archivo `src/main/resources/application.properties`. Asegúrate de configurar las siguientes variables:

- Conexión a MySQL:
  ```
  spring.datasource.url=jdbc:mysql://localhost:3306/notes_db?useSSL=false&serverTimezone=UTC
  spring.datasource.username=tu_usuario
  spring.datasource.password=tu_contraseña
  spring.jpa.hibernate.ddl-auto=update
  ```
Crea la base de datos `notes_db` en MySQL antes de ejecutar.


## Roadmap

- Implementar autenticación JWT para usuarios.
- Agregar soporte para edición colaborativa.
- Agregar permisos de usuario (lectura/escritura).
- Agregar TinyMCE para texto enriquecido.

Sugerencias para el roadmap son bienvenidas en los issues.

## Contacto

Para preguntas, bugs o sugerencias, abre un issue en el repositorio de GitHub. Enlaces relacionados:
- [Repositorio en GitHub](https://github.com/DamianL96/notes-back)

##Desarrollador
Damian Emmanuel Lambrecht
