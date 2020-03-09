/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.controlador;

import edu.utl.login.baseDatos.comandos.ComandoProducto;
import edu.utl.login.baseDatos.comandos.ComandoTratamiento;
import java.util.Queue;

/**
 *
 * @author marco
 */
public class ControladorTratamiento {

    public Queue listarTratamientos() {
        ComandoTratamiento cmdT = new ComandoTratamiento();
        return cmdT.listarTratamientos();

    }
}
