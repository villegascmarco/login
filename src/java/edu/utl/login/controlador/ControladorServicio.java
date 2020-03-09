/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.controlador;

import com.google.gson.Gson;
import edu.utl.login.baseDatos.comandos.ComandoServicio;
import edu.utl.login.modelo.Servicio;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author marco
 */
public class ControladorServicio {
    
    public String agregarTratamiento(Servicio servicio) {
        ControladorUsuario ctrUsuario = new ControladorUsuario();
        Controlador ctrl = new Controlador();
        ComandoServicio cmdServicio = new ComandoServicio();
        
        if (!ctrUsuario.validarToken(servicio.getEmpleado().getUsuario())) {
            return "Token inválido";
        }
        
        servicio.setFecha(ctrl.generarFecha());
        
        if (cmdServicio.agregarServicio(servicio)) {
            
        }
        Gson gson = new Gson();
        return gson.toJson(servicio);
    }
    
}
