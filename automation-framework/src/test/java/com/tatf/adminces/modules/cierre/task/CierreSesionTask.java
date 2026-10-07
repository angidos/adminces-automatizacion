package com.tatf.adminces.modules.cierre.task;

import com.tatf.core.browser.IBrowser;
import com.tatf.adminces.modules.cierre.pom.CierreSesionPO;
import com.tatf.core.verification.IVerify;

public class CierreSesionTask {
    private final CierreSesionPO cierre;

    public CierreSesionTask(IBrowser browser) {
        this.cierre = new CierreSesionPO(browser);
    }
    public void solicitarCierreSesion(String nombreCompleto) {
        cierre.abrirMenuUsuario(nombreCompleto);
        cierre.hacerClicEnCerrarSesion();
    }

    public void verificarPreguntaYConfirmar(String mensajeEsperado) {
        String mensajeObtenido = cierre.obtenerPreguntaConfirmacion();

        IVerify.create().verify(mensajeEsperado,mensajeObtenido,"No se mostró la confirmación para cerrar sesión.");

        cierre.confirmarCierre();
    }

    public void verificarSesionCerrada(String mensajeEsperado) {
        String mensajeObtenido = cierre.obtenerMensajeSesionCerrada();

        IVerify.create().verify(mensajeEsperado,mensajeObtenido,"No se confirmó el cierre de sesión.");

        cierre.aceptarConfirmacion();
    }
}