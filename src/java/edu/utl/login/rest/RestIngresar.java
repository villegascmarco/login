/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.login.rest;

import com.google.gson.Gson;
import edu.utl.login.controlador.Controlador;
import edu.utl.login.modelo.Cliente;
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
@Path("ingresar")
public class RestIngresar extends Application {

    @Path("ingresar")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response ingresar(
            @FormParam("usuario") @DefaultValue("0") String usuario,
            @FormParam("contrasenia") @DefaultValue("0") String contrasenia
    ) {
        Usuario u;
        Cliente c;
        Gson gson;
        String json;
        Controlador ctrl = new Controlador();

        u = new Usuario(usuario, contrasenia);

        json = ctrl.inicarSesion(u);

        return Response.status(Response.Status.OK).entity(json).build();
    }

}
