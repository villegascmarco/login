/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.controlador;

import com.google.gson.Gson;
import edu.utl.login.baseDatos.comandos.ComandoCliente;
import edu.utl.login.baseDatos.comandos.ComandoGenerales;
import edu.utl.login.baseDatos.conexionBasesDatos;
import edu.utl.login.modelo.Cliente;
import edu.utl.login.modelo.Empleado;
import edu.utl.login.modelo.Usuario;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controlador {

    private conexionBasesDatos conn = new conexionBasesDatos();
    private ComandoCliente ccli = new ComandoCliente();
    private ComandoGenerales cmd = new ComandoGenerales();

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

    public String limpiarDato(String dato) {

        String original = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝß";
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

    public String generarFecha() {

        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime myDateObj = LocalDateTime.now();

        return myDateObj.format(myFormatObj);
    }

}
