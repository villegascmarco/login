<%-- 
    Document   : datosReservacion
    Created on : 28/02/2020, 12:49:59 PM
    Author     : pollo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/Style.css">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    </head>
    <body>
        <div class="modal-dialog modal-xl modal-centered modal-dialog-scrollable" role="document">
            <div class="modal-content modal-xl modal-centered">
                <div class="modal-header">
                    <h1 class="modal-title" id="exampleModalCenterTitle">Detalles reservación</h1>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="limpiarTabla()">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="container">
                        <div class="row">

                            <div class="col-sm-2" id="resDetail">

                                <p>Fecha:</p>
                                <input type="text" id="fechaR" disabled class="modalF"/>
                                <p>Hora de inicio:</p>
                                <input type="text" id="horaInicio" disabled class="modalF"/>
                                <p>Hora de fin:</p>
                                <input type="text" id="horaFin" disabled class="modalF"/>
                                <p>Cliente:</p>
                                <input type="text" id="nombreCliente" disabled class="modalF"/>
                                <p>Sala:</p>
                                <input type="text" id="salaR" disabled class="modalF"/>
                            </div>

                            <div class="col-sm-4" id="resDetail">
                                <p>Empleado que atendió:</p>
                                <select id="cmbEmpleados" class="form-control">                                                              
                                </select>

                                <p>Tratamientos: <button type="button" class="btn" id="insertarTratamiento" onclick=" listarTratamientosR()"><i id="iconoPlus" class="fa fa-plus"></i></button> </p>
                                <select id="cmbTratamientos" style="width:auto" class="form-control">                                                              
                                </select>
                                <br>
                                <div class="scrollable">
                                    <table id="tableT" class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th scope="col">Nombre</th>
                                                <th scope="col">Costo</th>                                   
                                                <th scope="col"> </th>                                   
                                            </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>

                            </div>

                            <div class="col-sm-5" id="resProductos">
                                <p>Productos: </p>
                                <div class="input-group">
                                    <select id="cmbProductos" class="form-control">                                    
                                    </select>
                                    <button type='button' class='btn' id='insertarProducto' onclick='agregarProductos()'><i id='iconoPlus' class='fa fa-plus'></i></button>
                                </div>

                                <br>
                                <div class="scrollable2">
                                    <table id="tableP" class="table table-hover" style="width:350px">
                                        <thead>
                                            <tr>
                                                <th scope="col">Nombre</th>
                                                <th scope="col">Costo</th>                                   
                                                <th scope="col"> </th>                                   
                                            </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-danger" data-dismiss="modal" onclick="limpiarTabla()"> Cancelar</button>
                    <button type="button" class="btn btn-outline-primary">Guardar</button>
                </div>
            </div>
        </div>


        <!--        <div class="modal fade" id="exampleModalCenter2" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <%--<jsp:include page="datosTratamientos.jsp"></jsp:include>--%>
    </div>-->
        <script src="js/reservaciones.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>


    </body>
</html>
