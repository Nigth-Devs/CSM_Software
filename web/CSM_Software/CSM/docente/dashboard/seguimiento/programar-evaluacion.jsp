<%-- 
    Document   : programar-evaluacion
    Created on : 17-jun-2021, 16:41:08
    Author     : Stiward
--%>

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
                    "families": ["Flaticon", "Font Awesome 5 Solid", "Font Awesome 5 Regular",
                        "Font Awesome 5 Brands",
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
                            <h4 class="page-title">Programar Evaluación</h4>
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
                                        <a href="#">Evaluacion</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="row">
                            <!-- Tabla Grupos-->
                            <div class="col-md-8">
                                <div class="card">
                                    <div class="card-header text-center">
                                        <h2 class="card-title"> Lista Evaluaciones</h2>
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
                                                    <tr>
                                                        <td>1155704</td>
                                                        <td>TEORIA GENERAL DE LAS COMUNICACIONES</td>
                                                        <td>1151201</td>
                                                        <td>Salamanca Landinez Alvaro</td>
                                                        <td>A</td>
                                                        <td>
                                                            <div class="form-button-action">
                                                                <button id="pensum" type="button" data-toggle="tooltip"
                                                                        title="" class="btn btn-link btn-dark"
                                                                        data-original-title="See" style="color: black;">
                                                                    <i class="fas fa-search"></i>
                                                                </button>
                                                                <button type="button" data-toggle="tooltip" title=""
                                                                        class="btn btn-link btn-primary btn-lg"
                                                                        data-original-title="Edit Task">
                                                                    <i class="fa fa-edit" style="color: black;"></i>
                                                                </button>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>1155705</td>
                                                        <td>ANALISIS Y DISEÑO DE SISTEMAS</td>
                                                        <td>1151202</td>
                                                        <td>Pardo Garcia Carlos Eduardo</td>
                                                        <td>A</td>
                                                        <td>
                                                            <div class="form-button-action">
                                                                <button id="pensum" type="button" data-toggle="tooltip"
                                                                        title="" class="btn btn-link btn-dark"
                                                                        data-original-title="See" style="color: black;">
                                                                    <i class="fas fa-search"></i>
                                                                </button>
                                                                <button type="button" data-toggle="tooltip" title=""
                                                                        class="btn btn-link btn-primary btn-lg"
                                                                        data-original-title="Edit Task">
                                                                    <i class="fa fa-edit" style="color: black;"></i>
                                                                </button>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>1155706</td>
                                                        <td>SEMINARIO DE INVESTIGACION III</td>
                                                        <td>1151203</td>
                                                        <td>Vera Rivera Fredy Humberto</td>
                                                        <td>A</td>
                                                        <td>
                                                            <div class="form-button-action">
                                                                <button id="pensum" type="button" data-toggle="tooltip"
                                                                        title="" class="btn btn-link btn-dark"
                                                                        data-original-title="See" style="color: black;">
                                                                    <i class="fas fa-search"></i>
                                                                </button>
                                                                <button type="button" data-toggle="tooltip" title=""
                                                                        class="btn btn-link btn-primary btn-lg"
                                                                        data-original-title="Edit Task">
                                                                    <i class="fa fa-edit" style="color: black;"></i>
                                                                </button>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Find Tabla Grupos -->

                            <!--Crear Grupos-->
                            <div class="col-md-4">
                                <div class="">
                                    <div class="card">
                                        <div class="card-header d-flex justify-content-center">
                                            <h2 class="card-title mtittle">Programar Evaluacion</h2>
                                        </div>
                                        <div class="card-body pb-0 ">
                                            <form>
                                                <div class="form-group">
                                                    <label for="exampleFormControlSelect1">Materia</label>
                                                    <input type="text" class="form-control"
                                                           placeholder="ADMINISTRACION DE PROYECTOS INFORMATICOS" disabled>
                                                </div>
                                                <div class="form-group">
                                                    <label for="exampleFormControlSelect1">Docente</label>
                                                    <input type="text" class="form-control"
                                                           placeholder="Rojas Puentes Maria Del Pilar" disabled>
                                                </div>
                                                <div class="form-group">
                                                    <label for="exampleFormControlSelect1">Grupo</label>
                                                    <input type="text" class="form-control" placeholder="A" disabled>
                                                </div>
                                                <div class="form-group">
                                                    <label for="exampleFormControlSelect1">Fecha</label>
                                                    <input type="text" class="form-control" placeholder="A" disabled>
                                                </div>
                                                <div class="form-group">
                                                    <label for="exampleFormControlSelect1">Hora</label>
                                                    <input type="text" class="form-control" placeholder="A" disabled>
                                                </div>
                                                <div class="form-group">
                                                    <label for="exampleFormControlFile1">Importar Correos</label>
                                                    <input type="file" class="form-control-file"
                                                           id="exampleFormControlFile1">
                                                </div>
                                                <div class="form-group d-flex justify-content-center align-items-center">
                                                    <button class="btn btn-danger">Crear</button>
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