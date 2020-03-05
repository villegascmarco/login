<%-- 
    Document   : crearEmpleado
    Created on : Feb 15, 2020, 3:21:12 PM
    Author     : marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/Style.css">

    </head>
    <body>
        <h2 class="display-4">Agregar nuevos empleados</h2>

        <div class="d-flex justify-content-around formC">

            <form class="needs-validation" novalidate name="form" id="formulario1">
                <div class="form-row">
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltip01">Primer nombre</label>
                        <input type="text" class="form-control" id="nombre1" name="validationTooltip01" placeholder="Primer nombre"  required >
                        <div class="valid-tooltip">
                            Looks good!
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltip02">Apellido Paterno</label>
                        <input type="text" class="form-control" id="apellidoPaterno1" placeholder="Apellido Paterno"  required>
                        <div class="valid-tooltip">
                            Looks good!
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltip02">Apellido Materno</label>
                        <input type="text" class="form-control" id="apellidoMaterno1" placeholder="Apellido Materno"  required>
                        <div class="valid-tooltip">
                            Looks good!
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltip02">Genero</label>
                        <select class="form-control" id="genero1">
                            <option value="M">Hombre</option>
                            <option value="F">Mujer</option>
                            <option value="O">Otro</option>                            
                        </select> 
                        <div class="valid-tooltip">
                            Looks good!
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltip02">Domicilio</label>
                        <input type="text" class="form-control" id="domicilio1" placeholder="Domicilio"  required>
                        <div class="valid-tooltip">
                            Looks good!
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltip02">Teléfono</label>
                        <input type="text" class="form-control" id="telefono1" placeholder="Teléfono"  required>
                        <div class="valid-tooltip">
                            Looks good!
                        </div>
                    </div>

                </div>
                <div class="form-row">
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltip02">RFC</label>
                        <input type="text" class="form-control" id="rfc1" placeholder="RFC"  required>
                        <div class="valid-tooltip">
                            Looks good!
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltip02">Rol</label>
                        <select class="form-control" id="rol1">
                            <option value="Administrador">Administrador</option>
                            <option value="Gerente">Gerente</option>
                            <option value="Recepcionista">Recepcionista</option>                            
                            <option value="Tecnico">Tecnico</option>                            
                        </select> 
                        <div class="valid-tooltip">
                            Looks good!
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltip02">Puesto</label>
                        <select class="form-control" id="puesto1">
                            <option value="Administrador">Administrador</option>
                            <option value="Gerente">Gerente</option>
                            <option value="Recepcionista">Recepcionista</option>                            
                            <option value="Tecnico">Tecnico</option>                            
                        </select> 
                        <div class="valid-tooltip">
                            Looks good!
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltipUsername">Usuario</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text user" id="validationTooltipUsernamePrepend1">@</span>
                            </div>
                            <input type="text" class="form-control user" id="nombreUsuario1" placeholder="Usuario" aria-describedby="validationTooltipUsernamePrepend" required>
                            <div class="invalid-tooltip">
                                Please choose a unique and valid username.
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltip04">Contraseña</label>
                        <input type="text" class="form-control" id="contrasenia1" placeholder="Contraseña" required>
                        <div class="invalid-tooltip">
                            Please provide a valid state.
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltip04">Foto</label>
                        <input type="text" class="form-control" id="rutaFoto1" placeholder="Ruta foto" required>
                        <div class="invalid-tooltip">
                            Please provide a valid state.
                        </div>
                    </div>
                    <input type="text" id="idPersona1" value="" hidden> 
                    <input type="text" id="idEmpleado1" value="" hidden> 
                    <input type="text" id="numeroEmpleado1" value="" hidden> 
                    <input type="text" id="idUsuario1" value="" hidden>  
                    <input type="text" id="token1" value="" hidden> 
                    <input type="text" id="estatus1" value="" hidden> 

                </div>

                <div class=" d-flex justify-content-around ">

                    <button type="button" class="btn btn-outline-primary" onclick="guardarEmpleado()">Guardar empleado</button>
                    <button type="reset" class="btn btn-outline-danger" >Cancelar</button>
                </div>
            </form>

        </div>
        <script src="js/Empleado.js">
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </body>
</html>
