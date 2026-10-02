package com.tatf.tests;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;

@Order(3)
public class CrearTesterTest {

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
    void crearCuentaTester() {

        String correoAdministrador = "leonardoperez@gmail.com";
        String contrasenaAdministrador = "12345";
        String nombreTester = "Laura";
        String apellidoTester = "Prueba";
        String correoTester = "tester123@test.com";
        String paisTester = "Uruguay";
        String contrasenaTester = "Tester12345";
        String tipoTester = "Tester Junior";

        browser.interaction().navigateTo("http://cestore.ces.com.uy/adminces/");
        browser.find().id("pass").write("3)ea60e0be3ba12c6ecd%7297868%5c4");
        browser.find().xpath("//button[contains(text(),'Ingresar')]").click();
        browser.find().link("Iniciar sesión").click();
        browser.find().css("input[placeholder='Email']").write(correoAdministrador);
        browser.find().css("input[placeholder='Contraseña']").write(contrasenaAdministrador);
        browser.find().xpath("//button[contains(text(),'Iniciar Sesión')]").click();
        browser.wait("//*[normalize-space()='Sesión iniciada.']").xpath();

        String mensajeSesion = browser.find().xpath("//*[normalize-space()='Sesión iniciada.']").getText();
        IVerify.create().verify("Sesión iniciada.",mensajeSesion,"No se pudo iniciar sesión con el Administrador.");

        browser.find().xpath("//button[normalize-space()='OK']").click();
        browser.find().link("Crear usuario").click();
        browser.find().css("input[placeholder='Nombre']").write(nombreTester);
        browser.find().css("input[placeholder='Apellido']").write(apellidoTester);
        browser.find().css("input[placeholder='Email']").write(correoTester);
        browser.find().css("select").selectText(paisTester);
        browser.find().css("input[placeholder='Contraseña por defecto']").write(contrasenaTester);
        browser.find().xpath("//*[normalize-space()='" + tipoTester + "']").click();
        browser.find().xpath("//button[contains(text(),'Crear cuenta')]").click();
        browser.wait("//*[normalize-space()='Usuario creado.']").xpath();

        String mensajeCreacion = browser.find().xpath("//*[normalize-space()='Usuario creado.']").getText();
        IVerify.create().verify("Usuario creado.",mensajeCreacion,"No se mostró la confirmación de creación del Tester.");

        browser.find().xpath("//button[normalize-space()='OK']").click();
        browser.find().link("Ver usuarios").click();

        String filaTesterXpath ="//tr[td[normalize-space()='" + correoTester + "']]";

        browser.wait(filaTesterXpath).xpath();

        String datosTester = browser.find().xpath(filaTesterXpath).getText();
        IVerify.create().verifyTrue(datosTester.contains(nombreTester),"El nombre del Tester no coincide.");
        IVerify.create().verifyTrue(datosTester.contains(apellidoTester),"El apellido del Tester no coincide.");
        IVerify.create().verifyTrue(datosTester.contains(correoTester),"El correo del Tester no coincide.");
        IVerify.create().verifyTrue(datosTester.contains(paisTester),"El país del Tester no coincide.");
        IVerify.create().verifyTrue(datosTester.contains(tipoTester),"El tipo de Tester no coincide.");

        browser.wait(5).sleep();

    }
}