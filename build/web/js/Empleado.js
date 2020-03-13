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
    window.location = "index.jsp";
} else if (tipo.includes("Cliente")) {
    window.location = "Bienvenido.jsp";
}
var p = json.persona;
var u = json.usuario;

Object.keys(json).forEach(key => {

    if (key === 'usuario' && key !== null) {
        Object.keys(u).forEach(key => {
            document.getElementById(key).value = u[key];
        });

    } else if (key === 'persona' && key !== null) {
        Object.keys(p).forEach(key => {

            document.getElementById(key).value = p[key];
        });
//                    document.getElementById(key).value = json[key];
    } else {
        if (key === "rutaFoto") {
            document.getElementById(key).value = json[key];
            document.getElementById("imagen").src = json[key];
        }
        document.getElementById(key).value = json[key];
    }
});

var cancelar = () => {
    document.getElementById("formulario1").reset();
}


var guardarEmpleado = () => {
    $.ajax({
        type: 'POST',
        async: true,
        url: "api/empleado/crear",
        data: {
            nombre: document.getElementById("nombre1").value,
            apellidoPaterno: document.getElementById("apellidoPaterno1").value,
            apellidoMaterno: document.getElementById("apellidoMaterno1").value,
            genero: document.getElementById("genero1").value,
            domicilio: document.getElementById("domicilio1").value,
            telefono: document.getElementById("telefono1").value,
            rfc: document.getElementById("rfc1").value,
            puesto: document.getElementById("puesto1").value,
            rutaFoto: document.getElementById("rutaFoto1").value,
            nombreUsuario: document.getElementById("nombreUsuario1").value,
            contrasenia: document.getElementById("contrasenia1").value,
            rol: document.getElementById("rol1").value,
            idUsuarioAdmin: document.getElementById("idUsuario").value, //Datos del que da de alta
            tokenAdmin: document.getElementById("token").value, //Datos del que da de alta

        }
    }).done(function (data) {
        if (data != null) {
            if (data.error != null) {
                alert('Problemas');
                return;
            }
            //localStorage.setItem("cliente", JSON.stringify(data))
            //alert('Token generado ' + JSON.stringify(data));


            alert("Listo")
            cancelar();
            return;

        }
        alert("No generado");

        return;
    });
}

var modificarDatosE = () => {
    let tipo = JSON.parse(localStorage.getItem("cliente"));


    $.ajax({
        type: 'POST',
        async: true,
        url: "api/empleado/actualizarCuenta",
        data: {
            nombre: document.getElementById("nombre").value,
            apellidoPaterno: document.getElementById("apellidoPaterno").value,
            apellidoMaterno: document.getElementById("apellidoMaterno").value,
            genero: document.getElementById("genero").value,
            domicilio: document.getElementById("domicilio").value,
            telefono: document.getElementById("telefono").value,
            rfc: document.getElementById("rfc").value,

            puesto: document.getElementById("puesto").value,
            foto: document.getElementById("rutaFoto").value,

            nombreUsuario: document.getElementById("nombreUsuario").value,
            contrasenia: document.getElementById("contrasenia").value,
            rol: document.getElementById("rol").value,
            idUsuario: document.getElementById("idUsuario").value,
            idEmpleado: document.getElementById("idEmpleado").value,
            idPersona: document.getElementById("idPersona").value,
            numeroEmpleado: document.getElementById("numeroEmpleado").value,
            tokenAdmin: document.getElementById("token").value
        }
    }).done(function (data) {
        if (data != null) {
            if (data.error != null) {
                window.alert('Problemas');
                return;
            }
            $('#token').html(data.result);
            window.alert(JSON.stringify(data))
            return;

        }
        window.alert("No generado");

        return;
    });
}

var cerrarSesion = () => {

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
var eliminarMiCuenta = () => {

    $.ajax({
        type: 'POST',
        async: true,
        url: "api/empleado/eliminarCuenta",
        data: {
            idEmpleado: document.getElementById("idEmpleado").value,

            token: document.getElementById("token").value,
            idUsuario: document.getElementById("idUsuario").value
        }
    }).done(function (data) {
        if (data != null) {
            if (data.error != null) {
                alert('Problemas');
                return;
            }
            localStorage.clear();
            window.location = "index.jsp";
            return;

        }
        alert("No generado");

        return;
    });
};


var eliminarCuentas = () => {

    $.ajax({
        type: 'POST',
        async: true,
        url: "api/empleado/eliminarCuenta",
        data: {
            idEmpleado: document.getElementById("idEmpleado2").value,

            token: document.getElementById("token").value,
            idUsuario: document.getElementById("idUsuario").value
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
            window.alert(data);
            window.alert("Listo")
            document.getElementById("idEliminar").value = "";
            return;

        }

        alert("No generado");

        return;
    });
}

var opc = 0;

var listarEmpleados = () => {
    $.ajax({
        type: "POST",
        async: true,
        url: 'api/empleado/listado',
        data: {
            token: document.getElementById("token").value,
        }
    }).done(function (data)
    {
        if (data.error != null) {
            window.alert('Problemas para listar');
            return;
        } else {
            if (opc == 0) {
                let empleados = JSON.stringify(data);
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

                opc = 1;
            } else if (opc == 1) {
                document.getElementById('table').hidden = true;
                opc = 2;
            } else if (opc == 2) {
                document.getElementById('table').hidden = false;
                opc = 1;
            }
        }
    }
    );
}
