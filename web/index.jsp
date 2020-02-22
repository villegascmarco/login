<%-- 
    Document   : index
    Created on : Feb 1, 2020, 6:49:36 PM
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
        <link rel="stylesheet" href="css/Estilos.css">

    </head>
    <body>

        <jsp:include page="nabvar.jsp"></jsp:include>

        <div class="wrapper fadeInDown">
            <div id="formContent">

                <div class="fadeIn first">
                    <img src="src/icono.png" id="icon" alt="User Icon" />
                </div>

                <form method="post" action="Bienvenido.jsp">
                    <input type="text" id="txtUsuario" class="fadeIn second txtBox" name="login" placeholder="Usuario">
                    <input type="text" id="txtContra" class="fadeIn third txtBox" name="login" placeholder="Contraseña">
                    <button type="button" class="fadeIn fourth" onclick="usarToken()" id="boton">Entrar</button>
                </form>

                <div id="formFooter">
                    <a class="underlineHover" href="crearCuenta.jsp">Crear cuenta</a>
                </div>

            </div>
        </div>




        <script src="js/Token.js">
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
