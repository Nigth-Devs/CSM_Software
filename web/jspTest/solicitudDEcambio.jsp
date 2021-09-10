<%-- 
    Document   : solicitudDEcambio
    Created on : 18-jun-2021, 23:23:20
    Author     : Familia Pena Mena
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>JSP Page</title>
        <script src="../js/JQuery.js"></script>
        <script src="../js/microcurriculo.js"></script>
    </head>
    <body>
        <div class="table-responsive">

            <%
                negocio.AdministrarMicrocurriculo aas = new negocio.AdministrarMicrocurriculo();
                List<dto.SeccionCambio> lista = aas.obtenerSeccionesCambios();
            %>


            <table id="basic-datatables"
                   class="display table table-striped table-hover text-center">
                <thead>
                    <tr>
                        <th>Código Materia</th>
                        <th>Nombre Materia</th>
                        <th>Estado</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>Código Materia</th>
                        <th>Nombre Materia</th>
                        <th>Estado</th>
                        <th>Action</th>
                    </tr>
                </tfoot>
                <tbody>
                    <%
                                                        for (dto.SeccionCambio sec : lista) {%>

                    <tr>
                        <th><%=sec.getSeccionMicrocurriculoIdNuevo().getMicrocurriculo().getMateria().getMateriaPK().getCodigoMateria()%></th>
                        <th><%=sec.getSeccionMicrocurriculoIdNuevo().getMicrocurriculo().getMateria().getNombre()%></th>
                        <th><%=sec.getCambioId().getEstadoId().getEstado()%></th>
                        <td>
                            <div class="form-button-action">
                                <button id="pensum" type="button" data-toggle="tooltip"
                                        title="" class="btn btn-link btn-dark"
                                        data-original-title="See" style="color: black;">
                                    <i class="fas fa-search"></i>
                                </button>
                                <button type="button" data-toggle="tooltip" title=""
                                        class="btn btn-link btn-dark"
                                        data-original-title="Export PDF" style="color: black;">
                                    <i class="fas fa-file-pdf"></i>
                                </button>
                            </div>
                        </td>
                    </tr>


                    <%

                        }
                    %>

                </tbody>
            </table>
        </div>



</html>
