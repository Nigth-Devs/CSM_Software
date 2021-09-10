<%-- 
    Document   : listaPensum
    Created on : 15/06/2021, 07:35:12 PM
    Author     : jhoser
--%>

<%@page import="dto.Materia"%>
<%@page import="dto.Pensum"%>
<%@page import="java.util.List"%>
<%@page import="dto.Usuario"%>
<%@page import="negocio.AdministrarPensum"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Pensum</title>
    </head>
    <body>
        <h1>LISTAR MATERIAS TODAS</h1>

        <%
            List<Materia> materias = (List<Materia>) request.getSession().getAttribute("listaMateriasTodas");
            int mayor = 0;
            for (Materia m : materias) {
                if (m.getSemestre() > mayor) {

                    mayor = m.getSemestre();%>
        <h1><%= mayor%></h1>
        <%
            }
        %>
        Semestre  <%= m.getSemestre()%> <br>
        Codigo <%= m.getMateriaPK().getCodigoMateria()%> <br>
        Nombre <%= m.getNombre()%> <br>
        Creditos <%= m.getCreditos()%> <br>

        <%} %>

        <h1>Lista Pensum</h1>
        <table>
            <thead>
                <tr>
                    <th>Codigo</th>
                    <th>Creditos</th>
                    <th>Materias</th>
                    <th>Accion</th>
                </tr>
            </thead>
            <%
                AdministrarPensum admin = new AdministrarPensum();
                Usuario u = (Usuario) request.getSession().getAttribute("usuario");

                List<Pensum> pensum = (List<Pensum>) request.getSession().getAttribute("listaPensum2");
                //creditos 
                //cantidamaterias
                if (pensum != null) {
                    for (Pensum p : pensum) {
                        if (u.getDocente().getProgramaList().get(0).getDirectorPrograma().getCodigoDocente() == p.getPrograma().getDirectorPrograma().getCodigoDocente()) {
                            int materiaXcreditos[] = admin.creditosMateriasPensum(p.getPensumPK().getCodigo(), p.getPensumPK().getProgramaCodigo());
            %>
            <tbody>
                <tr>
                    <td><%= p.getPensumPK().getProgramaCodigo() + "-" + p.getPensumPK().getCodigo()%></td>
                    <td ><%= materiaXcreditos[1]%></td>
                    <td ><%= materiaXcreditos[0]%></td>
                    <td>Descargar<td>Visualizar</td></td>
                </tr>
            </tbody>
            <%}
                    }
                }%>
        </table>
    </body>
</html>
