<%-- 
    Document   : ver-pensum
    Created on : 17-jun-2021, 16:45:02
    Author     : Stiward
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- Estilos CSS -->
        <link rel="stylesheet" href="../../../../assets/css/ver-pensum.css">
    </head>

    <body>

        <!-- Modal -->
        <div class="modal fade" id="calculodiferencial" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header no-bd">
                        <h4 class="modal-title">
                            <b>SEMINARIO INVESTIGATIVO I - 115 5306-B</b>
                        </h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="row">
                                <div class="col-12">
                                    <div class="form-group form-group-default">
                                        <h6><b>CONTENIDO</b></h6>

                                        <p>UNIDAD 1. Medios, instrumentos, técnicas y
                                            método en la recolección de datos e
                                            información.</p>

                                        <p>UNIDAD 2. Tabulación, análisis e interpretación
                                            de datos.</p>


                                        <p>UNIDAD 3. Informe y presentación de los
                                            datos de una investigación.</p>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-group form-group-default">
                                        <h6><b>BIBLIOGRAFIA</b></h6>

                                        <p>- CERDA HUGO. Los elementos de la Investigación,
                                            como reconocerlos, diseñarlos y construirlos.
                                            Tercera edición, 2008. Editorial El Buho.</p>

                                        <p>- MUÑOZ G. Jose, QUINTERO C. Josefina. Como
                                            desarrollar competencias investigativas en
                                            educación. 4 edición. Editorial Magisterio.</p>

                                        <p>- HERNANDEZ Sampieri; FERNANDEZ Collado; BAPTISTA
                                            Lucio. Metodología de la investigación. McGraw
                                            Hill. 2006.</p>

                                        <p>- MÉNDEZ A. Carlos E. Metodología. McGraw Hill.
                                            2005.</p>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer no-bd">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="card">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>
                                    <h3 class="title">PRIMER SEMESTRE</h3>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <button class="btn btn-light btn-round ml-auto" data-toggle="modal"
                                            data-target="#calculodiferencial">
                                        <b>1155101</b><br />
                                        Calculo Diferencial<br />
                                        Horas:4 Cred:4<br />
                                        2018
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <button class="btn btn-light btn-round ml-auto" data-toggle="modal"
                                            data-target="#calculodiferencial">
                                        <b>1155102</b><br />
                                        Matematicas Discretas<br />
                                        horas:3 Cred:3<br />
                                        2018
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <button class="btn btn-light btn-round ml-auto" data-toggle="modal"
                                            data-target="#calculodiferencial">
                                        <b> 1155104</b><br />
                                        Fundamentos de Programación<br />
                                        Horas:4 Cred:3<br />
                                        2018
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <button class="btn btn-light btn-round ml-auto" data-toggle="modal"
                                            data-target="#calculodiferencial">
                                        <b>1155105</b><br />
                                        Introduccion Ing Sistemas<br />
                                        Horas:3 Cred:3<br />
                                        2018
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <button class="btn btn-light btn-round ml-auto" data-toggle="modal"
                                            data-target="#calculodiferencial">
                                        <b>1155106</b><br />
                                        Comunicacion I<br />
                                        Horas:2 Cred:2<br />
                                        2018
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <button class="btn btn-light btn-round ml-auto" data-toggle="modal"
                                            data-target="#calculodiferencial">
                                        <b>1155108</b><br />
                                        Intro Vida Universitaria<br />
                                        Horas:1 Cred:1<br />
                                        2018
                                    </button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card" id="pensum">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>
                                    <h3 class="title">SEGUNDO SEMESTRE</h3>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <b>1155201</b><br />
                                    Calculo Integral<br />
                                    Horas:4 Cred:4<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155202</b><br />
                                    Algebra Lineal<br />
                                    Horas:3 Cred:3<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155203</b><br />
                                    Fisica Mecanica<br />
                                    Horas:4 Cred:4<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155204</b><br />
                                    Programación Orientada a Objectos<br />
                                    Horas:4 Cred:3<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155206</b><br />
                                    Comunicacion II<br />
                                    Horas:2 Cred:2<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155209</b><br />
                                    Seminario Integrador I<br />
                                    Horas:1 Cred:1<br />
                                    2018
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>
                                    <h3 class="title">TERCER SEMESTRE</h3>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <b>1155301</b><br />
                                    Calculo Vectorial<br />
                                    Horas:4 Cred:4<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155303</b><br />
                                    Fisica Electromagnetica<br />
                                    Horas:4 Cred:4<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155304</b><br />
                                    Estructuras de Datos<br />
                                    Horas:4 Cred:3<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155305</b><br />
                                    Programación Orientada a Objectos II<br />
                                    Horas:4 Cred:3<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155306</b><br />
                                    Seminario Investigación I<br />
                                    Horas:2 Cred:2<br />
                                    2018
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>
                                    <h3 class="title">CUARTO SEMESTRE</h3>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <b>1155401</b><br />
                                    Ecuaciones Diferenciales<br />
                                    Horas:4 Cred:4<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155402</b><br />
                                    Probabilidad y Estadistica<br />
                                    Horas:4 Cred:3<br />
                                    2018<br />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155403</b><br />
                                    Ondas y Particulas<br />
                                    Horas:3 Cred:3<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155404</b><br />
                                    Analisis de Algoritmos<br />
                                    Horas:3 Cred:3<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155405</b><br />
                                    Teoria de la Computación<br />
                                    Horas:3 Cred:3<br />
                                    2018
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>
                                    <h3 class="title">QUINTO SEMESTRE</h3>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <b>1155501</b><br />
                                    Analisis Numerico<br />
                                    Horas:3 Cred:3<br />
                                    2020-1
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155502</b><br />
                                    Investigación de Operaciones<br />
                                    Horas:3 Cred:3<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155503</b><br />
                                    Electronica<br />
                                    Horas:4 Cred:3<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155504</b><br />
                                    Arquitectura de Computadores<br />
                                    Horas:3 Cred:3<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155506</b><br />
                                    Seminario Investigación II<br />
                                    Horas:2 Cred:2<br />
                                    2018
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>
                                    <h3 class="title">SEXTO SEMESTRE</h3>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <b>1155604</b><br />
                                    Sistemas Operativos<br />
                                    Horas:3 Cred:3<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155605</b><br />
                                    Bases de Datos<br />
                                    Horas:4 Cred:3<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155606</b><br />
                                    Programacion Web<br />
                                    Horas:4 Cred:3<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155607</b><br />
                                    Constitucion y Civismo<br />
                                    Horas:2 Cred:2<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155608</b><br />
                                    Planeacion Estrategica de Sistemas Información<br />
                                    Horas:3 Cred:3<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155609</b><br />
                                    Seminario Integrador II<br />
                                    Horas:2 Cred:2<br />
                                    2018
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>
                                    <h3 class="title">SEPTIMO SEMESTRE</h3>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <b>1155704</b><br />
                                    Teoria General de Comunicaciones<br />
                                    Horas:3 Cred:3<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155705</b><br />
                                    Analisis y Diseño de Sistema<br />
                                    Horas:4 Cred:4<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155706</b><br />
                                    Seminario Investigación III<br />
                                    Horas:2 Cred:2<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155707</b><br />
                                    Etica Profesional<br />
                                    Horas:2 Cred:2<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155708</b><br />
                                    Administracion de Proyectos Informaticos<br />
                                    Horas:3 Cred:3<br />
                                    2018
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>
                                    <h3 class="title">OCTAVO SEMESTRE</h3>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <b>1155804</b><br />
                                    Redes de Computadores<br />
                                    Horas:3 Cred:3<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155805</b><br />
                                    Ingenieria de Software<br />
                                    Horas:4 Cred:4<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155108</b><br />
                                    Formulacion y Evaluación de Proyectos<br />
                                    Horas:3 Cred:3<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155809</b><br />
                                    Seminario Integrador III<br />
                                    Horas:2 Cred:2<br />
                                    2020
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>
                                    <h3 class="title">NOVENO SEMESTRE</h3>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <b>1155905</b><br />
                                    Arquitectura de software<br />
                                    Horas:3 Cred:3<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155908</b><br />
                                    Gestion de Tics<br />
                                    Horas:2 Cred:2<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1155909</b><br />
                                    Practicas en Ing. Sistemas<br />
                                    Horas:4 Cred:6<br />
                                    2018
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>
                                    <h3 class="title">DECIMO SEMESTRE</h3>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <b>1151059</b><br />
                                    Sistemas Distribuidos<br />
                                    Horas:3 Cred:3<br />
                                    2018
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>1156001</b><br />
                                    Proyecto de Grado<br />
                                    Horas:0 Cred:8<br />
                                    2018
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
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
    </body>

</html>
