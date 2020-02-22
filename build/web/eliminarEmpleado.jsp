<%-- 
    Document   : eliminarEmpleados
    Created on : Feb 15, 2020, 6:11:11 PM
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
        <h3 class="display-4">Eliminar empledos</h3>

        <div class="d-flex justify-content-around formC">

            <form class="needs-validation" novalidate name="form" id="formulario1" style="width:75%; margin-left: auto; margin-right: auto;">

                <div class="col-md-4 mb-3" style="margin-left: auto; margin-right: auto;">
                    <label for="validationTooltip01">Id del empleado a eliminar</label>
                    <input type="text" class="form-control" id="idEmpleado2" name="validationTooltip01" placeholder="Id Empleado a eliminar"  required >
                    <div class="valid-tooltip">
                        Looks good!
                    </div>
                </div>




                <div class=" d-flex justify-content-around ">

                    <button type="button" class="btn upD" onclick="eliminarCuentas()" id="idEliminar">Borrar empleado</button>
                    <button type="reset" class="btn upD" >Cancelar</button>
                </div>
            </form>

        </div>
        <script src="js/Empleado.js">
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
