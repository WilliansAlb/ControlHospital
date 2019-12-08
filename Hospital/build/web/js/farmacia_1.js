function mostrar1(x, y) {
    if (x.style.display === 'none') {
        x.style.display = 'block';
        y.style.width = '8%';
        y.style.height = '8%';
    } else {
        x.style.display = 'none';
        y.style.width = '59%';
        y.style.height = '59%';
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
function sacando1(a, b, c) {
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
                data: {codigom: x, nombremedi: y, cantidadunid: z, totalvent: w, venta1: w, nit: a, namecl: b, ciu: c, fechav: h},
                url: '../Farmacia',
                success: function (result) {
                    resultado = result;
                    alert(resultado);
                    window.location = "farmacia_1.jsp";
                }
            });
        }
    }
}
function sacando(a, b, c) {
    if (a.length > 0 && b.length > 0 && c.length > 0) {
        sacando1(a, b, c);
    } else {
        alert("un campo está vacio");
    }
}
var eliminados = 0;
function descalcular() {
    var z = document.getElementById("calc");
    z.style.display = 'none';
}

function mostrandoDiv(x){
    var z = document.getElementById('radio');
   	for (let i=0;i<document.fcolors.test.length;i++){ 
        if (document.fcolors.test[i].value == x){
            document.getElementById(x).style.display = 'block';
        } else {
            document.getElementById(document.fcolors.test[i].value).style.display = 'none';
        }
   	} 
}
window.onload = function() {
    document.getElementById($('input:radio[name=test]:checked').val()).style.display='block';
};


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
            if (i === 1 || document.getElementById("latabla").style.display === 'none') {
                $('#latabla').show();
                $('#confirmarv').show();
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
        var table = document.getElementById("mytable");
            var rowCount = table.rows.length;
            if (rowCount === 2) {
                document.getElementById('latabla').style.display = 'none';
                document.getElementById('confirmarv').style.display = 'none';
            }
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

    $("#nombreb").on("keyup", function () {
        clearTimeout(controladorTiempo1);
        controladorTiempo1 = setTimeout(mostrandoFilas, 800);
    });
    function mostrandoFilas() {
        var imagenc = document.getElementById("cargandob");
        imagenc.src = "https://icon-library.net/images/loading-icon-transparent-background/loading-icon-transparent-background-18.jpg";
        imagenc.style.display = "inline-block";
        var buscando = document.getElementById("nombreb").value.toUpperCase();
        var table1 = document.getElementById("tablaresult1");
        var rowCount = table1.rows.length;
        var vecesa = [];
        var veceso = [];
        for (var i = 0; i < rowCount - 1; i++) {
            var nombre = document.getElementById('nomin' + i).innerText.toUpperCase();
            var codigo1 = document.getElementById('codbus' + i).innerText;
            if (buscando >= 0) {
                if (codigo1 === buscando) {
                    vecesa.push(i);
                } else {
                    veceso.push(i);
                }
            } else {
                if (!nombre.includes(buscando)) {
                    veceso.push(i);
                } else {
                    vecesa.push(i);
                }
            }
        }
        if (vecesa.length === 0) {
            if (buscando.length === 0) {
                for (var i = 0; i < rowCount - 1; i++) {
                    $("#" + i + "tabla").show();
                    imagenc.style.display = 'none';
                }
            } else {
                for (var i = 0; i < rowCount - 1; i++) {
                    $("#" + i + "tabla").show();
                }
                imagenc.src = "https://enphase.com/sites/default/files/content/Xmark_0.png";
                imagenc.title = "Ningun resultado coincide";
            }
        } else {

            for (var j = 0; j < vecesa.length; j++) {
                $("#" + vecesa[j] + "tabla").show();
            }

            if (veceso.length !== 0) {
                for (var j = 0; j < veceso.length; j++) {
                    $("#" + veceso[j] + "tabla").hide();
                }
            }
            imagenc.src = "http://www.pngmart.com/files/3/Green-Tick-Transparent-PNG.png";
        }
    }
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