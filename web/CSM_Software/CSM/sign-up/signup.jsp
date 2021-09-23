<%-- 
    Document   : signup
    Created on : 15-jun-2021, 23:42:29
    Author     : Stiward
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>CSM Software</title>
        <meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />
        <link rel="icon" href="<%=request.getContextPath()%>/CSM_Software/assets/img/icon.ico" type="image/x-icon" />
        <!-- Custom CSS -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/singup.css">

        <!-- CSS Files -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/checkradio.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/microcurriculo.css">
        <!-- CSS Just for demo purpose, don't include it in your project -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/demo.css">
        <!-- CSS Custom -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/microcurriculo.css">
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/JQuery.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/index.js" type="text/javascript"></script>
    </head>

    <body>
        <div class="cuerpologin">
            <div class="row vh-20">
                <div class="row w-100 d-flex justify-content-center">
                    <h2 class="t-white mt-5">Registro</h2>
                </div>
                <div class="row w-100 ">
                    <div class="offset-md-10 col-2">
                        <img class="logoUFPS" src="<%=request.getContextPath()%>/CSM_Software/assets/img/Logo-nuevo-vertical.png" alt="logo de la UFPS">
                    </div>
                </div>
            </div>
            <div class=" row d-flex justify-content-center">
                <form class="w-75" action="<%=request.getContextPath()%>/ControladorDocente" method="POST" accept-charset="ISO-8859-1">
                    <div class=" fbr plogin  gbcolor">
                        <div class="mb-3 w-100 d-flex justify-content-center align-items-center">
                            <img class="account" src="<%=request.getContextPath()%>/CSM_Software/assets/img/profile.svg" alt="">
                        </div>
                        <div class="mb-3 row w-100">
                            <h3 class="col-2">Nombres</h3>
                            <input type="text" class="col form-control  singin" name="txtNombre" placeholder="Nombres" required>
                            <h3 class="col-2">Apellidos</h3>
                            <input type="text" class="col form-control  singin" name="txtApellido" placeholder="Apellidos" required>
                        </div>
                        <div class="mb-3 row w-100">
                            <h3 class="col-2">Codigo</h3>
                            <input type="text" class="col form-control  singin" name="txtCodigo" placeholder="Codigo" required>
                            <h3 class="col-2" style="visibility: hidden">Codigo</h3>
                            <input type="text" class="col form-control  singin" placeholder="Correo" id="" style="visibility: hidden">
                        </div>
                        <div class="mb-3 row w-100">
                            <h3 class="col-2">Contraseña</h3>
                            <input type="password" class="col form-control   singin" name="txtPassword" placeholder="Contraseña" required>
                            <h3 class="col-2">Confirmar Contraseña</h3>
                            <input type="password" class="col form-control  singin" name="txtPassword" placeholder="Confirmar Contraseña" required>
                        </div>
                        <div class="mb-3 row w-100">
                            <h3 class="col-2">Facultad</h3>
                            <select class=" col form-control" name="optionFacultad" id="optionFacultad" onchange ="searchDepartamento()" required></select>
                            <h3 class="col-2">Departamento</h3>
                            <select class=" col form-control" name="optionDepartamento" id="optionDepartamento" required></select>
                        </div>
                        <!--botones-->
                        <div class="w-100 d-flex justify-content-center ">
                            <div class="w-25">
                                <a class="btn btn-danger mb-2 w-100 brlogin mr-2" href="<%=request.getContextPath()%>/CSM_Software/CSM/sign-in/singin.jsp">Iniciar
                                    Sesion</a>
                            </div>
                            <div class="w-25">
                                <input class="btn btn-danger mb-2 w-100 brlogin ml-2" type="submit" value="Registrar">
                            </div>
                        </div>
                        <!--botones-->
                    </div>
                </form>
            </div>
        </div>
        <!--   Core JS Files   -->
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/core/jquery.3.2.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/core/popper.min.js"></script>
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/core/bootstrap.min.js"></script>
        <!-- jQuery UI -->
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js"></script>
        <!-- jQuery Scrollbar -->
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
        <!-- Atlantis JS -->
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/atlantis.min.js"></script>
    </body>

</html>
