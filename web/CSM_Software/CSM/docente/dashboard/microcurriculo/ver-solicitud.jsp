<%-- 
    Document   : ver-solicitud
    Created on : 17-jun-2021, 16:48:04
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
                            <h4 class="page-title">Ver Solicitud</h4>
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
                                        <a href="solicitudes-microcurriculo.jsp">Solicitudes</a>
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
                        <div class="row">
                            <!--contenido de microcurriculo-->
                            <div class="col-md-10">
                                <!--Microcurriculo-->
                                <div class="card" id="Microcurriculo">
                                    <div class="card-header d-flex justify-content-center">
                                        <h2 class="card-title mtittle "> Microcurriculo</h2>
                                    </div>

                                    <div class="card-body pb-0 ">
                                        <table class="table table-hover ">
                                            <tbody>
                                                <tr>
                                                    <td class="text-right f border-right mth2"> Asignatura </td>
                                                    <td class="mt17" colspan="4">Materia</td>
                                                </tr>
                                                <tr>
                                                    <td class="text-right border-right mth2">Codigo</td>
                                                    <td class="mt17" colspan="4">xxxxx</td>
                                                </tr>
                                                <tr>
                                                    <td class="text-right border-right mth2">Area de Formacion</td>
                                                    <td colspan="4" class="mt17">
                                                        <div class="selectgroup w-100 d-flex justify-content-around ">
                                                            <label class="selectgroup-item pr-3">
                                                                <input type="radio" name="Areadeforamacion" value="50"
                                                                       class="selectgroup-input" disabled>
                                                                <span class="selectgroup-button">Ciencias Basicas</span>
                                                            </label>
                                                            <label class="selectgroup-item pr-3">
                                                                <input type="radio" name="Areadeforamacion" value="100"
                                                                       class="selectgroup-input" disabled>
                                                                <span class="selectgroup-button">Ciencias Basicas
                                                                    Aplicadas</span>
                                                            </label>
                                                            <label class="selectgroup-item pr-3">
                                                                <input type="radio" name="Areadeforamacion" value="150"
                                                                       class="selectgroup-input" checked="" disabled>
                                                                <span class="selectgroup-button">Profecional
                                                                    Especifica</span>
                                                            </label>
                                                            <label class="selectgroup-item ">
                                                                <input type="radio" name="Areadeforamacion" value="200"
                                                                       class="selectgroup-input" disabled>
                                                                <span class="selectgroup-button">Socio
                                                                    Humanistica</span>
                                                            </label>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="text-right border-right mth2">Tipos de Asignatura</td>
                                                    <td colspan="4" class="mt17">
                                                        <div class="selectgroup w-100 d-flex justify-content-around ">
                                                            <label class="selectgroup-item pr-3">
                                                                <input type="radio" name="Tiposdeasignatura" value="50"
                                                                       class="selectgroup-input" disabled>
                                                                <span class="selectgroup-button">Ciencias Basicas</span>
                                                            </label>
                                                            <label class="selectgroup-item pr-3">
                                                                <input type="radio" name="Tiposdeasignatura" value="150"
                                                                       class="selectgroup-input" checked="" disabled>
                                                                <span class="selectgroup-button">Profecional
                                                                    Especifica</span>
                                                            </label>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="text-right border-right mth2">Numero de Creditos</td>
                                                    <td class="mt17" colspan="4">xxxxx</td>
                                                </tr>
                                                <tr>
                                                    <td class="text-right border-right mth2">Prerrequisitos </td>
                                                    <td class="mt17" colspan="4">xxxxx</td>
                                                </tr>
                                                <tr>
                                                    <td class="text-right border-right mth2">Correquisito</td>
                                                    <td class="mt17" colspan="4">xxxxx</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                                <!--Unidades-->
                                <div class="card" id="Unidades">
                                    <div class="card-header">
                                        <div class="d-flex justify-content-center">
                                            <h4 class="card-title mtittle ">Unidades</h4>
                                        </div>
                                    </div>

                                    <div class="card-body pb-0">
                                        <table class="table table-hover">
                                            <tbody>
                                            <thead>
                                                <tr>
                                                    <th class="text-center" scope="col">Unidad</th>
                                                    <th class="text-center" scope="col">Nombre de la unidad y contenido
                                                        tematico</th>
                                                    <th class="text-center" scope="col">Dedicacion del estudiante (Hora)
                                                        Trabajo Presencial
                                                    </th>
                                                    <th class="text-center" scope="col">Dedicacion del estudiante (Hora)
                                                        Trabajo
                                                        Independiente</th>
                                                    <th class="text-center" scope="col">Horas totales (a+b)</th>
                                                </tr>
                                            </thead>
                                            <tr Class="">
                                                <td class="text-center "> 1 </td>
                                                <td class=""> Lorem ipsum dolor sit amet consectetur adipisicing elit.
                                                    Modi dolores laudantium iste, nemo error repudiandae voluptatem
                                                    neque unde minus quae dignissimos est accusantium qui id eligendi
                                                    nam, ad sint perferendis?</td>
                                                <td class="text-center "> 5 </td>
                                                <td class="text-center ">8</td>
                                                <td class="text-center ">1</td>
                                            </tr>
                                            <tr Class="">
                                                <td class="text-center "> 1 </td>
                                                <td class=""> Lorem ipsum dolor sit amet consectetur adipisicing elit.
                                                    Modi dolores laudantium iste, nemo error repudiandae voluptatem
                                                    neque unde minus quae dignissimos est accusantium qui id eligendi
                                                    nam, ad sint perferendis?</td>
                                                <td class="text-center "> 5 </td>
                                                <td class="text-center ">8</td>
                                                <td class="text-center ">1</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                                <!--Justificacion-->
                                <div class="card" id="Justificacion">
                                    <div class="card-header">
                                        <div class="d-flex justify-content-center">
                                            <h4 class="card-title mtittle">Justificacion</h4>
                                        </div>
                                    </div>

                                    <div class="card-body pb-0">
                                        <div class="d-flex justify-content-center ">
                                            <h2 class="">Justificacion y ubicacion en el programa</h2>
                                        </div>
                                        <div class="w-100 d-flex justify-content-center">
                                            <p class="w-75 text-justify ">
                                                El Programa de Ingeniería de Sistemas es consciente de la formación integral
                                                del estudiante ha
                                                formulado asignaturas que conduzcan al futuro profesional a un proceso de
                                                reflexión integración y la
                                                participación activa del estudiante, la asignatura de seminario
                                                investigativo I es la primera de tres
                                                seminarios investigativo que debe adelantar el estudiante en el transcurso
                                                de la carrera. Esto
                                                considerando que la investigación es una actividad sistemática dirigida a
                                                obtener, mediante
                                                observación, la experimentación, nuevas informaciones y conocimientos que
                                                necesitan para ampliar
                                                los diversos campos de la ciencia y la tecnología.

                                                Consciente de que en la actualidad la formación profesional es de mucha
                                                importancia la intervención
                                                y participación activa de los estudiantes, los seminarios de investigación
                                                están orientados al desarrollo
                                                de competencias académicas y profesionales para que el estudiante pueda
                                                asumir con responsabilidad
                                                y actitud crítica las situaciones que se presentan en el campo de la
                                                tecnología y más propiamente de
                                                la ingeniería de sistemas, y que además sea capaz, de transformar temas y
                                                convertirlos en proyectos
                                                de investigación.

                                                Por la naturaleza propia de los seminarios el estudiante entra en estrecho
                                                contacto con el profesor y
                                                éste le ofrece en los ejemplos y trabajos, el medio de desarrollar sus
                                                capacidades y de profundizar en
                                                las bases conceptuales necesarias hacia el camino de la investigación.
                                                Profesores y estudiantes
                                                trabajan conjuntamente para la solución de problemas y tareas las cuales
                                                fueron colocadas para
                                                adelantar la investigación, el estudiante investiga, compara, saca
                                                conclusiones, descubre caminos y
                                                el profesor busca conseguir sus fines por medio de enseñanza-aprendizaje
                                                dialogal.
                                            </p>
                                        </div>
                                    </div>

                                </div>
                                <!--Objetivo general-->
                                <div class="card" id="ObjetivoGeneral">
                                    <div class="card-header">
                                        <div class="d-flex justify-content-center">
                                            <h4 class="card-title mtittle">Objetivo General</h4>
                                        </div>
                                    </div>

                                    <div class="card-body pb-0">
                                        <div class="d-flex justify-content-center ">
                                            <h2 class="">Objetivo General</h2>
                                        </div>
                                        <div class="w-100 d-flex justify-content-center">
                                            <p class="w-75 text-justify ">
                                                Lorem ipsum dolor, sit amet consectetur adipisicing elit. Maiores eveniet
                                                quibusdam quae repellat harum quod soluta ipsum sequi ducimus, est, fuga
                                                quaerat odio impedit minus ipsam ab placeat, architecto velit!
                                            </p>
                                        </div>
                                    </div>

                                </div>
                                <!--Competencias-->
                                <div class="card" id="Competencias">
                                    <div class="card-header">
                                        <div class="d-flex justify-content-center">
                                            <h4 class="card-title mtittle">Competencias</h4>
                                        </div>
                                    </div>

                                    <div class="card-body pb-0">
                                        <table class="table table-hover">
                                            <tbody>
                                            <thead>
                                                <tr>
                                                    <th class="mt17 text-center" scope="col">Competencias
                                                        procedimentales</th>
                                                </tr>
                                            </thead>
                                            <tr class="w-100 d-flex justify-content-center">
                                                <td class="w-75"> Lorem ipsum dolor sit amet consectetur, adipisicing
                                                    elit. Non quibusdam ad nobis rerum! Aliquid eligendi magnam quasi
                                                    quos minima culpa natus ipsam veritatis, distinctio commodi expedita
                                                    officiis cum quod. Iure. </td>
                                            </tr>
                                            <tr class="w-100 d-flex justify-content-center">
                                                <td class="w-75"> Lorem ipsum dolor sit amet consectetur, adipisicing
                                                    elit. Non quibusdam ad nobis rerum! Aliquid eligendi magnam quasi
                                                    quos minima culpa natus ipsam veritatis, distinctio commodi expedita
                                                    officiis cum quod. Iure. </td>
                                            </tr>
                                            <tr class="w-100 d-flex justify-content-center">
                                                <td class="w-75"> Lorem ipsum dolor sit amet consectetur, adipisicing
                                                    elit. Non quibusdam ad nobis rerum! Aliquid eligendi magnam quasi
                                                    quos minima culpa natus ipsam veritatis, distinctio commodi expedita
                                                    officiis cum quod. Iure. </td>
                                            </tr>
                                            </tbody>
                                        </table>

                                        <table class="table table-hover">
                                            <tbody>
                                            <thead>
                                                <tr>
                                                    <th class="mt17 text-center" scope="col">Competencias
                                                        Conceptuales</th>
                                                </tr>
                                            </thead>
                                            <tr class="w-100 d-flex justify-content-center">
                                                <td class="w-75"> Lorem ipsum dolor sit amet consectetur, adipisicing
                                                    elit. Non quibusdam ad nobis rerum! Aliquid eligendi magnam quasi
                                                    quos minima culpa natus ipsam veritatis, distinctio commodi expedita
                                                    officiis cum quod. Iure. </td>
                                            </tr>
                                            <tr class="w-100 d-flex justify-content-center">
                                                <td class="w-75"> Lorem ipsum dolor sit amet consectetur, adipisicing
                                                    elit. Non quibusdam ad nobis rerum! Aliquid eligendi magnam quasi
                                                    quos minima culpa natus ipsam veritatis, distinctio commodi expedita
                                                    officiis cum quod. Iure. </td>
                                            </tr>
                                            <tr class="w-100 d-flex justify-content-center">
                                                <td class="w-75"> Lorem ipsum dolor sit amet consectetur, adipisicing
                                                    elit. Non quibusdam ad nobis rerum! Aliquid eligendi magnam quasi
                                                    quos minima culpa natus ipsam veritatis, distinctio commodi expedita
                                                    officiis cum quod. Iure. </td>
                                            </tr>
                                            </tbody>
                                        </table>

                                        <table class="table table-hover">
                                            <tbody>
                                            <thead>
                                                <tr>
                                                    <th class="mt17 text-center" scope="col">Competencias
                                                        Actitudinales</th>
                                                </tr>
                                            </thead>
                                            <tr class="w-100 d-flex justify-content-center">
                                                <td class="w-75"> Lorem ipsum dolor sit amet consectetur, adipisicing
                                                    elit. Non quibusdam ad nobis rerum! Aliquid eligendi magnam quasi
                                                    quos minima culpa natus ipsam veritatis, distinctio commodi expedita
                                                    officiis cum quod. Iure. </td>
                                            </tr>
                                            <tr class="w-100 d-flex justify-content-center">
                                                <td class="w-75"> Lorem ipsum dolor sit amet consectetur, adipisicing
                                                    elit. Non quibusdam ad nobis rerum! Aliquid eligendi magnam quasi
                                                    quos minima culpa natus ipsam veritatis, distinctio commodi expedita
                                                    officiis cum quod. Iure. </td>
                                            </tr>
                                            <tr class="w-100 d-flex justify-content-center">
                                                <td class="w-75"> Lorem ipsum dolor sit amet consectetur, adipisicing
                                                    elit. Non quibusdam ad nobis rerum! Aliquid eligendi magnam quasi
                                                    quos minima culpa natus ipsam veritatis, distinctio commodi expedita
                                                    officiis cum quod. Iure. </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                                <!--Contenido por unidad-->
                                <div class="card" id="ContenidoPorUnidad">
                                    <div class="card-header">
                                        <div class="d-flex justify-content-center">
                                            <h4 class="card-title mtittle ">Contenido por Unidades</h4>
                                        </div>
                                    </div>

                                    <div class="card-body pb-0">
                                        <table class="table table-hover">
                                            <tbody>
                                            <thead>
                                                <tr>
                                                    <th class="text-center mt17" scope="col">Contenido por Unidades</th>
                                                    <th class="text-center mt17" scope="col">Actividades presenciales
                                                    </th>
                                                    <th class="text-center mt17" scope="col">Trabajo independiente</th>
                                                </tr>
                                            </thead>
                                            <tr>
                                                <td> Lorem ipsum dolor sit amet consectetur, adipisicing
                                                    elit. Non quibusdam ad nobis rerum! Aliquid eligendi magnam quasi
                                                    quos minima culpa natus ipsam veritatis, distinctio commodi expedita
                                                    officiis cum quod. Iure. </td>
                                                <td> Lorem ipsum dolor sit amet consectetur, adipisicing
                                                    elit. Non quibusdam ad nobis rerum! Aliquid eligendi magnam quasi
                                                    quos minima culpa natus ipsam veritatis, distinctio commodi expedita
                                                    officiis cum quod. Iure. </td>
                                                <td> Lorem ipsum dolor, sit amet consectetur adipisicing elit. Eos saepe
                                                    repellendus provident ratione, iste inventore unde et a nisi culpa,
                                                    harum illum, aliquid exercitationem excepturi earum deleniti nulla
                                                    repellat error.</td>
                                            </tr>
                                            <tr>
                                                <td> Lorem ipsum dolor sit amet consectetur, adipisicing
                                                    elit. Non quibusdam ad nobis rerum! Aliquid eligendi magnam quasi
                                                    quos minima culpa natus ipsam veritatis, distinctio commodi expedita
                                                    officiis cum quod. Iure. </td>
                                                <td> Lorem ipsum dolor sit amet consectetur, adipisicing
                                                    elit. Non quibusdam ad nobis rerum! Aliquid eligendi magnam quasi
                                                    quos minima culpa natus ipsam veritatis, distinctio commodi expedita
                                                    officiis cum quod. Iure. </td>
                                                <td> Lorem ipsum dolor, sit amet consectetur adipisicing elit. Eos saepe
                                                    repellendus provident ratione, iste inventore unde et a nisi culpa,
                                                    harum illum, aliquid exercitationem excepturi earum deleniti nulla
                                                    repellat error.</td>
                                            </tr>
                                            <tr>
                                                <td> Lorem ipsum dolor sit amet consectetur, adipisicing
                                                    elit. Non quibusdam ad nobis rerum! Aliquid eligendi magnam quasi
                                                    quos minima culpa natus ipsam veritatis, distinctio commodi expedita
                                                    officiis cum quod. Iure. </td>
                                                <td> Lorem ipsum dolor sit amet consectetur, adipisicing
                                                    elit. Non quibusdam ad nobis rerum! Aliquid eligendi magnam quasi
                                                    quos minima culpa natus ipsam veritatis, distinctio commodi expedita
                                                    officiis cum quod. Iure. </td>
                                                <td> Lorem ipsum dolor, sit amet consectetur adipisicing elit. Eos saepe
                                                    repellendus provident ratione, iste inventore unde et a nisi culpa,
                                                    harum illum, aliquid exercitationem excepturi earum deleniti nulla
                                                    repellat error.</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                                <!--Metodologia-->
                                <div class="card" id="Metodologia">
                                    <div class="card-header">
                                        <div class="d-flex justify-content-center">
                                            <h4 class="card-title mtittle">Metodologia</h4>
                                        </div>
                                    </div>

                                    <div class="card-body pb-0">
                                        <div class="d-flex justify-content-center ">
                                            <h2 class="">Metodologia</h2>
                                        </div>
                                        <div class="w-100 d-flex justify-content-center">
                                            <p class="w-75 text-justify ">
                                                Lorem ipsum dolor, sit amet consectetur adipisicing elit. Maiores eveniet
                                                quibusdam quae repellat harum quod soluta ipsum sequi ducimus, est, fuga
                                                quaerat odio impedit minus ipsam ab placeat, architecto velit!
                                            </p>
                                        </div>
                                    </div>

                                </div>
                                <!--Estrategias de Evaluacion-->
                                <div class="card" id="EstrategiasDeEvaluacion">
                                    <div class="card-header">
                                        <div class="d-flex justify-content-center">
                                            <h4 class="card-title mtittle">Estrategias de Evaluacion</h4>
                                        </div>
                                    </div>

                                    <div class="card-body pb-0">
                                        <div class="d-flex justify-content-center ">
                                            <h2 class="">Estrategias de Evaluacion</h2>
                                        </div>
                                        <div class="w-100 d-flex justify-content-center">
                                            <p class="w-75 text-justify ">
                                                Lorem ipsum dolor, sit amet consectetur adipisicing elit. Maiores eveniet
                                                quibusdam quae repellat harum quod soluta ipsum sequi ducimus, est, fuga
                                                quaerat odio impedit minus ipsam ab placeat, architecto velit!
                                            </p>
                                        </div>
                                    </div>

                                </div>
                                <!--Recursos-->
                                <div class="card" id="Recursos">
                                    <div class="card-header">
                                        <div class="d-flex justify-content-center">
                                            <h4 class="card-title mtittle">Recursos</h4>
                                        </div>
                                    </div>

                                    <div class="card-body pb-0">
                                        <div class="d-flex justify-content-center ">
                                            <h2 class="">Recursos Utilizados</h2>
                                        </div>
                                        <div class="w-100 d-flex justify-content-center">
                                            <p class="w-75 text-justify ">
                                                Lorem ipsum dolor, sit amet consectetur adipisicing elit. Maiores eveniet
                                                quibusdam quae repellat harum quod soluta ipsum sequi ducimus, est, fuga
                                                quaerat odio impedit minus ipsam ab placeat, architecto velit!
                                            </p>
                                        </div>
                                    </div>

                                </div>
                                <!--Bibliografia-->
                                <div class="card" id="Bibliografia">
                                    <div class="card-header">
                                        <div class="d-flex justify-content-center">
                                            <h4 class="card-title mtittle">Bibliografía</h4>
                                        </div>
                                    </div>

                                    <div class="card-body pb-0">
                                        <div class="d-flex justify-content-center ">
                                            <h2 class="">Bibliografía</h2>
                                        </div>
                                        <div class="w-100 d-flex justify-content-center">
                                            <p class="w-75 text-justify ">
                                                Lorem ipsum dolor, sit amet consectetur adipisicing elit. Maiores eveniet
                                                quibusdam quae repellat harum quod soluta ipsum sequi ducimus, est, fuga
                                                quaerat odio impedit minus ipsam ab placeat, architecto velit!
                                            </p>
                                        </div>
                                    </div>

                                </div>

                            </div>
                            <!--fin contenido de microcurriculo-->

                            <!--navegacion de microcurriculo-->
                            <div class="col-md-2 position-relative">
                                <div class=" position-fixed ">
                                    <h2 class="d-flex justify-content-center font-weight-bold">Navegacion</h2>

                                    <a href="#Microcurriculo">
                                        <div class="card btn btn-light mb-2 p-2 d-flex justify-content-center">
                                            <h4 class="card-title ">Microcurriculo</h4>
                                        </div>
                                    </a>
                                    <a href="#Unidades">
                                        <div class="card btn btn-light mb-2 p-2 d-flex justify-content-center">
                                            <h4 class="card-title ">Unidades</h4>
                                        </div>
                                    </a>
                                    <a href="#Justificacion">
                                        <div class="card btn btn-light mb-2 p-2 d-flex justify-content-center">
                                            <h4 class="card-title ">Justificacion</h4>
                                        </div>
                                    </a>
                                    <a href="#ObjetivoGeneral">
                                        <div class="card btn btn-light mb-2 p-2 d-flex justify-content-center">
                                            <h4 class="card-title ">Objetivo General</h4>
                                        </div>
                                    </a>
                                    <a href="#Competencias">
                                        <div class="card btn btn-light mb-2 p-2 d-flex justify-content-center">
                                            <h4 class="card-title ">Competencias</h4>
                                        </div>
                                    </a>
                                    <a href="#ContenidoPorUnidad">
                                        <div class="card btn btn-light mb-2 p-2 d-flex justify-content-center">
                                            <h4 class="card-title ">Contenido Por Unidad</h4>
                                        </div>
                                    </a>
                                    <a href="#Metodologia">
                                        <div class="card btn btn-light mb-2 p-2 d-flex justify-content-center">
                                            <h4 class="card-title ">Metodologia</h4>
                                        </div>
                                    </a>
                                    <a href="#EstrategiasDeEvaluacion">
                                        <div class="card btn btn-light mb-2 p-2 d-flex justify-content-center">
                                            <h4 class="card-title ">Estrategias De Evaluacion</h4>
                                        </div>
                                    </a>
                                    <a href="#Recursos">
                                        <div class="card btn btn-light mb-2 p-2 d-flex justify-content-center">
                                            <h4 class="card-title ">Recursos</h4>
                                        </div>
                                    </a>
                                    <a href="#Bibliografia">
                                        <div class="card btn btn-light mb-2 p-2 d-flex justify-content-center">
                                            <h4 class="card-title ">Bibliografia</h4>
                                        </div>
                                    </a>
                                </div>


                            </div>
                            <!--fin navegacion de microcurriculo-->
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
