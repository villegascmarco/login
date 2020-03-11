/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.controlador;

import edu.utl.login.baseDatos.comandos.ComandoUsuario;
import edu.utl.login.modelo.Usuario;

/**
 *
 * @author marco
 */
public class ControladorUsuario {

    ComandoUsuario cmdUsuario;

    public boolean validarToken(Usuario usuario) {

        cmdUsuario = new ComandoUsuario();
        String token = usuario.getToken();

        if (token == null || token.trim().isEmpty()) {
            return false;
        }

        return cmdUsuario.validarToken(usuario);
    }
}
