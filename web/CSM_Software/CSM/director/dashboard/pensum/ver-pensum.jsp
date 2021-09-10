<%-- 
    Document   : ver-pensum
    Created on : 15-jun-2021, 23:45:57
    Author     : Stiward
--%>

<%@page import="dto.Materia"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- Estilos CSS -->
        <link rel="stylesheet" href="../../../../assets/css/ver-pensum.css">
    </head>

    <body>

        <!-- Modal -->
        <div class="modal fade" id="modal-materia" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content" id="modal-content">
                    <div class="modal-footer no-bd">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <%
            List<Materia> materiasSemestre[] = (List<Materia>[]) request.getSession().getAttribute("materiasSemestre");
            if (materiasSemestre != null && materiasSemestre.length > 0) {
                String titles[] = {"PRIMER SEMESTRE", "SEGUNDO SEMESTRE", "TERCER SEMESTRE", "CUARTO SEMESTRE", "QUINTO SEMESTRE",
                    "SEXTO SEMESTRE", "SEPTIMO SEMESTRE", "OCTAVO SEMESTRE", "NOVENO SEMESTRE", "DECIMO SEMESTRE"};
        %>
        <div class="container">
            <%
                int i = 0;
                for (List<Materia> materias : materiasSemestre) {
            %>
            <div class="card">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>
                                    <h3 class="title"><%=titles[i++]%></h3>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (Materia materia : materias) {
                            %>
                            <tr>
                                <td>
                                    <a onclick="verMateria(<%=materia.getMateriaPK().getCodigoMateria() %>,<%=materia.getSemestre() %>)" class="btn btn-light btn-round ml-auto">
                                        <b><%=materia.getMateriaPK().getCodigoMateria()%></b><br/>
                                        <%=materia.getNombre()%><br />
                                        Horas: <%=materia.getHt()%> Cred: <%=materia.getCreditos()%>
                                    </a>
                                </td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
            <%
                    }
                }
            %>

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
    
    <script>
        function verMateria(codigo, semestre) {
            $.post('../../../../../ControladorMicrocurriculo?accion=ver-materia', {cod: codigo, sem: semestre}, function(response){
                $('#modal-content').html(response);
                $('#modal-materia').modal('show');
            });
        }
    </script>
</html>