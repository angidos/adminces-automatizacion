package com.tatf.tests;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.adminces.modules.acceso.data.AccesoData;
import com.tatf.adminces.modules.acceso.task.AccesoTask;
import com.tatf.adminces.modules.login.data.LoginData;
import com.tatf.adminces.modules.login.task.LoginTask;
import com.tatf.adminces.modules.usuarios.data.EliminarTesterData;
import com.tatf.adminces.modules.usuarios.task.CrearTesterTask;
import com.tatf.adminces.modules.usuarios.task.EliminarTesterTask;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

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
        AccesoTask acceso = new AccesoTask(browser);
        acceso.accederAlSistema(AccesoData.URL, AccesoData.CLAVE_ACCESO);

        LoginTask login = new LoginTask(browser);
        login.iniciarSesion(EliminarTesterData.EMAIL_ADMINISTRADOR, EliminarTesterData.CONTRASENA_ADMINISTRADOR);
        login.verificarInicioSesion(LoginData.MENSAJE_CONFIRMACION);

        CrearTesterTask creacion = new CrearTesterTask(browser);
        creacion.crearTester(EliminarTesterData.NOMBRE, EliminarTesterData.APELLIDO, EliminarTesterData.EMAIL, EliminarTesterData.PAIS,
                EliminarTesterData.CONTRASENA, EliminarTesterData.TIPO );
        creacion.verificarCreacion(EliminarTesterData.MENSAJE_CREACION);

        EliminarTesterTask eliminacion = new EliminarTesterTask(browser);
        eliminacion.solicitarEliminacion(EliminarTesterData.EMAIL);
        eliminacion.verificarConfirmacionYEliminar(EliminarTesterData.EMAIL);
        eliminacion.verificarEliminacion(EliminarTesterData.MENSAJE_ELIMINACION);
        eliminacion.verificarAusenciaTester(EliminarTesterData.EMAIL);
    }
}