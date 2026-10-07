package com.tatf.adminces.modules.cierre.pom;

import com.tatf.core.browser.IBrowser;

public class CierreSesionPO {

    private final IBrowser browser;
    private final String enlaceCerrarSesion = "Cerrar sesión";
    private final String mensajePregunta = "//*[normalize-space()='Te quieres ir?']";
    private final String botonSi = "//button[normalize-space()='Sí']";
    private final String mensajeSesionCerrada = "//*[normalize-space()='Sesión cerrada.']";
    private final String botonOK = "//button[normalize-space()='OK']";

    public CierreSesionPO(IBrowser browser) {
        this.browser = browser;
    }

    public void abrirMenuUsuario(String nombreCompleto) {
        browser.find().xpath(
                "//*[self::button or self::a][contains(normalize-space(),'"
                        + nombreCompleto + "')]"
        ).click();
    }

    public void hacerClicEnCerrarSesion() {
        browser.find().link(enlaceCerrarSesion).click();
    }

    public String obtenerPreguntaConfirmacion() {
        browser.wait(mensajePregunta).xpath();
        return browser.find().xpath(mensajePregunta).getText();
    }

    public void confirmarCierre() {
        browser.find().xpath(botonSi).click();
    }

    public String obtenerMensajeSesionCerrada() {
        browser.wait(mensajeSesionCerrada).xpath();
        return browser.find().xpath(mensajeSesionCerrada).getText();
    }

    public void aceptarConfirmacion() {
        browser.find().xpath(botonOK).click();
    }

}