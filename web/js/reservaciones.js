

var json = JSON.parse(localStorage.getItem("cliente"));
var tipo = "";


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

var reservaciones = null;

var listarReservaciones = () => {

    var token = json.usuario.token;


    console.log(token);

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
            console.log(reservaciones);
//            let table = document.getElementById('table');
//            for (let element of data) {
//                let row = table.insertRow();
//                for (key in element) {
//                    if (key !== "" || key !== null) {
//
//                        let us = data;
//                        let cell = row.insertCell();
//                        let text = document.createTextNode(element[key]);
//                        cell.appendChild(text);
//
//                    } else {
//                    }
//                }
//                let cell2 = row.insertCell(-1);
//                cell2.innerHTML = '<a href="BienvenidoEmpleado.jsp">Ver detalle</a>';
//
//            }
            var datos = "";
            datos += '<tr>';
            datos += '<th scope="col"> Fecha </th>';
            datos += '<th scope="col"> Hora de inicio </th>';
            datos += '<th scope="col"> Hora de fin </th>';
            datos += '<th scope="col"> Cliente </th>';
            datos += '<th scope="col"> Sala </th>';
            datos += '<th scope="col"></th>';

            for (var i = 0; i < reservaciones.length; i++) {
                datos += "<tr>";
                datos += "<td>" + reservaciones[i].fechaHoraInicio + "</td>";
                datos += "<td>" + reservaciones[i].horaInicio + "</td>";
                datos += "<td>" + reservaciones[i].horaFin + "</td>";
                datos += "<td>" + reservaciones[i].cliente + "</td>";
                datos += "<td>" + reservaciones[i].sucursal + "</td>";
                datos += "<td> <button class='btn btn-outline-success' onclick='mostrarAtender(" + i + ")'>Ver detalle</a> </td>";
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
        datos += "<tr>";
        datos += "<td>" + fechaI + "</td>";
        datos += "<td>" + horaI + "</td>";
        datos += "<td>" + horaF + "</td>";
        datos += "<td>" + nombre + "</td>";
        datos += "<td>" + sala + "</td>";

        datos += "</tr>";
    }
    $('#tableDetalle').html(datos);

}
