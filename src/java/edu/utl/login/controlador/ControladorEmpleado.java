/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.controlador;

import com.google.gson.Gson;
import edu.utl.login.baseDatos.comandos.ComandoEmpleado;
import edu.utl.login.baseDatos.comandos.ComandosGenerales;
import edu.utl.login.modelo.Empleado;
import edu.utl.login.modelo.Usuario;
import java.sql.Timestamp;

/**
 *
 * @author marco
 */
public class ControladorEmpleado {

    private ComandosGenerales cmd = new ComandosGenerales();
    private ComandoEmpleado cmdE = new ComandoEmpleado();
    private Controlador ctrl = new Controlador();

    public boolean insertar(Empleado e) {
        if (!ctrl.validarToken(e.getUsuario())) {
            return false;
        }

        if (!validarDatos(e) || !validarAutenticidad(e)) {
            return false;
        }

        String tm = "" + new Timestamp(System.currentTimeMillis());
        e.setNumeroEmpleado(tm);

        return cmdE.insertarEmpleado(e);
    }

    public boolean validarDatos(Empleado e) {

        if (e.getPersona().getNombre().trim().isEmpty()) {
            return false;
        }
        if (e.getPersona().getApellidoPaterno().trim().isEmpty()) {
            return false;
        }
        if (e.getUsuario().getNombreUsuario().trim().isEmpty()) {
            return false;
        }
        if (e.getUsuario().getContrasenia().trim().isEmpty()) {
            return false;
        }
        if (e.getRutaFoto() != null) {
            if (e.getRutaFoto().length() > 300) {
                return false;
            }
        }
        return true;
    }

    public boolean validarAutenticidad(Empleado e) {
        return cmdE.validarAutenticidad(e.getUsuario());
    }

    public boolean eliminarCuenta(String idEmpleado, String token, String idUsuario) {
        Usuario u = new Usuario(Integer.parseInt(idUsuario), token);
        if (!ctrl.validarToken(u)) {
            return false;
        }

        return cmdE.eliminarCuenta(Integer.parseInt(idEmpleado));
    }

    public boolean actualizarCuenta(Empleado e) {
        if (!ctrl.validarToken(e.getUsuario())) {
            return false;
        }
        return cmdE.actualizarEmpleado(e);
    }

    public String listarEmpleados(Usuario u, int opcion) {
        if (!ctrl.validarToken(u) || opcion == 1) {
            Gson gson = new Gson();
            String json = cmdE.listarEmpleados();
            return json;
        }

        return null;
    }

    public String listarReservaciones(Usuario u) {
        if (!ctrl.validarToken(u)) {
            Gson gson = new Gson();
            String json = cmdE.listarReservaciones();
            return json;
        }
        return null;
    }

}
