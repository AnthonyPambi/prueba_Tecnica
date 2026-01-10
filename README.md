# Prueba Técnica: Sistema de Gestión de Usuarios

Este proyecto consiste en una aplicación Fullstack que permite la autenticación y gestión de usuarios, cumpliendo con requisitos de seguridad y persistencia de datos.

## 🛠️ Tecnologías Utilizadas
* **Backend:** Java 17, Spring Boot, Spring Security, JPA/Hibernate.
* **Frontend:** React.js, Axios para consumo de API.
* **Base de Datos:** PostgreSQL.

## 🏗️ Arquitectura
El proyecto sigue una **Arquitectura Hexagonal (Puertos y Adaptadores)**, separando claramente la lógica de negocio de la infraestructura y los controladores.

## 🚀 Cómo ejecutar el proyecto
1. **Base de Datos:** Asegurarse de tener PostgreSQL corriendo y crear una base de datos llamada `prueba_tecnica`.
2. **Backend:** - Configurar credenciales en `application.properties`.
   - Ejecutar con `./mvnw spring-boot:run`.
3. **Frontend:**
   - Ejecutar `npm install` y luego `npm start`.

## 📝 Notas del Desarrollador
- El flujo de autenticación está mapeado desde el Frontend hasta el Controlador.
- Se implementó la lógica de bloqueo por intentos fallidos y la integración con PostgreSQL para la persistencia de sesiones.