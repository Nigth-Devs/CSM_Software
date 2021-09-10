<%-- 
    Document   : profile
    Created on : 15-jun-2021, 23:38:33
    Author     : Stiward
--%>

<%@page import="dto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

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
            <div class="logo-header" data-background-color="red">
                <a href="dashboard.jsp" style="text-decoration: none;">
                    <h2 class=" ml-3 text-white d-flex align-items-center">CSM Software</h2>
                </a>
                <button class="navbar-toggler sidenav-toggler ml-auto" type="button" data-toggle="collapse"
                    data-target="collapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon">
                        <i class="icon-menu"></i>
                    </span>
                </button>
                <button class="topbar-toggler more"><i class="icon-options-vertical"></i></button>
                <div class="nav-toggle">
                    <button class="btn btn-toggle toggle-sidebar">
                        <i class="icon-menu"></i>
                    </button>
                </div>
            </div>
            <!-- End Logo Header -->

            <!-- Navbar Header -->
            <nav class="navbar navbar-header navbar-expand-lg" data-background-color="red">
                <div class="container-fluid ">
                    <div class="d-inline-flex">
                        <div class="avatar"><img
                                src="<%=request.getContextPath()%>/CSM_Software/assets/img/logo_ingsistemas.png"
                                alt="logotipo de ing Sistemas" class="avatar-img rounded"></div>
                        <h1 class=" ml-3 text-white d-flex align-items-center">Ingeniería de Sistemas</h1>
                    </div>
                    <ul class="navbar-nav topbar-nav ml-md-auto align-items-center">
                        <li class="nav-item dropdown hidden-caret">
                            <a class="nav-link dropdown-toggle" href="#" id="notifDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-bell"></i>
                                <span class="notification">4</span>
                            </a>
                            <ul class="dropdown-menu notif-box animated fadeIn" aria-labelledby="notifDropdown">
                                <li>
                                    <div class="dropdown-title">You have 4 new notification</div>
                                </li>
                                <li>
                                    <div class="notif-scroll scrollbar-outer">
                                        <div class="notif-center">
                                            <a href="#">
                                                <div class="notif-icon notif-primary"> <i class="fa fa-user-plus"></i>
                                                </div>
                                                <div class="notif-content">
                                                    <span class="block">
                                                        New user registered
                                                    </span>
                                                    <span class="time">5 minutes ago</span>
                                                </div>
                                            </a>
                                            <a href="#">
                                                <div class="notif-icon notif-success"> <i class="fa fa-comment"></i>
                                                </div>
                                                <div class="notif-content">
                                                    <span class="block">
                                                        Rahmad commented on Admin
                                                    </span>
                                                    <span class="time">12 minutes ago</span>
                                                </div>
                                            </a>
                                            <a href="#">
                                                <div class="notif-img">
                                                    <img src="<%=request.getContextPath()%>/CSM_Software/assets/img/profile2.jpg"
                                                        alt="Img Profile">
                                                </div>
                                                <div class="notif-content">
                                                    <span class="block">
                                                        Reza send messages to you
                                                    </span>
                                                    <span class="time">12 minutes ago</span>
                                                </div>
                                            </a>
                                            <a href="#">
                                                <div class="notif-icon notif-danger"> <i class="fa fa-heart"></i> </div>
                                                <div class="notif-content">
                                                    <span class="block">
                                                        Farrah liked Admin
                                                    </span>
                                                    <span class="time">17 minutes ago</span>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <a class="see-all" href="javascript:void(0);">See all notifications<i
                                            class="fa fa-angle-right"></i> </a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown hidden-caret">
                            <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"
                                aria-expanded="false">
                                <div class="avatar-sm">
                                    <img src="<%=request.getContextPath()%>/CSM_Software/assets/img/profile.jfif"
                                        alt="..." class="avatar-img rounded-circle">
                                </div>
                            </a>
                            <ul class="dropdown-menu dropdown-user animated fadeIn">
                                <div class="dropdown-user-scroll scrollbar-outer">
                                    <li>
                                        <div class="user-box">
                                            <div class="avatar-lg">
                                                <img src="<%=request.getContextPath()%>/CSM_Software/assets/img/profile.jfif"
                                                    alt="image profile" class="avatar-img rounded">
                                            </div>
                                            <div class="u-text">
                                                <h4><%=user.getDocente().getNombre()%></h4>
                                                <p class="text-muted"><%=user.getRol().getRol()%></p>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/CSM_Software/CSM/director/dashboard/perfil/profile.jsp">Perfil</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/CSM_Software/CSM/director/dashboard/perfil/edit-profile.jsp">Configuración</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="<%=request.getContextPath()%>/CSM_Software/CSM/sign-in/singin.jsp">Salir</a>
                                    </li>
                                </div>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
            <!-- End Navbar -->
        </div>

        <!-- Sidebar -->
        <div class="h-100 sidebar sidebar-style-2">
            <div class="h-100 sidebar-wrapper scrollbar scrollbar-inner">
                <div class="sidebar-content">
                    <div>
                        <div class="user">
                            <div class="avatar-sm float-left mr-2">
                                <img src="<%=request.getContextPath()%>/CSM_Software/assets/img/profile.jfif" alt="..."
                                    class="avatar-img rounded-circle">
                            </div>
                            <div class="info">
                                <a data-toggle="collapse" href="#tables">
                                    <span>
                                        <%=user.getDocente().getNombre()%>
                                        <span class="user-level"><%=user.getRol().getRol()%></span>
                                        <span class="caret"></span>
                                    </span>
                                </a>
                                <div class="clearfix"></div>
                                <div class="collapse show" id="tables">
                                    <ul class="nav">
                                        <li class="active">
                                            <a href="<%=request.getContextPath()%>/CSM_Software/CSM/director/dashboard/perfil/profile.jsp">
                                                <span class="link-collapse">Perfil</span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="<%=request.getContextPath()%>/CSM_Software/CSM/director/dashboard/perfil/edit-profile.jsp">
                                                <span class="link-collapse">Configuración</span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="<%=request.getContextPath()%>/CSM_Software/CSM/sign-in/singin.jsp">
                                                <span class="link-collapse">Salir</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <ul class="nav nav-primary">
                            <li class="nav-item">
                                <a href="<%=request.getContextPath()%>/CSM_Software/CSM/director/dashboard/dashboard.jsp"
                                    class="collapsed" aria-expanded="false">
                                    <i class="fas fa-home"></i>
                                    <p>Dashboard</p>
                                </a>
                            </li>
                            <li class="nav-section">
                                <span class="sidebar-mini-icon">
                                    <i class="fa fa-ellipsis-h"></i>
                                </span>
                                <h4 class="text-section">Components</h4>
                            </li>
                            <li class="nav-item">
                                <a href="<%=request.getContextPath()%>/ControladorPensum?accion=listarPensum">
                                    <i class="fas fa-layer-group"></i>
                                    <p>Pensum</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a data-toggle="collapse" href="#sidebarLayouts">
                                    <i class="fas fa-th-list"></i>
                                    <p>Microcurriculo</p>
                                    <span class="caret"></span>
                                </a>
                                <div class="collapse" id="sidebarLayouts">
                                    <ul class="nav nav-collapse">
                                        <li>
                                            <a href="<%=request.getContextPath()%>/ControladorMicrocurriculo?accion=listarTodos">
                                                <span class="sub-item">Consultar Microcurriculo</span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="<%=request.getContextPath()%>/CSM_Software/CSM/director/dashboard/microcurriculo/solicitudes-microcurriculo.jsp">
                                                <span class="sub-item">Solicitudes Microcurriculo</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li class="nav-item">
                                <a href="<%=request.getContextPath()%>/CSM_Software/CSM/director/dashboard/seguimiento.jsp">
                                    <i class="fas fa-chart-bar"></i>
                                    <p>Seguimiento</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="<%=request.getContextPath()%>/ControladorGrupos?accion=listar">
                                    <i class="fas fa-table"></i>
                                    <p>Grupos</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="<%=request.getContextPath()%>/ControladorDocente?action=listarDocente">
                                    <i class="fas fa-user-friends"></i>
                                    <p>Docentes</p>
                                </a>
                            </li>
                        </ul>
                        <div class=" d-flex justify-content-center align-items-end w-100 logoUFPS">
                            <img src="<%=request.getContextPath()%>/CSM_Software/assets/img/Logo-nuevo-vertical.png"
                                alt="..." class="avatar-img w-75">
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
                                            <img src="<%=request.getContextPath()%>/CSM_Software/assets/img/profile.jfif"
                                                alt="..." class="avatar-img rounded-circle">
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
                                                    <th><%=user.getDocente().getCodigoDocente()%></th>
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
                                                    <td><%=user.getDocente().getDepartamentoId().getNombreDepartamento()%>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td><b>Programa</b></td>
                                                    <td>Ingeniería de Sistemas</td>
                                                </tr>
                                                <tr>
                                                    <td><b>Rol</b></td>
                                                    <td><%=user.getRol().getRol()%></td>
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
            <footer class="footer">
                <div class="container-fluid">
                    <nav class="pull-left">
                        <ul class="nav">
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    Nigth Devs
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    Help
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                    Licenses
                                </a>
                            </li>
                        </ul>
                    </nav>
                    <div class="copyright ml-auto">
                        2021 &copy; All Rights Reserved.
                        Desarrollado por: <a href="#">Nigth Devs</a>
                    </div>
                </div>
            </footer>
        </div>
    </div>
    <!--   Core JS Files   -->
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/core/jquery.3.2.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/core/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/core/bootstrap.min.js"></script>
    <!-- jQuery UI -->
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js">
    </script>
    <script
        src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js">
    </script>

    <!-- jQuery Scrollbar -->
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js">
    </script>
    <!-- Atlantis JS -->
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/atlantis.min.js"></script>
    <!-- Atlantis DEMO methods, don't include it in your project! -->
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/setting-demo2.js"></script>
    <script>
        $('#displayNotif').on('click', function () {
            var placementFrom = $('#notify_placement_from option:selected').val();
            var placementAlign = $('#notify_placement_align option:selected').val();
            var state = $('#notify_state option:selected').val();
            var style = $('#notify_style option:selected').val();
            var content = {};

            content.message = 'Turning standard Bootstrap alerts into "notify" like notifications';
            content.title = 'Bootstrap notify';
            if (style == "withicon") {
                content.icon = 'fa fa-bell';
            } else {
                content.icon = 'none';
            }
            content.url = 'index.jsp';
            content.target = '_blank';

            $.notify(content, {
                type: state,
                placement: {
                    from: placementFrom,
                    align: placementAlign
                },
                time: 1000,
            });
        });
    </script>
</body>

</html>