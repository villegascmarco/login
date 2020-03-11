/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.modelo;

import java.sql.Date;

/**
 *
 * @author marco
 */
public class Servicio {

    private int idServicio;
    private String fecha;
    private Reservacion reservacion;
    private Empleado empleado;
    private Tratamiento tratamientos[];

    public Servicio() {
    }

    public Servicio(int idServicio, String fecha, Reservacion reservacion, Empleado empleado, Tratamiento[] tratamientos) {
        this.idServicio = idServicio;
        this.fecha = fecha;
        this.reservacion = reservacion;
        this.empleado = empleado;
        this.tratamientos = tratamientos;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Reservacion getReservacion() {
        return reservacion;
    }

    public void setReservacion(Reservacion reservacion) {
        this.reservacion = reservacion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Tratamiento[] getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(Tratamiento[] tratamientos) {
        this.tratamientos = tratamientos;
    }

}
