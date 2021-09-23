<%-- 
    Document   : edit-profile
    Created on : 15-jun-2021, 23:38:18
    Author     : Stiward
--%>

<%@page import="dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>CSM Software</title>
        <meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />
        <link rel="icon" href="<%=request.getContextPath()%>/CSM_Software/assets/img/icon.ico" type="image/x-icon" />

        <!-- Fonts and icons -->
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/webfont/webfont.min.js"></script>
        <script>
            WebFont.load({
                google: {
                    "families": ["Lato:300,400,700,900"]
                },
                custom: {
                    "families": ["Flaticon", "Font Awesome 5 Solid", "Font Awesome 5 Regular",
                        "Font Awesome 5 Brands",
                        "simple-line-icons"
                    ],
                    urls: ['<%=request.getContextPath()%>/CSM_Software/assets/css/fonts.min.css']
                },
                active: function () {
                    sessionStorage.fonts = true;
                }
            });
        </script>

        <!-- CSS Files -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/atlantis.min.css">
        <!-- CSS Just for demo purpose, don't include it in your project -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/demo.css">
    </head>

    <body>
        <%
            Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        %>
        <div class="wrapper">
            <div class="main-header">

                <!-- Logo Header -->
                <jsp:include page="/CSM_Software/includes/header.jsp" />
                <!-- End Logo Header -->

                <!-- Navbar Header -->
                <jsp:include page="/CSM_Software/includes/navbar.jsp" />
                <!-- End Navbar -->

            </div>

            <!-- Sidebar -->
            <jsp:include page="/CSM_Software/includes/sidebar.jsp" />
            <!-- End Sidebar -->

            <div class="main-panel">
                <div class="content">
                    <div class="page-inner">
                        <div class="page-header">
                            <h4 class="page-title">Perfil</h4>
                            <div class="ml-md-auto">
                                <ul class="breadcrumbs">
                                    <li class="nav-home">
                                        <a href="<%=request.getContextPath()%>/CSM_Software/CSM/director/dashboard/dashboard.jsp">
                                            <i class="flaticon-home"></i>
                                        </a>
                                    </li>
                                    <li class="separator">
                                        <i class="flaticon-right-arrow"></i>
                                    </li>
                                    <li class="nav-item">
                                        <a href="#">Perfil</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="row">
                            <!-- Perfil -->
                            <div class="col-md-3">
                                <div class="card">
                                    <div class="card-header">
                                        <h4 class="card-title"><b>Perfil</b></h4>
                                    </div>
                                    <div class="card-body d-flex justify-content-center">
                                        <p class="demo">
                                        <div class="avatar avatar-xxl">
                                            <img src="<%=request.getContextPath()%>/CSM_Software/assets/img/profile.jfif" alt="..." class="avatar-img rounded-circle">
                                        </div>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <!-- End Perfil -->

                            <!-- Detalles Usuario -->
                            <div class="col-md-9">
                                <div class="card">
                                    <div class="card-header">
                                        <h4 class="card-title"><b>Detalles del Usuario</b></h4>
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table class="table table-hover">
                                                <tbody>
                                                    <tr>
                                                        <th><b>Codigo</b></th>
                                                        <th><%=user.getDocente().getCodigo()%></th>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Nombres</b></td>
                                                        <td><%=user.getDocente().getNombre()%></td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Apellidos</b></td>
                                                        <td><%=user.getDocente().getApellido()%></td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Facultad</b></td>
                                                        <td>Ingenieria</td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Departamento</b></td>
                                                        <td><%=user.getDocente().getProgramaCodigo().getDepartamentoId().getNombre() %>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Programa</b></td>
                                                        <td>Ingeniería de Sistemas</td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Rol</b></td>
                                                        <td><%=user.getRolId().getRol()%></td>
                                                    </tr>
                                                    <tr>
                                                        <td><b>Estado</b></td>
                                                        <td><%=(user.getDocente().getEstado() == 1) ? "Activo" : "Inactivo"%>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- End Detalles Usuario -->
                        </div>
                    </div>
                </div>

                <!-- Footer -->
                <jsp:include page="/CSM_Software/includes/footer.jsp" />
                <!-- End Footer -->

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