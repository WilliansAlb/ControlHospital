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
    if (dato === "todo") {
        x.style.display = 'block';
    } else {
        x.style.display = 'none';
    }
}
function mostrarI(dato) {
    $.ajax({
        type: 'POST',
        data: {fullname: dato},
        url: '../Redirect',
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

    if (x !== 0 && y !== 0) {
        if (z.style.display === 'none') {
            z.style.display = 'block';
            a.value = x * y;
        }
    } else {
        if (x == 0) {
            alert("El aspecto de cantidad no puede ser 0");
        } else {
            if (e.value == 'todo') {
                alert("El aspecto de costo no puede ser 0");
            }
        }
    }
}
function descalcular() {
    var z = document.getElementById("calc");
    z.style.display = 'none';
}
$(document).ready(function () {
    $('#ingm').click(function () {
        var fullname = $('#nombrem').val();
        var colocando = $('#costom');
        $.ajax({
            type: 'POST',
            data: {fullname: fullname},
            url: '../Redirect',
            success: function (result) {
                colocando.val(result);
                calcular();
            }
        });
    });
    var i = 1;
    $('#agregarv').click(function () {
        var nombre = document.getElementById("nombrev").value;
        var nombrev = $('select[name="nombrev"] option:selected').text();
        var cedula = document.getElementById("cantidadmv").value;
        var costou = document.getElementById("costou").value;
        var fila = '<tr id="row' + i + '"><td>' + nombre + '</td><td>' + nombrev + '</td><td>' + costou + '</td><td>' + cedula + '</td><td><button type="button" name="remove" id="' + i + '" class="btn btn-danger btn_remove">Quitar</button></td></tr>'; //esto seria lo que contendria la fila
        i++;
        $('#mytable tr:last').after(fila);
    });

    $(document).on('click', '.btn_remove', function () {
        var button_id = $(this).attr("id");
        $('#row' + button_id + '').remove();
    });
    
    $('#confirmarv').click(function (){
       var x = document.getElementById("datosv");
       if(x.style.display === 'none'){
           x.style.display = 'block';
       }
        
    });
});