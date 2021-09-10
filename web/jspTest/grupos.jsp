<%-- 
    Document   : grupos
    Created on : 10/06/2021, 07:45:23 PM
    Author     : jhoser
--%>

<%@page import="dto.MateriaPeriodoGrupo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">      
        <script src="../js/JQuery.js"></script>
        <script src="../js/administrarGrupos.js"></script>
        <title>Grupos</title>
    </head>
    <body>
        <h1>Grupos!</h1>

        <div>
            <form action="../ControladorGrupos" method="GET">
                <label>Pensum</label>
                <select name="optionPensum" id="optionPensum" onchange="searchMateria()"></select>
                <%dto.Usuario usuario = (dto.Usuario) (request.getSession().getAttribute("usuario"));%>
                <br><br>


                <label>Materia  </label>
                <select name="optionMateria" id="optionMateria">
                </select><br><br>

                <label> Docente  </label>
                <% List<dto.Docente> docentes = (List<dto.Docente>) (request.getSession().getAttribute("docentesPrograma"));%>
                <select name="optionDocente" id="optionDocente">
                    <%
                        for (dto.Docente docente : docentes) {


                    %>

                    <option value="<%=docente.getCodigoDocente()%>"><%=docente.getNombre() + " " + docente.getApellido()%></option>
                    <%}%>
                </select><br>    <br>         




                <%    Date fecha = new Date();%> 

                <input type="hidden" name="anio" value="<%=fecha.getYear() + 1900%>">
                <input type="hidden" name="periodo" value="<%=fecha.getMonth() + 1%>">

                <input type="submit" name="accion" value="Registrar Grupo"> Crear Grupo 
            </form><br><br>
        </div> 


        <div>
            <table>
                <thead>
                    <tr>
                        <th>Codigo Materia</th>
                        <th>Nombre Materia</th>
                        <th>Codigo Docente</th>
                        <th>Nombre Docente</th>
                        <th>Grupo</th>
                        <th>Accion</th>
                    </tr>
                </thead>

                <tbody>
                    <% List<dto.MateriaPeriodoGrupo> materias = (List<dto.MateriaPeriodoGrupo>)request.getSession().getAttribute("grupos");
                    for (dto.MateriaPeriodoGrupo materia : materias) {
                            
                        
                    %>
                    <tr>
                        <td><%=materia.getMateriaPeriodo().getMateria().getMateriaPK().getCodigoMateria() %></td>
                        <td><%=materia.getMateriaPeriodo().getMateria().getNombre() %></td>
                        <td><%=materia.getDocente().getCodigoDocente() %></td>
                        <td><%=materia.getDocente().getNombre()+" "+materia.getDocente().getApellido() %></td>
                        <td><%=materia.getMateriaPeriodoGrupoPK().getGrupo() %></td>
                        <td><a href="../ControladorGrupos?accion=Eliminar&grupo=<%=materia.getMateriaPeriodoGrupoPK().getGrupo()%>&docente_codigo=<%=materia.getDocente().getCodigoDocente()%>&anio=<%=materia.getMateriaPeriodoGrupoPK().getMateriaPeriodoAnio() %>&semestre_anio=<%=materia.getMateriaPeriodoGrupoPK().getMateriaPeriodoSemestreAnio() %>&codigo_materia=<%=materia.getMateriaPeriodoGrupoPK().getMateriaPeriodoMateriaCodigoMateria()%>&codigoPensum=<%=materia.getMateriaPeriodoGrupoPK().getMateriaPeriodoMateriaPensumCodigo() %>"><button type="button">Eliminar</button></a> </td>
                    </tr>
                    <%}%>
                </tbody>


            </table>
        </div> 
    </body>
</html>
