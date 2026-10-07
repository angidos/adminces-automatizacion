package com.tatf.adminces.modules.reinicio.pom;

import com.tatf.core.browser.IBrowser;

public class ReinicioPO {
    private final IBrowser browser;
    private final String enlaceReiniciar = "Reiniciar contraseña";
    private final String campoEmail = "input[placeholder='Email']";
    private final String campoContrasena = "input[placeholder='Contraseña']";
    private final String campoRepetirContrasena = "input[placeholder='Repetir contraseña']";
    private final String botonReiniciar = "//button[contains(text(),'Rei. Contraseña')]";
    private final String mensajeConfirmacion = "//*[normalize-space()='Contraseña reiniciada.']";
    private final String botonOK = "//button[normalize-space()='OK']";

    public ReinicioPO(IBrowser browser) {
        this.browser = browser;
    }

    public void abrirReinicio() {
        browser.find().link(enlaceReiniciar).click();
    }

    public void ingresarEmail(String email) {
        browser.find().css(campoEmail).write(email);
    }

    public void ingresarContrasena(String contrasena) {
        browser.find().css(campoContrasena).write(contrasena);
    }

    public void repetirContrasena(String contrasena) {
        browser.find().css(campoRepetirContrasena).write(contrasena);
    }

    public void hacerClicEnReiniciar() {
        browser.find().xpath(botonReiniciar).click();
    }

    public String obtenerMensajeConfirmacion() {
        browser.wait(mensajeConfirmacion).xpath();
        return browser.find().xpath(mensajeConfirmacion).getText();
    }

    public void aceptarConfirmacion() {
        browser.find().xpath(botonOK).click();
    }

}