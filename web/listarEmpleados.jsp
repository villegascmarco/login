<%-- 
    Document   : listarEmpleados
    Created on : 17/02/2020, 11:42:45 AM
    Author     : pollo
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
        <h3 class="display-4">Listado de Empleados</h3>

        <button type="button" class="btn btn-outline-primary" onclick="listarEmpleados()" style="margin-left: 50%; margin-right: auto; margin-bottom: 5%;">Listar</button>

        


        <table id="table" class="table table-hover" width="450px" style="width: 85%; margin-left: auto; margin-right: auto;">
            <thead>
                <tr>
                    <th scope="col">ID usuario</th>
                    <th scope="col">Nombre de usuario</th>
                    <th scope="col">ID empleado</th>
                    <th scope="col">Numero empleado</th>
                    <th scope="col">Puesto</th>
                    <th scope="col">Estatus</th>
                </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
        <script src="js/Empleado.js">
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
