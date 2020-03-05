

var json = JSON.parse(localStorage.getItem("cliente"));

var tipo = "";

//Arreglo con listado de objetos reservacion
var reservaciones = null;



if (json !== "" && json !== null) {
    tipo = json.usuario.rol;
} else {
    window.location = "index.jsp";
}
if (tipo === null || tipo === "") {
    window.location = "index.jsp";
} else if (tipo.includes("Cliente") || tipo.includes("Empleado")) {
    window.location = "reservaciones.jsp";
}

//Posicion de reservacion
var numR;

var listarReservaciones = () => {

    var token = json.usuario.token;

    $.ajax({
        type: "POST",
        async: true,
        url: 'api/empleado/listarReservaciones',
        data: {
            token: token
        }
    }).done(function (data)
    {
        if (data.error != null) {
            window.alert('Problemas para listar');
            return;
        } else {
            reservaciones = data;
            console.log('Reservaciones: ' + reservaciones);

            var datos = "";
            datos += '<tr>';
            datos += '<th scope="col"> Fecha </th>';
            datos += '<th scope="col"> Hora de inicio </th>';
            datos += '<th scope="col"> Hora de fin </th>';
            datos += '<th scope="col"> Cliente </th>';
            datos += '<th scope="col"> Sala </th>';
            datos += '<th scope="col"></th>';

            for (var i = 0; i < reservaciones.length; i++) {
                numR = reservaciones[i].IdReservacion;
                datos += "<tr>";
                datos += "<td>" + reservaciones[i].fechaHoraInicio + "</td>";
                datos += "<td>" + reservaciones[i].horaInicio + "</td>";
                datos += "<td>" + reservaciones[i].horaFin + "</td>";
                datos += "<td>" + reservaciones[i].cliente + "</td>";
                datos += "<td>" + reservaciones[i].sucursal + "</td>";
                datos += "<td>    <button type='button' class='btn\n\
    btn-outline-success' data-toggle='modal' data-target='#exampleModalCenter' \n\
    onclick='mostrarAtender(" + i + ")'> Ver detalle</button> </td>";
                datos += "</tr>";
            }
            $('#table').html(datos);
        }
    }
    );
};

function mostrarAtender(posicion) {
    if (posicion != null && reservaciones != null && reservaciones[posicion] != null) {
        reservacionA = reservaciones[posicion];


        var nombre = reservacionA.cliente;
        var fechaI = reservacionA.fechaHoraInicio;
        var horaI = reservacionA.horaInicio;
        var horaF = reservacionA.horaFin;
        var sala = reservacionA.sucursal;

        var datos = "";

        datos = fechaI;
        $('#fechaR').val(datos);

        datos = horaI;
        $('#horaInicio').val(datos);

        datos = horaF;
        $('#horaFin').val(datos);

        datos = nombre;
        $('#nombreCliente').val(datos);

        datos = sala;
        $('#salaR').val(datos);
        listarEmpleados();
        listarTratamientos();
    }

}

//Arreglo con todos los objetos empleados
var empleados = null;

function listarEmpleados() {
    var token = json.usuario.token;

    $.ajax({
        type: "GET",
        async: true,
        url: 'api/empleado/listado2',
        data: {
            token: token
        }
    }).done(function (data)
    {
        if (data.error != null) {
            window.alert('Problemas para listar');
            return;
        } else {
            empleados = data;
            console.log(empleados.length);

            var datos = "";


            for (var i = 0; i < empleados.length; i++) {
                datos += "<option value=" + empleados[i].idEmpleado + ">" + empleados[i].puesto + "</option>";
                $('#cmbEmpleados').html(datos);
            }
        }
    }
    );
}
;

//Arreglo con listado de objetos tratamiento
var tratamientos = null;

function listarTratamientos() {
    var token = json.usuario.token;

    $.ajax({
        type: "GET",
        async: true,
        url: 'api/tratamiento/listado',
        data: {
            token: token
        }
    }).done(function (data)
    {
        if (data.error != null) {
            window.alert('Problemas para listar');
            return;
        } else {
            tratamientos = data;
            console.log(tratamientos.length);

            var datos = "";

            for (var i = 0; i < tratamientos.length; i++) {
                datos += "<option value='" + i + "'>" + tratamientos[i].nombre + ", $" + tratamientos[i].costo + "</option>";
                $('#cmbTratamientos').html(datos);
            }
        }
    }
    );
}
;

//Arreglo con los listados que se van a agregar
var tratamientosR = [];

//Posicion del arreglo en la que esta el tratamiento agregado
var posTrat = 0;

//ID del tratamiento agregado
var idTrat = 0;

//Arreglo que se mandaría al REST AQUI VAN LOS PRODUCTOS
var tratamientosG = [];

function listarTratamientosR() {

    var p = ($('#cmbTratamientos').val());

    var t = (tratamientos[p]);

    tratamientosR[tratamientosR.length] = t;

    console.log(tratamientos);
    posTrat = p;
    var datos = "";
    datos += "<tr>";
    datos += "<th scope='col'>Nombre</th>";
    datos += "<th scope='col'>Costo</th>";
    datos += "<th scope='col'> </th>";
    datos += "</tr>";

    for (var i = 0; i < tratamientosR.length; i++) {

        idTrat = tratamientos[i].idTratamiento;
        tratamientosG[i] = tratamientosR[i];
        datos += "<tr>";
        datos += "<td>" + tratamientosR[i].nombre + "</td>";
        datos += "<td> $" + tratamientosR[i].costo + "</td>";
        datos += "<td>  <button type='button' class='btn' id='insertarProducto' onclick='listarProductos(" + i + ")'><i id='iconoPlus' class='fa fa-plus'></i></button> ";
        datos += "</tr>";
    }
    $('#tableT').html(datos);

}


//ID del producto seleccionado
var idProd = 0;

//Arreglo con listado de objetos producto
var productos = null;

//Arreglo con los productos que se van a agregar VA DENTRO DEL ARREGLO DE TRATAMIENTOS A AGREGAR
var productoT = [];


function listarProductos(pos) {
    console.log(tratamientosG);

    var token = json.usuario.token;

    $.ajax({
        type: "GET",
        async: true,
        url: 'api/producto/listado',
        data: {
            token: token
        }
    }).done(function (data)
    {
        if (data.error != null) {
            window.alert('Problemas para listar');
            return;
        } else {
            productos = data;
            console.log(productos);

            var datos = "";

            for (var i = 0; i < productos.length; i++) {
                datos += "<option value='" + i + "'>" + productos[i].nombre + ", $" + productos[i].precioUso + "</option>";
            }
            $('#cmbProductos').html(datos);
        }
    }
    );

    var i = pos;
    posTrat = i;
    var j = idTrat;

    window.alert("Posición: " + i + ", ID Tratamiento: " + j);
}
;

//Variable que almacena el objeto producto
var prodG = null;
//Arreglo que almacena los objetos producto que se van a guardar en el tratamiento
var productosG = [];

function agregarProductos() {

    var p = ($('#cmbProductos').val());

    var t = (productos[p]);

    productoT[productoT.length] = t;

    var datos = "";
    datos += "<tr>";
    datos += "<th scope='col'>Nombre</th>";
    datos += "<th scope='col'>Costo</th>";
    datos += "</tr>";

    window.alert(posTrat);

    for (var i = 0; i < productoT.length; i++) {
        datos += "<tr>";
        datos += "<td>" + productoT[i].nombre + "</td>";
        datos += "<td> $" + productoT[i].precioUso + "</td>";
        datos += "</tr>";
        prodG = productoT[i];
        productosG[i] = prodG;
    }
    $('#tableP').html(datos);
    if (tratamientosG[posTrat].productos === undefined) {
        tratamientosG[posTrat].productos = [];
    }
    tratamientosG[posTrat].productos.push(t);
    console.log(tratamientosG);
}


function limpiarTabla() {
    tratamientosR = [];
    productosT = [];
    productoT = [];
    productos = null;
    productosG = [];
    tratamientosG = [];
    prodG = null;
    
    datos = "";
    datos += "<tr>";
    datos += "<th scope='col'>Nombre</th>";
    datos += "<th scope='col'>Costo</th>";
    datos += "<th scope='col'> </th>";
    datos += "</tr>";
    $('#tableT').html(datos);

           
    datos2 = "";
    datos2 += "<tr>";
    datos2 += "<th scope='col'>Nombre</th>";
    datos2 += "<th scope='col'>Costo</th>";
    datos2 += "<th scope='col'> </th>";
    datos2 += "</tr>";
    $('#tableP').html(datos2);
    $('#cmbProductos').html("");

}