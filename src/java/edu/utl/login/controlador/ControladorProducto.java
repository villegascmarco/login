/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.controlador;

import edu.utl.login.baseDatos.comandos.ComandoProducto;
import java.util.Queue;

/**
 *
 * @author marco
 */
public class ControladorProducto {

    public Queue listarProductos() {
        ComandoProducto cmdP = new ComandoProducto();
        return cmdP.listarProductos();

    }

}
