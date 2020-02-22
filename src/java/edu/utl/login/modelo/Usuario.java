/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.modelo;

import java.sql.Timestamp;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author marco
 */
public class Usuario {

    private int idUsuario;
    private String nombreUsuario;
    private String contrasenia;
    private String rol;
    private String token;

    public Usuario() {
    }

    public Usuario(int idUsuario, String token) {
        this.idUsuario = idUsuario;
        this.token = token;
    }

    public Usuario(String nombreUsuario, String contrasenia, String rol) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public Usuario(int idUsuario, String nombreUsuario, String contrasenia, String rol) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public Usuario(int idUsuario, String nombreUsuario, String contrasenia, String rol, String token) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.token = token;
    }

    public Usuario(String nombreUsuario, String contrasenia) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public void setToken() {

        Timestamp key;
        String usuario;
        String contra;

        key = new Timestamp(System.currentTimeMillis());

        usuario = this.getNombreUsuario();

        contra = this.getContrasenia();

        this.token = (DigestUtils.sha256Hex(
                usuario + ";" + contra + ";" + key));

    }

    public void cerrarSesion() {
        this.token = "";
    }

}
