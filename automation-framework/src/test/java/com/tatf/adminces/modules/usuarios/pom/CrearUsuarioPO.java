package com.tatf.adminces.modules.usuarios.pom;

import com.tatf.core.browser.IBrowser;

public class CrearUsuarioPO {
    private final IBrowser browser;

    private final String enlaceCrearUsuario = "Crear usuario";
    private final String campoNombre = "input[placeholder='Nombre']";
    private final String campoApellido = "input[placeholder='Apellido']";
    private final String campoEmail = "input[placeholder='Email']";
    private final String selectorPais = "select";
    private final String campoContrasena = "input[placeholder='Contraseña por defecto']";
    private final String botonCrearCuenta = "//button[contains(text(),'Crear cuenta')]";
    private final String mensajeConfirmacion = "//*[normalize-space()='Usuario creado.']";
    private final String botonOK = "//button[normalize-space()='OK']";

    public CrearUsuarioPO(IBrowser browser) {
        this.browser = browser;
    }

    public void abrirCreacionUsuario() {
        browser.wait(enlaceCrearUsuario).link();
        browser.find().link(enlaceCrearUsuario).click();
    }

    public void ingresarNombre(String nombre) {
        browser.find().css(campoNombre).write(nombre);
    }

    public void ingresarApellido(String apellido) {
        browser.find().css(campoApellido).write(apellido);
    }

    public void ingresarEmail(String email) {
        browser.find().css(campoEmail).write(email);
    }

    public void seleccionarPais(String pais) {
        browser.find().css(selectorPais).selectText(pais);
    }

    public void ingresarContrasena(String contrasena) {
        browser.find().css(campoContrasena).write(contrasena);
    }

    public void seleccionarTipoTester(String tipo) {
        browser.find().xpath("//*[normalize-space()='" + tipo + "']" ).click();
    }

    public void hacerClicEnCrearCuenta() {
        browser.find().xpath(botonCrearCuenta).click();
    }

    public String obtenerMensajeConfirmacion() {
        browser.wait(mensajeConfirmacion).xpath();
        return browser.find().xpath(mensajeConfirmacion).getText();
    }

    public void aceptarConfirmacion() {
        browser.find().xpath(botonOK).click();
    }
}