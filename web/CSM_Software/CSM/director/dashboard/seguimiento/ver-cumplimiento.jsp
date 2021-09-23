<%-- 
    Document   : ver-cumplimiento
    Created on : 15-jun-2021, 23:40:00
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/microcurriculo.css">
    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/demo.css">
    <!-- CSS Custom -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/microcurriculo.css">
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
                        <h4 class="page-title">Materia Cumplimiento</h4>
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
                                    <a href="<%=request.getContextPath()%>/CSM_Software/CSM/director/dashboard/seguimiento.jsp">Seguimiento</a>
                                </li>
                                <li class="separator">
                                    <i class="flaticon-right-arrow"></i>
                                </li>
                                <li class="nav-item">
                                    <a href="<%=request.getContextPath()%>/CSM_Software/CSM/director/dashboard/seguimiento/seguimiento-materia.jsp">Materia</a>
                                </li>
                                <li class="separator">
                                    <i class="flaticon-right-arrow"></i>
                                </li>
                                <li class="nav-item">
                                    <a href="#">Cumplimiento</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="row">

                        <!-- Contenido de Microcurriculo -->
                        <div class="col-md-10">
                            <!-- Title -->
                            <div class="card">
                                <div class="card-header d-flex justify-content-center text-center">
                                    <h2 class="card-title mtittle">1155705 - Seminario de Investigacion III - Grupo
                                        A<br>Salamanca Landinez Alvaro</h2>
                                </div>
                            </div>
                            <!-- Fin Title -->

                            <!-- Unidad 1 -->
                            <div class="card" id="Unidad-1">
                                <div class="card-header d-flex justify-content-center text-center">
                                    <h2 class="card-title font-weight-bold">UNIDAD 1: GENERALIDADES DE
                                        LA INVESTIGACIÓN</h2>
                                </div>
                                <div class="card-body pb-0 ">
                                    <!-- Progress-Status -->
                                    <div class="progress-card">
                                        <div class="progress-status">
                                            <span class="text-muted fw-bold ml-auto"> 70%</span>
                                        </div>
                                        <div class="progress" style="height: 6px;">
                                            <div class="progress-bar bg-primary" role="progressbar" style="width: 70%"
                                                aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"
                                                data-toggle="tooltip" data-placement="top" title=""
                                                data-original-title="70%"></div>
                                        </div>
                                    </div>
                                    <!-- Fin Progress-Status -->

                                    <!-- Tabla Unidades -->
                                    <table class="table mt-3 text-center">
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Contenido Unidad</th>
                                                <th scope="col">Estado</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>1</td>
                                                <td>COLCIENCIAS, como Sistema
                                                    Nacional de Ciencia, Tecnología e
                                                    Innovación.</td>
                                                <td><i class="fas fa-check"></i></td>
                                            </tr>
                                            <tr>
                                                <td>2</td>
                                                <td>Sistema Universitario de
                                                    Investigación de la Universidad,
                                                    según Acuerdo 056 de 2012</td>
                                                <td><i class="fas fa-check"></i></td>
                                            </tr>
                                            <tr>
                                                <td>3</td>
                                                <td>Valor estratégico de los Centros de
                                                    Investigación, Grupos y Semilleros</td>
                                                <td><i class="fa fa-times"></i></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <!-- Fin Tabla Unidades -->
                                </div>
                            </div>

                            <!--Unidad 2 -->
                            <div class="card" id="Unidad-2">
                                <div class="card-header d-flex justify-content-center text-center">
                                    <h2 class="card-title font-weight-bold">UNIDAD 2: INTRODUCCION A LAS METODOLOGIAS DE LA INVESTIGACIÓN</h2>
                                </div>
                                <div class="card-body pb-0 ">
                                    <!-- Progress-Status -->
                                    <div class="progress-card">
                                        <div class="progress-status">
                                            <span class="text-muted fw-bold ml-auto"> 70%</span>
                                        </div>
                                        <div class="progress" style="height: 6px;">
                                            <div class="progress-bar bg-primary" role="progressbar" style="width: 70%"
                                                aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"
                                                data-toggle="tooltip" data-placement="top" title=""
                                                data-original-title="70%"></div>
                                        </div>
                                    </div>
                                    <!-- Fin Progress-Status -->

                                    <!-- Tabla Unidades -->
                                    <table class="table mt-3 text-center">
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Contenido Unidad</th>
                                                <th scope="col">Estado</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>1</td>
                                                <td>COLCIENCIAS, como Sistema
                                                    Nacional de Ciencia, Tecnología e
                                                    Innovación.</td>
                                                <td><i class="fas fa-check"></i></td>
                                            </tr>
                                            <tr>
                                                <td>2</td>
                                                <td>Sistema Universitario de
                                                    Investigación de la Universidad,
                                                    según Acuerdo 056 de 2012</td>
                                                <td><i class="fas fa-check"></i></td>
                                            </tr>
                                            <tr>
                                                <td>3</td>
                                                <td>Valor estratégico de los Centros de
                                                    Investigación, Grupos y Semilleros</td>
                                                <td><i class="fa fa-times"></i></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <!-- Fin Tabla Unidades -->
                                </div>
                            </div>

                            <!--Unidad 3 -->
                            <div class="card" id="Unidad-3">
                                <div class="card-header d-flex justify-content-center text-center">
                                    <h2 class="card-title font-weight-bold">UNIDAD 3: INTRODUCCION A LAS METODOLOGIAS DE LA INVESTIGACIÓN</h2>
                                </div>
                                <div class="card-body pb-0 ">
                                    <!-- Progress-Status -->
                                    <div class="progress-card">
                                        <div class="progress-status">
                                            <span class="text-muted fw-bold ml-auto"> 70%</span>
                                        </div>
                                        <div class="progress" style="height: 6px;">
                                            <div class="progress-bar bg-primary" role="progressbar" style="width: 70%"
                                                aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"
                                                data-toggle="tooltip" data-placement="top" title=""
                                                data-original-title="70%"></div>
                                        </div>
                                    </div>
                                    <!-- Fin Progress-Status -->

                                    <!-- Tabla Unidades -->
                                    <table class="table mt-3 text-center">
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Contenido Unidad</th>
                                                <th scope="col">Estado</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>1</td>
                                                <td>COLCIENCIAS, como Sistema
                                                    Nacional de Ciencia, Tecnología e
                                                    Innovación.</td>
                                                <td><i class="fas fa-check"></i></td>
                                            </tr>
                                            <tr>
                                                <td>2</td>
                                                <td>Sistema Universitario de
                                                    Investigación de la Universidad,
                                                    según Acuerdo 056 de 2012</td>
                                                <td><i class="fas fa-check"></i></td>
                                            </tr>
                                            <tr>
                                                <td>3</td>
                                                <td>Valor estratégico de los Centros de
                                                    Investigación, Grupos y Semilleros</td>
                                                <td><i class="fa fa-times"></i></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <!-- Fin Tabla Unidades -->
                                </div>
                            </div>
                        </div>
                        <!-- Fin Contenido de Unidades-->

                        <!-- Navegacion Unidades -->
                        <div class="col-md-2 position-relative">
                            <div class="position-fixed" style="width: 13%;">
                                <div>
                                    <div class="card">
                                        <div class="card-header d-flex justify-content-center">
                                            <h2 class="card-title mt17 font-weight-bold">Cumplimiento</h2>
                                        </div>
                                        <div class="card-body d-flex justify-content-center">
                                            <div class="px-2 pb-2 pb-md-0 text-center">
                                                <div id="circles-1"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <h2 class="d-flex justify-content-center font-weight-bold">Unidades</h2>
                                <a href="#Unidad-1" style="color: #000;">
                                    <div class="card btn btn-light mb-2 p-2 d-flex justify-content-center">
                                        <h4 class="card-title ">Unidad 1</h4>
                                    </div>
                                </a>
                                <a href="#Unidad-2">
                                    <div class="card btn btn-light mb-2 p-2 d-flex justify-content-center">
                                        <h4 class="card-title ">Unidad 2</h4>
                                    </div>
                                </a>
                                <a href="#Unidad-3">
                                    <div class="card btn btn-light mb-2 p-2 d-flex justify-content-center">
                                        <h4 class="card-title ">Unidad 3</h4>
                                    </div>
                                </a>
                                <a href="#Unidad-4">
                                    <div class="card btn btn-light mb-2 p-2 d-flex justify-content-center">
                                        <h4 class="card-title ">Unidad 4</h4>
                                    </div>
                                </a>
                                <a href="#Unidad-5">
                                    <div class="card btn btn-light mb-2 p-2 d-flex justify-content-center">
                                        <h4 class="card-title ">Unidad 5</h4>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <!-- Fin Navegacion Unidades -->
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
    <!-- Chart Circle -->
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/chart-circle/circles.min.js"></script>
    <!-- Atlantis JS -->
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/atlantis.min.js"></script>
    <script>
        Circles.create({
            id: 'circles-1',
            radius: 45,
            value: 80,
            maxValue: 100,
            width: 7,
            text: "80%",
            colors: ['#f1f1f1', '#AA1916'],
            duration: 400,
            wrpClass: 'circles-wrp',
            textClass: 'circles-text',
            styleWrapper: true,
            styleText: true
        });
    </script>
    <script>
        $(document).ready(function () {
            document.getElementById('microcurriculo').classList.toggle('active');
            document.getElementById('microcurriculo').classList.toggle('submenu');
            document.getElementById('sidebarLayouts').classList.toggle('show');
            document.getElementById('seguimiento').classList.toggle('active');
        });
    </script>
</body>

</html>
