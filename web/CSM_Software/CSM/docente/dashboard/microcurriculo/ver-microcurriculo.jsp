<%-- 
    Document   : ver-microcurriculo
    Created on : 17-jun-2021, 16:47:50
    Author     : Stiward
--%>

<%@page import="dto.SeccionMicrocurriculo"%>
<%@page import="dto.ContenidoUnidad"%>
<%@page import="dto.Unidad"%>
<%@page import="dto.Tabla"%>
<%@page import="dto.TablaSeccion"%>
<%@page import="java.util.List"%>
<%@page import="dto.Usuario"%>
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
        <link rel="stylesheet" href="../../../../assets/css/microcurriculo.css">
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
                            <h4 class="page-title">Ver Microcurriculo</h4>
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
                                        <a href="consultar-microcurriculo.jsp">Consultar</a>
                                    </li>
                                    <li class="separator">
                                        <i class="flaticon-right-arrow"></i>
                                    </li>
                                    <li class="nav-item">
                                        <a href="#">Ver</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <%
                            dto.Microcurriculo microcurriculo = (dto.Microcurriculo) request.getSession().getAttribute("microcurriculo");
                            List<dto.AreaFormacion> areasFormacion = (List<dto.AreaFormacion>) request.getSession().getAttribute("areasFormacion");
                            List<dto.TipoMateria> tiposAsignatura = (List<dto.TipoMateria>) request.getSession().getAttribute("tipoAsignatura");
                        %>
                        <div class="row">
                            <!-- Contenido de Microcurriculo -->
                            <div class="col-md-10">
                                <!--Microcurriculo-->
                                <div class="card">
                                    <div class="card-header d-flex justify-content-center">
                                        <h2 class="card-title mtittle "> Microcurriculo</h2>
                                    </div>
                                    <div class="card-body pb-0 ">
                                        <table class="table table-hover ">
                                            <tbody>
                                                <tr>
                                                    <td class="text-right f border-right mth2"> Asignatura </td>
                                                    <td class="mt17" colspan="4"><%=microcurriculo.getMateria().getNombre()%></td>
                                                </tr>
                                                <tr>
                                                    <td class="text-right border-right mth2">Codigo</td>
                                                    <td class="mt17" colspan="4"><%=microcurriculo.getMateria().getMateriaPK().getCodigo()%></td>
                                                </tr>
                                                <tr>
                                                    <td class="text-right border-right mth2">Area de Formacion</td>
                                                    <td colspan="4" class="mt17">
                                                        <div class="selectgroup w-100 d-flex justify-content-around">
                                                            <%
                                                                for (dto.AreaFormacion elem : areasFormacion) {
                                                                    if (microcurriculo.getAreaDeFormacionId().getId() == elem.getId()) {
                                                            %>
                                                            <label class="selectgroup-item pr-3">
                                                                <input type="radio" name="Areadeforamacion" value="50"
                                                                       class="selectgroup-input" checked="" disabled>
                                                                <span class="selectgroup-button"><%=microcurriculo.getAreaDeFormacionId().getNombre()%></span>
                                                            </label>
                                                            <%
                                                            } else {
                                                            %>
                                                            <label class="selectgroup-item pr-3">
                                                                <input type="radio" name="Areadeforamacion" value="50"
                                                                       class="selectgroup-input" disabled>
                                                                <span class="selectgroup-button"><%=elem.getNombre()%></span>
                                                            </label>
                                                            <%
                                                                    }
                                                                }
                                                            %>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="text-right border-right mth2">Tipos de Asignatura</td>
                                                    <td colspan="4" class="mt17">
                                                        <div class="selectgroup w-100 d-flex justify-content-around ">
                                                            <%
                                                                for (dto.TipoMateria elem : tiposAsignatura) {
                                                                    if (microcurriculo.getMateria().getTipoId().getId() == elem.getId()) {
                                                            %>
                                                            <label class="selectgroup-item pr-3">
                                                                <input type="radio" name="Tiposdeasignatura" value="50"
                                                                       class="selectgroup-input" checked="" disabled>
                                                                <span class="selectgroup-button"><%=microcurriculo.getMateria().getTipoId().getTipo()%></span>
                                                            </label>
                                                            <%
                                                            } else {
                                                            %>
                                                            <label class="selectgroup-item pr-3">
                                                                <input type="radio" name="Tiposdeasignatura" value="150"
                                                                       class="selectgroup-input" disabled>
                                                                <span class="selectgroup-button"><%=elem.getTipo()%></span>
                                                            </label>
                                                            <%
                                                                    }
                                                                }
                                                            %>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="text-right border-right mth2">Numero de Creditos</td>
                                                    <td class="mt17" colspan="4"><%=microcurriculo.getMateria().getCreditos()%></td>
                                                </tr>
                                                <tr>
                                                    <td class="text-right border-right mth2">Prerrequisitos </td>
                                                    <td class="mt17" colspan="4">
                                                        <%
                                                            for (dto.PrerrequisitoMateria prerrequisito : microcurriculo.getMateria().getPrerrequisitoMateriaList()) {
                                                        %>
                                                        <%=prerrequisito.getMateria1().getNombre()%><br><%}%>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <%
                                    List<SeccionMicrocurriculo> secciones = microcurriculo.getSeccionMicrocurriculoList();
                                    for (SeccionMicrocurriculo seccion : secciones) {
                                %>
                                <!-- Seccion -->  
                                <% int tipo = seccion.getSeccionId().getTipoSeccionId().getId();
                                        if (tipo == 1) {%>
                                <div class="card" id="<%=seccion.getSeccionId().getNombre()%>">
                                    <div class="card-header">
                                        <div class="d-flex justify-content-center">
                                            <h4 class="card-title mtittle"><%=seccion.getSeccionId().getNombre()%></h4>
                                        </div>
                                    </div>
                                    <div class="card-body pb-0">
                                        <div class="w-100 d-flex justify-content-center">
                                            <p class="w-75 text-justify "><%=seccion.getContenidoList().get(0).getTexto()%></p>
                                        </div>
                                    </div>
                                </div>
                                <!-- Fin Seccion -->
                                <%   } else {
                                    TablaSeccion tableSeccion = seccion.getTablaSeccion();
                                    Tabla table = tableSeccion.getTablaId();
                                    int canColum = table.getEncabezadoList().size();
                                %>
                                <!-- Tabla -->
                                <div class="card" id="<%=seccion.getSeccionId().getNombre()%>">
                                    <div class="card-header">
                                        <div class="d-flex justify-content-center">
                                            <h4 class="card-title mtittle "><%=seccion.getSeccionId().getNombre()%></h4>
                                        </div>
                                    </div>
                                    <div class="card-body pb-0">
                                        <table class="table table-hover">
                                            <thead>
                                                <tr>
                                                    <% for (int i = 0; i < canColum; i++) {%>
                                                    <th class="text-center" scope="col"><%=table.getEncabezadoList().get(i).getEncabezado()%></th>
                                                        <%}%>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%for (Unidad unidad : microcurriculo.getUnidadList()) {
                                                            if (seccion.getSeccionId().getId() == 1) {%>
                                                <tr>
                                                    <td class="text-center "><%=unidad.getNum()%></td>
                                                    <td class="text-center "><%=unidad.getNombre()%></td>
                                                    <td class="text-center "><%=unidad.getHorasPresencial()%></td>
                                                    <td class="text-center "><%=unidad.getHorasIndependiente()%></td>
                                                    <td class="text-center "><%=unidad.getHorasIndependiente() + unidad.getHorasPresencial()%></td>
                                                </tr>
                                                <%
                                                } else {
                                                    int i = 0;
                                                    for (ContenidoUnidad content : unidad.getContenidoUnidadList()) {
                                                %>
                                                <tr>
                                                    <%if (i++ == 0) {%><td class="text-center " rowspan="<%=unidad.getContenidoUnidadList().size()%>" title="<%=unidad.getNombre()%>" data-toggle="tooltip"><%=unidad.getNum()%></td><%}%>
                                                    <td class="text-center "><%=content.getContenido()%></td>
                                                    <td class="text-center "><%=content.getTrabajoPresencial()%></td>
                                                    <td class="text-center "><%=content.getTrabajoIndependiente()%></td>
                                                </tr>
                                                <%
                                                            }
                                                        }
                                                    }
                                                %>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <!-- Fin Tabla -->
                                <%
                                        }
                                    }
                                %>
                            </div>
                            <!-- Fin Contenido de Microcurriculo -->

                            <!-- Navegacion de Microcurriculo -->
                            <div class="col-md-2 position-relative">
                                <div class=" position-fixed ">
                                    <h2 class="d-flex justify-content-center font-weight-bold">Navegacion</h2>
                                    <%
                                        for (dto.SeccionMicrocurriculo seccion : secciones) {
                                    %>
                                    <a href="#<%=seccion.getSeccionId().getNombre()%>">
                                        <div class="card btn btn-light mb-2 p-2 d-flex justify-content-center">
                                            <h4 class="card-title "><%=seccion.getSeccionId().getNombre()%></h4>
                                        </div>
                                    </a>
                                    <%
                                        }
                                    %>      
                                </div>
                            </div>
                            <!-- Fin Navegacion de Microcurriculo -->
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
    </body>

</html>