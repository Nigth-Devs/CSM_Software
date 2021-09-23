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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/dashboard.css">

    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/demo.css">
</head>

<body>
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
                                                <p class="card-category">Cumplimiento Global (%)</p>
                                                <h4 class="card-title">20 / 100</h4>
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
                                                <p class="card-category">Periodo Acádemico</p>
                                                <h4 class="card-title">2021 - 2</h4>
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
                                                <h4 class="card-title">5</h4>
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
                                                <h4 class="card-title">5</h4>
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

                        <!-- Tabla Docentes -->
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header text-center">
                                    <h4 class="card-title">Cumplimiento de Periodos Acádemicos</h4>
                                </div>
                                <div class="card-body">
                                    <!-- Tabla Pensum -->
                                    <div class="table-responsive">
                                        <table id="basic-datatables"
                                            class="display table table-striped table-hover text-center">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>Periodo</th>
                                                    <th># Materias</th>
                                                    <th>Cumplimiento Global (%)</th>
                                                    <th>Estado</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tfoot>
                                                <tr>
                                                    <th>#</th>
                                                    <th>Periodo</th>
                                                    <th># Materias</th>
                                                    <th>Cumplimiento Global (%)</th>
                                                    <th>Estado</th>
                                                    <th>Action</th>
                                                </tr>
                                            </tfoot>
                                            <tbody>
                                                <tr>
                                                    <td>1</td>
                                                    <td>2021 - 2</td>
                                                    <td>5</td>
                                                    <td>20</td>
                                                    <td>En Curso</td>
                                                    <td>
                                                        <div class="form-button-action">
                                                            <a href="#" type="button" data-toggle="tooltip" title=""
                                                                class="btn btn-link btn-dark" data-original-title="Ver"
                                                                style="color: black;">
                                                                <i class="fas fa-search"></i>
                                                            </a>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                    <td>2021 - 1</td>
                                                    <td>5</td>
                                                    <td>85</td>
                                                    <td>Finalizado</td>
                                                    <td>
                                                        <div class="form-button-action">
                                                            <a href="#" type="button" data-toggle="tooltip" title=""
                                                                class="btn btn-link btn-dark" data-original-title="Ver"
                                                                style="color: black;">
                                                                <i class="fas fa-search"></i>
                                                            </a>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>3</td>
                                                    <td>2020 - 2</td>
                                                    <td>5</td>
                                                    <td>90</td>
                                                    <td>Finalizado</td>
                                                    <td>
                                                        <div class="form-button-action">
                                                            <a href="#" type="button" data-toggle="tooltip" title=""
                                                                class="btn btn-link btn-dark" data-original-title="Ver"
                                                                style="color: black;">
                                                                <i class="fas fa-search"></i>
                                                            </a>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>4</td>
                                                    <td>2020 - 1</td>
                                                    <td>5</td>
                                                    <td>96</td>
                                                    <td>Finalizado</td>
                                                    <td>
                                                        <div class="form-button-action">
                                                            <a href="#" type="button" data-toggle="tooltip" title=""
                                                                class="btn btn-link btn-dark" data-original-title="Ver"
                                                                style="color: black;">
                                                                <i class="fas fa-search"></i>
                                                            </a>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- Fin Tabla Pensum -->
                                </div>
                            </div>
                        </div>

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
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js">
    </script>
    <script
        src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js">
    </script>

    <!-- jQuery Scrollbar -->
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js">
    </script>

    <!-- Chart JS -->
    <script src="https://www.gstatic.com/charts/loader.js"></script>

    <!-- jQuery Sparkline -->
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/jquery.sparkline/jquery.sparkline.min.js">
    </script>

    <!-- Datatables -->
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/datatables/datatables.min.js"></script>

    <!-- Bootstrap Notify -->
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/bootstrap-notify/bootstrap-notify.min.js">
    </script>

    <!-- jQuery Vector Maps -->
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/jqvmap/jquery.vmap.min.js"></script>
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/jqvmap/maps/jquery.vmap.world.js"></script>

    <!-- Sweet Alert -->
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/sweetalert/sweetalert.min.js"></script>

    <!-- Atlantis JS -->
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/atlantis.min.js"></script>

    <!-- Atlantis DEMO methods, don't include it in your project! -->
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/setting-demo.js"></script>
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/demo.js"></script>

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
                ["Calculo Diferencial", 5, "#a81f2b"],
                ["Matematicas Discretas", 5, "#a81f2b"],
                ["Fundamentos de Programacion", 5, "#a81f2b"],
                ["Introduccion Ing. Sistemas", 5, "#a81f2b"],
                ["Comunicacion I", 20, "#a81f2b"]
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
                }
            };
            var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
            chart.draw(view, options);
        }
    </script>
    <script>
        $(document).ready(function () {
            document.getElementById('microcurriculo').classList.toggle('active');
            document.getElementById('microcurriculo').classList.toggle('submenu');
            document.getElementById('sidebarLayouts').classList.toggle('show');
            document.getElementById('dashboard').classList.toggle('active');
            pageLength();
        });

        function pageLength() {
            // Basic
            $('#basic-datatables').DataTable({
                "pageLength": 5
            });
        }
    </script>
</body>

</html>