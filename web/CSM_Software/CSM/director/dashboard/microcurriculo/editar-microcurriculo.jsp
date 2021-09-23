<%-- 
    Document   : editar-microcurriculo
    Created on : 15-jun-2021, 23:35:34
    Author     : Stiward
--%>

<%@page import="dto.ContenidoUnidad"%>
<%@page import="dto.Unidad"%>
<%@page import="dto.Tabla"%>
<%@page import="dto.TablaSeccion"%>
<%@page import="dto.SeccionMicrocurriculo"%>
<%@page import="dto.AreaFormacion"%>
<%@page import="dto.Microcurriculo"%>
<%@page import="dto.TipoMateria"%>
<%@page import="dto.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>CSM Software</title>
        <meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />
        <link rel="icon" href="<%=request.getContextPath()%>/CSM_Software/assets/img/icon.ico" type="image/x-icon" />

        <!-- Fonts and icons -->
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/webfont/webfont.min.js"></script>
        <!-- Custom JS -->
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/JQuery.js"></script>
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/micro.js"></script>
        <script>
            WebFont.load({
                google: {
                    "families": ["Lato:300,400,700,900"]
                },
                custom: {
                    "families": ["Flaticon", "Font Awesome 5 Solid", "Font Awesome 5 Regular", "Font Awesome 5 Brands",
                        "simple-line-icons"
                    ],
                    urls: ['<%=request.getContextPath()%>/CSM_Software/assets/css/fonts.min.css']
                },
                active: function () {
                    sessionStorage.fonts = true;
                }
            });
        </script>

        <!-- CSS Files -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/atlantis.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/microcurriculo.css">
        <!-- CSS Just for demo purpose, don't include it in your project -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSM_Software/assets/css/demo.css">
        <style>
            input[type="text"]:disabled{
                background: #fff;
            }
            
            .form-micro{
                display:block;
                width:100%;
                border:none;
                margin:auto auto;
                text-align:center;
            }
        </style>
    </head>

    <body>
        <%
            Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        %>
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
                            <h4 class="page-title">Editar Microcurriculo</h4>
                            <div class="ml-md-auto">
                                <ul class="breadcrumbs">
                                    <li class="nav-home">
                                        <a href="<%=request.getContextPath()%>/CSM_Software/CSM/director/dashboard/dashboard.jsp">
                                            <i class="flaticon-home"></i>
                                        </a>
                                    </li>
                                    <li class="separator">
                                        <i class="flaticon-right-arrow"></i>
                                    </li>
                                    <li class="nav-item">
                                        <a href="<%=request.getContextPath()%>/ControladorMicrocurriculo?accion=listarTodos">Microcurriculo</a>
                                    </li>
                                    <li class="separator">
                                        <i class="flaticon-right-arrow"></i>
                                    </li>
                                    <li class="nav-item">
                                        <a href="#">Editar</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <%
                            Microcurriculo microcurriculo = (Microcurriculo) request.getSession().getAttribute("microcurriculo");
                            List<AreaFormacion> areasFormacion = (List<AreaFormacion>) request.getSession().getAttribute("areasFormacion");
                            List<TipoMateria> tiposAsignatura = (List<TipoMateria>) request.getSession().getAttribute("tipoAsignatura");
                        %>
                        <div class="row">
                            <!-- Contenido de Microcurriculo -->
                            <div class="col-md-10">
                                <form action="../../../../../ControladorMicrocurriculo?accion=editar" method="POST" accept-charset="ISO-8859-1">
                                    <input type="hidden"  name="microcurriculoId"  value=<%=microcurriculo.getMicrocurriculoPK().getMateriaCodigo()+"-"+microcurriculo.getMicrocurriculoPK().getMateriaPensumCodigo() %>>
                                    <!--Microcurriculo-->
                                    <div class="card">
                                        <div class="card-header d-flex justify-content-center">
                                            <h2 class="card-title mtittle "> Microcurriculo</h2>
                                        </div>
                                        <div class="card-body pb-0 ">
                                            <table class="table table-hover ">
                                                <tbody>
                                                    <tr>
                                                        <td class="text-right f border-right mth2"> Asignatura </td>
                                                        <td class="mt17" colspan="4"><%=microcurriculo.getMateria().getNombre()%></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-right border-right mth2">Codigo</td>
                                                        <td class="mt17" colspan="4"><%=microcurriculo.getMateria().getMateriaPK().getCodigo()%></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-right border-right mth2">Area de Formacion</td>
                                                        <td colspan="4" class="mt17">
                                                            <div class="selectgroup w-100 d-flex justify-content-around">
                                                                <%
                                                                    int id = microcurriculo.getAreaDeFormacionId().getId();
                                                                    for (dto.AreaFormacion elem : areasFormacion) {
                                                                %>
                                                                <label class="selectgroup-item pr-3">
                                                                    <input type="radio" name="areasFormacion" value="<%=elem.getId()%>"
                                                                           class="selectgroup-input" <%if(id==elem.getId()){%>checked<%}%>>
                                                                    <span class="selectgroup-button"><%=elem.getNombre()%></span>
                                                                </label>
                                                                <%
                                                                    }
                                                                %>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-right border-right mth2">Tipos de Asignatura</td>
                                                        <td colspan="4" class="mt17">
                                                            <div class="selectgroup w-100 d-flex justify-content-around ">
                                                                <%
                                                                    for (TipoMateria elem : tiposAsignatura) {
                                                                        if (microcurriculo.getMateria().getTipoId().getId() == elem.getId()) {
                                                                %>
                                                                <label class="selectgroup-item pr-3">
                                                                    <input type="radio" name="Tiposdeasignatura" value="50"
                                                                           class="selectgroup-input" checked="" disabled>
                                                                    <span class="selectgroup-button"><%=microcurriculo.getMateria().getTipoId().getTipo()%></span>
                                                                </label>
                                                                <%
                                                                } else {
                                                                %>
                                                                <label class="selectgroup-item pr-3">
                                                                    <input type="radio" name="Tiposdeasignatura" value="150"
                                                                           class="selectgroup-input" checked="" disabled>
                                                                    <span class="selectgroup-button"><%=elem.getTipo()%></span>
                                                                </label>
                                                                <%
                                                                        }
                                                                    }
                                                                %>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-right border-right mth2">Numero de Creditos</td>
                                                        <td class="mt17" colspan="4"><%=microcurriculo.getMateria().getCreditos()%></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-right border-right mth2">Prerrequisitos</td>
                                                        <td class="mt17" colspan="4">
                                                            <%
                                                                for (dto.PrerrequisitoMateria prerrequisito : microcurriculo.getMateria().getPrerrequisitoMateriaList()) {
                                                            %>
                                                            <%=prerrequisito.getMateria1().getNombre()%><br><%}%>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>  
                                    <%
                                        List<SeccionMicrocurriculo> secciones = microcurriculo.getSeccionMicrocurriculoList();
                                        for (SeccionMicrocurriculo seccion : secciones) {
                                    %>
                                    <!-- Seccion -->  
                                    <% int tipo = seccion.getSeccionId().getTipoSeccionId().getId();
                                        if (tipo == 1) {%>
                                    <div class="card" id="<%=seccion.getSeccionId().getNombre()%>">
                                        <input  type="hidden"  name="seccionId-<%=seccion.getSeccionId().getId()%>" value="<%=seccion.getId()%>">  
                                        <div class="card-header">
                                            <div class="d-flex justify-content-center">
                                                <h4 class="card-title mtittle"><%=seccion.getSeccionId().getNombre()%></h4>
                                            </div>
                                        </div>
                                        <div class="card-body pb-0">
                                            <div class="w-100 d-flex justify-content-center">
                                                <div class="w-75 form-group">
                                                    <textarea class="form-control" name="seccion-<%= seccion.getSeccionId().getId()%>" value="info" rows="3"><%=seccion.getContenidoList().get(0).getTexto()%></textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Fin Seccion -->
                                    <%  } else {
                                        TablaSeccion tableSeccion = seccion.getTablaSeccion();
                                        Tabla table = tableSeccion.getTablaId();
                                        int canColum = table.getEncabezadoList().size();
                                    %>
                                    <!-- Tabla -->
                                    <div class="card" id="<%=seccion.getSeccionId().getNombre()%>">
                                        <div class="card-header">
                                            <div class="d-flex justify-content-center">
                                                <h4 class="card-title mtittle "><%=seccion.getSeccionId().getNombre()%></h4>
                                            </div>
                                        </div>
                                        <div class="card-body pb-0">
                                            <table class="table table-hover" id="tabla<%=seccion.getSeccionId().getId()%>">
                                                <thead>
                                                    <!-- Encabezados -->
                                                    <tr>
                                                        <% for (int i = 0; i < canColum; i++) {%>
                                                        <th class="text-center" scope="col"><%=table.getEncabezadoList().get(i).getEncabezado() %></th>
                                                        <%}%>
                                                    </tr>
                                                    
                                                    <!-- Hidden rows tables -->
                                                    <%if(seccion.getSeccionId().getId() == 1){%>
                                                        <input  type="hidden" name="nfilas-<%=seccion.getId()%>" id="nfilas-<%=seccion.getSeccionId().getId()%>" value="<%=microcurriculo.getUnidadList().size() %>">
                                                    <%}else{
                                                        int rowNum = 0;
                                                        for(Unidad unidad: microcurriculo.getUnidadList()){rowNum += unidad.getContenidoUnidadList().size();}%>
                                                        <input  type="hidden" name="nfilas-<%=seccion.getId()%>" id="nfilas-<%=seccion.getSeccionId().getId()%>" value="<%=rowNum %>">
                                                    <%}%>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        int row = 0;
                                                        Integer idSeccion = seccion.getSeccionId().getId();
                                                        String idSecRow = "", idSecContRow = ""; 
                                                        for(Unidad unidad: microcurriculo.getUnidadList()){
                                                            if(seccion.getSeccionId().getId() == 1){
                                                                idSecRow = idSeccion+"-"+row;
                                                                idSecContRow = "contenido-"+idSecRow;
                                                    %>
                                                    <tr>
                                                        <input type="hidden" value="<%=unidad.getId()%>" name="old_unit" />
                                                        <td><div class='form-group'><input class="form-control form-micro" value="<%=unidad.getNum() %>" readonly name="<%=idSecContRow%>-0" /></div></td>
                                                        <td><div class='form-group'><textarea class="form-control form-micro" name="<%=idSecContRow%>-1"><%=unidad.getNombre() %></textarea></div></td>
                                                        <td><div class='form-group'><input class="form-control form-micro" type="text" name="<%=idSecContRow%>-2" id="<%=idSecRow%>-2" onkeyup="sumFields('<%=idSecRow %>-2')" onkeypress="return validate(event,'<%=idSecRow %>-2')" value="<%=unidad.getHorasPresencial() %>" /></div></td>
                                                        <td><div class='form-group'><input class="form-control form-micro" type="text" name="<%=idSecContRow%>-3" id="<%=idSecRow%>-3" onkeyup="sumFields('<%=idSecRow %>-2')" onkeypress="return validate(event,'<%=idSecRow %>-3')" value="<%=unidad.getHorasIndependiente()%>" /></div></td>
                                                        <td><div class='form-group'><input class="form-control form-micro" value="<%=unidad.getHorasIndependiente()+unidad.getHorasPresencial() %>" readonly/></div></td>
                                                    </tr>
                                                    <%row++;
                                                            }else{
                                                                for(ContenidoUnidad contenido: unidad.getContenidoUnidadList()){
                                                                    idSecRow = idSeccion+"-"+row;
                                                                    idSecContRow = "contenido-"+idSecRow;
                                                    %>
                                                    <tr>
                                                        <input type="hidden" value="<%=contenido.getId() %>-<%=row%>" name="old_content" />
                                                        <td>
                                                            <select id="<%=idSecContRow %>-0" name="<%=idSecContRow %>-0">
                                                                <%for(Unidad u: microcurriculo.getUnidadList()){%>
                                                                <option <%if(u.equals(unidad)){%>selected<%}%> value="<%=u.getNum() %>"><%=u.getNum() %></option>
                                                                <%}%>
                                                            </select>
                                                        </td>
                                                        <td><div class='form-group'><textarea class="form-control form-micro" name="<%=idSecContRow %>-1"><%=contenido.getContenido() %></textarea></td>
                                                        <td><div class='form-group'><textarea class="form-control form-micro" name="<%=idSecContRow %>-2"><%=contenido.getTrabajoPresencial() %></textarea></td>
                                                        <td><div class='form-group'><textarea class="form-control form-micro" name="<%=idSecContRow %>-3"><%=contenido.getTrabajoIndependiente() %></textarea></td>
                                                    </tr>
                                                    <%row++;
                                                                }
                                                            }
                                                        }
                                                    %>
                                                </tbody>
                                            </table>
                                            <div class="p-3">
                                                <button class="btn btn-danger" type="button" onclick="agregarFila(<%=idSeccion%>)">
                                                    <span class="btn-label">
                                                        <i class="fas fa-plus"></i>
                                                    </span>
                                                    Agregar Fila
                                                </button>
                                                <button class="btn btn-danger" type="button" onclick="eliminarFila(<%=idSeccion%>)">
                                                    <span class="btn-label">
                                                        <i class="fas fa-times"></i>
                                                    </span>
                                                    Eliminar Fila
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Fin Tabla -->
                                    <%
                                            }
                                        }
                                    %>
                                    <div class="w-100 d-flex justify-content-center">
                                        <input class="w-75 btn btn-danger" type="submit" value="Registrar">
                                    </div>
                                </form>
                            </div>
                            <!-- Fin Contenido de Microcurriculo-->

                            <!-- Navegacion de Microcurriculo -->
                            <div class="col-md-2 position-relative">
                                <div class=" position-fixed ">
                                    <h2 class="d-flex justify-content-center font-weight-bold">Navegacion</h2>
                                    <%
                                        for (dto.SeccionMicrocurriculo seccion : secciones) {
                                    %>
                                    <a href="#<%=seccion.getSeccionId().getNombre()%>">
                                        <div class="card btn btn-light mb-2 p-2 d-flex justify-content-center">
                                            <h4 class="card-title "><%=seccion.getSeccionId().getNombre()%></h4>
                                        </div>
                                    </a>
                                    <%
                                        }
                                    %>      
                                </div>
                            </div>
                            <!-- Fin Navegacion de Microcurriculo -->
                        </div>
                    </div>
                </div>

                <!-- Footer -->
                <jsp:include page="/CSM_Software/includes/footer.jsp" />
                <!-- End Footer -->  

            </div>
        </div>
        <!--   Core JS Files   -->
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/core/jquery.3.2.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/core/popper.min.js"></script>
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/core/bootstrap.min.js"></script>
        <!-- jQuery UI -->
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js"></script>

        <!-- jQuery Scrollbar -->
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
        <!-- Atlantis JS -->
        <script src="<%=request.getContextPath()%>/CSM_Software/assets/js/atlantis.min.js"></script>
    </body>

</html>
