/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.controlador;

import com.google.gson.Gson;
import edu.utl.login.baseDatos.comandos.ComandoReservacion;
import edu.utl.login.modelo.Horario;
import edu.utl.login.modelo.Reservacion;
import edu.utl.login.modelo.Usuario;
import java.util.LinkedList;

/**
 *
 * @author marco
 */
public class ControladorReservacion {

    private Horario horarios = new Horario();
    private ComandoReservacion cmdReservacion = new ComandoReservacion();
    private Gson gson = new Gson();

    public String convertirAJson() {
        return horarios.convertirAJson();
    }

    public String consultarHorariosDisponibles(String fecha, int idSala) {
        if ("0".equals(fecha)) {
            return null;
        }
        return gson.toJson(cmdReservacion.consultarHorariosDisponibles(fecha, idSala));
    }

    public String agregarReservacion(String horarioInicio, String fecha, int idSala, int idCliente) {
        Reservacion reservacion = new Reservacion(horarioInicio, idCliente, idSala);

        reservacion = crearFecha(crearHorario(horarioInicio), reservacion, fecha);

        return cmdReservacion.crearReservacion(reservacion);
    }

    public Horario crearHorario(String horarioInicio) {
        Horario h = new Horario();
        LinkedList<Horario> lHorario = h.obtenerHorarios();

        for (Horario horario : lHorario) {
            if (horario.getHoraInicio().contains(horarioInicio)) {
                return horario;
            }
        }
        return null;
    }

    public Reservacion crearFecha(Horario horario, Reservacion reservacion, String fecha) {
        reservacion.setHoraInicio(horario.getHoraInicio());
        reservacion.setHoraFin(horario.getHoraFin());

        reservacion.setIdHorario(horario.getIdHorario());

        reservacion.setFechaHoraInicio(fecha + " " + horario.getHoraInicio());
        reservacion.setFechaHoraFin(fecha + " " + horario.getHoraFin());

        return reservacion;
    }

    public String eliminarReservación(int idReservacion, int idUsuario, String token) {
        ControladorUsuario ctrlUsuario = new ControladorUsuario();

        Usuario usuario = new Usuario(idUsuario, token);

        if (!ctrlUsuario.validarToken(usuario)) {
            return "Token inválido";
        }

        if (cmdReservacion.eliminarReservacion(idReservacion)) {
            return "Éxito";
        }

        return "Hubo un problema en la BD";

    }

}
