package com.tatf.adminces.modules.usuarios.task;

import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;
import com.tatf.adminces.modules.usuarios.pom.UsuariosPO;

public class EliminarTesterTask {
    private final UsuariosPO usuarios;

    public EliminarTesterTask(IBrowser browser) {
        this.usuarios = new UsuariosPO(browser);
    }

    public void solicitarEliminacion(String email) {
        usuarios.abrirUsuarios();

        IVerify.create().verifyTrue(usuarios.esVisibleUsuario(email),"No se encontró el Tester que se desea eliminar.");

        usuarios.solicitarEliminacion(email);
    }

    public void verificarConfirmacionYEliminar(String email) {
        String confirmacion = usuarios.obtenerConfirmacionEliminacion();

        IVerify.create().verifyTrue(confirmacion.contains(email),"La confirmación no muestra el correo del Tester seleccionado.");

        usuarios.confirmarEliminacion();
    }

    public void verificarEliminacion(String mensajeEsperado) {
        String mensajeObtenido = usuarios.obtenerMensajeEliminacion();

        IVerify.create().verify(mensajeEsperado,mensajeObtenido,"No se mostró la confirmación de eliminación.");

        usuarios.aceptarConfirmacion();
    }

    public void verificarAusenciaTester(String email) {
        IVerify.create().verifyTrue(usuarios.noExisteUsuario(email),"El Tester eliminado todavía aparece en la tabla.");
    }
}
