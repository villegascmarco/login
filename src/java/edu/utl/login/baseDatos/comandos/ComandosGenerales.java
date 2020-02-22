/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.baseDatos.comandos;

import com.google.gson.Gson;
import edu.utl.login.baseDatos.conexionBasesDatos;
import edu.utl.login.modelo.Cliente;
import edu.utl.login.modelo.Empleado;
import edu.utl.login.modelo.Persona;
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
public class ComandosGenerales {

    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;
    conexionBasesDatos conn = new conexionBasesDatos();
    String query;

    public String buscarUsuario(Usuario u, int veces) {
        switch (veces) {
            case 1:
                query = "SELECT * FROM v_inicioSesionC WHERE ";
                break;
            case 2:
                query = "SELECT * FROM v_inicioSesionE WHERE ";
                break;
        }

        String json = null;

        try {
            conn.Conectar();

            query += "nombreUsuario = ? AND contrasenia = ? AND token = '' AND estatus = 1 ";
            ps = conn.getConexión().prepareStatement(query);
            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getContrasenia());

            rs = ps.executeQuery();

            Cliente c = null;
            Persona per = null;
            Empleado p = null;

            if (rs.next()) {
                Gson gson = new Gson();

                u = new Usuario(rs.getInt("idUsuario"),
                        rs.getString("nombreUsuario"),
                        rs.getString("contrasenia"),
                        rs.getString("rol"));

                per = new Persona();
                per.setIdPersona(rs.getInt("idPersona"));
                per.setApellidoPaterno(rs.getString("apellidoPaterno"));
                per.setApellidoMaterno(rs.getString("apellidoMaterno"));
                per.setNombre(rs.getString("nombre"));
                per.setDomicilio(rs.getString("domicilio"));
                per.setGenero(rs.getString("genero"));
                per.setRfc(rs.getString("rfc"));
                per.setTelefono(rs.getString("telefono"));

                if (u.getRol().equals("Cliente")) {

                    c = new Cliente(rs.getInt("idCliente"),
                            rs.getString("numeroUnico"),
                            rs.getString("correo"),
                            rs.getInt("estatus"),
                            u,
                            rs.getInt("idPersona"),
                            rs.getString("nombre"),
                            rs.getString("apellidoPaterno"),
                            rs.getString("apellidoMaterno"),
                            rs.getString("genero"),
                            rs.getString("domicilio"),
                            rs.getString("telefono"),
                            rs.getString("rfc"));

                    conn.Desconectar();

                    return gson.toJson(c);

                }
                p = new Empleado();
                p.setPersona(per);
                p.setUsuario(u);
                p.setIdEmpleado(rs.getInt("idEmpleado"));

                p.setNumeroEmpleado(rs.getString("numeroEmpleado"));
                p.setPuesto(rs.getString("puesto"));
                p.setEstatus(rs.getInt("estatus"));
                p.setRutaFoto(rs.getString("rutaFoto"));

                conn.Desconectar();
                return gson.toJson(p);

            }

            conn.Desconectar();

            if (veces == 2) {
                return null;
            }
            return buscarUsuario(u, 2);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(ComandoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void actualizarToken(Usuario u) {
        query = "call actualizarToken(?,?)";

        try {
            conn.Conectar();

            ps = conn.getConexión().prepareCall(query);
            ps.setString(1, u.getToken());
            ps.setInt(2, u.getIdUsuario());

            ps.executeQuery();

            conn.Desconectar();

        } catch (Exception ex) {
            Logger.getLogger(ComandoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean validarToken(Usuario u) {
        query = "select * from usuario where token = ? AND idUsuario = ?";

        try {
            conn.Conectar();

            ps = conn.getConexión().prepareStatement(query);

            ps.setString(1, u.getToken());
            ps.setInt(2, u.getIdUsuario());

            rs = ps.executeQuery();

            if (rs.next()) {
                conn.Desconectar();
                return true;
            } else {
                conn.Desconectar();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
