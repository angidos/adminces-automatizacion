package com.tatf.adminces.modules.acceso.task;

import com.tatf.core.browser.IBrowser;
import com.tatf.adminces.modules.acceso.pom.AccesoPO;

public class AccesoTask {

    private final IBrowser browser;
    private final AccesoPO acceso;

    public AccesoTask(IBrowser browser) {
        this.browser = browser;
        this.acceso = new AccesoPO(browser);
    }

    public void accederAlSistema(String url, String clave) {
        browser.interaction().navigateTo(url);
        acceso.ingresarClave(clave);
        acceso.hacerClicEnIngresar();
    }

}