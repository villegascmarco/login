<%-- 
    Document   : reservaciones
    Created on : 25/02/2020, 09:29:28 AM
    Author     : pollo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="nabvar.jsp"></jsp:include>
        <h1>Hello Cola!</h1>

        <table id="table" class="table table-hover" width="450px" style="width: 85%; margin-left: auto; margin-right: auto;">
            <thead>
                <tr>
                    <th scope="col">ID Reservación</th>
                    <th scope="col">Fecha de inicio</th>
                    <th scope="col">Fecha de fin</th>
                    <th scope="col">Estatus</th>
                    <th scope="col">Cliente</th>
                    <th scope="col">Sala</th>
                </tr>
            </thead>
            <tbody>

            </tbody>
        </table>

        <script src="js/reservaciones.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </body>
</html>
