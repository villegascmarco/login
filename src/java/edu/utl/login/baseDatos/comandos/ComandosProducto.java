/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.baseDatos.comandos;

import edu.utl.login.baseDatos.conexionBasesDatos;
import edu.utl.login.modelo.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marco
 */
public class ComandosProducto {

    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;
    conexionBasesDatos conn = new conexionBasesDatos();
    String query;

    public Queue listarProductos() {

        try {
            Producto p = null;
            Queue productos = new LinkedList<Producto>();

            query = "SELECT * FROM v_productos";

            conn.Conectar();

            ps = conn.getConexión().prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                p = new Producto(rs.getInt("idProducto"), rs.getString("nombre"), rs.getString("marca"), rs.getInt("estatus"), rs.getInt("precioUso"));

                productos.add(p);

            }

            return productos;
        } catch (SQLException ex) {

            Logger.getLogger(ComandosProducto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.Desconectar();
        }
        return null;
    }

}
