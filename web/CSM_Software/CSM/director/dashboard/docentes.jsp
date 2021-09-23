<%-- 
    Document   : docente
    Created on : 15-jun-2021, 19:51:55
    Author     : Stiward
--%>

<%@page import="dto.Usuario"%>
<%@page import="dto.Docente"%>
<%@page import="java.util.List"%>
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
    <!-- switch-button CSS  -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/switch-button.css">
</head>

<body>
    <% Usuario user = (Usuario) request.getSession().getAttribute("usuario");%>
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
                        <h4 class="page-title">Docentes</h4>
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
                                    <a href="#">Docentes</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="row">
                        <!-- Tabla Docentes -->
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header text-center">
                                    <h4 class="card-title">Listado Docentes</h4>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table id="basic-datatables" class="display table table-striped table-hover text-center">
                                            <thead>
                                                <tr>
                                                    <th>Código Docente</th>
                                                    <th>Nombre Docente</th>
                                                    <th>Estado</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tfoot>
                                                <tr>
                                                    <th>Código Docente</th>
                                                    <th>Nombre Docente</th>
                                                    <th>Estado</th>
                                                    <th>Action</th>
                                                </tr>
                                            </tfoot>
                                            <tbody>
                                                <%
                                                        List<Docente> docentes = (List<Docente>) request.getSession().getAttribute("listaDocentes");
                                                        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
                                                        for (Docente teacher : docentes) {
                                                            if (teacher.getUsuario().getRolId().getId() != 1 && u.getDocente().getProgramaCodigo().getDepartamentoId().equals(teacher.getProgramaCodigo().getDepartamentoId())) {
                                                    %>
                                                <tr>
                                                    <td><%= teacher.getCodigo()%></td>
                                                    <td><%= teacher.getNombre() + " " + teacher.getApellido()%></td>
                                                    <td><label
                                                            id="label-<%=teacher.getCodigo()%>"><%=(teacher.getEstado() == 1) ? "Activo" : "Inactivo"%><label>
                                                    </td>
                                                    <td>
                                                        <div class="switch-button">
                                                            <%if (teacher.getEstado() == 1) {%>
                                                            <button class="btn btn-primary btn-link">
                                                                <input class="switch-button__checkbox" type="checkbox"
                                                                    id="check-<%=teacher.getCodigo()%>"
                                                                    onchange="validarCheck(<%=teacher.getCodigo()%>)"
                                                                    checked>
                                                                <label for="<%=teacher.getCodigo()%>"
                                                                    class="switch-button__label"
                                                                    onclick="validarCheck(<%=teacher.getCodigo()%>)"></label>
                                                            </button>
                                                            <%} else {%>
                                                            <button class="btn btn-primary btn-link">
                                                                <input class="switch-button__checkbox" type="checkbox"
                                                                    id="check-<%=teacher.getCodigo()%>"
                                                                    onchange="validarCheck(<%=teacher.getCodigo()%>)">
                                                                <label for="<%=teacher.getCodigo()%>"
                                                                    class="switch-button__label"
                                                                    onclick="validarCheck(<%=teacher.getCodigo()%>)"></label>
                                                            </button>
                                                            <%}%>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <%}}%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Find Tabla Docentes -->
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
    <!-- Datatables -->
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/datatables/datatables.min.js"></script>
    <!-- Atlantis JS -->
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/atlantis.min.js"></script>
    <!-- Filtros Tablas JS -->
    <script>
        $(document).ready(function () {
            document.getElementById('microcurriculo').classList.toggle('active');
            document.getElementById('microcurriculo').classList.toggle('submenu');
            document.getElementById('sidebarLayouts').classList.toggle('show');
            document.getElementById('docentes').classList.toggle('active');
            pageLength();
        });

        function pageLength() {
            // Basic
            $('#basic-datatables').DataTable({
                "pageLength": 15
            });
        }
    </script>
    <script>
        function validarCheck(codigo) {
            $.post("<%=request.getContextPath()%>/ControladorDocente?action=activarDocente", {
                cod: codigo
            }, function (response) {
                var val = !($('#check-' + codigo).prop('checked'));
                $('#check-' + codigo).prop('checked', val);
                $('#label-' + codigo).text(val ? "Activo" : "Inactivo");
            });
        }
    </script>
</body>

</html>
