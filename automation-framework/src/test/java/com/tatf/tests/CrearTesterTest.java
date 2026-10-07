package com.tatf.tests;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.adminces.modules.acceso.data.AccesoData;
import com.tatf.adminces.modules.acceso.task.AccesoTask;
import com.tatf.adminces.modules.login.data.LoginData;
import com.tatf.adminces.modules.login.task.LoginTask;
import com.tatf.adminces.modules.usuarios.data.CrearTesterData;
import com.tatf.adminces.modules.usuarios.task.CrearTesterTask;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

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
        AccesoTask acceso = new AccesoTask(browser);
        acceso.accederAlSistema(AccesoData.URL,AccesoData.CLAVE_ACCESO);

        LoginTask login = new LoginTask(browser);
        login.iniciarSesion(CrearTesterData.EMAIL_ADMINISTRADOR,CrearTesterData.CONTRASENA_ADMINISTRADOR);
        login.verificarInicioSesion(LoginData.MENSAJE_CONFIRMACION);

        CrearTesterTask tester = new CrearTesterTask(browser);
        tester.crearTester( CrearTesterData.NOMBRE, CrearTesterData.APELLIDO, CrearTesterData.EMAIL, CrearTesterData.PAIS, CrearTesterData.CONTRASENA, CrearTesterData.TIPO);
        tester.verificarCreacion(CrearTesterData.MENSAJE_CONFIRMACION);
        tester.verificarDatosTester(CrearTesterData.NOMBRE, CrearTesterData.APELLIDO, CrearTesterData.EMAIL, CrearTesterData.PAIS, CrearTesterData.TIPO);
    }
}