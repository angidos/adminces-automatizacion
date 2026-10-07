package com.tatf.adminces.modules.usuarios.task;

import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;
import com.tatf.adminces.modules.usuarios.pom.CrearUsuarioPO;
import com.tatf.adminces.modules.usuarios.pom.UsuariosPO;

public class CrearTesterTask {
    private final CrearUsuarioPO creacion;
    private final UsuariosPO usuarios;

    public CrearTesterTask(IBrowser browser) {
        this.creacion = new CrearUsuarioPO(browser);
        this.usuarios = new UsuariosPO(browser);
    }

    public void crearTester(String nombre, String apellido, String email, String pais, String contrasena, String tipo) {

        creacion.abrirCreacionUsuario();
        creacion.ingresarNombre(nombre);
        creacion.ingresarApellido(apellido);
        creacion.ingresarEmail(email);
        creacion.seleccionarPais(pais);
        creacion.ingresarContrasena(contrasena);
        creacion.seleccionarTipoTester(tipo);
        creacion.hacerClicEnCrearCuenta();
    }

    public void verificarCreacion(String mensajeEsperado) {
        String mensajeObtenido = creacion.obtenerMensajeConfirmacion();

        IVerify.create().verify(mensajeEsperado,mensajeObtenido,"No se mostró la confirmación de creación del Tester.");

        creacion.aceptarConfirmacion();
    }

    public void verificarDatosTester(String nombre, String apellido, String email, String pais, String tipo) {

        usuarios.abrirUsuarios();
        String datosTester = usuarios.obtenerDatosUsuario(email);

        IVerify.create().verifyTrue(datosTester.contains(nombre),"El nombre del Tester no coincide.");
        IVerify.create().verifyTrue(datosTester.contains(apellido),"El apellido del Tester no coincide.");
        IVerify.create().verifyTrue(datosTester.contains(email),"El correo del Tester no coincide.");
        IVerify.create().verifyTrue(datosTester.contains(pais),"El país del Tester no coincide.");
        IVerify.create().verifyTrue(datosTester.contains(tipo),"El tipo de Tester no coincide.");
    }
}
