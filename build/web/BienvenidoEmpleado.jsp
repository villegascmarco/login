<%-- 
    Document   : BienvenidoEmpleado
    Created on : Feb 15, 2020, 12:06:35 PM
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
        <jsp:include page="nabvar.jsp"></jsp:include>
            <div class="formM">
            <jsp:include page="miCuenta.jsp"></jsp:include>            
            </div>
            <div class="formM">
            <jsp:include page="crearEmpleado.jsp"></jsp:include>            
            </div>
            <div id="eliminarEmpleado">
            <jsp:include page="eliminarEmpleado.jsp"></jsp:include>            
            </div>
            <div>
            <jsp:include page="listarEmpleados.jsp"></jsp:include>            
        </div>




        <script src="js/Empleado.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </body>
</html>
