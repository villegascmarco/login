/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.modelo;

import com.google.gson.Gson;
import java.util.LinkedList;

/**
 *
 * @author marco
 */
public class Horario {

    private int idHorario;
    private String horaInicio;
    private String horaFin;

    public Horario() {
    }

    private Horario(int idHorario, String horaInicio, String horaFin) {
        this.idHorario = idHorario;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public LinkedList<Horario> obtenerHorarios() {

        LinkedList<Horario> horarios = new LinkedList<>();
        
        horarios.add(new Horario(1, "9:00:00", "9:30:00"));
        horarios.add(new Horario(2, "9:30:00", "10:00:00"));
        horarios.add(new Horario(3, "10:00:00", "10:30:00"));
        horarios.add(new Horario(4, "10:30:00", "11:00:00"));
        horarios.add(new Horario(5, "11:00:00", "11:30:00"));
        horarios.add(new Horario(6, "11:30:00", "12:00:00"));
        horarios.add(new Horario(7, "12:00:00", "12:30:00"));
        horarios.add(new Horario(8, "12:30:00", "13:00:00"));
        horarios.add(new Horario(9, "13:00:00", "13:30:00"));
        horarios.add(new Horario(10, "13:30:00", "14:00:00"));

        return horarios;
    }

    public String convertirAJson() {
        Gson gson = new Gson();

        return gson.toJson(obtenerHorarios());
    }

}
