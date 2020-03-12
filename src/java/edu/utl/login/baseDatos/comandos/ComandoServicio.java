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
import edu.utl.login.modelo.Producto;
import edu.utl.login.modelo.Reservacion;
import edu.utl.login.modelo.Servicio;
import edu.utl.login.modelo.Tratamiento;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author marco
 */
public class ComandoServicio {

    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    Statement stmt;
    conexionBasesDatos conn = new conexionBasesDatos();
    String query;

    public boolean agregarServicio(Servicio servicio) {

        boolean respuesta = true;
        int idServicio;
        int idServicioTratamiento;
        Tratamiento tratamientos[] = servicio.getTratamientos();
        Producto productos[];
        try {

            conn.Conectar();

            conn.setAutoCommit(false);

            query = "{call insertarServicio( ?, ?, ?, ?)}";

            cs = conn.getConexión().prepareCall(query);

            cs.setString(1, servicio.getFecha());
            cs.setInt(2, servicio.getReservacion().getIdReservacion());
            cs.setInt(3, servicio.getEmpleado().getIdEmpleado());

            cs.registerOutParameter(4, java.sql.Types.INTEGER);

            cs.executeUpdate();

            idServicio = cs.getInt(4);

            for (Tratamiento tratamiento : tratamientos) {

                productos = tratamiento.getProductos();

                query = "{call insertarServicio_tratamiento( ?, ?, ?)}";

                cs = conn.getConexión().prepareCall(query);

                cs.setInt(1, tratamiento.getIdTratamiento());
                cs.setInt(2, idServicio);

                cs.registerOutParameter(3, java.sql.Types.INTEGER);

                cs.executeUpdate();

                idServicioTratamiento = cs.getInt(3);

                for (Producto producto : productos) {

                    query = "{call insertarServicio_tratamiento_producto( ?, ?, ?)}";

                    cs = conn.getConexión().prepareCall(query);

                    cs.setInt(1, idServicioTratamiento);
                    cs.setInt(2, producto.getIdProducto());
                    cs.setFloat(3, (float) producto.getPrecioUso());

                    cs.executeUpdate();
                }

            }

            query = "UPDATE reservacion "
                    + "SET "
                    + "estatus = 0 "
                    + "WHERE "
                    + "idReservacion = (SELECT "
                    + "s.idReservacion "
                    + "FROM "
                    + "servicio s "
                    + "WHERE "
                    + "s.idServicio = ? )";

            ps = conn.getConexión().prepareStatement(query);

            ps.setInt(1, idServicio);

            ps.execute();

            servicio.setIdServicio(idServicio);

            conn.commit();

        } catch (Exception e) {
            conn.rollback();
            respuesta = false;
        } finally {
            conn.setAutoCommit(true);

            conn.Desconectar();
        }
        return respuesta;
    }

    public String listado() {

        Gson gson = new Gson();
        LinkedList<Servicio> servicios = new LinkedList<>();
        Tratamiento tratamiento;
        Tratamiento tratamientos[] = null;
        Producto producto;
        Producto productos[] = null;
        Servicio servicio;
        Empleado empleado;
        Persona persona;
        Reservacion reservacion;

        int renglones = 0;
        int posicion = 0;

        int reng = 0;
        int pos = 0;

        ResultSet rs1;
        ResultSet rs2;
        ResultSet rs3;
        ResultSet rs4;
        ResultSet rs5;

        try {
            conn.Conectar();

            query = "SELECT * FROM servicio";

            ps = conn.getConexión().prepareStatement(query);

            rs1 = ps.executeQuery();

            while (rs1.next()) {
                servicio = new Servicio(
                        rs1.getInt("idServicio"),
                        rs1.getString("fecha"),
                        null, null, null);

                query = "SELECT "
                        + "* "
                        + "FROM "
                        + "servicio_tratamiento "
                        + "WHERE "
                        + "idServicio = ?";

                ps = conn.getConexión().prepareStatement(query);

                ps.setInt(1, servicio.getIdServicio());

                rs2 = ps.executeQuery();

                if (rs2.last()) {
                    renglones = rs2.getRow();
                    tratamientos = new Tratamiento[renglones];
                    posicion = 0;
                    rs2.beforeFirst();
                }

                while (rs2.next()) {

                    query = "SELECT "
                            + "* "
                            + "FROM "
                            + "tratamiento "
                            + "WHERE "
                            + "idTratamiento = ? ";

                    ps = conn.getConexión().prepareStatement(query);

                    ps.setInt(1, rs2.getInt("idTratamiento"));

                    rs3 = ps.executeQuery();
                    while (rs3.next()) {
                        tratamiento = new Tratamiento(
                                rs3.getInt("idTratamiento"),
                                rs3.getString("nombre"),
                                rs3.getString("descripcion"),
                                rs3.getDouble("costo"),
                                rs3.getInt("estatus"));
                        //////
                        query = "SELECT "
                                + "stp.idServicioTratamiento,"
                                + "p.idProducto,"
                                + "p.nombre,"
                                + "p.marca,"
                                + "p.precioUso,"
                                + "p.estatus "
                                + "FROM "
                                + "producto p "
                                + "INNER JOIN "
                                + "    servicio_tratamiento_producto stp ON stp.idProducto = p.idProducto "
                                + "WHERE stp.idServicioTratamiento = ?";

                        ps = conn.getConexión().prepareStatement(query);

                        ps.setInt(1, rs2.getInt("idServicioTratamiento"));

                        rs4 = ps.executeQuery();

                        if (rs4.last()) {
                            reng = rs4.getRow();
                            productos = new Producto[reng];
                            pos = 0;
                            rs4.beforeFirst();
                        }

                        while (rs4.next()) {
                            producto = new Producto();
                            producto.setIdProducto(rs4.getInt("idProducto"));
                            producto.setNombre(rs4.getString("nombre"));
                            producto.setMarca(rs4.getString("marca"));
                            producto.setPrecioUso(rs4.getDouble("precioUso"));
                            producto.setStatus(rs4.getInt("estatus"));

                            productos[pos] = producto;
                            pos++;
                        }

                        ///////
                        tratamiento.setProductos(productos);
                        tratamientos[posicion] = tratamiento;
                        posicion++;
                    }
                    servicio.setTratamientos(tratamientos);
                }

                servicios.add(servicio);
            }

        } catch (Exception e) {
            servicios = null;
        } finally {
            conn.Desconectar();
            return gson.toJson(servicios);
        }
    }

}
