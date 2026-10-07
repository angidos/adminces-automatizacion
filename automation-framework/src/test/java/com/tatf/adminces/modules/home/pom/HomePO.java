package com.tatf.adminces.modules.home.pom;

import com.tatf.core.browser.IBrowser;

public class HomePO {
    private final IBrowser browser;
    private final String enlaceCrearUsuario = "Crear usuario";

    public HomePO(IBrowser browser) {
        this.browser = browser;
    }

    public boolean esVisibleCrearUsuario() {
        browser.wait(enlaceCrearUsuario).link();
        return browser.find().link(enlaceCrearUsuario).isDisplayed();
    }

}