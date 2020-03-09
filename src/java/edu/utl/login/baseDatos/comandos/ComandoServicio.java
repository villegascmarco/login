/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.baseDatos.comandos;

import edu.utl.login.baseDatos.conexionBasesDatos;
import edu.utl.login.modelo.Producto;
import edu.utl.login.modelo.Servicio;
import edu.utl.login.modelo.Tratamiento;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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

}
