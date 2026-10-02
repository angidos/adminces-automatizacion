package com.tatf.tests;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;

@Order(2)
public class ReiniciarContrasenaTest {

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
    void reiniciarContrasena() {

        String correo = "yaniscorrea@gmail.com";
        String contrasenaActual = "12345";

        browser.interaction().navigateTo("http://cestore.ces.com.uy/adminces/");
        browser.find().id("pass").write("3)ea60e0be3ba12c6ecd%7297868%5c4");
        browser.find().xpath("//button[contains(text(),'Ingresar')]").click();
        browser.find().link("Iniciar sesión").click();
        browser.find().css("input[placeholder='Email']").write(correo);
        browser.find().css("input[placeholder='Contraseña']").write(contrasenaActual);
        browser.find().xpath("//button[contains(text(),'Iniciar Sesión')]").click();
        browser.wait("//*[normalize-space()='Sesión iniciada.']").xpath();

        String mensajeSesion = browser.find().xpath("//*[normalize-space()='Sesión iniciada.']").getText();
        IVerify.create().verify("Sesión iniciada.",mensajeSesion,"No se pudo iniciar sesión con el Administrador." );

        browser.find().xpath("//button[normalize-space()='OK']").click();
        browser.find().link("Reiniciar contraseña").click();

        String correoUsuario = "yaniscorrea@gmail.com";
        String nuevaContrasena = "1234";

        browser.find().css("input[placeholder='Email']").write(correoUsuario);
        browser.find().css("input[placeholder='Contraseña']").write(nuevaContrasena);
        browser.find().css("input[placeholder='Repetir contraseña']").write(nuevaContrasena);
        browser.find().xpath("//button[contains(text(),'Rei. Contraseña')]").click();
        browser.wait("//*[normalize-space()='Contraseña reiniciada.']").xpath();

        String mensajeReinicio = browser.find().xpath("//*[normalize-space()='Contraseña reiniciada.']").getText();
        IVerify.create().verify("Contraseña reiniciada.",mensajeReinicio,"No se mostró la confirmación de reinicio de contraseña.");

        browser.find().xpath("//button[normalize-space()='OK']").click();
        browser.find().xpath("//*[self::button or self::a][contains(normalize-space(),'Yanis Correa')]").click();
        browser.find().link("Cerrar sesión").click();
        browser.wait("//*[normalize-space()='Te quieres ir?']").xpath();

        String mensajeCierre = browser.find().xpath("//*[normalize-space()='Te quieres ir?']").getText();
        IVerify.create().verify("Te quieres ir?",mensajeCierre,"No se mostró la confirmación para cerrar sesión.");

        browser.find().xpath("//button[normalize-space()='Sí']").click();
        browser.wait("//*[normalize-space()='Sesión cerrada.']").xpath();

        String mensajeSesionCerrada = browser.find().xpath("//*[normalize-space()='Sesión cerrada.']").getText();
        IVerify.create().verify("Sesión cerrada.",mensajeSesionCerrada,"No se confirmó el cierre de sesión.");

        browser.find().xpath("//button[normalize-space()='OK']").click();
        browser.find().link("Iniciar sesión").click();
        browser.find().css("input[placeholder='Email']").write(correoUsuario);
        browser.find().css("input[placeholder='Contraseña']").write(nuevaContrasena);
        browser.find().xpath("//button[contains(text(),'Iniciar Sesión')]").click();
        browser.wait("//*[normalize-space()='Sesión iniciada.']").xpath();

        String mensajeNuevoInicio = browser.find().xpath("//*[normalize-space()='Sesión iniciada.']").getText();
        IVerify.create().verify("Sesión iniciada.",mensajeNuevoInicio,"No fue posible iniciar sesión con la contraseña reiniciada.");

        browser.find().xpath("//button[normalize-space()='OK']").click();
        browser.wait("Crear usuario").link();

        IVerify.create().verifyTrue(browser.find().link("Crear usuario").isDisplayed(),"No se accedió al panel con la contraseña reiniciada.");

        browser.wait(5).sleep();

    }
}