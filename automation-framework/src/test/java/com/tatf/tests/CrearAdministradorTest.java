package com.tatf.tests;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.tatf.core.verification.IVerify;
import org.junit.jupiter.api.Order;

@Order(1)
public class CrearAdministradorTest {

    private static IBrowser browser;

    @BeforeAll
    static void beforeAll() {
        browser = BrowserFactory.getBrowser(true);
    }

    @AfterAll
    static void afterAll() {
        BrowserFactory.quitBrowser();
    }

    @Test
    void crearCuentaAdministrador() {
        browser.interaction().navigateTo("http://cestore.ces.com.uy/adminces/");
        browser.find().id("pass").write("3)ea60e0be3ba12c6ecd%7297868%5c4");
        browser.find().xpath("//button[contains(text(),'Ingresar')]").click();
        browser.find().link("Registrarse").click();

        String correo = "Nancy123@test.com";
        String contrasena = "Admin12345";

        browser.find().css("input[placeholder='Nombre']").write("Nancy");
        browser.find().css("input[placeholder='Apellido']").write("Cantos");
        browser.find().css("input[placeholder='Email']").write(correo);
        browser.find().css("input[placeholder='Contraseña']").write(contrasena);
        browser.find().css("input[placeholder='Repetir contraseña']").write(contrasena);
        browser.find().css("input[placeholder='Pais nacimiento']").write("Uruguay");
        browser.find().xpath("//button[contains(text(),'Registrarse')]").click();
        browser.wait("//*[normalize-space()='Usuario creado.']").xpath();

        String mensajeObtenido = browser.find().xpath("//*[normalize-space()='Usuario creado.']").getText();
        IVerify.create().verify("Usuario creado.",mensajeObtenido,"No se mostró el mensaje de confirmación esperado.");

        browser.find().xpath("//button[normalize-space()='OK']").click();
        browser.find().link("Iniciar sesión").click();
        browser.find().css("input[placeholder='Email']").write(correo);
        browser.find().css("input[placeholder='Contraseña']").write(contrasena);
        browser.find().xpath("//button[contains(text(),'Iniciar Sesión')]").click();
        browser.wait("//*[normalize-space()='Sesión iniciada.']").xpath();

        String mensajeSesion = browser.find().xpath("//*[normalize-space()='Sesión iniciada.']").getText();

        IVerify.create().verify("Sesión iniciada.",mensajeSesion,"No se mostró la confirmación de inicio de sesión.");

        browser.find().xpath("//button[normalize-space()='OK']").click();
        browser.wait("Crear usuario").link();

        boolean pantallaPrincipalVisible = browser.find().link("Crear usuario").isDisplayed();

        IVerify.create().verifyTrue(pantallaPrincipalVisible,"No se accedió al panel principal del Administrador.");

        browser.wait(5).sleep();
    }
}
