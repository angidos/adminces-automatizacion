package com.tatf.tests;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import com.tatf.adminces.modules.acceso.data.AccesoData;
import com.tatf.adminces.modules.acceso.task.AccesoTask;
import com.tatf.adminces.modules.login.data.LoginData;
import com.tatf.adminces.modules.login.task.LoginTask;
import com.tatf.adminces.modules.home.task.HomeTask;
import com.tatf.adminces.modules.reinicio.data.ReinicioData;
import com.tatf.adminces.modules.reinicio.task.ReinicioTask;
import com.tatf.adminces.modules.cierre.data.CierreSesionData;
import com.tatf.adminces.modules.cierre.task.CierreSesionTask;

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
        String correo = ReinicioData.EMAIL;
        String contrasenaActual = ReinicioData.CONTRASENA_ACTUAL;
        String correoUsuario = ReinicioData.EMAIL;
        String nuevaContrasena = ReinicioData.NUEVA_CONTRASENA;

        AccesoTask acceso = new AccesoTask(browser);
        acceso.accederAlSistema(AccesoData.URL, AccesoData.CLAVE_ACCESO);

        LoginTask login = new LoginTask(browser);
        login.iniciarSesion(correo, contrasenaActual);
        login.verificarInicioSesion(LoginData.MENSAJE_CONFIRMACION);

        ReinicioTask reinicio = new ReinicioTask(browser);
        reinicio.reiniciarContrasena(correoUsuario, nuevaContrasena);
        reinicio.verificarReinicio(ReinicioData.MENSAJE_CONFIRMACION);

        CierreSesionTask cierre = new CierreSesionTask(browser);
        cierre.solicitarCierreSesion(ReinicioData.NOMBRE_COMPLETO);
        cierre.verificarPreguntaYConfirmar(CierreSesionData.PREGUNTA_CONFIRMACION);
        cierre.verificarSesionCerrada(CierreSesionData.MENSAJE_SESION_CERRADA);

        login.iniciarSesion(correoUsuario, nuevaContrasena);
        login.verificarInicioSesion(LoginData.MENSAJE_CONFIRMACION);

        HomeTask home = new HomeTask(browser);
        home.verificarPanelAdministrador();

    }
}