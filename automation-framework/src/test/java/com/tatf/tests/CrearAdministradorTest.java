package com.tatf.tests;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import com.tatf.adminces.modules.acceso.data.AccesoData;
import com.tatf.adminces.modules.acceso.task.AccesoTask;
import com.tatf.adminces.modules.registro.data.RegistroData;
import com.tatf.adminces.modules.registro.task.RegistroTask;
import com.tatf.adminces.modules.login.data.LoginData;
import com.tatf.adminces.modules.login.task.LoginTask;
import com.tatf.adminces.modules.home.task.HomeTask;

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
        AccesoTask acceso = new AccesoTask(browser);
        acceso.accederAlSistema(AccesoData.URL, AccesoData.CLAVE_ACCESO);

        String correo = RegistroData.EMAIL;
        String contrasena = RegistroData.CONTRASENA;

        RegistroTask registro = new RegistroTask(browser);
        registro.registrarAdministrador(RegistroData.NOMBRE,RegistroData.APELLIDO,correo,contrasena,RegistroData.PAIS);
        registro.verificarRegistro(RegistroData.MENSAJE_CONFIRMACION);

        LoginTask login = new LoginTask(browser);
        login.iniciarSesion(correo, contrasena);
        login.verificarInicioSesion(LoginData.MENSAJE_CONFIRMACION);
        HomeTask home = new HomeTask(browser);
        home.verificarPanelAdministrador();

    }
}
