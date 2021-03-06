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
public class Producto {

    private int idProducto;
    private String nombre;
    private String marca;
    private int status;
    private double precioUso;

    public Producto() {
    }

    public Producto(int idProducto, String nombre, String marca, int status, double precioUso) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.status = status;
        this.precioUso = precioUso;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecioUso() {
        return precioUso;
    }

    public void setPrecioUso(double precioUso) {
        this.precioUso = precioUso;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
