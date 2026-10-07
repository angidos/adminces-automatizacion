package com.tatf.adminces.modules.login.task;

import com.tatf.core.browser.IBrowser;
import com.tatf.adminces.modules.login.pom.LoginPO;
import com.tatf.core.verification.IVerify;

public class LoginTask {
    private final LoginPO login;

    public LoginTask(IBrowser browser) {
        this.login = new LoginPO(browser);
    }

    public void iniciarSesion(String email, String contrasena) {

        login.abrirInicioSesion();
        login.ingresarEmail(email);
        login.ingresarContrasena(contrasena);
        login.hacerClicEnIniciarSesion();
    }

    public void verificarInicioSesion(String mensajeEsperado) {

        String mensajeObtenido = login.obtenerMensajeConfirmacion();

        IVerify.create().verify(mensajeEsperado,mensajeObtenido,"No se mostró la confirmación de inicio de sesión.");

        login.aceptarConfirmacion();
    }
}
