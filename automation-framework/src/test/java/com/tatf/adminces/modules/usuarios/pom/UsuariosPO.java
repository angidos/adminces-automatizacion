package com.tatf.adminces.modules.usuarios.pom;

import com.tatf.core.browser.IBrowser;

public class UsuariosPO {
    private final IBrowser browser;

    private final String enlaceVerUsuarios = "Ver usuarios";
    private final String botonSi = "//button[normalize-space()='Sí']";
    private final String confirmacionEliminacion ="//*[contains(@class,'swal') and .//button[normalize-space()='Sí']]";
    private final String mensajeEliminacion ="//*[normalize-space()='Usuario eliminado.']";
    private final String botonOK = "//button[normalize-space()='OK']";

    public UsuariosPO(IBrowser browser) {
        this.browser = browser;
    }

    private String obtenerFilaXpath(String email) {
        return "//tr[td[normalize-space()='" + email + "']]";
    }

    public void abrirUsuarios() {
        browser.wait(enlaceVerUsuarios).link();
        browser.find().link(enlaceVerUsuarios).click();
    }

    public String obtenerDatosUsuario(String email) {
        String filaXpath = obtenerFilaXpath(email);

        browser.wait(filaXpath).xpath();
        return browser.find().xpath(filaXpath).getText();
    }

    public boolean esVisibleUsuario(String email) {
        String filaXpath = obtenerFilaXpath(email);

        browser.wait(filaXpath).xpath();
        return browser.find().xpath(filaXpath).isDisplayed();
    }

    public void solicitarEliminacion(String email) {
        browser.find().xpath(obtenerFilaXpath(email) + "//button").click();
    }

    public String obtenerConfirmacionEliminacion() {
        browser.wait(botonSi).xpath();
        return browser.find().xpath(confirmacionEliminacion).getText();
    }

    public void confirmarEliminacion() {
        browser.find().xpath(botonSi).click();
    }

    public String obtenerMensajeEliminacion() {
        browser.wait(mensajeEliminacion).xpath();
        return browser.find().xpath(mensajeEliminacion).getText();
    }

    public void aceptarConfirmacion() {
        browser.find().xpath(botonOK).click();
    }

    public boolean noExisteUsuario(String email) {
        browser.wait(1).sleep();
        return browser.find().xpathList(obtenerFilaXpath(email)).isEmpty();
    }
}