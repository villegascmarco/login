/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


let cancelar = () => {
    window.location = "index.jsp";
}

let crear = () => {

    $.ajax({
        type: 'POST',
        async: true,
        url: "api/cliente/crear",
        data: {
            nombre: document.getElementById("nombre").value,
            apellidoPaterno: document.getElementById("apellidoPaterno").value,
            apellidoMaterno: document.getElementById("apellidoMaterno").value,
            genero: document.getElementById("genero").value,
            domicilio: document.getElementById("domicilio").value,
            telefono: document.getElementById("telefono").value,
            rfc: document.getElementById("rfc").value,

            correo: document.getElementById("correo").value,

            nombreUsuario: document.getElementById("nombreUsuario").value,
            contrasenia: document.getElementById("contrasenia").value,

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
            window.location = "index.jsp";
            return;

        }
        alert("No generado");

        return;
    });
    console.log("Holaa")
}