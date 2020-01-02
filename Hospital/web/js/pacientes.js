$(document).ready(function () {
    $("#nombrepaciente").val("");
    $("#ingresarconsulta").attr("disabled", true);
    $("#peso").val("");
    $("#cui").val("");
    $("#edad").val("");
    $("#nit").val("");
    $("#ciudadfactura").val("");
    $("#nombrefactura").val("");
    $("#confirmarconsulta").attr("disabled", true);
    var controladorTiempo1 = "";
    var existe = false;
    var existeNit = false;
    function encontrarPaciente(t) {
        if (t === 1) {
            var nombre = $("#nombrepaciente").val();
        } else {
            var nombre = $("#pacientescirugia").val();
        }
        if (nombre.length > 0) {
            $.ajax({
                type: 'GET',
                data: {txt: nombre},
                url: '../Pacientes',
                success: function (result) {
                    $('#temp').val(result);

                    if ($('#temp').val() === '1') {
                        $('#fme2').hide();
                        if (t === 1) {
                            $('#ingresarconsulta').attr("disabled", false);
                        } else {

                        }
                        existe = true;
                    } else {
                        $('#fme2').show();
                        $("#ingresodatos").text(nombre.toUpperCase());
                        existe = false;
                    }
                }
            });
        } else {

        }
    }
    $("#ingresarpaciente").click(function () {
        var nombre = $("#nombrepaciente").val();
        var edad = $("#edad").val();
        var peso = $("#peso").val();
        var cui = $("#cui").val();
        var sexo = $("#sexo").val();
        if (peso.length > 0 && edad.length > 0 && cui.length > 0) {
            $.ajax({
                type: 'POST',
                data: {nombre: nombre, edad: edad, peso: peso, cui: cui, tipo: "1", sexo: sexo},
                url: '../Recepcion',
                success: function (result) {
                    if (result === "Creado 2") {
                        $("#fme2").hide();
                        alert("El paciente " + nombre + " se ingresó correctamente al sistema");
                        $("#ingresarconsulta").attr("disabled", false);
                    } else if (result === "Existe") {
                        $("#fme2").hide();
                        alert("El paciente " + nombre + " ya existe en el sistema");
                    } else {
                        alert("Ha ocurrido un error con la base de datos");
                    }
                }
            });
        } else {
            alert("Un valor está en blanco");
        }
    });
    $("#ingresarconsulta").click(function () {
        var valor = $("#valorconsulta").val();
        $("#preciofactura").val(valor);
        $("#fme").show();
    });
    $("#cerrarmensaje").click(function () {
        $("#fme").hide();
        $("#nit").val("");
        $("#ciudadfactura").val("");
        $("#nombrefactura").val("");
        $("#confirmarconsulta").attr("disabled", true);
    });
    $("#cerrarmensaje2").click(function () {
        $("#fme2").hide();
        $("#nit").val("");
        $("#ciudadfactura").val("");
        $("#nombrefactura").val("");
        $("#confirmarconsulta").attr("disabled", true);
    });
    function hoyFecha() {
        var hoy = new Date();
        var dd = hoy.getDate();
        var mm = hoy.getMonth() + 1;
        var yyyy = hoy.getFullYear();

        dd = addZero(dd);
        mm = addZero(mm);

        return yyyy + '/' + mm + '/' + dd;
    }
    function addZero(i) {
        if (i < 10) {
            i = '0' + i;
        }
        return i;
    }
    $("#confirmarconsulta").click(function () {
        var cui = $("#cui").val();
        var nit = $("#nit").val();
        var nombre = $("#nombrefactura").val();
        var nombrePaciente = $("#nombrepaciente").val();
        var valor = $("#preciofactura").val();
        var ciudad = $("#ciudadfactura").val();
        if (!existeNit) {
            $.ajax({
                type: 'POST',
                data: {cui: cui, tipo: "2", nombre: nombre, nit: nit, valor: valor, ciudad: ciudad, fecha: hoyFecha(), nombrePaciente: nombrePaciente, id_empleado: $("#doctores").val()},
                url: '../Recepcion',
                success: function (result) {
                    if (result === "Creado Cliente") {
                        $("#fme").hide();
                        $("#nombrepaciente").val("");
                        $("#ingresarconsulta").attr("disabled", true);
                        $("#peso").val("");
                        $("#cui").val("");
                        $("#edad").val("");
                        $("#nit").val("");
                        $("#ciudadfactura").val("");
                        $("#nombrefactura").val("");
                        $("#confirmarconsulta").attr("disabled", true);
                        $("#ocultopacientes").hide();
                        listadoEspera();
                    } else {
                        alert("Ha ocurrido un error con la base de datos");
                    }
                }
            });
        } else {
            $.ajax({
                type: 'POST',
                data: {tipo: "3", nit: nit, valor: valor, fecha: hoyFecha(), nombrePaciente: nombrePaciente, id_empleado: $("#doctores").val()},
                url: '../Recepcion',
                success: function (result) {
                    if (result === "Creado Cliente") {
                        $("#fme").hide();
                        $("#nombrepaciente").val("");
                        $("#ingresarconsulta").attr("disabled", true);
                        $("#peso").val("");
                        $("#cui").val("");
                        $("#edad").val("");
                        $("#nit").val("");
                        $("#ciudadfactura").val("");
                        $("#nombrefactura").val("");
                        $("#confirmarconsulta").attr("disabled", true);
                        $("#ocultopacientes").hide();
                        listadoEspera();
                    } else {
                        alert("Ha ocurrido un error con la base de datos");
                    }
                }
            });
        }
    });
    $("#nombrepaciente").blur(function () {
        encontrarPaciente(1);
    });
    $("#pacientescirugia").blur(function () {
        encontrarPaciente(2);
    });
    $("#nombrefactura").blur(function () {
        activarConfirmarConsulta();
    });
    $("#ciudadfactura").blur(function () {
        activarConfirmarConsulta();
    });

    $("#cui").on("keyup", function () {
        clearTimeout(controladorTiempo1);
        controladorTiempo1 = setTimeout(activarIngresarConsulta, 1000);
    });
    $("#peso").on("keyup", function () {
        clearTimeout(controladorTiempo1);
        controladorTiempo1 = setTimeout(activarIngresarConsulta, 1000);
    });
    $("#edad").on("keyup", function () {
        clearTimeout(controladorTiempo1);
        controladorTiempo1 = setTimeout(activarIngresarConsulta, 1000);
    });
    $("#nit").on("keyup", function () {
        clearTimeout(controladorTiempo1);
        controladorTiempo1 = setTimeout(codigoAJAX1, 1000);
    });
    var k = 0;
    function activarIngresarConsulta() {
        if ($("#cui").val().length > 0 && $("#peso").val().length > 0 && $("#edad").val().length > 0) {
            if ($("#cui").val().length === 13 && $("#cui").val() > 0) {
                $("#ingresarpaciente").attr("disabled", false);
            } else {
                if ($("#cui").val().length < 13) {
                    alert("El CUI no está completo");
                } else {
                    alert("El CUI tiene caracteres no permitidos");
                }
            }
        }
    }
    function activarConfirmarConsulta() {
        if ($("#nombrefactura").val().length > 0 && $("#ciudadfactura").val().length > 0) {
            $("#confirmarconsulta").attr("disabled", false);
        }
    }
    function codigoAJAX1() {
        var texto = document.getElementById("nit").value;
        if ($('#nit').val().length < 8) {
            if (texto.toUpperCase() === "CF" || texto.toUpperCase() === "C/F") {
                $('#nombrefactura').val("---");
                $('#ciudadfactura').val("---");
                $('#confirmarconsulta').attr("disabled", false);
            } else {
                $('#nombrefactura').val("");
                $('#ciudadfactura').val("");
                $('#confirmarconsulta').attr("disabled", true);
            }
        } else {
            $.ajax({
                type: 'GET',
                data: {txt1: texto},
                url: '../Farmacia',
                success: function (result) {
                    $.each(result, function (index, item) {
                        if (k === 0) {
                            $('#nombrefactura').val(item);
                            $('#nombrefactura').attr("disabled", true);
                        } else {
                            $('#ciudadfactura').val(item);
                            $('#ciudadfactura').attr("disabled", true);
                            if ($('#nombrefactura').val() === '') {
                                $('#nombrefactura').attr("disabled", false);
                                $('#ciudadfactura').attr("disabled", false);
                                existeNit = false;
                                $('#confirmarconsulta').attr("disabled", true);
                            } else {
                                $('#confirmarconsulta').attr("disabled", false);
                                existeNit = true;
                            }
                        }
                        k++;
                    });
                }
            });
        }
        k = 0;
    }
    $("#doctores").change(function () {
        listadoEspera();
    });
    function listadoEspera() {
        var x = document.getElementById("cuerpolista");
        var y = document.getElementById("nombreDoctor");
        var nombre_doctor = $("#doctores").val();
        $("#nombreDoctor").text("Listado de espera Doctor " + $("select[name='doctores'] option:selected").text());
        x.innerHTML = "";
        $.ajax({
            type: 'GET',
            data: {txt: nombre_doctor, tipo: 1, fechaconsulta: hoyFecha()},
            url: '../Recepcion',
            success: function (result) {
                $.each(result, function (index, item) {
                    x.innerHTML += "<tr><th>" + (index + 1) + "</th><th>" + item + "</th></tr>";
                });
            }
        });
    }
});