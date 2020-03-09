/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.controlador;

import edu.utl.login.baseDatos.comandos.ComandoCliente;
import edu.utl.login.baseDatos.comandos.ComandoGenerales;
import edu.utl.login.baseDatos.comandos.ComandoUsuario;
import edu.utl.login.modelo.Cliente;
import edu.utl.login.modelo.Usuario;
import java.sql.Timestamp;

/**
 *
 * @author marco
 */
public class ControladorCliente {

    private ComandoCliente ccli = new ComandoCliente();
    private ComandoGenerales cmd = new ComandoGenerales();
    private ComandoUsuario cmdUsuario = new ComandoUsuario();
    private Controlador ctrl = new Controlador();

    public Cliente modificarDatos(Cliente c) {

        if (cmdUsuario.validarToken(c.getUsuario())) {
            c = ccli.modificarDatos(c);
            return c;
        }
        return null;
    }

    public boolean cerrarSesion(Usuario u) {
        if (cmdUsuario.validarToken(u)) {
            u.cerrarSesion();

            cmd.actualizarToken(u);
            return true;
        }
        return false;
    }

    public boolean eliminarCuenta(Usuario u) {
        if (cmdUsuario.validarToken(u)) {
            u.cerrarSesion();

            cmd.actualizarToken(u);

            ccli.eliminarCuenta(u);
            return true;
        }
        return false;
    }

    public boolean crearCuenta(Cliente c) {

        if (validarDatos(c) && validarAutenticidad(c)) {

            String tm = "" + new Timestamp(System.currentTimeMillis());
            c.setNumeroUnico(tm);

            return ccli.crearCuenta(c);
        }

        return false;
    }

    public boolean validarDatos(Cliente c) {
        if (c.getNombre().trim().isEmpty()) {
            return false;
        }
        if (c.getApellidoPaterno().trim().isEmpty()) {
            return false;
        }
        if (c.getCorreo().trim().isEmpty()) {
            return false;
        }
        if (c.getUsuario().getNombreUsuario().trim().isEmpty()) {
            return false;
        }
        if (c.getUsuario().getContrasenia().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validarAutenticidad(Cliente c) {
        return ccli.validarAutenticidad(c);
    }
}
