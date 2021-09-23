<%-- 
    Document   : pensum
    Created on : 17-jun-2021, 16:39:41
    Author     : Stiward
--%>

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
                            <div>
                                <h4 class="page-title">Pensum</h4>
                            </div>
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
                                        <a href="#">Pensum</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="row">

                            <!-- Pensum Actual-->
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-header">
                                        <div class="d-flex justify-content-center">
                                            <h4 class="card-title">Pensum 115-3</h4>
                                        </div>
                                    </div>
                                    <div class="card-body d-flex align-items-end">
                                        <iframe class="materialboxed" id="contenedor" src="pensum/ver-pensum.jsp"
                                                title="visualizar pensum" scrolling="no" frameborder="0" height="850px"
                                                width="100%" allowfullscreen></iframe>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin Pensum Actual -->

                            <!-- Card Pensum -->
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-header row">
                                        <div class="col d-flex justify-content-center align-items-center">
                                            <h4 class="card-title  ">Listado Pensum</h4>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <!-- Tabla Pensum -->
                                        <div class="table-responsive">
                                            <table id="basic-datatables"
                                                   class="display table table-striped table-hover text-center">
                                                <thead>
                                                    <tr>
                                                        <th>Código</th>
                                                        <th>Créditos</th>
                                                        <th>Cant Materias</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tfoot>
                                                    <tr>
                                                        <th>Código</th>
                                                        <th>Créditos</th>
                                                        <th>Cant Materias</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </tfoot>
                                                <tbody>
                                                    <%
                                                        Object metaData[][] = (Object[][]) request.getSession().getAttribute("pensumList");
                                                        if (metaData != null) {
                                                            for (Object data[] : metaData) {
                                                    %>
                                                    <tr>
                                                        <td><%=data[0] %></td>
                                                        <td><%=data[1] %></td>
                                                        <td><%=data[2] %></td>
                                                        <td>
                                                            <div class="form-button-action">
                                                                <a href="<%=request.getContextPath()%>/ControladorPensum?accion=ver&cod=<%=data[3]%>"
                                                                   type="button" data-toggle="tooltip" title=""
                                                                   class="btn btn-link btn-dark" data-original-title="Ver"
                                                                   style="color: black;">
                                                                    <i class="fas fa-search"></i>
                                                                </a>
                                                                <button type="button" data-toggle="tooltip" title=""
                                                                        class="btn btn-link btn-dark"
                                                                        data-original-title="Exportar PDF"
                                                                        style="color: black;">
                                                                    <i class="fas fa-file-pdf"></i>
                                                                </button>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <%}
                                                        }
                                                    %>
                                                </tbody>
                                            </table>
                                        </div>
                                        <!-- Fin Tabla Pensum -->
                                    </div>
                                </div>
                            </div>
                            <!-- Fin Card Pensum -->
                        </div>
                    </div>
                </div>
                <!-- Footer -->
                <jsp:include page="/CSM_Software/includes/footer.jsp" />
                <!-- End Footer -->
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
        <!-- Datatables -->
        <script src="../../../assets/js/plugin/datatables/datatables.min.js"></script>
        <!-- Atlantis JS -->
        <script src="../../../assets/js/atlantis.min.js"></script>
        <!-- Atlantis DEMO methods, don't include it in your project! -->
        <script src="../../../assets/js/setting-demo2.js"></script>
        <!-- Filtros Tablas JS -->
        <script>
            function pageLength() {
                // Basic
                $('#basic-datatables').DataTable({
                    "pageLength": 5});
            }

            // Add Row
            $('#addRowButton').click(function () {
                $('#addRowModal').modal('hide');
            });
        </script>
    </body>

</html>