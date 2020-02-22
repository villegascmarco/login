<%-- 
    Document   : miCuenta
    Created on : Feb 15, 2020, 4:56:23 PM
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
        <h2 class="display-4">Mi información</h2>

        <div class="d-flex justify-content-around formC">

            <form class="needs-validation" novalidate name="form" id="formulario">
                <div class="form-row">
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltip0">Primer nombre</label>
                        <input type="text" class="form-control" id="nombre" name="validationTooltip0" placeholder="Primer nombre"  required >
                        <div class="valid-tooltip">
                            Looks good!
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltip02">Apellido Paterno</label>
                        <input type="text" class="form-control" id="apellidoPaterno" placeholder="Apellido Paterno"  required>
                        <div class="valid-tooltip">
                            Looks good!
                        </div>
                    </div>               
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltip02">Apellido Materno</label>
                        <input type="text" class="form-control" id="apellidoMaterno" placeholder="Apellido Materno"  required>
                        <div class="valid-tooltip">
                            Looks good!
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltip02">Genero</label>
                        <select class="form-control" id="genero">
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
                        <input type="text" class="form-control" id="domicilio" placeholder="Domicilio"  required>
                        <div class="valid-tooltip">
                            Looks good!
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltip02">Teléfono</label>
                        <input type="text" class="form-control" id="telefono" placeholder="Teléfono"  required>
                        <div class="valid-tooltip">
                            Looks good!
                        </div>
                    </div>

                </div>
                <div class="form-row">
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltip02">RFC</label>
                        <input type="text" class="form-control" id="rfc" placeholder="RFC"  required>
                        <div class="valid-tooltip">
                            Looks good!
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltipUsername">Usuario</label>
                        <div class="input-group">
                            <div class="input-group-prepend user">
                                <span class="input-group-text user" id="validationTooltipUsernamePrepend">@</span>
                            </div>
                            <input type="text" class="form-control user" id="nombreUsuario" placeholder="Usuario" aria-describedby="validationTooltipUsernamePrepend" required>
                            <div class="invalid-tooltip">
                                Please choose a unique and valid username.
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltip04">Contraseña</label>
                        <input type="text" class="form-control" id="contrasenia" placeholder="Contraseña" required>
                        <div class="invalid-tooltip">
                            Please provide a valid state.
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <img src="..." alt="..."   id="imagen"  class="img-thumbnail " width="400px" height="400px">
                        <input type="text" class="form-control" id="rutaFoto" placeholder="URL foto"  required>
                    </div>
                    <input type="text" id="idPersona" value="" hidden> 
                    <input type="text" id="idEmpleado" value="" hidden> 
                    <input type="text" id="numeroEmpleado" value="" hidden> 
                    <input type="text" id="idUsuario" value="" hidden>  
                    <input type="text" id="token" value="" hidden> 
                    <input type="text" id="estatus" value="" hidden> 
                    <input type="text" id="puesto" value="" hidden> 
                    <input type="text" id="rol" value="" hidden> 

                </div>

                <div class=" d-flex justify-content-around ">

                    <button type="button" class="btn upD" onclick="eliminarMiCuenta()">Eliminar cuenta</button>
                    <button type="button" class="btn logO" onclick="modificarDatosE()">Actualizar Datos</button>
                    <button type="button" class="btn upD" onclick="cerrarSesion()">Cerrar Sesión</button>
                </div>
            </form>

        </div>

        <script src="js/Empleado.js">
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
