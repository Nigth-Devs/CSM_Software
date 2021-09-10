<%-- 
    Document   : pensum
    Created on : 15-jun-2021, 19:50:02
    Author     : Stiward
--%>

<%@page import="dto.Materia"%>
<%@page import="dto.Pensum"%>
<%@page import="java.util.List"%>
<%@page import="dto.Usuario"%>
<%@page import="negocio.AdministrarPensum"%>
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
        <!-- Custom JS -->
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/JQuery.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/index.js" type="text/javascript"></script>
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
                            <div>
                                <h4 class="page-title">Pensum</h4>
                            </div>
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
                                            <h4 class="card-title">
                                                <% List<Materia> materiasSemestre[] = (List<Materia>[]) request.getSession().getAttribute("materiasSemestre");
                                                    if (materiasSemestre != null && materiasSemestre.length > 0) {%>
                                                Pensum <%=materiasSemestre[0].get(0).getPensum().getPensumPK().getProgramaCodigo()%> - <%=materiasSemestre[0].get(0).getPensum().getPensumPK().getCodigo()%>
                                                <%} else {%>No hay Pensum Registrado<%}%>
                                            </h4>
                                        </div>
                                    </div>
                                    <div class="card-body d-flex align-items-end">
                                        <iframe class="materialboxed" id="contenedor" src="pensum/ver-pensum.jsp"
                                                title="visualizar pensum" scrolling="auto" frameborder="0" height="850px"
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
                                        <button class="btn btn-primary btn-round ml-auto" data-toggle="modal"
                                                data-target="#addRowModal">
                                            <i class="fa fa-plus"></i>
                                            Cargar Pensum
                                        </button>
                                    </div>
                                    <div class="card-body">
                                        <!-- Modal -->
                                        <div class="modal fade" id="addRowModal" tabindex="-1" role="dialog"
                                             aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header no-bd">
                                                        <h5 class="modal-title">Cargar Pensum</h5>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form action="<%=request.getContextPath()%>/ControladorPensum?accion=registrar" method="POST" enctype="multipart/form-data">
                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group form-group-default">
                                                                        <label>Codigo Pensum</label>
                                                                        <input class="form-control" type="text"
                                                                               name="programa" placeholder="115">
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-12">
                                                                    <div class="form-group form-group-default">
                                                                        <p class="small">Importe el Archivo en Formato PDF
                                                                        </p>
                                                                        <input class="form-control-file" type="file"
                                                                               name="pensum">
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer no-bd">
                                                                    <input class="btn btn-primary" type="submit"
                                                                           value="Agregar">
                                                                    <input class="btn btn-primary" type="submit"
                                                                           value="Cerrar" data-dismiss="modal">
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Fin Modal -->

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
                                                        AdministrarPensum admin = new AdministrarPensum();
                                                        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
                                                        List<Pensum> pensum = (List<Pensum>) request.getSession().getAttribute("listaPensum2");
                                                        if (pensum != null) {
                                                            for (Pensum p : pensum) {
                                                                if (u.getDocente().getProgramaList().get(0).getDirectorPrograma().getCodigoDocente() == p.getPrograma().getDirectorPrograma().getCodigoDocente()) {
                                                                    int materiaXcreditos[] = admin.creditosMateriasPensum(p.getPensumPK().getCodigo(), p.getPensumPK().getProgramaCodigo());
                                                    %>
                                                    <tr>
                                                        <td><%= p.getPensumPK().getProgramaCodigo() + " - " + p.getPensumPK().getCodigo()%></td>
                                                        <td><%= materiaXcreditos[1]%></td>
                                                        <td><%= materiaXcreditos[0]%></td>
                                                        <td>
                                                            <div class="form-button-action">
                                                                <a href="<%=request.getContextPath()%>/ControladorPensum?accion=ver&cod=<%=p.getPensumPK().getCodigo()%>"
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
                                                    }%>
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
        <!-- Atlantis DEMO methods, don't include it in your project! -->
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/setting-demo2.js"></script>
        <!-- Filtros Tablas JS -->
        <script>
            $(document).ready(function () {
                document.getElementById('pensum').classList.toggle('active');
                pageLength();
            });

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
