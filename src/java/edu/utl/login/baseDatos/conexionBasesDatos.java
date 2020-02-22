/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.baseDatos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class conexionBasesDatos {

    private Connection conn;

    public conexionBasesDatos() {
        try {

            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Conectar() {
        try {
            String usuario = "root";
            String contrasenia = "root";
            //String ubicacion = "jdbc:mysql://127.0.0.1:3306/myspa?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String ubicacion = "jdbc:mysql://127.0.0.1:3306/myspa?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

            //conn=DriverManager.getConnection(ubicacion, usuario, contrasenia);
            conn = DriverManager.getConnection(ubicacion, usuario, contrasenia);
        } catch (SQLException ex) {
            Logger.getLogger(conexionBasesDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(conexionBasesDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConexión() {
        return conn;
    }

    public void setAutoCommit(boolean estado) {
        try {
            conn.setAutoCommit(estado);
        } catch (SQLException ex) {
            Logger.getLogger(conexionBasesDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void commit() {
        try {
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(conexionBasesDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rollback() {
        try {
            conn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(conexionBasesDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
