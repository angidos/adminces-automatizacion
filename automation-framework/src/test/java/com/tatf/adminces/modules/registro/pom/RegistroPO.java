package com.tatf.adminces.modules.registro.pom;

import com.tatf.core.browser.IBrowser;

public class RegistroPO {

    private final IBrowser browser;
    private final String enlaceRegistrarse = "Registrarse";
    private final String campoNombre = "input[placeholder='Nombre']";
    private final String campoApellido = "input[placeholder='Apellido']";
    private final String campoEmail = "input[placeholder='Email']";
    private final String campoContrasena = "input[placeholder='Contraseña']";
    private final String campoRepetirContrasena = "input[placeholder='Repetir contraseña']";
    private final String campoPais = "input[placeholder='Pais nacimiento']";
    private final String botonRegistrarse = "//button[contains(text(),'Registrarse')]";
    private final String mensajeConfirmacion = "//*[normalize-space()='Usuario creado.']";
    private final String botonOK = "//button[normalize-space()='OK']";

    public RegistroPO(IBrowser browser) {
        this.browser = browser;
    }

    public void abrirRegistro() {
        browser.find().link(enlaceRegistrarse).click();
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

    public void ingresarContrasena(String contrasena) {
        browser.find().css(campoContrasena).write(contrasena);
    }

    public void repetirContrasena(String contrasena) {
        browser.find().css(campoRepetirContrasena).write(contrasena);
    }

    public void ingresarPais(String pais) {
        browser.find().css(campoPais).write(pais);
    }

    public void hacerClicEnRegistrarse() {
        browser.find().xpath(botonRegistrarse).click();
    }

    public String obtenerMensajeConfirmacion() {
        browser.wait(mensajeConfirmacion).xpath();
        return browser.find().xpath(mensajeConfirmacion).getText();
    }

    public void aceptarConfirmacion() {
        browser.find().xpath(botonOK).click();
    }

}