

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


var listarReservaciones = () => {
    $.ajax({
        type: "POST",
        async: true,
        url: 'api/reservaciones/listado',
        data: {
            token: document.getElementById("token").value,
        }
    }).done(function (data)
    {
        if (data.error != null) {
            window.alert('Problemas para listar');
            return;
        } else {

            let reservaciones = JSON.stringify(data);
            let table = document.getElementById('table');
            for (let element of data) {
                let row = table.insertRow();
                for (key in element) {
                    if (key == 'usuario') {

                        let us = data.usuario;
                        let row2 = table.insertRow();
                        let cell = row.insertCell();
                        let cell2 = row.insertCell();
                        let text = document.createTextNode(element[key].idUsuario);
                        cell.appendChild(text);
                        let text2 = document.createTextNode(element[key].nombreUsuario);
                        cell2.appendChild(text2);
                    } else {
                        let cell = row.insertCell();
                        let text = document.createTextNode(element[key]);
                        cell.appendChild(text);
                    }
                }
            }
        }
    }
    );
};