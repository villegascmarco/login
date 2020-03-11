/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.baseDatos.comandos;

import edu.utl.login.baseDatos.conexionBasesDatos;
import edu.utl.login.modelo.Usuario;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marco
 */
public class ComandoUsuario {

    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    Statement stmt;
    conexionBasesDatos conn = new conexionBasesDatos();
    String query;

    public boolean validarToken(Usuario usuario) {
        boolean respuesta = false;

        query = "select * from usuario where token = ? AND idUsuario = ?";

        try {

            conn.Conectar();

            ps = conn.getConexión().prepareCall(query);

            ps.setString(1, usuario.getToken());
            ps.setInt(2, usuario.getIdUsuario());

            rs = ps.executeQuery();

            if (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ComandoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ComandoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.Desconectar();
        }

        return respuesta;

    }
}
