/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.baseDatos.comandos;

import edu.utl.login.baseDatos.conexionBasesDatos;
import edu.utl.login.modelo.Tratamiento;
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
public class ComandosTratamiento {

    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;
    conexionBasesDatos conn = new conexionBasesDatos();
    String query;

    public Queue listarTratamientos() {

        try {
            Tratamiento t = null;
            Queue tratamientos = new LinkedList<Tratamiento>();

            query = "SELECT * FROM v_tratamientos";

            conn.Conectar();

            ps = conn.getConexión().prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                t = new Tratamiento(rs.getInt("idTratamiento"), rs.getString("nombre"), rs.getString("descripcion"), rs.getInt("costo"), rs.getInt("estatus"));

                tratamientos.add(t);

            }

            return tratamientos;
        } catch (SQLException ex) {

            Logger.getLogger(ComandosProducto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.Desconectar();
        }
        return null;
    }
}
