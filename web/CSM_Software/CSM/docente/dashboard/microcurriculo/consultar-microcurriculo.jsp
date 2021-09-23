<%-- 
    Document   : consultar-microcurriculo
    Created on : 17-jun-2021, 16:46:17
    Author     : Stiward
--%>

<%@page import="dto.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>CSM Software</title>
        <meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />
        <link rel="icon" href="../../../../assets/img/icon.ico" type="image/x-icon" />

        <!-- Fonts and icons -->
        <script src="../../../../assets/js/plugin/webfont/webfont.min.js"></script>
        <script>
            WebFont.load({
                google: {
                    "families": ["Lato:300,400,700,900"]
                },
                custom: {
                    "families": ["Flaticon", "Font Awesome 5 Solid", "Font Awesome 5 Regular", "Font Awesome 5 Brands",
                        "simple-line-icons"
                    ],
                    urls: ['../../../../assets/css/fonts.min.css']
                },
                active: function () {
                    sessionStorage.fonts = true;
                }
            });
        </script>

        <!-- CSS Files -->
        <link rel="stylesheet" href="../../../../assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="../../../../assets/css/atlantis.min.css">
        <!-- CSS Just for demo purpose, don't include it in your project -->
        <link rel="stylesheet" href="../../../../assets/css/demo.css">
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
                            <h4 class="page-title">Consultar Microcurriculo</h4>
                            <div class="ml-md-auto">
                                <ul class="breadcrumbs">
                                    <li class="nav-home">
                                        <a href="../dashboard.jsp">
                                            <i class="flaticon-home"></i>
                                        </a>
                                    </li>
                                    <li class="separator">
                                        <i class="flaticon-right-arrow"></i>
                                    </li>
                                    <li class="nav-item">
                                        <a href="#">Microcurriculo</a>
                                    </li>
                                    <li class="separator">
                                        <i class="flaticon-right-arrow"></i>
                                    </li>
                                    <li class="nav-item">
                                        <a href="#">Consultar</a>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="row">
                            <!-- Tabla Consultar Microcurriculos -->
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-header row">
                                        <div class="col d-flex justify-content-center align-items-center">
                                            <h4 class="card-title">Listado Microcurriculos</h4>
                                        </div>
                                        <a href="../../../../../ControladorMicrocurriculo?accion=listarTodos">
                                            <button class="btn btn-primary btn-round ml-auto">
                                                <i class="fas fa-sync"></i>
                                                Actualizar
                                            </button>
                                        </a>
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table id="basic-datatables"
                                                   class="display table table-striped table-hover text-center">
                                                <thead>
                                                    <tr>
                                                        <th>Pensum</th>
                                                        <th>Código Materia</th>
                                                        <th>Nombre Materia</th>
                                                        <th>Créditos</th>
                                                        <th>Semestre</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tfoot>
                                                    <tr>
                                                        <th>Pensum</th>
                                                        <th>Código Materia</th>
                                                        <th>Nombre Materia</th>
                                                        <th>Créditos</th>
                                                        <th>Semestre</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </tfoot>
                                                <tbody>
                                                    <%
                                                        List<dto.Materia> materias = (List<dto.Materia>) request.getSession().getAttribute("materias");
                                                        for (dto.Materia elem : materias) {
                                                            if (elem.getMicrocurriculo() != null) {
                                                    %>
                                                    <tr>
                                                        <td><%=elem.getPensum().getPensumPK().getProgramaCodigo()%> - <%=elem.getPensum().getPensumPK().getCodigo()%></td>
                                                        <td><%=elem.getMateriaPK().getCodigo()%></td>
                                                        <td><%=elem.getNombre()%></td>
                                                        <td><%=elem.getCreditos()%></td>
                                                        <td><%=elem.getSemestre()%></td>
                                                        <td>
                                                            <div class="form-button-action">
                                                                <a href="../../../../../ControladorMicrocurriculo?accion=Consultar&codigoMateria=<%=elem.getMicrocurriculo().getMateria().getMateriaPK().getCodigo()%>&codigoPensum=<%=elem.getPensum().getPensumPK().getCodigo()%>"> 
                                                                    <button id="pensum" type="button" data-toggle="tooltip"
                                                                            title="" class="btn btn-link btn-dark"
                                                                            data-original-title="Ver" style="color: black;">
                                                                        <i class="fas fa-search"></i>
                                                                    </button>
                                                                </a>
                                                                <a href="../../../../../ControladorMicrocurriculoDocente?accion=Solicitud&codigoMateria=<%=elem.getMicrocurriculo().getMateria().getMateriaPK().getCodigo()%>&codigoPensum=<%=elem.getPensum().getPensumPK().getCodigo()%>">
                                                                    <button id="pensum" type="button" data-toggle="tooltip"
                                                                            title="" class="btn btn-link btn-dark"
                                                                            data-original-title="Solicitud"
                                                                            style="color: black;">
                                                                        <i class="fas fa-clipboard-list"></i>
                                                                    </button>
                                                                </a>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin Tabla Consultar Microcurriculos -->
                        </div>
                    </div>
                </div>
                <!-- Footer -->
                <jsp:include page="/CSM_Software/includes/footer.jsp" />
                <!-- End Footer -->
            </div>
        </div>
        <!--   Core JS Files   -->
        <script src="../../../../assets/js/core/jquery.3.2.1.min.js"></script>
        <script src="../../../../assets/js/core/popper.min.js"></script>
        <script src="../../../../assets/js/core/bootstrap.min.js"></script>
        <!-- jQuery UI -->
        <script src="../../../../assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
        <script src="../../../../assets/js/plugin/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js"></script>
        <!-- jQuery Scrollbar -->
        <script src="../../../../assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
        <!-- Datatables -->
        <script src="../../../../assets/js/plugin/datatables/datatables.min.js"></script>
        <!-- Atlantis JS -->
        <script src="../../../../assets/js/atlantis.min.js"></script>
        <!-- Atlantis DEMO methods, don't include it in your project! -->
        <script src="../../../../assets/js/setting-demo2.js"></script>
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
        <script>
            // Basic
            $('#basic-datatables').DataTable({
                "pageLength": 5,
            });
        </script>
    </body>

</html>