function mostrar1(x, y) {
    if (x.style.display === 'none') {
        x.style.display = 'block';
        y.style.width = '8%';
        y.style.height = '8%';
    } else {
        x.style.display = 'none';
        y.style.width = '38%';
        y.style.height = '38%';
    }
}
function mostrarInputs(dato) {
    var x = document.getElementById("nuevoc");
    var p = 0;
    $('#costom').val('0');
    $('#minimom').val('0');
    if (dato === "todo") {
        x.style.display = 'block';
    } else {
        x.style.display = 'none';
        $.ajax({
            type: 'GET',
            data: {id2: dato},
            url: '../Farmacia',
            success: function (result) {
                $.each(result, function (index, item) {
                    if (p === 0) {
                        $('#costom').val(item);
                    } else {
                        $('#minimom').val(item);
                    }
                    p++;
                });
            }
        });
    }
    $('#cantidadm').val('0');
}

function mostrarI(dato) {
    $.ajax({
        type: 'GET',
        data: {fac: dato},
        url: '../Farmacia',
        success: function (result) {
            $('#costou').val(result);
        }
    });
}
function calcular() {
    var x = document.getElementById("cantidadm").value;
    var y = document.getElementById("costom").value;
    var z = document.getElementById("calc");
    var a = document.getElementById("costototalm");
    var e = document.getElementById("nombrem");

    if (x !== '0' && y !== '0') {
        if (z.style.display === 'none') {
            z.style.display = 'block';
            a.value = x * y;
        } else {
            a.value = x * y;
        }
    } else {
        if (x === '0') {
            alert("El aspecto de cantidad no puede ser 0");
        } else {
            if (e.value === 'todo') {
                alert("El aspecto de costo no puede ser 0");
            }
        }
    }
}
function sacando1(a,b,c) {
    var resultado = "";
    var table = document.getElementById("mytable");
    var rowCount = table.rows.length + eliminados;
    for (var i = 1; i < (rowCount - 1); i++) {
        if ($("#codigo" + i).length > 0) {
            var h = document.getElementById("fechav").value;
            var x = document.getElementById("codigo" + i).innerText;
            var y = document.getElementById("nom" + i).innerText;
            var z = document.getElementById("cantidaduni" + i).innerText;
            var w = document.getElementById("totalventa" + i).innerText;
            $.ajax({
                type: 'POST',
                data: {codigom: x, nombremedi: y, cantidadunid: z, totalvent: w, venta1: w,nit:a,namecl:b,ciu:c,fechav:h},
                url: '../Farmacia',
                success: function (result) {
                    resultado = result;
                    alert(resultado);
                    window.location = "farmacia.jsp";
                }
            });
        }
    }
}
function sacando(a,b,c){
    if (a.length > 0 && b.length > 0 && c.length > 0){
        sacando1(a,b,c);
    } else {
        alert("un campo está vacio");
    }
}
var eliminados = 0;
function descalcular() {
    var z = document.getElementById("calc");
    z.style.display = 'none';
}
var util = 0;
$(document).ready(function () {
    $('#ingm').click(function () {
        calcular();
    });
    var i = 1;
    var total = 0;
    $('#agregarv').click(function () {
        if (document.getElementById("cantidadmv").value == 0) {
            alert("El aspecto de cantidad debe de ser diferente a 0");
        } else {
            if (i === 1) {
                $('#latabla').show();
            }
            var nombre = document.getElementById("nombrev").value;
            var nombrev = $('select[name="nombrev"] option:selected').text();
            var cedula = document.getElementById("cantidadmv").value;
            var costou = document.getElementById("costou").value;

            var semitotal = costou * cedula;
            var fila = '<tr id="row' + i + '"><td id="codigo' + i + '">' + nombre + '</td><td id="nom' + i + '">' + nombrev + '</td><td id="costounidad' + i + '">' + costou + '</td><td id="cantidaduni' + i + '">' + cedula + '</td><td id="totalventa' + i + '">' + cedula * costou + '</td><td><button type="button" name="' + semitotal + '" id="' + i + '" class="btn btn-danger btn_remove">Quitar</button></td></tr>'; //esto seria lo que contendria la fila
            i++;
            $('#mytable tr:last').after(fila);
            var table = document.getElementById("mytable");
            var rowCount = table.rows.length;
            if (rowCount >= 3) {
                table.deleteRow(rowCount - 2);
            }
            total += semitotal;
            var fila1 = '<tr id="totaltabla"><td>Total</td><td colspan=5>' + total + '</td></tr>';
            $('#mytable tr:last').after(fila1);

        }

    });

    $(document).on('click', '.btn_remove', function () {
        var button_id = $(this).attr("id");
        var button_name = $(this).attr("name");
        total -= button_name;
        $('#row' + button_id + '').remove();
        $('#totaltabla').remove();
        var fila1 = '<tr id="totaltabla"><td>Total</td><td colspan=5>' + total + '</td></tr>';
        $('#mytable tr:last').after(fila1);
        eliminados++;
    });

    $('#confirmarv').click(function () {
        var x = document.getElementById("datosv");
        if (x.style.display === 'none') {
            x.style.display = 'block';
            $('#nit').val("");
            $('#nombreventa').val("");
            $('#ciudad').val("");
        }
    });
    var controladorTiempo = "";

    function codigoAJAX() {
        var resultado = "";
        var texto = document.getElementById("nombremn").value;
        if ($('#nombremn').val().length === 0) {
            document.getElementById("cargando").src = "https://enphase.com/sites/default/files/content/Xmark_0.png";
        } else {
            $.ajax({
                type: 'GET',
                data: {txt: texto},
                url: '../Farmacia',
                success: function (result) {
                    $('#temp').val(result);

                    if (document.getElementById("temp").value === '1') {
                        document.getElementById("cargando").src = "https://enphase.com/sites/default/files/content/Xmark_0.png";
                    } else
                        document.getElementById("cargando").src = "http://www.pngmart.com/files/3/Green-Tick-Transparent-PNG.png";
                }
            });
        }
        util = 0;
    }

    $("#nombremn").on("keyup", function () {
        if (util === 0) {
            util = 1;
            document.getElementById("cargando").src = "https://icon-library.net/images/loading-icon-transparent-background/loading-icon-transparent-background-18.jpg";

        }
        clearTimeout(controladorTiempo);
        controladorTiempo = setTimeout(codigoAJAX, 250);
    });
    var controladorTiempo1 = "";
    $("#nit").on("keyup", function () {
        clearTimeout(controladorTiempo1);
        controladorTiempo1 = setTimeout(codigoAJAX1, 1000);
    });
    $('#nombremn').click(function () {
        if (util === 0) {
            $("#cargando").show();
        }
    });

    var k = 0;
    function codigoAJAX1() {
        var texto = document.getElementById("nit").value;
        if ($('#nit').val().length < 8) {
            $('#nombreventa').val("");
            $('#ciudad').val("");
        } else {
            $.ajax({
                type: 'GET',
                data: {txt1: texto},
                url: '../Farmacia',
                success: function (result) {
                    $.each(result, function (index, item) {
                        if (k === 0) {
                            $('#nombreventa').val(item);
                        } else {
                            $('#ciudad').val(item);
                        }
                        k++;
                    });
                }
            });
        }
        k = 0;
    }
});