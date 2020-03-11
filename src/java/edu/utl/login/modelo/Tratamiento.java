/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.modelo;

/**
 *
 * @author marco
 */
public class Tratamiento {

    private int idTratamiento;
    private String nombre;
    private String descripcion;
    private double costo;
    private int estatus;
    private Producto productos[];

    public Tratamiento() {
    }

    public Tratamiento(int idTratamiento, String nombre, String descripcion, double costo, int estatus) {
        this.idTratamiento = idTratamiento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.estatus = estatus;
    }

    public Tratamiento(int idTratamiento, String nombre, String descripcion, double costo, int estatus, Producto[] productos) {
        this.idTratamiento = idTratamiento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.estatus = estatus;
        this.productos = productos;
    }

    public int getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public Producto[] getProductos() {
        return productos;
    }

    public void setProductos(Producto[] productos) {
        this.productos = productos;
    }

}
