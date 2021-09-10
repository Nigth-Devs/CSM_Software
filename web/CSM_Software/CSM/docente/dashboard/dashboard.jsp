<%-- 
    Document   : dashboard
    Created on : 17-jun-2021, 16:38:59
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
        <link rel="icon" href="../../../assets/img/icon.ico" type="image/x-icon" />

        <!-- Fonts and icons -->
        <script src="../../../assets/js/plugin/webfont/webfont.min.js"></script>
        <script>
            WebFont.load({
                google: {
                    "families": ["Lato:300,400,700,900"]
                },
                custom: {
                    "families": ["Flaticon", "Font Awesome 5 Solid", "Font Awesome 5 Regular", "Font Awesome 5 Brands",
                        "simple-line-icons"
                    ],
                    urls: ['../../../assets/css/fonts.min.css']
                },
                active: function () {
                    sessionStorage.fonts = true;
                }
            });
        </script>

        <!-- CSS Files -->
        <link rel="stylesheet" href="../../../assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="../../../assets/css/atlantis.min.css">
        <link rel="stylesheet" href="../../../assets/css/dashboard.css">

        <!-- CSS Just for demo purpose, don't include it in your project -->
        <link rel="stylesheet" href="../../../assets/css/demo.css">
    </head>

    <body>
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
                            <div class="avatar"><img src="../../../assets/img/logo_ingsistemas.png"
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
                                                        <img src="../../../assets/img/profile2.jpg" alt="Img Profile">
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
                                        <img src="../../../assets/img/profile-docente.jfif" alt="..."
                                             class="avatar-img rounded-circle">
                                    </div>
                                </a>
                                <ul class="dropdown-menu dropdown-user animated fadeIn">
                                    <div class="dropdown-user-scroll scrollbar-outer">
                                        <li>
                                            <div class="user-box">
                                                <div class="avatar-lg">
                                                    <img src="../../../assets/img/profile-docente.jfif" alt="image profile"
                                                         class="avatar-img rounded">
                                                </div>
                                                <div class="u-text">
                                                    <%
                                                        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
                                                    %>
                                                    <h4><%=user.getDocente().getNombre()%></h4>
                                                    <p class="text-muted">Docente</p>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="dropdown-divider"></div>
                                            <a class="dropdown-item" href="perfil/profile.jsp">My Profile</a>
                                            <div class="dropdown-divider"></div>
                                            <a class="dropdown-item" href="perfil/edit-profile.jsp">Account Setting</a>
                                            <div class="dropdown-divider"></div>
                                            <a class="dropdown-item" href="../../sign-in/singin.jsp">Logout</a>
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
            <div class=" h-100  sidebar sidebar-style-2">
                <div class=" h-100  sidebar-wrapper scrollbar scrollbar-inner">
                    <div class="sidebar-content">
                        <div class="user">
                            <div class="avatar-sm float-left mr-2">
                                <img src="../../../assets/img/profile-docente.jfif" alt="..." class="avatar-img rounded-circle">
                            </div>
                            <div class="info">
                                <a data-toggle="collapse" href="#collapseExample" aria-expanded="true">
                                    <span>
                                        <%=user.getDocente().getNombre()%>
                                        <span class="user-level">Docente</span>
                                        <span class="caret"></span>
                                    </span>
                                </a>
                                <div class="clearfix"></div>
                                <div class="collapse in" id="collapseExample">
                                    <ul class="nav">
                                        <li>
                                            <a href="perfil/profile.jsp">
                                                <span class="link-collapse">My Profile</span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="perfil/edit-profile.jsp">
                                                <span class="link-collapse">Account Setting</span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="../../sign-in/singin.jsp">
                                                <span class="link-collapse">Logout</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <ul class="nav nav-primary">
                            <li class="nav-item active">
                                <a href="dashboard.jsp" class="collapsed" aria-expanded="false">
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
                                <a href="pensum.jsp">
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
                                            <a href="../../../../ControladorMicrocurriculo?accion=listarTodos">
                                                <span class="sub-item">Consultar Microcurriculo</span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="microcurriculo/solicitudes-microcurriculo.jsp">
                                                <span class="sub-item">Mis Solicitudes Microcurriculo</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li class="nav-item">
                                <a href="seguimiento.jsp">
                                    <i class="fas fa-chart-bar"></i>
                                    <p>Seguimiento</p>
                                </a>
                            </li>
                        </ul>
                        <div class=" d-flex justify-content-center align-items-end w-100 logoUFPS">
                            <img src="../../../assets/img/Logo-nuevo-vertical.png" alt="..." class="avatar-img w-75">
                        </div>
                    </div>
                </div>

            </div>
            <!-- End Sidebar -->

            <div class="main-panel">
                <div class="content">
                    <div class="page-inner">
                        <div class="page-header">
                            <h4 class="page-title">Dashboard</h4>
                            <div class="ml-md-auto">
                                <ul class="breadcrumbs">
                                    <li class="nav-home">
                                        <a href="#">
                                            <i class="flaticon-home"></i>
                                        </a>
                                    </li>
                                    <li class="separator">
                                        <i class="flaticon-right-arrow"></i>
                                    </li>
                                    <li class="nav-item">
                                        <a href="#">Home</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="row">
                            <!-- Cards Dashboard -->
                            <div class="col-sm-6 col-md-3">
                                <div class="card card-stats card-primary card-round">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-5">
                                                <div class="icon-big text-center">
                                                    <i class="flaticon-users"></i>
                                                </div>
                                            </div>
                                            <div class="col-7 col-stats">
                                                <div class="numbers">
                                                    <p class="card-category">Docentes Activos</p>
                                                    <h4 class="card-title">50</h4>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="card card-stats card-info card-round">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-5">
                                                <div class="icon-big text-center">
                                                    <i class="flaticon-layers"></i>
                                                </div>
                                            </div>
                                            <div class="col-7 col-stats">
                                                <div class="numbers">
                                                    <p class="card-category">Pensum</p>
                                                    <h4 class="card-title">3</h4>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="card card-stats card-warning card-round">
                                    <div class="card-body ">
                                        <div class="row">
                                            <div class="col-5">
                                                <div class="icon-big text-center">
                                                    <i class="flaticon-interface-2"></i>
                                                </div>
                                            </div>
                                            <div class="col-7 col-stats">
                                                <div class="numbers">
                                                    <p class="card-category">Materias</p>
                                                    <h4 class="card-title">35</h4>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <div class="card card-stats card-danger card-round">
                                    <div class="card-body ">
                                        <div class="row">
                                            <div class="col-5">
                                                <div class="icon-big text-center">
                                                    <i class="flaticon-file-1"></i>
                                                </div>
                                            </div>
                                            <div class="col-7 col-stats">
                                                <div class="numbers">
                                                    <p class="card-category">Microcurriculos</p>
                                                    <h4 class="card-title">105</h4>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin Cards Dashboard -->

                            <!-- Tabla % Seguimiento -->
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-header text-center">
                                        <div class="card-title">Porcentaje de Cumplimiento</div>
                                    </div>
                                    <div class="card-body">
                                        <div class="d-flex justify-content-center align-items-center"
                                             id="columnchart_values"></div>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin Tabla % Seguimiento -->
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
        <script src="../../../assets/js/core/jquery.3.2.1.min.js"></script>
        <script src="../../../assets/js/core/popper.min.js"></script>
        <script src="../../../assets/js/core/bootstrap.min.js"></script>

        <!-- jQuery UI -->
        <script src="../../../assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
        <script src="../../../assets/js/plugin/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js"></script>

        <!-- jQuery Scrollbar -->
        <script src="../../../assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>

        <!-- Chart JS -->
        <script src="https://www.gstatic.com/charts/loader.js"></script>

        <!-- jQuery Sparkline -->
        <script src="../../../assets/js/plugin/jquery.sparkline/jquery.sparkline.min.js"></script>

        <!-- Datatables -->
        <script src="../../../assets/js/plugin/datatables/datatables.min.js"></script>

        <!-- Bootstrap Notify -->
        <script src="../../../assets/js/plugin/bootstrap-notify/bootstrap-notify.min.js"></script>

        <!-- jQuery Vector Maps -->
        <script src="../../../assets/js/plugin/jqvmap/jquery.vmap.min.js"></script>
        <script src="../../../assets/js/plugin/jqvmap/maps/jquery.vmap.world.js"></script>

        <!-- Sweet Alert -->
        <script src="../../../assets/js/plugin/sweetalert/sweetalert.min.js"></script>

        <!-- Atlantis JS -->
        <script src="../../../assets/js/atlantis.min.js"></script>

        <!-- Atlantis DEMO methods, don't include it in your project! -->
        <script src="../../../assets/js/setting-demo.js"></script>
        <script src="../../../assets/js/demo.js"></script>

        <!-- Chart -->
        <script type="text/javascript">
            google.charts.load("current", {
                packages: ['corechart']
            });
            google.charts.setOnLoadCallback(drawChart);

            function drawChart() {
                var data = google.visualization.arrayToDataTable([
                    ["Materia", "Porcentaje", {
                            role: "style"
                        }],
                    ["Calculo Diferencial", 50, "#a81f2b"],
                    ["Matematicas Discretas", 10, "#a81f2b"],
                    ["Fundamentos de Programacion", 25, "#a81f2b"],
                    ["Introduccion Ing. Sistemas", 30, "#a81f2b"],
                    ["Comunicacion I", 50, "#a81f2b"],
                    ["Intro Vida Universitaria", 25, "#a81f2b"],
                    ["Calculo Integral", 89, "#a81f2b"],
                    ["Algebra Lineal", 100, "#a81f2b"],
                    ["Fisica Mecanica", 19, "#a81f2b"],
                    ["Programacion Orientada a Objectos", 30, "#a81f2b"],
                    ["Comunicacion II", 75, "#a81f2b"],
                    ["Seminario Integrador I", 50, "#a81f2b"],
                    ["Calculo Vectorial", 50, "#a81f2b"],
                    ["Fisica Electromagnetica", 27, "#a81f2b"],
                    ["Estructuras de Datos", 50, "#a81f2b"],
                    ["Programacion Orientada a Objectos II", 10, "#a81f2b"],
                    ["Seminario de Investigacion I", 25, "#a81f2b"],
                    ["Ecuaciones Diferenciales", 30, "#a81f2b"],
                    ["Probabilidad y Estadistica", 50, "#a81f2b"],
                    ["Ondas y Particulas", 25, "#a81f2b"],
                    ["Analisis de Algoritmos", 89, "#a81f2b"],
                    ["Teoria de la Computación", 100, "#a81f2b"],
                    ["Analisis Numerico", 19, "#a81f2b"],
                    ["Investigacion de Operaciones", 30, "#a81f2b"],
                    ["Electronica", 75, "#a81f2b"],
                    ["Arquitectura de Computadores", 50, "#a81f2b"],
                    ["Seminario de Investigacion II", 50, "#a81f2b"],
                    ["Sistemas Operativos", 27, "#a81f2b"],
                    ["Bases de Datos", 50, "#a81f2b"],
                    ["Programacion Web", 10, "#a81f2b"],
                    ["Constitucion y Civismo", 25, "#a81f2b"],
                    ["Planeacion Estrategica de Sistemas Informacion", 30, "#a81f2b"],
                    ["Teoria General de las Comunicaciones", 50, "#a81f2b"],
                    ["Analisis y Diseño de Sistemas", 25, "#a81f2b"],
                    ["Seminario de Investigacion III", 89, "#a81f2b"],
                    ["Etica Profesional", 100, "#a81f2b"],
                    ["Administracion de Proyectos Informaticos", 19, "#a81f2b"],
                    ["Programacion Orientada a Objectos", 30, "#a81f2b"],
                    ["Redes de Computadores", 75, "#a81f2b"],
                    ["Ingenieria de Software", 50, "#a81f2b"],
                    ["Formulacion y Evaluacion de Proyectos de Sistemas", 50, "#a81f2b"],
                    ["Seminario Integrador III", 27, "#a81f2b"],
                    ["Arquitectura de Software", 37, "#a81f2b"],
                    ["Gestion de Tics", 77, "#a81f2b"],
                    ["Practica en Ing. Sistemas", 87, "#a81f2b"]
                ]);


                var view = new google.visualization.DataView(data);
                view.setColumns([0, 1,
                    {
                        calc: "stringify",
                        sourceColumn: 1,
                        type: "string",
                        role: "annotation"
                    },
                    2
                ]);

                var options = {
                    width: 1500,
                    height: 500,
                    bar: {
                        groupWidth: "50%"
                    },
                    legend: {
                        position: "none"
                    },
                };
                var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
                chart.draw(view, options);
            }
        </script>
    </body>

</html>