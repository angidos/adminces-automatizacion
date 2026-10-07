package com.tatf.adminces.modules.reinicio.task;

import com.tatf.core.browser.IBrowser;
import com.tatf.adminces.modules.reinicio.pom.ReinicioPO;
import com.tatf.core.verification.IVerify;

public class ReinicioTask {
    private final ReinicioPO reinicio;

    public ReinicioTask(IBrowser browser) {
        this.reinicio = new ReinicioPO(browser);
    }

    public void reiniciarContrasena(String email, String nuevaContrasena) {
        reinicio.abrirReinicio();
        reinicio.ingresarEmail(email);
        reinicio.ingresarContrasena(nuevaContrasena);
        reinicio.repetirContrasena(nuevaContrasena);
        reinicio.hacerClicEnReiniciar();
    }
    public void verificarReinicio(String mensajeEsperado) {
        String mensajeObtenido = reinicio.obtenerMensajeConfirmacion();

        IVerify.create().verify(
                mensajeEsperado,
                mensajeObtenido,
                "No se mostró la confirmación de reinicio de contraseña."
        );

        reinicio.aceptarConfirmacion();
    }

}