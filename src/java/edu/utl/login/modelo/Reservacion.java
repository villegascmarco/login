/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.modelo;

/**
 *
 * @author pollo
 */
public class Reservacion {

    private int idReservacion;
    private String fechaHoraInicio;
    private String fechaHoraFin;
    private String horaInicio;
    private String horaFin;
    private int estatus;
    private String cliente;
    private String sucursal;

    public Reservacion() {

    }

    public Reservacion(int idReservacion, String fechaHoraInicio, String fechaHoraFin, String horaInicio, String horaFin, int estatus, String cliente, String sucursal) {
        this.idReservacion = idReservacion;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estatus = estatus;
        this.cliente = cliente;
        this.sucursal = sucursal;
    }

    public Reservacion(int idReservacion, String fechaHoraInicio, String horaInicio, String horaFin, int estatus, String cliente, String sucursal) {
        this.idReservacion = idReservacion;
        this.fechaHoraInicio = fechaHoraInicio;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estatus = estatus;
        this.cliente = cliente;
        this.sucursal = sucursal;
    }

    
    
    public int getIdReservacion() {
        return idReservacion;
    }

    public String getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public String getFechaHoraFin() {
        return fechaHoraFin;
    }

    public int getEstatus() {
        return estatus;
    }

    public String getCliente() {
        return cliente;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setIdReservacion(int idReservacion) {
        this.idReservacion = idReservacion;
    }

    public void setFechaHoraInicio(String fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public void setFechaHoraFin(String fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getHoraInicio() {
        return horaInicio;
    }       
    
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }
                    
    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }    
    
}
