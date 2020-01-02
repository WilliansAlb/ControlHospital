
$(document).ready(function () {
    $("#cuis").val("SIN CARGAR");
    $("#vamos2").attr("disabled", true);
    $("#vamos3").attr("disabled", true);
    $("#contratar").attr("disabled", true);
    $("#crearempleo").attr("disabled", true);
    var controladorTiempo1 = "";
    var nombres = [];
    var ids = [];
    var donde = 1;
    var posicion = 0;
    $("#vamos2").click(function () {
        if (posicion < (nombres.length - 1)) {
            posicion++;
            var nombretemp = ids[posicion];
            var nombretemp2 = nombres[posicion];
            $("embed#pdf").attr("src", "../Administracion?idempleado=" + nombretemp + "#toolbar=0&navpanes=0&scrollbar=0&zoom=60");
            $("#vamos3").attr("disabled", false);
            if (posicion === (nombres.length - 1)) {
                $("#vamos2").attr("disabled", true);
            }
            $("#cuis").val(nombretemp);
        } else {
            $("#vamos2").attr("disabled", true);
        }
    });
    $("#vamos3").click(function () {
        if (posicion > 0) {
            posicion--;
            var nombretemp = ids[posicion];
            $("embed#pdf").attr("src", "../Administracion?idempleado=" + nombretemp + "#toolbar=0&navpanes=0&scrollbar=0&zoom=60");
            $("#vamos2").attr("disabled", false);
            if (posicion === 0) {
                $("#vamos3").attr("disabled", true);
            }
            $("#cuis").val(nombretemp);
        } else {
            $("#vamos3").attr("disabled", true);
        }

    });
    $("#vamos").click(function () {
        $.ajax({
            type: 'GET',
            data: {tipo: 1, area: $("#areasp").val()},
            url: '../Administracion',
            success: function (result) {
                $.each(result, function (index, item) {
                    if (donde === 1) {
                        ids.push(item);
                        donde++;
                    } else {
                        nombres.push(item);
                        donde = 1;
                    }
                });
                alert("Solicitudes cargadas");
                $("#vamos2").attr("disabled", false);
                $("#vamos3").attr("disabled", false);
                $("#cuis").val(ids[0]);
                $("embed#pdf").attr("src", "../Administracion?idempleado=" + ids[0] + "#toolbar=0&navpanes=0&scrollbar=0&zoom=60");
                $("#contratar").attr("disabled", false);
                $("#rechazar").attr("disabled", false);
            }
        });
    });
    $("#nombreempleo").on("keyup", function () {
        clearTimeout(controladorTiempo1);
        controladorTiempo1 = setTimeout(revisar, 800);
    });
    $("#salario").on("keyup", function () {
        clearTimeout(controladorTiempo1);
        controladorTiempo1 = setTimeout(revisar, 800);
    });
    function revisar() {
        if ($("#nombreempleo").val().length > 0 && $("#salario").val().length > 0) {
            if ($("#salario").val() > 0) {
                $("#crearempleo").attr("disabled", false);
            } else {
                $("#crearempleo").attr("disabled", true);
                alert("Introduzca un salario valido");
            }
        } else {
            $("#crearempleo").attr("disabled", true);
        }
    }
    $("#crearempleo").click(function () {
        $.ajax({
            type: 'POST',
            data: {tipo: 1, nombreempleo: $("#nombreempleo").val(), salario: $("#salario").val(), descuentos: $("#descontar").val()},
            url: '../Admin',
            success: function (result) {
                if (result === "Creado empleo") {
                    editarTablaEmpleos();
                } else {
                    alert(result);
                }
            }
        });
    });
    var seguimiento = 0;
    function editarTablaEmpleos() {
        alert("cambiando tabla");
        var mensaje = "";
        var x = document.getElementById("cuerpoempleo");
        $.ajax({
            type: 'GET',
            data: {tipo2: 1},
            url: '../Admin',
            success: function (result) {
                $.each(result, function (index, item) {
                    if (seguimiento === 0) {
                        mensaje += "<tr><td>" + item + "</td>";
                        seguimiento++;
                    } else if (seguimiento === 1) {
                        mensaje += "<td>" + item + "</td>";
                        seguimiento++;
                    } else if (seguimiento === 2) {
                        mensaje += "<td>" + item + "</td>";
                        seguimiento++;
                    } else if (seguimiento === 3) {
                        mensaje += "<td>" + item + "</td>";
                        seguimiento++;
                    } else if (seguimiento === 4) {
                        mensaje += "<td>" + item + "</td><td><button class='btnespecial'>EDITAR</button></td></tr>";
                        seguimiento = 0;
                    }
                });
                alert(result);
                x.innerHTML = mensaje;
            }
        });
    }
    $("#creararea").click(function () {
        $.ajax({
            type: 'POST',
            data: {tipo: 2, nombrearea: $("#nombrearea").val(), contratando: $("#contratando").val()},
            url: '../Admin',
            success: function (result) {
                if (result === "Creada area") {
                    editarTablaAreas();
                } else {
                    alert(result);
                }
            }
        });
    });
    var seguimiento2 = 0;
    function editarTablaAreas() {
        var mensaje = "";
        var x = document.getElementById("cuerpoarea");
        $.ajax({
            type: 'GET',
            data: {tipo2: 2},
            url: '../Admin',
            success: function (result) {
                $.each(result, function (index, item) {
                    if(seguimiento2 === 0){
                        mensaje += "<tr><td>"+ item +"</td>";
                        seguimiento2++;
                    }else if (seguimiento2 === 1) {
                        mensaje += "<td>" + item + "</td>";
                        seguimiento2++;
                    } else if (seguimiento2 === 2) {
                        mensaje += "<td>" + item + "</td>";
                        seguimiento2++;
                    } else if (seguimiento2 === 3) {
                        mensaje += "<td>" + item + "</td>";
                        seguimiento2++;
                    } else if (seguimiento2 === 4) {
                        mensaje += "<td>" + item + "</td><td><button class='btnespecial'>EDITAR</button></td></tr>";
                        seguimiento2 = 0;
                    }
                });
                alert(result);
                x.innerHTML = mensaje;
            }
        });
    }
});