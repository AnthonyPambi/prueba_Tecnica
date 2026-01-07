Prueba Técnica - Sistema de Autenticación Spring Boot
Este proyecto consiste en el desarrollo de un backend robusto utilizando Spring Boot 3 y PostgreSQL, enfocado en la gestión de usuarios y procesos de autenticación segura.

🚀 Logros del Proyecto (Lo que se hizo)
1. Arquitectura y Estructura
Diseño Modular: Se implementó una arquitectura limpia dividida en capas: controller, service, repository, entity, model y configuration.

Inyección de Dependencias: Uso de anotaciones de Spring y Lombok (@RequiredArgsConstructor) para un manejo eficiente de componentes.

2. Persistencia de Datos
Base de Datos: Configuración exitosa con PostgreSQL.

Mapeo ORM: Implementación de entidades JPA (Persona, Usuario, Rol, etc.) con sus respectivas relaciones.

Conectividad: El sistema inicializa correctamente el pool de conexiones mediante HikariCP y valida el esquema de la base de datos al arrancar.

3. Seguridad (Spring Security)
Filtros Personalizados: Configuración de una cadena de filtros de seguridad (SecurityFilterChain) que deshabilita CSRF para pruebas de API.

Stateless: Implementación de política de sesión sin estado (STATELESS), ideal para integración con Postman y APIs REST.

Auth Manager: Configuración de un AuthenticationManager personalizado vinculado a un UserDetailsService que busca credenciales directamente en PostgreSQL.

4. Endpoints y Controladores
Auth Controller: Creación de rutas específicas para el proceso de login (/auth/login).

🛠️ Requisitos para Ejecutar
Java 17 o superior.

PostgreSQL (Base de datos: viamatica).

Maven (incluido mediante ./mvnw).

Pasos para levantar el servidor:
Bash

.\mvnw clean compile spring-boot:run
El servidor estará listo cuando aparezca: Started PruebaTecnicaApplication in ... seconds.

⏳ Pendientes y Próximos Pasos (Lo que falta)
Debido a las restricciones de tiempo de la prueba técnica, quedaron los siguientes puntos como mejoras planificadas:

Encriptación de Contraseñas: Actualmente se utiliza NoOpPasswordEncoder para validar la conexión lógica entre el servicio y la base de datos. La implementación de BCryptPasswordEncoder está lista en código, pendiente de la migración de datos existentes en PostgreSQL.

Manejo de Errores Avanzado: Implementación de un @ControllerAdvice para personalizar las respuestas de error (como el 401 Unauthorized o 403 Forbidden).

Generación de Tokens (JWT): Evolucionar la autenticación básica hacia un sistema de tokens JWT para mayor seguridad en el intercambio de información entre cliente y servidor.

Pruebas Unitarias: Cobertura de tests para la lógica de negocio en la capa de servicios.

Nota Final: El proyecto es totalmente funcional en su núcleo, permitiendo el arranque del servidor, la conexión con la base de datos y la validación de la cadena de filtros de seguridad.