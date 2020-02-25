/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.rest;

import com.google.gson.Gson;
import edu.utl.login.controlador.ControladorEmpleado;
import edu.utl.login.controlador.Controlador;
import edu.utl.login.modelo.Empleado;
import edu.utl.login.modelo.Persona;
import edu.utl.login.modelo.Usuario;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author marco
 */
@Path("empleado")
public class RestEmpleado extends Application {

    @Path("crear")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response insertar(
            @FormParam("nombre")
            @DefaultValue("0") String nombre,
            @FormParam("apellidoPaterno")
            @DefaultValue("0") String apellidoPaterno,
            @FormParam("apellidoMaterno")
            @DefaultValue("0") String apellidoMaterno,
            @FormParam("genero")
            @DefaultValue("0") String genero,
            @FormParam("domicilio")
            @DefaultValue("0") String domicilio,
            @FormParam("telefono")
            @DefaultValue("0") String telefono,
            @FormParam("rfc")
            @DefaultValue("0") String rfc,
            @FormParam("puesto")
            @DefaultValue("0") String puesto,
            @FormParam("rutaFoto")
            @DefaultValue("0") String rutaFoto,
            @FormParam("nombreUsuario")
            @DefaultValue("0") String nombreUsuario,
            @FormParam("contrasenia")
            @DefaultValue("0") String contrasenia,
            @FormParam("rol")
            @DefaultValue("0") String rol,
            @FormParam("idUsuarioAdmin")
            @DefaultValue("0") String idUsuarioAdmin,
            @FormParam("tokenAdmin")
            @DefaultValue("0") String tokenAdmin
    ) {
        Controlador cmd = new Controlador();
        ControladorEmpleado cmdE = new ControladorEmpleado();
        Gson gson = new Gson();

        Usuario u = new Usuario(
                Integer.parseInt(idUsuarioAdmin),
                nombreUsuario,
                contrasenia,
                rol,
                tokenAdmin);
        Persona p = new Persona(
                cmd.limpiarDato(nombre),
                cmd.limpiarDato(apellidoPaterno),
                cmd.limpiarDato(apellidoMaterno),
                genero,
                cmd.limpiarDato(domicilio),
                cmd.limpiarDato(telefono),
                cmd.limpiarDato(rfc));
        Empleado e = new Empleado(
                p,
                u,
                0,
                "",
                puesto,
                0,
                rutaFoto);

        String respuesta = "" + cmdE.insertar(e);

        return Response.status(Response.Status.OK).entity(respuesta).build();

    }

    @Path("eliminarCuenta")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response eliminarCuenta(
            @FormParam("idEmpleado")
            @DefaultValue("0") String idEmpleado,
            @FormParam("token")
            @DefaultValue("0") String tokenAdmin,
            @FormParam("idUsuario")
            @DefaultValue("0") String idUsuarioAdmin
    ) {
        Controlador cmd = new Controlador();
        ControladorEmpleado cmdE = new ControladorEmpleado();
        Gson gson = new Gson();

        String respuesta = "" + cmdE.eliminarCuenta(idEmpleado, tokenAdmin, idUsuarioAdmin);

        return Response.status(Response.Status.OK).entity(respuesta).build();

    }

    @Path("listado")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response listarEmpleados(@FormParam("tokenAdmin")
            @DefaultValue("0") String tokenAdmin) {

        Controlador cmd = new Controlador();
        ControladorEmpleado cmdE = new ControladorEmpleado();
        Gson gson = new Gson();

        Usuario u = new Usuario(0, tokenAdmin);
        String e = cmdE.listarEmpleados(u);
        System.out.println(e);
        return Response.status(Response.Status.OK).entity(e).build();

    }

    @Path("actualizarCuenta")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response actualizarCuenta(
            @FormParam("nombre")
            @DefaultValue("0") String nombre,
            @FormParam("apellidoPaterno")
            @DefaultValue("0") String apellidoPaterno,
            @FormParam("apellidoMaterno")
            @DefaultValue("0") String apellidoMaterno,
            @FormParam("genero")
            @DefaultValue("0") String genero,
            @FormParam("domicilio")
            @DefaultValue("0") String domicilio,
            @FormParam("telefono")
            @DefaultValue("0") String telefono,
            @FormParam("rfc")
            @DefaultValue("0") String rfc,
            @FormParam("puesto")
            @DefaultValue("0") String puesto,
            @FormParam("foto")
            @DefaultValue("0") String foto,
            @FormParam("nombreUsuario")
            @DefaultValue("0") String nombreUsuario,
            @FormParam("contrasenia")
            @DefaultValue("0") String contrasenia,
            @FormParam("rol")
            @DefaultValue("0") String rol,
            @FormParam("idUsuario")
            @DefaultValue("0") String idUsuario,
            @FormParam("idEmpleado")
            @DefaultValue("0") String idEmpleado,
            @FormParam("idPersona")
            @DefaultValue("0") String idPersona,
            @FormParam("numeroEmpleado")
            @DefaultValue("0") String numeroEmpleado,
            @FormParam("tokenAdmin")
            @DefaultValue("0") String tokenAdmin
    ) {

        Controlador cmd = new Controlador();
        ControladorEmpleado cmdE = new ControladorEmpleado();
        Gson gson = new Gson();

        Usuario u = new Usuario(
                Integer.parseInt(idUsuario),
                nombreUsuario,
                contrasenia,
                rol,
                tokenAdmin);
        Persona p = new Persona(
                Integer.parseInt(idPersona),
                cmd.limpiarDato(nombre),
                cmd.limpiarDato(apellidoPaterno),
                cmd.limpiarDato(apellidoMaterno),
                genero,
                cmd.limpiarDato(domicilio),
                telefono,
                rfc);
        Empleado e = new Empleado(
                p,
                u,
                Integer.parseInt(idEmpleado),
                numeroEmpleado,
                puesto,
                0,
                foto);

        boolean respuesta = cmdE.actualizarCuenta(e);

        String res = String.valueOf(respuesta);

        return Response.status(Response.Status.OK).entity(res).build();

    }

}
