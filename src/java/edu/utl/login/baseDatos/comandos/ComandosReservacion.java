/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.baseDatos.comandos;

import com.google.gson.Gson;
import edu.utl.login.baseDatos.conexionBasesDatos;
import edu.utl.login.modelo.Horario;
import edu.utl.login.modelo.Reservacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author marco
 */
public class ComandosReservacion {

    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;
    conexionBasesDatos conn = new conexionBasesDatos();
    String query;

    public LinkedList<Horario> consultarHorariosDisponibles(String fecha, int idSala) {
        Horario hora = new Horario();

        LinkedList<Horario> h = hora.obtenerHorarios();
        String fechaHoraInicio;

        query = "SELECT"
                + " *"
                + " FROM"
                + " v_horasReservadas"
                + " WHERE"
                + " fechaHoraInicio LIKE ?"
                + " AND estatus = 1"
                + " AND idSala = ?";

        try {
            conn.Conectar();

            ps = conn.getConexión().prepareStatement(query);

            ps.setString(1, fecha + "%");
            ps.setInt(2, idSala);

            rs = ps.executeQuery();
            /**
             * Esto no es lo correcto, es mejor hacer una consulta que ya me de
             * los horarios filtrados
             */
            while (rs.next()) {
                fechaHoraInicio = rs.getString("fechaHoraInicio");
                try {
                    for (Horario horario : h) {
                        if (fechaHoraInicio.contains(horario.getHoraInicio())) {
                            h.remove(horario);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Excepcion controlada" + e);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            h = null;
        } finally {
            conn.Desconectar();
        }

        return h;
    }

    public String listarReservaciones() {
        try {
            Reservacion[] reservaciones = null;
            Gson gson = new Gson();
            int renglones = 0;
            int con = 0;
            conn.Conectar();
            query = "SELECT  * FROM v_reservacion";
            ps = conn.getConexión().prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.last()) {
                renglones = rs.getRow();
                reservaciones = new Reservacion[renglones];
                rs.beforeFirst();
                while (rs.next()) {
                    Reservacion r = new Reservacion();
                    r.setIdReservacion(rs.getInt("idReservacion"));
                    r.setFechaHoraInicio(recortarFecha(rs.getString("fechaHoraInicio")));
                    r.setEstatus(rs.getInt("estatus"));
                    r.setHoraInicio(rs.getString("horaInicio"));
                    r.setHoraFin(rs.getString("horaFin"));
                    r.setCliente(rs.getString("Cliente"));
                    r.setSucursal(rs.getString("Sala"));
                    reservaciones[con] = r;
                    con++;
                }
            }

            conn.Desconectar();

            System.out.println(reservaciones);
            return gson.toJson(reservaciones);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String recortarFecha(String fecha) {
        char[] fechaC = fecha.toCharArray();
        String fechaF = "";
        for (int i = 0; i < 10; i++) {
            fechaF += fechaC[i];
        }

        return fechaF;
    }

    public String crearReservacion(Reservacion reservacion) {

        query = "call crearReservacion( ?, ?, ?, ?)";

        try {
            conn.Conectar();

            ps = conn.getConexión().prepareCall(query);

            ps.setString(1, reservacion.getFechaHoraInicio());
            ps.setString(2, reservacion.getFechaHoraFin());
            ps.setInt(3, reservacion.getIdCliente());
            ps.setInt(4, reservacion.getIdSala());

            ps.executeQuery();

        } catch (Exception e) {
            return "Algo falló " + e;
        } finally {
            conn.Desconectar();
        }
        return "Exito";
    }
}
