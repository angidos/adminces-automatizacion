package com.tatf.adminces.modules.login.pom;

import com.tatf.core.browser.IBrowser;

public class LoginPO {

    private final IBrowser browser;
    private final String enlaceIniciarSesion = "Iniciar sesión";
    private final String campoEmail = "input[placeholder='Email']";
    private final String campoContrasena = "input[placeholder='Contraseña']";
    private final String botonIniciarSesion = "//button[contains(text(),'Iniciar Sesión')]";
    private final String mensajeConfirmacion = "//*[normalize-space()='Sesión iniciada.']";
    private final String botonOK = "//button[normalize-space()='OK']";

    public LoginPO(IBrowser browser) {
        this.browser = browser;
    }

    public void abrirInicioSesion() {
        browser.find().link(enlaceIniciarSesion).click();
    }

    public void ingresarEmail(String email) {
        browser.find().css(campoEmail).write(email);
    }

    public void ingresarContrasena(String contrasena) {
        browser.find().css(campoContrasena).write(contrasena);
    }

    public void hacerClicEnIniciarSesion() {
        browser.find().xpath(botonIniciarSesion).click();

    }

    public String obtenerMensajeConfirmacion() {
        browser.wait(mensajeConfirmacion).xpath();
        return browser.find().xpath(mensajeConfirmacion).getText();
    }

    public void aceptarConfirmacion() {
        browser.find().xpath(botonOK).click();
    }

}
