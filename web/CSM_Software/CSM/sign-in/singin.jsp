<%-- 
    Document   : singin
    Created on : 15-jun-2021, 18:42:59
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
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/singin.css">
        <!-- CSS Files -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/checkradio.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/microcurriculo.css">
        <!-- CSS Just for demo purpose, don't include it in your project -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/demo.css">
        <!-- CSS Custom -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/microcurriculo.css">
    </head>

    <body>
        <div class="row cuerpologin">
            <div class="logoUFPS col d-flex justify-content-center align-items-center">
                <img class="w-50" src="<%=request.getContextPath()%>/CSM_Software/assets/img/Logo-nuevo-vertical.png" alt="logo de la UFPS">
            </div>
            <div class="col d-flex justify-content-center align-items-center ">
                <div class="  w-50 h-50">
                    <form action="<%=request.getContextPath()%>/ControladorLogin" method="POST">
                        <div class=" fbr plogin prlogin gbcolor">
                            <div class="mb-3 w-100 d-flex justify-content-center">
                                <img class="w-50" src="<%=request.getContextPath()%>/CSM_Software/assets/img/profile.svg" alt="">
                            </div>
                            <div class="mb-3">
                                <input class="form-control brlogin singin" type="number" name="codigo" placeholder="Codigo" required>
                            </div>
                            <div class="mb-3">
                                <input class="form-control brlogin singin" type="password" name="clave" placeholder="Contraseña" required>
                            </div>
                            <div class="mb-3  d-flex justify-content-around">
                                <div>
                                    <label for="opt1" class="radio">
                                        <input class="hidden" type="radio" id="opt1" name="rol" value="1" required>
                                        <span class="label"></span>Director de Programa
                                    </label>

                                    <label for="opt2" class="radio">
                                        <input class="hidden" type="radio" id="opt2" name="rol" value="2" required>
                                        <span class="label"></span>Docente
                                    </label>
                                </div>
                            </div>
                            <div class="w-100 d-flex justify-content-center ">
                                <input class="btn btn-danger mb-2 w-100 brlogin" type="submit" name="accion" value="Iniciar Sesion">  
                            </div>
                            <div class="w-100 d-flex justify-content-center ">
                                <a class="btn btn-danger mb-2 w-100 brlogin" href="<%=request.getContextPath()%>/CSM_Software/CSM/sign-up/signup.jsp">Registrarse</a>
                            </div>
                        </div>
                    </form>
                </div>
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
