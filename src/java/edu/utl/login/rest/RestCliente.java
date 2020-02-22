/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.rest;

import com.google.gson.Gson;
import edu.utl.login.controlador.Controlador;
import edu.utl.login.controlador.ControladorCliente;
import edu.utl.login.modelo.Usuario;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author marco
 */
@Path("cliente")
public class RestCliente extends Application {

    private ControladorCliente ctrl = new ControladorCliente();

    @Path("modificarDatos")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response modificarDatos(
            @FormParam("idPersona")
            @DefaultValue("0") String idPersona,
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
            @FormParam("idCliente")
            @DefaultValue("0") String idCliente,
            @FormParam("numeroUnico")
            @DefaultValue("0") String numeroUnico,
            @FormParam("correo")
            @DefaultValue("0") String correo,
            @FormParam("estatus")
            @DefaultValue("0") String estatus,
            @FormParam("idUsuario")
            @DefaultValue("0") String idUsuario,
            @FormParam("nombreUsuario")
            @DefaultValue("0") String nombreUsuario,
            @FormParam("contrasenia")
            @DefaultValue("0") String contrasenia,
            @FormParam("rol")
            @DefaultValue("0") String rol,
            @FormParam("token")
            @DefaultValue("0") String token
    ) {
        Usuario u;
        edu.utl.login.modelo.Cliente c;
        Gson gson;
        String json;

        u = new Usuario(
                Integer.parseInt(idUsuario),
                nombreUsuario,
                contrasenia,
                rol,
                token);
        c = new edu.utl.login.modelo.Cliente(
                Integer.parseInt(idCliente),
                numeroUnico,
                correo,
                Integer.parseInt(estatus),
                u,
                Integer.parseInt(idPersona),
                nombre,
                apellidoPaterno,
                apellidoMaterno,
                genero,
                domicilio,
                telefono,
                rfc);

        c = ctrl.modificarDatos(c);

        gson = new Gson();
        json = gson.toJson(c);

        return Response.status(Response.Status.OK).entity(json).build();
    }

    @Path("cerrarSesion")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response cerrarSesion(
            @FormParam("idUsuario")
            @DefaultValue("0") String idUsuario,
            @FormParam("token")
            @DefaultValue("0") String token
    ) {
        Usuario u;

        Gson gson;
        String json;
        boolean respuesta;

        u = new Usuario(Integer.parseInt(idUsuario), token);

        respuesta = ctrl.cerrarSesion(u);

        gson = new Gson();
        json = gson.toJson(respuesta);

        return Response.status(Response.Status.OK).entity(json).build();
    }

    @Path("eliminarCuenta")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response eliminarCuenta(
            @FormParam("idUsuario")
            @DefaultValue("0") String idUsuario,
            @FormParam("token")
            @DefaultValue("0") String token
    ) {
        Usuario u;

        Gson gson;
        String json;
        boolean respuesta;

        u = new Usuario(Integer.parseInt(idUsuario), token);

        respuesta = ctrl.eliminarCuenta(u);

        gson = new Gson();
        json = gson.toJson(respuesta);

        return Response.status(Response.Status.OK).entity(json).build();
    }

    @Path("crear")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response crear(
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
            @FormParam("numeroUnico")
            @DefaultValue("0") String numeroUnico,
            @FormParam("correo")
            @DefaultValue("0") String correo,
            @FormParam("nombreUsuario")
            @DefaultValue("0") String nombreUsuario,
            @FormParam("contrasenia")
            @DefaultValue("0") String contrasenia,
            @FormParam("token")
            @DefaultValue("0") String token
    ) {
        Usuario u;
        edu.utl.login.modelo.Cliente c;
        Gson gson;
        String json;

        u = new Usuario(
                0,
                nombreUsuario,
                contrasenia,
                "Cliente",
                token);
        c = new edu.utl.login.modelo.Cliente(
                0,
                numeroUnico,
                correo,
                1,
                u,
                0,
                nombre,
                apellidoPaterno,
                apellidoMaterno,
                genero,
                domicilio,
                telefono,
                rfc);

        gson = new Gson();
        boolean respuesta = !ctrl.crearCuenta(c);
        if (respuesta) {
            json = gson.toJson(null);
        } else {
            json = gson.toJson(respuesta);

        }

        return Response.status(Response.Status.OK).entity(json).build();
    }

    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response test(
            @QueryParam("usuario")
            @DefaultValue("0") String usuario,
            @QueryParam("contrasenia")
            @DefaultValue("0") String contrasenia
    ) {
        Usuario u;
        edu.utl.login.modelo.Cliente c;
        Gson gson;
        String json;

        gson = new Gson();
        json = gson.toJson(usuario);

        return Response.status(Response.Status.OK).entity(json).build();
    }
}
