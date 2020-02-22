package edu.utl.login.modelo;

/**
 *
 * @author marco
 */
public class Empleado {

    private Persona persona;
    private Usuario usuario;
    private int idEmpleado;
    private String numeroEmpleado;
    private String puesto;
    private int estatus;
    private String rutaFoto;

    public Empleado() {
    }

    public Empleado(Persona persona, Usuario usuario, int idEmpleado, String numeroEmpleado, String puesto, int estatus, String rutaFoto) {
        this.persona = persona;
        this.usuario = usuario;
        this.idEmpleado = idEmpleado;
        this.numeroEmpleado = numeroEmpleado;
        this.puesto = puesto;
        this.estatus = estatus;
        this.rutaFoto = rutaFoto;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

}