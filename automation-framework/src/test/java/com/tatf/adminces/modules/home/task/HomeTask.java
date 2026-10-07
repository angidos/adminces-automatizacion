package com.tatf.adminces.modules.home.task;

import com.tatf.core.browser.IBrowser;
import com.tatf.adminces.modules.home.pom.HomePO;
import com.tatf.core.verification.IVerify;

public class HomeTask {
    private final HomePO home;

    public HomeTask(IBrowser browser) {
        this.home = new HomePO(browser);
    }

    public void verificarPanelAdministrador() {
        boolean pantallaPrincipalVisible = home.esVisibleCrearUsuario();

        IVerify.create().verifyTrue(pantallaPrincipalVisible,"No se accedió al panel principal del Administrador.");
    }

}
