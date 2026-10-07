package com.tatf.adminces.modules.registro.task;

import com.tatf.core.browser.IBrowser;
import com.tatf.adminces.modules.registro.pom.RegistroPO;
import com.tatf.core.verification.IVerify;

public class RegistroTask {

    private final RegistroPO registro;

    public RegistroTask(IBrowser browser) {
        this.registro = new RegistroPO(browser);
    }

    public void registrarAdministrador(
            String nombre, String apellido, String email,String contrasena, String pais) {

        registro.abrirRegistro();
        registro.ingresarNombre(nombre);
        registro.ingresarApellido(apellido);
        registro.ingresarEmail(email);
        registro.ingresarContrasena(contrasena);
        registro.repetirContrasena(contrasena);
        registro.ingresarPais(pais);
        registro.hacerClicEnRegistrarse();
    }

    public void verificarRegistro(String mensajeEsperado) {
        String mensajeObtenido = registro.obtenerMensajeConfirmacion();

        IVerify.create().verify(mensajeEsperado,mensajeObtenido,"No se mostró el mensaje de confirmación esperado.");

        registro.aceptarConfirmacion();
    }

}