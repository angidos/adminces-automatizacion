# AdminCES — Aplicando POM

Adaptación de las cuatro pruebas de AdminCES al patrón Page Object Model, con Java, Maven, Selenium y JUnit.

Aplicación: http://cestore.ces.com.uy/adminces/

## Estructura

Las clases de AdminCES están en `src/test/java/com/tatf/adminces/modules`, organizadas por módulo:

- `pom`: localizadores, acciones y esperas de cada pantalla.
- `task`: secuencias de acciones y verificaciones.
- `data`: datos de prueba y mensajes esperados.

Los tests están en `src/test/java/com/tatf/tests` y utilizan las tareas y los datos. Las verificaciones se realizan con `IVerify`.

## Pruebas

| Orden |  Clase                  | Caso                                                                                  |
|-------|-------------------------|---------------------------------------------------------------------------------------|
| 1     | CrearAdministradorTest  | Crear un administrador y comprobar el inicio de sesión y el acceso al panel.          |
| 2     | ReiniciarContrasenaTest | Reiniciar la contraseña, cerrar sesión y comprobar el acceso con la nueva contraseña. |
| 3     | CrearTesterTest         | Crear un tester y verificar sus datos en la tabla de usuarios.                        |
| 4     | EliminarTesterTest      | Crear un tester, eliminarlo y comprobar que no aparezca en la tabla.                  |

El orden se configura con `@Order` y `src/test/resources/junit-platform.properties`.

## Ejecución

1. Abrir `automation-framework` en IntelliJ como proyecto Maven.
2. Revisar los datos antes de ejecutar:
   - Usar correos no registrados en `RegistroData.EMAIL`, `CrearTesterData.EMAIL` y `EliminarTesterData.EMAIL`.
   - En `ReinicioData`, indicar la contraseña actual de la cuenta y una nueva contraseña distinta. Después de una ejecución exitosa, la cuenta queda con la nueva contraseña.
3. Seleccionar las cuatro clases de AdminCES y ejecutarlas con **Run**. Las pruebas abren Chrome.

`TestForm`, `ConsultarInmobiliariaTest` y las clases del paquete `soluciones` son ejemplos anteriores y no forman parte de estas cuatro pruebas.

Los guiones están en [Guiones.md](Guiones.md).
