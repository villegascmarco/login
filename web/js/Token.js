/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var us = "" + JSON.stringify(localStorage.getItem("cliente"));

if (us !== null) {
    let json = us.toString();
    if (json.includes("idCliente")) {
        window.location = "Bienvenido.jsp";
    } else if (json.includes("idEmpleado")) {//Sólo para validar que esté logeado
        window.location = "BienvenidoEmpleado.jsp";
        console.log(json);
    } else {
        localStorage.clear();
    }
}





function usarToken() {

    var usuario = $('#txtUsuario').val();
    var password = $('#txtContra').val();

    $.ajax({

        type: "POST",
        async: true,
        url: "api/ingresar/ingresar",
        data: {
            usuario: usuario,
            contrasenia: password
        }
    }).done(function (data)
    {
        if (data != null) {
            if (data.error != null) {
                alert('Problemas para generar el token');
                return;
            }
            let json = JSON.stringify(data);
            localStorage.setItem("cliente", json);
            //alert('Token generado ' + JSON.stringify(data));
            $('#token').html(data.result);

            alert(json);

            if (json.includes("Cliente")) {

                window.location = "Bienvenido.jsp";
                return;

            } else if (json.includes("Empleado")) {

                window.location = "BienvenidoEmpleado.jsp";
                return;
            }
        }
        alert("No generado");

        return;


    });

}


