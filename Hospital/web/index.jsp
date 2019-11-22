<%-- 
    Document   : index
    Created on : 20/10/2019, 05:25:17 PM
    Author     : yelbetto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>El Renacido</title>
        <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon"> 
        <link rel='stylesheet' href='css/css1.css'>

        <link rel="stylesheet" href="../css/strctr.css">
        <script type="text/javascript" src="../js/funs.js"></script>
    </head>
    <body>
        <%HttpSession s = request.getSession();

            if (s.getAttribute("usuario") == null) {%>
        <%
            if (s.getAttribute("mensaje") != null) {%>
        <div id="fme" name="fme">
            <div id="mensaje" name="mensaje">
                <center>
                    <button onclick="ocultar()">CERRAR MENSAJE</button>
                    <br>
                    <br>
                    <img src="img/problema.jpg" width="30%" height="30%" alt="Mensaje" title="HOUSTON, WE HAVE A PROBLEM" style="float:left;">
                    <h1><%out.print(s.getAttribute("mensaje"));%></h1>
                </center>
            </div>
        </div>
        <%}%> 
        <div class="login-form">
            <div class="login-wrap">
                <div class="login-html">
                    <center>
                        <img src="img/hosp.png" width="20%" height="20%" style="margin: 10px 0px 0px"/>
                        <h5 style="margin: 10px;color:#137347">HOSPITAL</h5>
                        <h1 style="margin: 10px;color:#137347">EL RENACIDO</h1>
                        <br>
                    </center>
                    <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Ingresar</label>
                    <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Crear cuenta</label>
                    <div class="login-form">
                        <div class="sign-in-htm">
                            <form action="Inicio1" method="POST">
                                <div class="group">
                                    <label for="user" class="label">Usuario</label>
                                    <input id="user1" name="user1" type="text" class="input" required>
                                </div>
                                <div class="group">
                                    <label for="pass" class="label">Contraseña</label>
                                    <input id="pass1" name="pass1" type="password" class="input" data-type="password" required>
                                </div>
                                <div class="group">
                                    <input id="check" type="checkbox" class="check" checked>
                                    <label for="check"><span class="icon"></span>Mantener la sesion abierta</label>
                                </div>
                                <div class="group">
                                    <input type="submit" class="button" value="Ingresar">
                                </div>
                            </form>
                            <hr width="100%" />
                            <center>
                                <img src="img/OIHHFX0.jpg" width="60%" height="60%" style="margin: 10px 0px 0px"/>
                            </center>
                        </div>
                        <div class="sign-up-htm">
                            <form action="CrearUsuario" method="POST" enctype="multipart/form-data">
                                <div class="group">
                                    <label for="user" class="label">Id Empleado</label>
                                    <input id="user" name="user" type="text" class="input" required>
                                </div>
                                <div class="group">
                                    <label for="nombre" class="label">Nombre</label>
                                    <input id="nombre" name="nombre" type="text" class="input" required>
                                </div>
                                <div class="group">
                                    <label for="pass" class="label">Password</label>
                                    <input id="pass" name="pass" type="password" class="input" data-type="password" required>
                                </div>

                                <div class="group">
                                    <label for="pass2" class="label">Repite la password</label>
                                    <input id="pass2" name="pass2" type="password" class="input" data-type="password" required="">
                                </div>
                                <div class="group">
                                    <label for="fichero" class="label">Foto de perfil (opcional)</label>
                                    <input id="fichero" name="fichero" type="file" class="input">
                                </div>
                                <div class="group">
                                    <input type="submit" class="button" value="Solicitar cuenta">
                                </div>
                            </form>
                            <hr width="100%" />
                            <div class="foot-lnk">
                                <center>
                                    <label for="tab-1"><a id="agrandar">¿TIENES UNA CUENTA?</a></label>
                                </center>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%}%>
        <%
            if (s.getAttribute("mensaje") != null) {%>
        <script>
            function ocultar() {
                var x = document.getElementById("mensaje");
                var y = document.getElementById("fme");
                if (x.style.display = 'block') {
                    x.style.display = 'none';
                    y.style.display = 'none';
                }
            }
        </script>
        <%}
            s.setAttribute("mensaje", null);%> 
    </body>
</html>
