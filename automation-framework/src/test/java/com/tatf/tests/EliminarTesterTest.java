package com.tatf.tests;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;

@Order(4)
public class EliminarTesterTest {

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
    void eliminarCuentaTester() {

        String correoAdministrador = "leonardoperez@gmail.com";
        String contrasenaAdministrador = "12345";
        String nombreTester = "Usuario";
        String apellidoTester = "Eliminar";
        String correoTester = "eliminar@test.com";
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
        IVerify.create().verify("Usuario creado.",mensajeCreacion,"No se creó el Tester necesario para la eliminación.");

        browser.find().xpath("//button[normalize-space()='OK']").click();


        browser.find().link("Ver usuarios").click();

        String filaTesterXpath ="//tr[td[normalize-space()='" + correoTester + "']]";

        browser.wait(filaTesterXpath).xpath();

        IVerify.create().verifyTrue(browser.find().xpath(filaTesterXpath).isDisplayed(),"No se encontró el Tester que se desea eliminar.");


        browser.find().xpath(filaTesterXpath + "//button").click();
        browser.wait("//button[normalize-space()='Sí']").xpath();

        String confirmacionEliminacion = browser.find().xpath("//*[contains(@class,'swal')" + " and .//button[normalize-space()='Sí']]").getText();

        IVerify.create().verifyTrue(confirmacionEliminacion.contains(correoTester),"La confirmación no muestra el correo del Tester seleccionado.");


        browser.find().xpath("//button[normalize-space()='Sí']").click();
        browser.wait("//*[normalize-space()='Usuario eliminado.']").xpath();

        String mensajeEliminacion = browser.find().xpath("//*[normalize-space()='Usuario eliminado.']").getText();

        IVerify.create().verify("Usuario eliminado.",mensajeEliminacion,"No se mostró la confirmación de eliminación.");

        browser.find().xpath("//button[normalize-space()='OK']").click();

        browser.wait(1).sleep();

        IVerify.create().verifyTrue(browser.find().xpathList(filaTesterXpath).isEmpty(),"El Tester eliminado todavía aparece en la tabla.");

        browser.wait(5).sleep();
    }
}