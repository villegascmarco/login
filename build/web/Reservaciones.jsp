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
        <title>Reservaciones</title>
        <link rel="stylesheet" href="css/Style.css">

    </head>
    <body onload="listarReservaciones()">
        <jsp:include page="nabvar.jsp"></jsp:include>
            <div id="titulo"><h2 class="display-4">Reservaciones:</h2></div>


            <div class="container">
                <div class="row">
                    <div class="col-xl-12" id="resInfo">
                        <table id="table" class="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">Fecha de inicio</th>
                                    <th scope="col">Hora Inicio</th>
                                    <th scope="col">Hora Fin</th>
                                    <th scope="col">Cliente</th>
                                    <th scope="col">Sala</th>
                                    <th scope="col"></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>


                </div>
            </div>

            <div class="modal fade modal-xl modal-dialog-scrollable" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <jsp:include page="datosReservacion.jsp"></jsp:include>
        </div>

        <script src="js/reservaciones.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </body>
</html>
