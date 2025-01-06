# Proyecto de Autenticación JWT con Spring Boot

Este repositorio contiene un sistema de autenticación seguro utilizando **Spring Boot** y **JSON Web Tokens (JWT)**. Aquí encontrarás los pasos necesarios para configurar y ejecutar el proyecto, así como una descripción de sus principales funcionalidades.

---

## Tabla de Contenidos

1. [Características Principales](#características-principales)
2. [Instalación y Configuración](#instalación-y-configuración)
3. [Uso](#uso)
4. [Endpoints](#endpoints)

---

## Características Principales

- Autenticación basada en JWT (JSON Web Tokens).
- Protección de rutas y validación de roles.
- Envío de correos electrónicos para confirmación de registro y restablecimiento de contraseña.
- Gestión de errores personalizada.

---

## Instalación y Configuración

### Requisitos previos

- Java 17 o superior.
- Maven.
- PostgreSQL configurado.

### Pasos para configurar el proyecto

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/CristianVazquez09/SpringSecureAuth.git
   cd <NOMBRE_DEL_PROYECTO>
   ```

2. **Configurar la base de datos**:
   - Crear una base de datos en PostgreSQL.
   - Actualizar las credenciales en el archivo `application.properties`:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/nombre_base_datos
     spring.datasource.username=tu_usuario
     spring.datasource.password=tu_contraseña
     ```

3. **Configurar el envío de correos electrónicos**:
   - Añadir las siguientes propiedades al archivo `application.properties`:
     ```properties
     spring.mail.default-encoding=UTF-8
     spring.mail.host=smtp.yourmailprovider.com
     spring.mail.username=your_email_address
     spring.mail.password=your_email_password
     spring.mail.port=587
     spring.mail.properties.mail.ssl.enable=true
     spring.mail.properties.mail.smtp.auth=true
     spring.mail.properties.mail.smtp.starttls.enable=true
     spring.mail.properties.mail.smtp.starttls.required=true
     spring.mail.protocol=smtp
     spring.mail.test-connection=false
     ```
     **Nota:**
     - La configuración de `spring.mail.password` hace referencia a una **contraseña de aplicación** generada específicamente para este propósito, **no uses la contraseña de tu cuenta personal de correo electrónico**.
     - Si utilizas un correo empresarial, consulta con tu proveedor para obtener las credenciales correctas de `SMTP`.
     - Si tienes dudas sobre cómo generar una contraseña de aplicación, puedes consultar este [video explicativo](https://www.youtube.com/watch?v=h4eVrDSf8Eg).

4. **Configurar la clave secreta para JWT**:
   ```properties
   jwt.secret=your_secret_key
   ```
   **Nota:**
     - Ten en cuenta que la key tiene que ser una cadena de 256 bits, 512 bits u otras según el nivel de seguridad deseado. 

5. **Ejecutar el proyecto**:
   ```bash
   mvn spring-boot:run
   ```

---

## Uso

### Login
El endpoint de login devuelve un token JWT que debe incluirse en cada solicitud protegida en el encabezado de autorización:
```http
Authorization: Bearer <token>
```

### Funcionalidades adicionales
- Registro de usuarios.
- Restablecimiento de contraseñas mediante correo electrónico.

---

## Endpoints

### Autenticación
- **POST /login**: Genera un token JWT para un usuario autenticado.

### Registro de Usuarios
- **POST /signup/sendMail/signup**: Envía un correo para confirmar el registro del usuario.
- **GET /signup/check/{random}**: Valida el token único y permite establecer una contraseña.

### Restablecimiento de Contraseña
- **POST /mail/sendMail**: Envía un correo al usuario para restablecer su contraseña.
- **GET /mail/reset/check/{random}**: Verifica si el token único todavía es válido.
- **GET /mail/reset/{random}**: Permite al usuario establecer una nueva contraseña.

---
