<%-- 
    Document   : solicitudes-microcurriculo
    Created on : 15-jun-2021, 23:36:18
    Author     : Stiward
--%>

<%@page import="java.util.List"%>
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
                        <h4 class="page-title">Solicitud Microcurriculo</h4>
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
                                    <a href="<%=request.getContextPath()%>/ControladorMicrocurriculo?accion=listarTodos">Microcurriculo</a>
                                </li>
                                <li class="separator">
                                    <i class="flaticon-right-arrow"></i>
                                </li>
                                <li class="nav-item">
                                    <a href="#">Solicitudes</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="row">
                        <!-- Tabla Solicitudes de Cambio del Microcurriculo-->
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header text-center">
                                    <h4 class="card-title">Listado Solicitudes de Cambio del Microcurriculo</h4>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <%
                                            negocio.AdministrarMicrocurriculo aas = new negocio.AdministrarMicrocurriculo();
                                            List<dto.SeccionCambio> lista = aas.obtenerSeccionesCambios();
                                        %>
                                        <table id="basic-datatables"
                                            class="display table table-striped table-hover text-center">
                                            <thead>
                                                <tr>
                                                    <th>Código Materia</th>
                                                    <th>Nombre Materia</th>
                                                    <th>Estado</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tfoot>
                                                <tr>
                                                    <th>Código Materia</th>
                                                    <th>Nombre Materia</th>
                                                    <th>Estado</th>
                                                    <th>Action</th>
                                                </tr>
                                            </tfoot>
                                            <tbody>
                                                <%
                                                    for (dto.SeccionCambio sec : lista) {
                                                %>
                                                <tr>
                                                    <th><%=sec.getSeccionMicrocurriculoIdNuevo().getMicrocurriculo().getMateria().getMateriaPK().getCodigo()%>
                                                    </th>
                                                    <th><%=sec.getSeccionMicrocurriculoIdNuevo().getMicrocurriculo().getMateria().getNombre()%>
                                                    </th>
                                                    <th><%=sec.getCambioId().getEstadoId().getEstado()%></th>
                                                    <td>
                                                        <div class="form-button-action">
                                                            <a
                                                                href="<%=request.getContextPath()%>/CSM_Software/CSM/director/dashboard/microcurriculo/ver-solicitud.jsp">
                                                                <button id="pensum" type="button" data-toggle="tooltip"
                                                                    title="" class="btn btn-link btn-dark"
                                                                    data-original-title="Ver" style="color: black;">
                                                                    <i class="fas fa-search"></i>
                                                                </button>
                                                            </a>
                                                            <a href="#">
                                                                <button type="button" data-toggle="tooltip" title=""
                                                                    class="btn btn-link btn-dark"
                                                                    data-original-title="Exportar PDF"
                                                                    style="color: black;">
                                                                    <i class="fas fa-file-pdf"></i>
                                                                </button>
                                                            </a>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <%
                                                        }
                                                    %>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Find Tabla Solicitudes de Cambio del Microcurriculo -->
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
    <!-- Datatables -->
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/datatables/datatables.min.js"></script>
    <!-- Atlantis JS -->
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/atlantis.min.js"></script>
    <script>
        $(document).ready(function () {
            document.getElementById('solicitudes_microcurriculo').classList.toggle('active');
            pageLength();
        });
        
        // Basic
        function pageLength() {
        $('#basic-datatables').DataTable({
            "pageLength": 5,
        });
        }
    </script>
</body>

</html>