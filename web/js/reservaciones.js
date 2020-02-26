

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

            let table = document.getElementById('table');
            for (let element of data) {
                let row = table.insertRow();
                for (key in element) {
                    if (key !== "" || key !== null) {

                        let us = data.usuario;
                        let cell = row.insertCell();
                        let text = document.createTextNode(element[key]);
                        cell.appendChild(text);
                    } else {
                    }
                }
            }
        }
    }
    );
};