

var json = JSON.parse(localStorage.getItem("cliente"));


var token = json.usuario.token;


var tipo = "";

//Arreglo con listado de objetos reservacion
var reservaciones = null;

//Variable que almacena el objeto producto
var prodG = null;
//Arreglo que almacena los objetos producto que se van a guardar en el tratamiento
var productosG = [];

//Arreglo que contiene los tratamientos y el empleado encargado de la reservación
var reservacionTratamientos = new Object;

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

            var datos = "";
            datos += '<thead>';
            datos += '<tr>';
            datos += '<th scope="col"> Fecha </th>';
            datos += '<th scope="col"> Hora de inicio </th>';
            datos += '<th scope="col"> Hora de fin </th>';
            datos += '<th scope="col"> Cliente </th>';
            datos += '<th scope="col"> Sala </th>';
            datos += '<th scope="col"></th>';
            datos += '</thead>';
            datos += '<tbody>';
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
            datos += '</tbody>';

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
var usuario;
var empleado = new Object;
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

            var datos = "";


            for (var i = 0; i < empleados.length; i++) {
                empleado = empleados[i];
                usuario = empleados[i].usuario;
                datos += "<option value=" + empleados[i].idEmpleado + ">" + empleados[i].persona.nombre + ", " + empleados[i].puesto + "</option>";
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


//Método para listar tratamientos que se guardaron
function listarTratamientosR() {

    var p = ($('#cmbTratamientos').val());

    var t = (tratamientos[p]);

    tratamientosR[tratamientosR.length] = t;

    var datos = "";
    var datos = "<thead>";
    datos += "<tr>";
    datos += "<th scope='col'>Nombre</th>";
    datos += "<th scope='col'>Costo</th>";
    datos += "<th scope='col'> </th>";
    datos += "</tr>";
    datos += "</thead>";
    datos += "<tbody>";

    for (var i = 0; i < tratamientosR.length; i++) {
        idTrat = tratamientos[i].idTratamiento;
        datos += "<tr>";
        datos += "<td>" + tratamientosR[i].nombre + "</td>";
        datos += "<td> $" + tratamientosR[i].costo + "</td>";
        datos += "<td>  <button type='button' class='btn btn-outline-info' id='insertarProducto' onclick='listarProductos(" + i + ")'><i id='iconoPlus' class='fa fa-plus'></i></button> ";
        datos += "<td>  <button type='button' class='btn btn-outline-danger' id='eliminarTratamiento' onclick='eliminarTratamiento(" + i + ")'><i id='iconoMinus' class='fa fa-minus'></i></button> ";
        datos += "</tr>";
        tratamientosG[i] = tratamientosR[i];

        if (tratamientosG[i].productos === undefined) {

        } else {
            tratamientosG[i].productos = [];
        }
    }
    datos += "</tbody>";
    $('#tableT').html(datos);


}


//ID del producto seleccionado
var idProd = 0;

//Arreglo con listado de objetos producto
var productos = null;

//Arreglo con los productos que se van a agregar VA DENTRO DEL ARREGLO DE TRATAMIENTOS A AGREGAR
var productoT = [];


function listarProductos(pos) {

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
}




function agregarProductos() {

//Posicion en la que está el objeto producto dentro del comboBox
    var p = ($('#cmbProductos').val());

//Objeto tipo producto, entra al arreglo donde se almacenan los productos y busca el producto en la posicion P
    var t = new Object;
    t = (productos[p]);

    if (t.pos === undefined) {
        t.pos = "";
    }
    t.pos = posTrat;


    if (t.posT === undefined) {
        t.posT = "";
    }
//Asigna dentro del arreglo de productos que se listan en la tabla el objeto seleccionado en el comoBox
    productoT[productoT.length] = t;

//Si el campo productos es = undefined, inicializa el arreglo en 0's
    if (tratamientosG[posTrat].productos === undefined) {
        tratamientosG[posTrat].productos = [];
    }

//Asigna dentro del arreglo de tratamientosGuardar el producto (t)
    tratamientosG[posTrat].productos.push(t);

    var datos = "";
    datos += "<thead>";
    datos += "<tr>";
    datos += "<th scope='col'>Nombre</th>";
    datos += "<th scope='col'>Costo</th>";
    datos += "</tr>";
    datos += "</thead>";
    datos += "<tbody>";

    for (var i = 0; i < tratamientosG.length; i++) {
        for (var j = 0; j < tratamientosG[i].productos.length; j++) {
            tratamientosG[i].productos[j].posT = j;
            datos += "<tr>";
            datos += "<td>" + tratamientosG[i].productos[j].nombre + "</td>";
            datos += "<td id='precioProducto'> $" + tratamientosG[i].productos[j].precioUso + "</td>";
            datos += "<td>  <button type='button' class='btn btn-outline-danger' id='eliminarProducto' onclick='eliminarProducto( " + tratamientosG[i].productos[j].posT + ", " + tratamientosG[i].productos[j].pos + " )'><i id='iconoMinus' class='fa fa-minus'></i></button> ";
            datos += "</tr>";
        }
    }
    datos += "</tbody>";

    $('#tableP').html(datos);
//Recorre todo el arreglo de productos listados en la tabla productosTabla

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


//p es la posición de la tabla en la que está el tratamiento a eliminar
function eliminarTratamiento(p) {

    if (tratamientosG[p].productos === undefined) {
//        window.alert('Error');
    }

    for (var i = 0; i < tratamientosG[p].productos; i++) {
        tratamientosG[p].productos.splice(i, 1);
    }

    window.alert('Eliminado!');

    tratamientosR.splice(p, 1);
    tratamientosG.splice(p, 1);

    var datos = "";
    datos += "<tr>";
    datos += "<th scope='col'>Nombre</th>";
    datos += "<th scope='col'>Costo</th>";
    datos += "<th scope='col'> </th>";
    datos += "</tr>";

    for (var i = 0; i < tratamientosR.length; i++) {
        idTrat = tratamientos[i].idTratamiento;
        datos += "<tr>";
        datos += "<td>" + tratamientosR[i].nombre + "</td>";
        datos += "<td> $" + tratamientosR[i].costo + "</td>";
        datos += "<td>  <button type='button' class='btn btn-outline-info' id='insertarProducto' onclick='listarProductos(" + i + ")'><i id='iconoPlus' class='fa fa-plus'></i></button> ";
        datos += "<td>  <button type='button' class='btn btn-outline-danger' id='eliminarTratamiento' onclick='eliminarTratamiento(" + i + ")'><i id='iconoMinus' class='fa fa-minus'></i></button> ";
        datos += "</tr>";
        tratamientosG[i] = tratamientosR[i];

    }
    $('#tableT').html(datos);

    var datos2 = "";
    datos2 += "<thead>";
    datos2 += "<tr>";
    datos2 += "<th scope='col'>Nombre</th>";
    datos2 += "<th scope='col'>Costo</th>";
    datos2 += "</tr>";
    datos2 += "</thead>";
    datos2 += "<tbody>";


//Recorre todo el arreglo de productos listados en la tabla productosTabla
    for (var i = 0; i < tratamientosG.length; i++) {
        for (var j = 0; j < tratamientosG[i].productos.length; j++) {
            tratamientosG[i].productos[j].pos = tratamientosG[i].productos[j].pos - 1;
            datos2 += "<tr>";
            datos2 += "<td>" + tratamientosG[i].productos[j].nombre + "</td>";
            datos2 += "<td id='precioProducto'> $" + tratamientosG[i].productos[j].precioUso + "</td>";
            datos2 += "<td>  <button type='button' class='btn btn-outline-danger' id='eliminarProducto' onclick='eliminarProducto( " + tratamientosG[i].productos[j].posT + ", " + tratamientosG[i].productos[j].pos + " )'><i id='iconoMinus' class='fa fa-minus'></i></button> ";
            datos2 += "</tr>";
        }
    }

    datos2 += "</tbody>";
    $('#tableP').html(datos2);

}

//p es la posición de la tabla productos en la que se encuentra el producto a eliminar
//posi es la posicion del tratamiento
function eliminarProducto(p, posi) {
//
//    if (tratamientosG[posi].productos === undefined) {
//        window.alert('Error');
//    }

//TABLA
    productoT.splice(p, 1);

//JSON A ENVIAR    
    if (tratamientosG[posi].productos.length === 1) {
        tratamientosG[posi].productos.splice(0, 1);
    }
    tratamientosG[posi].productos.splice(p, 1);

    var datos2 = "";
    datos2 += "<thead>";
    datos2 += "<tr>";
    datos2 += "<th scope='col'>Nombre</th>";
    datos2 += "<th scope='col'>Costo</th>";
    datos2 += "</tr>";
    datos2 += "</thead>";
    datos2 += "<tbody>";


//Recorre todo el arreglo de productos listados en la tabla productosTabla
    for (var i = 0; i < tratamientosG.length; i++) {
        for (var j = 0; j < tratamientosG[i].productos.length; j++) {
            if (tratamientosG[i].productos[j].posT = tratamientosG[i].productos[j].posT > 0) {
                tratamientosG[i].productos[j].posT = tratamientosG[i].productos[j].posT - 1;
            } else {
                tratamientosG[i].productos[j].posT = tratamientosG[i].productos[j].posT = 0;
            }
            datos2 += "<tr>";
            datos2 += "<td>" + tratamientosG[i].productos[j].nombre + "</td>";
            datos2 += "<td id='precioProducto'> $" + tratamientosG[i].productos[j].precioUso + "</td>";
            datos2 += "<td>  <button type='button' class='btn btn-outline-danger' id='eliminarProducto' onclick='eliminarProducto( " + tratamientosG[i].productos[j].posT + ", " + tratamientosG[i].productos[j].pos + " )'><i id='iconoMinus' class='fa fa-minus'></i></button> ";
            datos2 += "</tr>";
        }
    }

    datos2 += "</tbody>";
    $('#tableP').html(datos2);

}

function guardarServicio() {

//SE AGREGA ARREGLO CON RESERVACION
    if (reservacionTratamientos.reservacion === undefined) {
        reservacionTratamientos.reservacion = new Object;
    }

    var data = '{"idReservacion":' + reservacionA.idReservacion + '}';
    var jso = JSON.parse(data);

    reservacionTratamientos.reservacion = jso;

//SE AGREGA ARREGLO CON EMPLEADO
    if (reservacionTratamientos.empleado === undefined) {
        reservacionTratamientos.empleado = [];
    }


    if (reservacionTratamientos.empleado.length < 1) {
        reservacionTratamientos.empleado = empleado;
    }

    if (usuario.token === undefined) {
        usuario.token = '';
    }
    usuario.token = token;


//SE AGREGA ARREGLO DE TRATAMIENTOS CON ARREGLO DE PRODUCTOS
    if (reservacionTratamientos.tratamientos === undefined) {
        reservacionTratamientos.tratamientos = new Object;
    }

    reservacionTratamientos.tratamientos = (tratamientosG);

    var respuesta = JSON.stringify(reservacionTratamientos);

    window.alert(respuesta);
}

function calcularTotal() {
    var total = 0;
    //SE AGREGA EL TOTAL DE PRODUCTOS
    for (var i = 0; i < tratamientosG.length; i++) {
        if (tratamientosG[i].productos !== undefined) {
            for (var j = 0; j < tratamientosG[i].productos.length; j++) {
                total += Number(tratamientosG[i].productos[j].precioUso);
            }
        }
    }

    //SE AGREGA EL TOTAL DE TRATAMIENTOS
    for (var i = 0; i < tratamientosG.length; i++) {
        total += Number(tratamientosG[i].costo);
    }
    window.alert("Costo total: $" + total);
}
