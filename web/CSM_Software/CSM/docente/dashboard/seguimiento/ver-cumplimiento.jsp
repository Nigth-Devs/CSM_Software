<%-- 
    Document   : ver-cumplimiento
    Created on : 17-jun-2021, 16:41:21
    Author     : Stiward
--%>

<%@page import="java.util.Map"%>
<%@page import="dto.ContenidoUnidad"%>
<%@page import="dto.Unidad"%>
<%@page import="dto.Microcurriculo"%>
<%@page import="dto.MateriaPeriodoGrupo"%>
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
        <!-- CSS Custom -->
        <link rel="stylesheet" href="../../../../assets/css/microcurriculo.css">
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
                            <h4 class="page-title">Materia Cumplimiento</h4>
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
                                        <a href="../seguimiento.jsp">Seguimiento</a>
                                    </li>
                                    <li class="separator">
                                        <i class="flaticon-right-arrow"></i>
                                    </li>
                                    <li class="nav-item">
                                        <a href="seguimiento-materia.jsp">Materia</a>
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
                            <%
                                MateriaPeriodoGrupo matPrGr = (MateriaPeriodoGrupo)request.getSession().getAttribute("mat_per_gr");
                                Microcurriculo micro = matPrGr.getMateriaPeriodoId().getMateria().getMicrocurriculo();
                                int percentages[] = (int[])request.getSession().getAttribute("percentages");
                                Map<Integer, Boolean> checkeds = (Map<Integer, Boolean> )request.getSession().getAttribute("checkeds");
                            %>
                            <div class="col-md-10">
                                <!-- Title -->
                                <div class="card">
                                    <div class="card-header d-flex justify-content-center text-center">
                                        <h2 class="card-title mtittle"><%=matPrGr.getMateriaPeriodoId().getMateria().getMateriaPK().getCodigo() %> - <%=matPrGr.getMateriaPeriodoId().getMateria().getNombre() %> -
                                            Grupo
                                            <%=matPrGr.getGrupo() %><br><%=matPrGr.getDocenteCodigo().getNombre()+" "+matPrGr.getDocenteCodigo().getApellido() %></h2>
                                    </div>
                                </div>
                                <!-- Fin Title -->
                                <%
                                    for(Unidad unidad: micro.getUnidadList()){
                                %>
                                <!-- Unidad 1 -->
                                <div class="card" id="Unidad-<%=unidad.getNum() %>">
                                    <div class="card-header d-flex justify-content-center text-center">
                                        <h2 class="card-title font-weight-bold"><%=unidad.getNombre() %></h2>
                                    </div>
                                    <div class="card-body pb-0 ">
                                        <!-- Progress-Status -->
                                        <div class="progress-card">
                                            <div class="progress-status">
                                                <span class="text-muted fw-bold ml-auto"><%=percentages[unidad.getNum()] %>%</span>
                                            </div>
                                            <div class="progress" style="height: 6px;">
                                                <div class="progress-bar bg-primary" role="progressbar" style="width: <%=percentages[unidad.getNum()] %>%"
                                                     aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"
                                                     data-toggle="tooltip" data-placement="top" title=""
                                                     data-original-title="<%=percentages[unidad.getNum()] %>%"></div>
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
                                                <%
                                                    int i=0;
                                                    for(ContenidoUnidad content: unidad.getContenidoUnidadList()){
                                                %>
                                                <tr>
                                                    <td><%=(++i)%></td>
                                                    <td><%=content.getContenido() %></td>
                                                    <td>
                                                        <div class="">
                                                            <input type="checkbox" class="form-check-input"
                                                                   id="exampleCheck1" <%if(checkeds.get(content.getId())){%>checked<%}%>>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <%}%>
                                            </tbody>
                                        </table>
                                        <!-- Fin Tabla Unidades -->
                                    </div>
                                </div>
                                <%}%>
                                <div class="w-100 d-flex justify-content-center">
                                    <a class="w-75 btn btn-danger" href="seguimiento-materia.jsp">Guardar</a>
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
                                    <%
                                        for(Unidad unidad: micro.getUnidadList()){
                                    %>
                                    <a href="#Unidad-<%=unidad.getNum() %>">
                                        <div class="card btn btn-light mb-2 p-2 d-flex justify-content-center">
                                            <h4 class="card-title ">Unidad <%=unidad.getNum() %></h4>
                                        </div>
                                    </a>
                                    <%}%>
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
        <script src="../../../../assets/js/core/jquery.3.2.1.min.js"></script>
        <script src="../../../../assets/js/core/popper.min.js"></script>
        <script src="../../../../assets/js/core/bootstrap.min.js"></script>
        <!-- jQuery UI -->
        <script src="../../../../assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
        <script src="../../../../assets/js/plugin/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js"></script>
        <!-- jQuery Scrollbar -->
        <script src="../../../../assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
        <!-- Chart Circle -->
        <script src="../../../../assets/js/plugin/chart-circle/circles.min.js"></script>
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
            Circles.create({
                id: 'circles-1',
                radius: 45,
                value: <%=percentages[0] %>,
                maxValue: 100,
                width: 7,
                text: "<%=percentages[0] %>%",
                colors: ['#f1f1f1', '#AA1916'],
                duration: 400,
                wrpClass: 'circles-wrp',
                textClass: 'circles-text',
                styleWrapper: true,
                styleText: true
            });
        </script>
    </body>

</html>
