/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.baseDatos.comandos;

import edu.utl.login.baseDatos.conexionBasesDatos;
import edu.utl.login.modelo.Cliente;
import edu.utl.login.modelo.Usuario;
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
public class ComandoCliente {

    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;
    conexionBasesDatos conn = new conexionBasesDatos();
    String query;

    public Cliente modificarDatos(Cliente c) {

        query = "call actualizarCliente(?,?,?,?,?,?,?,?,?,?,?,@uno,@dos,@tres)";

        try {
            conn.Conectar();

            ps = conn.getConexión().prepareStatement(query);

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellidoPaterno());
            ps.setString(3, c.getApellidoMaterno());
            ps.setString(4, c.getGenero());
            ps.setString(5, c.getDomicilio());
            ps.setString(6, c.getTelefono());
            ps.setString(7, c.getRfc());
            ps.setString(8, c.getUsuario().getNombreUsuario());
            ps.setString(9, c.getUsuario().getContrasenia());
            ps.setString(10, c.getNumeroUnico());
            ps.setString(11, c.getCorreo());

            ps.executeQuery();

            conn.Desconectar();

        } catch (SQLException ex) {
            Logger.getLogger(ComandoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ComandoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            conn.Desconectar();
        } catch (Exception ex) {
            Logger.getLogger(ComandoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public void eliminarCuenta(Usuario u) {
        query = "call eliminarCuentaCliente(?)";

        try {

            conn.Conectar();
            ps = conn.getConexión().prepareCall(query);

            ps.setInt(1, u.getIdUsuario());

            ps.executeQuery();

            conn.Desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(ComandoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ComandoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean validarAutenticidad(Cliente c) {
        query = "select * from v_buscarCliente where correo = ? OR nombreUsuario = ?";

        try {

            conn.Conectar();

            ps = conn.getConexión().prepareCall(query);

            ps.setString(1, c.getCorreo());
            ps.setString(2, c.getUsuario().getNombreUsuario());

            rs = ps.executeQuery();

            if (rs.next()) {
                conn.Desconectar();
                return false;
            } else {
                conn.Desconectar();
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ComandoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ComandoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.Desconectar();
        } catch (Exception ex) {
            Logger.getLogger(ComandoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean crearCuenta(Cliente c) {
        query = "call insertarCliente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        try {

            conn.Conectar();

            ps = conn.getConexión().prepareCall(query);

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellidoPaterno());
            ps.setString(3, c.getApellidoMaterno());
            ps.setString(4, c.getGenero());
            ps.setString(5, c.getDomicilio());
            ps.setString(6, c.getTelefono());
            ps.setString(7, c.getRfc());
            ps.setString(8, c.getUsuario().getNombreUsuario());
            ps.setString(9, c.getUsuario().getContrasenia());
            ps.setString(10, c.getUsuario().getRol());
            ps.setString(11, c.getNumeroUnico());
            ps.setString(12, c.getCorreo());
            ps.setInt(13, 0);
            ps.setInt(14, 0);
            ps.setInt(15, 0);

            rs = ps.executeQuery();

            conn.Desconectar();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ComandoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ComandoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.Desconectar();
        } catch (Exception ex) {
            Logger.getLogger(ComandoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
