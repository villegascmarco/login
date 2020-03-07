/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.baseDatos.comandos;

import com.google.gson.Gson;
import edu.utl.login.baseDatos.conexionBasesDatos;
import edu.utl.login.modelo.Empleado;
import edu.utl.login.modelo.Persona;
import edu.utl.login.modelo.Usuario;
import edu.utl.login.modelo.Reservacion;
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
public class ComandoEmpleado {

    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    Statement stmt;
    conexionBasesDatos conn = new conexionBasesDatos();
    String query;

    public boolean insertarEmpleado(Empleado e) {
        Persona p = e.getPersona();
        Usuario u = e.getUsuario();

        try {
            conn.Conectar();

            conn.setAutoCommit(false);

            query = "{call insertarPersona(?,?, ?, ?, ?, ?, ?, ?)}";

            cs = conn.getConexión().prepareCall(query);

            cs.setString(1, p.getNombre());
            cs.setString(2, p.getApellidoPaterno());
            cs.setString(3, p.getApellidoMaterno());
            cs.setString(4, p.getGenero());
            cs.setString(5, p.getDomicilio());
            cs.setString(6, p.getTelefono());
            cs.setString(7, p.getRfc());

            cs.registerOutParameter(8, java.sql.Types.INTEGER);

            cs.executeUpdate();

            int idPersona = cs.getInt(8);

            query = "{call insertarUsuario( ?, ?, ?, ?)}";

            cs = conn.getConexión().prepareCall(query);

            cs.setString(1, u.getNombreUsuario());
            cs.setString(2, u.getContrasenia());
            cs.setString(3, u.getRol());

            cs.registerOutParameter(4, java.sql.Types.INTEGER);

            cs.executeUpdate();

            int idUsuario = cs.getInt(4);

            query = "{call insertarEmpleado( ?, ?, ?, ?, ?)}";

            cs = conn.getConexión().prepareCall(query);

            cs.setString(1, e.getNumeroEmpleado());
            cs.setString(2, e.getPuesto());
            cs.setString(3, e.getRutaFoto());
            cs.setInt(4, idPersona);
            cs.setInt(5, idUsuario);

            cs.executeUpdate();

            conn.commit();

            conn.Desconectar();
            return true;

        } catch (Exception ex) {

            conn.rollback();

            conn.setAutoCommit(true);

            conn.Desconectar();

            Logger.getLogger(ComandoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public boolean validarAutenticidad(Usuario u) {
        query = "select * from v_inicioSesionE where nombreUsuario = ? OR contrasenia = ?";

        try {

            conn.Conectar();

            ps = conn.getConexión().prepareCall(query);

            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getContrasenia());

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

    public boolean eliminarCuenta(int idEmpleado) {
        query = "{call eliminarEmpleado(?)}";

        try {
            conn.Conectar();

            conn.setAutoCommit(false);
            cs = conn.getConexión().prepareCall(query);

            cs.setInt(1, idEmpleado);

            cs.executeUpdate();

            conn.commit();

            conn.setAutoCommit(true);
            conn.Desconectar();
            return true;
        } catch (SQLException ex) {
            conn.rollback();

            conn.setAutoCommit(true);

            conn.Desconectar();
            Logger.getLogger(ComandoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        conn.rollback();
        conn.setAutoCommit(true);
        conn.Desconectar();

        return false;
    }

    public boolean actualizarEmpleado(Empleado e) {
        Persona p = e.getPersona();
        Usuario u = e.getUsuario();

        try {
            conn.Conectar();

            conn.setAutoCommit(false);

            int idUsuario = u.getIdUsuario();

            int idPersona = p.getIdPersona();

            String numeroEmpleado = e.getNumeroEmpleado();

            query = "CALL actualizarPersona(?,?,?,?,?,?,?,?);";

            ps = conn.getConexión().prepareStatement(query);

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellidoPaterno());
            ps.setString(3, p.getApellidoMaterno());
            ps.setString(4, p.getGenero());
            ps.setString(5, p.getDomicilio());
            ps.setString(6, p.getTelefono());
            ps.setString(7, p.getRfc());
            ps.setInt(8, idPersona);

            ps.execute();

            query = "CALL actualizarUsuario(?,?,?,?)";

            ps = conn.getConexión().prepareStatement(query);

            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getContrasenia());
            ps.setString(3, u.getRol());
            ps.setInt(4, idUsuario);

            ps.execute();

            query = "CALL actualizarEmpleado(?,?,?,?) ";

            ps = conn.getConexión().prepareStatement(query);

            ps.setString(1, numeroEmpleado);
            ps.setString(2, e.getPuesto());
            ps.setString(3, e.getRutaFoto());
            ps.setInt(4, idPersona);
            System.out.println(ps);

            ps.execute();

            conn.commit();

            ComandosGenerales cmd = new ComandosGenerales();

            String out = cmd.buscarUsuario(u, 2);

            conn.Desconectar();
            return true;

        } catch (Exception ex) {

            conn.rollback();

            conn.setAutoCommit(true);

            conn.Desconectar();

            Logger.getLogger(ComandoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public String listarEmpleados() {
        try {
            Empleado[] empleados = null;
            Gson gson = new Gson();
            int renglones = 0;
            int con = 0;
            conn.Conectar();
            query = "SELECT  * FROM v_listaEmpleados WHERE estatus=1";
            ps = conn.getConexión().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.last()) {
                renglones = rs.getRow();
                empleados = new Empleado[renglones];
                rs.beforeFirst();
                while (rs.next()) {
                    Empleado e = new Empleado();
                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getInt("idUsuario"));
                    u.setNombreUsuario(rs.getString("nombreUsuario"));
                    e.setIdEmpleado(rs.getInt("idEmpleado"));
                    e.setNumeroEmpleado(rs.getString("numeroEmpleado"));
                    e.setPuesto(rs.getString("puesto"));
                    e.setEstatus(rs.getInt("estatus"));

                    e.setUsuario(u);
                    empleados[con] = e;
                    con++;
                }
            }

            conn.Desconectar();

            return gson.toJson(empleados);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

  
}
