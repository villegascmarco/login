<%-- 
    Document   : Bienvenido
    Created on : Jan 28, 2020, 2:17:06 PM
    Author     : marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="nabvar.jsp"></jsp:include>

        <div class="d-flex justify-content-around">

            <form class="needs-validation" novalidate name="form" id="formulario">
                <div class="form-row">
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltip01">Primer nombre</label>
                        <input type="text" class="form-control" id="nombre" name="validationTooltip01" placeholder="Primer nombre"  required >
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
                        <label for="validationTooltip02">Correo Electrónico</label>
                        <input type="text" class="form-control" id="correo" placeholder="Correo Electrónico"  required>
                        <div class="valid-tooltip">
                            Looks good!
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="validationTooltipUsername">Usuario</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="validationTooltipUsernamePrepend">@</span>
                            </div>
                            <input type="text" class="form-control" id="nombreUsuario" placeholder="Usuario" aria-describedby="validationTooltipUsernamePrepend" required>
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
                    <input type="text" id="idPersona" value="" hidden> 
                    <input type="text" id="idCliente" value="" hidden> 
                    <input type="text" id="numeroUnico" value="" hidden> 
                    <input type="text" id="idUsuario" value="" hidden> 
                    <input type="text" id="rol" value="" hidden> 
                    <input type="text" id="token" value="" hidden> 
                    <input type="text" id="estatus" value="" hidden> 

                </div>

                <div class=" d-flex justify-content-around ">

                    <button type="button" class="btn btn-dark" onclick="eliminarCuenta()">Eliminar cuenta</button>
                    <button type="button" class="btn btn-light" onclick="modificarDatos()">Actualizar Datos</button>
                    <button type="button" class="btn btn-dark" onclick="cerrarSesion()">Cerrar Sesión</button>
                </div>
            </form>

        </div>



        <script src="js/Cliente.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
