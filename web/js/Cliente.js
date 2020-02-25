/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var json = JSON.parse(localStorage.getItem("cliente"));
var tipo = "";

if (json !== "" && json !== null) {
    tipo = json.usuario.rol;
} else {
    window.location = "index.jsp";
}
if (tipo === null || tipo === "") {
} else if (!tipo.includes("Cliente")) {
    window.location = "BienvenidoEmpleado.jsp";
}
var u = json.usuario;


Object.keys(json).forEach(key => {

    if (key == 'usuario' && key !== null) {
        Object.keys(u).forEach(key => {
            document.getElementById(key).value = u[key];
        })

    } else if (key == 'persona' && key !== null) {

        document.getElementById(key).value = json[key];

    }
});






let modificarDatos = () => {
    let tipo = JSON.parse(localStorage.getItem("cliente"));


    $.ajax({
        type: 'POST',
        async: true,
        url: "api/cliente/modificarDatos",
        data: {
            idPersona: document.getElementById("idPersona").value,
            nombre: document.getElementById("nombre").value,
            apellidoPaterno: document.getElementById("apellidoPaterno").value,
            apellidoMaterno: document.getElementById("apellidoMaterno").value,
            genero: document.getElementById("genero").value,
            domicilio: document.getElementById("domicilio").value,
            telefono: document.getElementById("telefono").value,
            rfc: document.getElementById("rfc").value,

            idCliente: document.getElementById("idCliente").value,
            numeroUnico: document.getElementById("numeroUnico").value,
            correo: document.getElementById("correo").value,
            estatus: document.getElementById("estatus").value,

            idUsuario: document.getElementById("idUsuario").value,
            nombreUsuario: document.getElementById("nombreUsuario").value,
            contrasenia: document.getElementById("contrasenia").value,
            rol: document.getElementById("rol").value,
            token: document.getElementById("token").value
        }
    }).done(function (data) {
        if (data != null) {
            if (data.error != null) {
                alert('Problemas');
                return;
            }
            //localStorage.setItem("cliente", JSON.stringify(data))
            //alert('Token generado ' + JSON.stringify(data));
            $('#token').html(data.result);
            //window.location = "Bienvenido.jsp";
            alert(JSON.stringify(data))
            return;

        }
        alert("No generado");

        return;
    });
    console.log("Holaa")
}
let cerrarSesion = () => {

    $.ajax({
        type: 'POST',
        async: true,
        url: "api/cliente/cerrarSesion",
        data: {
            idUsuario: document.getElementById("idUsuario").value,

            token: document.getElementById("token").value
        }
    }).done(function (data) {
        if (data != null) {
            if (data.error != null) {
                alert('Problemas');
                return;
            }

            //localStorage.setItem("cliente", JSON.stringify(data))
            //alert('Token generado ' + JSON.stringify(data));
            $('#token').html(data.result);
            localStorage.clear();
            //window.location = "Bienvenido.jsp";
            window.location = "index.jsp";
            return;

        }
        alert("No generado");

        return;
    });
    console.log("bye")
}
let eliminarCuenta = () => {

    $.ajax({
        type: 'POST',
        async: true,
        url: "api/cliente/eliminarCuenta",
        data: {
            idUsuario: document.getElementById("idUsuario").value,

            token: document.getElementById("token").value
        }
    }).done(function (data) {
        if (data != null) {
            if (data.error != null) {
                alert('Problemas');
                return;
            }
            //localStorage.setItem("cliente", JSON.stringify(data))
            //alert('Token generado ' + JSON.stringify(data));
            $('#token').html(data.result);
            //window.location = "Bienvenido.jsp";
            localStorage.clear();
            window.location = "index.jsp";
            return;

        }
        alert("No generado");

        return;
    });
    console.log("NOOO")
}