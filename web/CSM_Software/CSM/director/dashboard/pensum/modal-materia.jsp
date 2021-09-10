<%-- 
    Document   : modal-materia
    Created on : 15-jun-2021, 23:45:18
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
        <!-- Modal -->
        <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle"
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
