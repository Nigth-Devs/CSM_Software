<%-- 
    Document   : grupos
    Created on : 15-jun-2021, 19:51:14
    Author     : Stiward
--%>

<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
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
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/JQuery.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/administrarGrupos.js" type="text/javascript">
    </script>
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
                        <h4 class="page-title">Grupos</h4>
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
                                    <a href="#">Grupos</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="row">
                        <!-- Tabla Grupos-->
                        <div class="col-md-8">
                            <div class="card">
                                <div class="card-header text-center">
                                    <h2 class="card-title"> Grupos</h2>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table id="basic-datatables"
                                            class="display table table-striped table-hover text-center">
                                            <thead>
                                                <tr>
                                                    <th>Código Materia</th>
                                                    <th>Nombre Materia</th>
                                                    <th>Código Docente</th>
                                                    <th>Nombre Docente</th>
                                                    <th>Grupo</th>
                                                    <th>Acción</th>
                                                </tr>
                                            </thead>
                                            <tfoot>
                                                <tr>
                                                    <th>Código Materia</th>
                                                    <th>Nombre Materia</th>
                                                    <th>Código Docente</th>
                                                    <th>Nombre Docente</th>
                                                    <th>Grupo</th>
                                                    <th>Acción</th>
                                                </tr>
                                            </tfoot>
                                            <tbody>
                                                <% List<dto.MateriaPeriodoGrupo> materias = (List<dto.MateriaPeriodoGrupo>) request.getSession().getAttribute("grupos");
                                                        for (dto.MateriaPeriodoGrupo materia : materias) {
                                                    %>
                                                <tr>
                                                    <td><%=materia.getMateriaPeriodo().getMateria().getMateriaPK().getCodigoMateria()%>
                                                    </td>
                                                    <td><%=materia.getMateriaPeriodo().getMateria().getNombre()%></td>
                                                    <td><%=materia.getDocente().getCodigoDocente()%></td>
                                                    <td><%=materia.getDocente().getNombre() + " " + materia.getDocente().getApellido()%>
                                                    </td>
                                                    <td><%=materia.getMateriaPeriodoGrupoPK().getGrupo()%></td>
                                                    <td>
                                                        <div class="form-button-action">
                                                            <a
                                                                href="<%=request.getContextPath()%>/ControladorGrupos?accion=Editar&grupo=<%=materia.getMateriaPeriodoGrupoPK().getGrupo()%>&docente_codigo=<%=materia.getDocente().getCodigoDocente()%>&anio=<%=materia.getMateriaPeriodoGrupoPK().getMateriaPeriodoAnio()%>&semestre_anio=<%=materia.getMateriaPeriodoGrupoPK().getMateriaPeriodoSemestreAnio()%>&codigo_materia=<%=materia.getMateriaPeriodoGrupoPK().getMateriaPeriodoMateriaCodigoMateria()%>&codigoPensum=<%=materia.getMateriaPeriodoGrupoPK().getMateriaPeriodoMateriaPensumCodigo()%>">
                                                                <button type="button" data-toggle="tooltip" title=""
                                                                    class="btn btn-link btn-dark"
                                                                    data-original-title="Editar">
                                                                    <i class="fa fa-edit" style="color: black;"></i>
                                                                </button>
                                                            </a>
                                                            <a
                                                                href="<%=request.getContextPath()%>/ControladorGrupos?accion=Eliminar&grupo=<%=materia.getMateriaPeriodoGrupoPK().getGrupo()%>&docente_codigo=<%=materia.getDocente().getCodigoDocente()%>&anio=<%=materia.getMateriaPeriodoGrupoPK().getMateriaPeriodoAnio()%>&semestre_anio=<%=materia.getMateriaPeriodoGrupoPK().getMateriaPeriodoSemestreAnio()%>&codigo_materia=<%=materia.getMateriaPeriodoGrupoPK().getMateriaPeriodoMateriaCodigoMateria()%>&codigoPensum=<%=materia.getMateriaPeriodoGrupoPK().getMateriaPeriodoMateriaPensumCodigo()%>">
                                                                <button id="pensum" type="button" data-toggle="tooltip"
                                                                    title="" class="btn btn-link btn-dark"
                                                                    data-original-title="Eliminar"
                                                                    style="color: black;">
                                                                    <i class="fas fa-times"></i>
                                                                </button>
                                                            </a>
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
                            <!-- Find Tabla Grupos -->
                            <%dto.Usuario usuario = (dto.Usuario) (request.getSession().getAttribute("usuario"));%>
                                                <!--Crear Grupos-->
                                                <div class="col-md-4">
                                                    <div class="">
                                                        <div class="card">
                                                            <div class="card-header d-flex justify-content-center">
                                                                <h2 class="card-title mtittle"> Crear Grupos</h2>
                                                            </div>
                                                            <div class="card-body pb-0 ">
                                                                <form
                                                                    action="<%=request.getContextPath()%>/ControladorGrupos"
                                                                    method="GET">
                                                                    <div class="form-group">
                                                                        <label
                                                                            for="exampleFormControlSelect1">Pensum</label>
                                                                        <select class="form-control" name="optionPensum"
                                                                            id="optionPensum"
                                                                            onchange="searchMateria()"></select>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label
                                                                            for="exampleFormControlSelect1">Materia</label>
                                                                        <select class="form-control"
                                                                            name="optionMateria"
                                                                            id="optionMateria"></select>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label
                                                                            for="exampleFormControlSelect1">Docente</label>
                                                                        <% List<dto.Docente> docentes = (List<dto.Docente>) (request.getSession().getAttribute("docentesPrograma"));%>
                                                                        <select class="form-control"
                                                                            name="optionDocente" id="optionDocente">
                                                                            <%
                                                            for (dto.Docente docente : docentes) {
                                                        %>
                                                                            <option
                                                                                value="<%=docente.getCodigoDocente()%>">
                                                                                <%=docente.getNombre() + " " + docente.getApellido()%>
                                                                            </option>
                                                                            <%}%>
                                                    </select>
                                                </div>
                                                <%    Date fecha = new Date();%>
                                                                            <input type="hidden" name="anio"
                                                                                value="<%=fecha.getYear() + 1900%>">
                                                                            <input type="hidden" name="periodo"
                                                                                value="<%=fecha.getMonth() + 1%>">
                                                                            <div
                                                                                class="form-group d-flex justify-content-center align-items-center">
                                                                                <input class="btn btn-danger"
                                                                                    type="submit" name="accion"
                                                                                    value="Registrar Grupo">
                                                                            </div>
                                                                </form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- Fin Crear Grupos-->
                                    </div>
                                </div>
                            </div>

                            <!-- Footer -->
                            <jsp:include page="/CSM_Software/includes/footer.jsp" />
                            <!-- End Footer -->

                        </div>
                    </div>
                    <!--   Core JS Files   -->
                    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/core/jquery.3.2.1.min.js">
                    </script>
                    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/core/popper.min.js"></script>
                    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/core/bootstrap.min.js"></script>
                    <!-- jQuery UI -->
                    <script
                        src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js">
                    </script>
                    <script
                        src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js">
                    </script>
                    <!-- jQuery Scrollbar -->
                    <script
                        src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js">
                    </script>
                    <!-- Datatables -->
                    <script
                        src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/datatables/datatables.min.js">
                    </script>
                    <!-- Atlantis JS -->
                    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/atlantis.min.js"></script>
                    <!-- Atlantis DEMO methods, don't include it in your project! -->
                    <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/setting-demo2.js"></script>
                    <!-- Filtros Tablas JS -->
                    <script>
                        $(document).ready(function () {
                            document.getElementById('grupos').classList.toggle('active');
                            pageLength();
                        });

                        function pageLength() {
                            // Basic
                            $('#basic-datatables').DataTable({
                                "pageLength": 5});
                        }
                    </script>
</body>

</html>