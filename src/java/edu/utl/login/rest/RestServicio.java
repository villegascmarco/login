/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.rest;

import com.google.gson.Gson;
import edu.utl.login.controlador.ControladorServicio;
import edu.utl.login.controlador.ControladorUsuario;
import edu.utl.login.modelo.Servicio;
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
@Path("servicio")
public class RestServicio extends Application {

    @Path("agregar")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response agregar(
            @FormParam("servicio")
            @DefaultValue("0") String servicio) {
        ControladorServicio ctrlServicio = new ControladorServicio();
        String json = null;
        Gson gson = new Gson();
        try {

            Servicio servicio1 = gson.fromJson(servicio, Servicio.class);

            json = ctrlServicio.agregarTratamiento(servicio1);
        } catch (Exception e) {
            json = null;
            json = gson.toJson(json);
        } finally {

            return Response.status(Response.Status.OK).entity(json).build();
        }

    }

    @Path("listado")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response listado(
            @QueryParam("idUsuario")
            @DefaultValue("0") int idUsuario,
            @QueryParam("token")
            @DefaultValue("0") String token) {
        Usuario usuario = new Usuario(idUsuario, token);
        ControladorServicio ctrlServicio = new ControladorServicio();

        String json = ctrlServicio.listado(usuario);
        return Response.status(Response.Status.OK).entity(json).build();
    }
}
