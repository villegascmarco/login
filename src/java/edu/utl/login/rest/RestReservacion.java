/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.rest;

import edu.utl.login.controlador.ControladorReservacion;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
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
@Path("reservacion")
public class RestReservacion extends Application {

    @Path("agregar")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response agregar(
            @FormParam("horarioInicio")
            @DefaultValue("0") String horarioInicio,
            @FormParam("fecha")
            @DefaultValue("0") String fecha,
            @FormParam("idSala")
            @DefaultValue("0") int idSala,
            @FormParam("idSucursal")
            @DefaultValue("0") int idSucursal,
            @FormParam("idCliente")
            @DefaultValue("0") int idCliente,
            @FormParam("tokenCliente")
            @DefaultValue("0") String tokenCliente) {
        String json;

        ControladorReservacion ctrlR = new ControladorReservacion();

        json = ctrlR.agregarReservacion(horarioInicio, fecha, idSala, idCliente);

        return Response.status(Response.Status.OK).entity(json).build();
    }

    @Path("eliminar")
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    public Response eliminar(
            @QueryParam("idReservación")
            @DefaultValue("0") int idReservación,
            @QueryParam("idUsuario")
            @DefaultValue("0") int idUsuario,
            @QueryParam("token")
            @DefaultValue("0") String token) {
        ControladorReservacion ctrlReservacion = new ControladorReservacion();

        String json = ctrlReservacion.eliminarReservación(idReservación, idUsuario, token);

        return Response.status(Response.Status.OK).entity(json).build();
    }

    @Path("consultarHorariosDisponibles")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response consultarHorariosDisponibles(
            @FormParam("fecha")
            @DefaultValue("0") String fecha,
            @FormParam("idSala")
            @DefaultValue("0") int idSala) {
        String json = null;

        ControladorReservacion ctrlR = new ControladorReservacion();

        json = ctrlR.consultarHorariosDisponibles(fecha, idSala);

        return Response.status(Response.Status.OK).entity(json).build();
    }
}
