package com.tatf.adminces.modules.acceso.pom;

import com.tatf.core.browser.IBrowser;

public class AccesoPO {

    private final IBrowser browser;
    private final String campoClave = "pass";
    private final String botonIngresar = "//button[contains(text(),'Ingresar')]";

    public AccesoPO(IBrowser browser) {
        this.browser = browser;
    }

    public void ingresarClave(String clave) {
        browser.find().id(campoClave).write(clave);
    }

    public void hacerClicEnIngresar() {
        browser.find().xpath(botonIngresar).click();
    }

}