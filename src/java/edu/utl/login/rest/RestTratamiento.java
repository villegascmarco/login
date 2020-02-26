/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.rest;

import com.google.gson.Gson;
import edu.utl.login.controlador.ControladorProducto;
import edu.utl.login.controlador.ControladorTratamiento;
import java.util.Queue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author marco
 */
@Path("tratamiento")
public class RestTratamiento extends Application {

    @Path("listado")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response listado() {

        String json = "";

        ControladorTratamiento ctrlT = new ControladorTratamiento();
        Gson gson = new Gson();

        Queue tratamientos = ctrlT.listarTratamientos();

        json = gson.toJson(tratamientos);

        return Response.status(Response.Status.OK).entity(json).build();
    }
}
