<%-- 
    Document   : seguimiento
    Created on : 17-jun-2021, 16:39:54
    Author     : Stiward
--%>

<%@page import="java.util.List"%>
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
                "families": ["Flaticon", "Font Awesome 5 Solid", "Font Awesome 5 Regular",
                    "Font Awesome 5 Brands",
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
    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link rel="stylesheet" href="../../../assets/css/demo.css">
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
                            <h4 class="page-title">Seguimiento</h4>
                            <div class="ml-md-auto">
                                <ul class="breadcrumbs">
                                    <li class="nav-home">
                                        <a href="dashboard.jsp">
                                            <i class="flaticon-home"></i>
                                        </a>
                                    </li>
                                    <li class="separator">
                                        <i class="flaticon-right-arrow"></i>
                                    </li>
                                    <li class="nav-item">
                                        <a href="#">Seguimiento</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="row">
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

                            <!-- Tabla Seguimiento -->
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-header text-center">
                                        <h4 class="card-title">Listado Materias</h4>
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table id="basic-datatables"
                                                   class="display table table-striped table-hover text-center">
                                                <thead>
                                                    <tr>
                                                        <th>Código Materia</th>
                                                        <th>Nombre Materia</th>
                                                        <th>Créditos</th>
                                                        <th>Semestre</th>
                                                        <th>Progreso</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tfoot>
                                                    <tr>
                                                        <th>Código Materia</th>
                                                        <th>Nombre Materia</th>
                                                        <th>Créditos</th>
                                                        <th>Semestre</th>
                                                        <th>Progreso</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </tfoot>
                                                <tbody>
                                                    <tr>
                                                        <%
                                                            List<Object[]> data = (List<Object[]>) request.getSession().getAttribute("listSeguimiento");
                                                            for (Object row[] : data) {
                                                        %>
                                                    <tr>
                                                        <td><%=row[0]%></td>
                                                        <td><%=row[1]%></td>
                                                        <td><%=row[2]%></td>
                                                        <td><%=row[3]%></td>
                                                        <td>
                                                            <div class="progress" style="height: 6px;">
                                                                <div class="progress-bar bg-primary" role="progressbar"
                                                                     style="width: <%=row[4]%>%" aria-valuenow="70" aria-valuemin="0"
                                                                     aria-valuemax="100" data-toggle="tooltip"
                                                                     data-placement="top" title="" data-original-title="<%=row[4]%>%">
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div class="form-button-action">
                                                                <a href="<%=request.getContextPath()%>/ControladorSeguimiento?accion=listTo&mat_per=<%=row[5] %>">
                                                                <!--a href="seguimiento/seguimiento-materia.jsp"-->
                                                                    <button id="pensum" type="button" data-toggle="tooltip"
                                                                            title="" class="btn btn-link btn-dark"
                                                                            data-original-title="Ver" style="color: black;">
                                                                        <i class="fas fa-search"></i>
                                                                    </button>
                                                                </a>
                                                                <button type="button" data-toggle="tooltip" title=""
                                                                        class="btn btn-link btn-dark"
                                                                        data-original-title="Exportar PDF" style="color: black;">
                                                                    <i class="fas fa-file-pdf"></i>
                                                                </button>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <%}%>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Find Tabla Seguimiento -->
                        </div>
                    </div>
                </div>
                <!-- Footer -->
                <jsp:include page="/CSM_Software/includes/footer.jsp" />
                <!-- End Footer -->
            </div>
        </div>
    </div>
    <!--   Core JS Files   -->
    <script src="../../../assets/js/core/jquery.3.2.1.min.js"></script>
    <script src="../../../assets/js/core/popper.min.js"></script>
    <script src="../../../assets/js/core/bootstrap.min.js"></script>
    <!-- jQuery UI -->
    <script src="../../../assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
    <script src="../../../assets/js/plugin/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js"></script>
    <!-- Chart JS -->
    <script src="https://www.gstatic.com/charts/loader.js"></script>
    <!-- jQuery Scrollbar -->
    <script src="../../../assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
    <!-- Datatables -->
    <script src="../../../assets/js/plugin/datatables/datatables.min.js"></script>
    <!-- Atlantis JS -->
    <script src="../../../assets/js/atlantis.min.js"></script>
    <!-- Atlantis DEMO methods, don't include it in your project! -->
    <script src="../../../assets/js/setting-demo2.js"></script>
    <!-- Chart -->
    <script>
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

    <!-- Filtros Tablas JS -->
    <script>
        $(document).ready(function () {
            // Basic
            $('#basic-datatables').DataTable({
                "pageLength": 5,
            });
        });
    </script>
</body>

</html>
