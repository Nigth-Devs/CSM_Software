<%-- 
    Document   : MicrocurriculoDocente
    Created on : 16/06/2021, 06:17:44 PM
    Author     : jhoser
--%>

<%@page import="java.util.List"%>
<%@page import="dto.MateriaPeriodo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <thead>            
            <th>Pensum</th>
            <th>Codigo Materia</th>
            <th>Nombre Materia</th>
            <th>Creditos</th>
            <th>Semestre</th>
            <th>Accion</th>
        </thead>     
        <tbody>

            <%
                List<MateriaPeriodo> mp = (List<MateriaPeriodo>) request.getSession().getAttribute("misMicrocurriculos");

                for (MateriaPeriodo materia : mp) {
            %>
            <tr>
                <td><%=materia.getMateria().getPensum().getPensumPK().getProgramaCodigo() + "-" + materia.getMateria().getPensum().getPensumPK().getCodigo()%></td> 
                <td><%=materia.getMateria().getMateriaPK().getCodigoMateria()%></td> 
                <td><%=materia.getMateria().getNombre()%></td> 
                <td><%=materia.getMateria().getCreditos()%></td>                
                <td><%=materia.getMateriaPeriodoPK().getSemestreAnio()%></td>         
                <td>
                    ALGO1
                    ALGO2
                </td> 
            </tr>
            <%}%>

        </tbody>

    </table> 
</body>
</html>
