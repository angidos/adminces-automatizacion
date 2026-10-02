# Estructura del proyecto

## Interfaces

| Entidad  | Descripción                                                                                                       |
|----------|-------------------------------------------------------------------------------------------------------------------|
| IBrowser | Interfaz que define operaciones comunes para la gestión del navegador y la espera de elementos en una página web. |
| IVerify  | Interfaz que define métodos para verificar resultados esperados con obtenidos y agregar mensajes asociados.       |

## Patrones aplicados

| Patrón     | Entidad                 | Descripción                                                               |
|------------|-------------------------|---------------------------------------------------------------------------|
| Factory    | DriverManagerFactory    | Clase encargada de indicar que driver se debe retornar.                   |
| Singlenton | DriverManagerSinglenton | Clase encargada de mantener una sola instancia configurada con un driver. |

## Pruebas de AdminCES

Aplicación: http://cestore.ces.com.uy/adminces/

Las pruebas están en `src/test/java/com/tatf/tests` y utilizan `IBrowser` para interactuar con la aplicación e `IVerify` para verificar los resultados.

| Orden | Clase | Prueba |
|-------|-------|--------|
| 1 | CrearAdministradorTest | Crear cuenta Administrador y comprobar el inicio de sesión. |
| 2 | ReiniciarContrasenaTest | Reiniciar contraseña y comprobar el inicio de sesión con la nueva contraseña. |
| 3 | CrearTesterTest | Crear cuenta Tester y verificar sus datos en la tabla de usuarios. |
| 4 | EliminarTesterTest | Crear un Tester, eliminarlo y verificar que ya no aparezca en la tabla. |

El orden se define con `@Order` y el archivo `src/test/resources/junit-platform.properties`.

### Ejecución

En IntelliJ, seleccionar las cuatro clases y ejecutarlas con **Run**. Las pruebas utilizan JUnit y abren Chrome mediante el framework.

### Guiones

Los pasos y las verificaciones de cada prueba están en [Guiones.md](Guiones.md).
