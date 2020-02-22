/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.controlador;

import com.google.gson.Gson;
import edu.utl.login.baseDatos.comandos.ComandoCliente;
import edu.utl.login.baseDatos.comandos.ComandosGenerales;
import edu.utl.login.baseDatos.conexionBasesDatos;
import edu.utl.login.modelo.Cliente;
import edu.utl.login.modelo.Empleado;
import edu.utl.login.modelo.Usuario;

public class Controlador {

    private conexionBasesDatos conn = new conexionBasesDatos();
    private ComandoCliente ccli = new ComandoCliente();
    private ComandosGenerales cmd = new ComandosGenerales();

    public String inicarSesion(Usuario u) {
        Cliente c;
        Empleado p;
        Gson gson = new Gson();
        String json = cmd.buscarUsuario(u, 1);
        String usuario = null;

        if (json == null || json.equals("")) {
            return null;
        }
        if (json.contains("Cliente")) {
            c = gson.fromJson(json, Cliente.class);
            c.getUsuario().setToken();
            cmd.actualizarToken(c.getUsuario());
            usuario = gson.toJson(c);
            return usuario;
        } else {
            p = gson.fromJson(json, Empleado.class);
            p.getUsuario().setToken();
            cmd.actualizarToken(p.getUsuario());
            usuario = gson.toJson(p);
        }

        return usuario;
    }

    public boolean validarToken(Usuario u) {
        if (u.getToken().trim().isEmpty()) {
            return false;
        }
        return cmd.validarToken(u);
    }

    public String limpiarDato(String dato) {

        String original = "";
        String cambios = "AAAAAAACEEEEIIIIDNOOOOOOUUUUYB";
        String resultado = dato.toUpperCase();

        for (int i = 0; i < original.length(); i++) {
            resultado = resultado.replace(original.charAt(i), cambios.charAt(i));
        }

        String original2 = "=+-*/.:;~-_!#$%&()?'¡¿[]{}|°\"";

        for (int i = 0; i < original2.length(); i++) {
            resultado = resultado.replace(original2.charAt(i), ' ');
        }

        return resultado;
    }

}
