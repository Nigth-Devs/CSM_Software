<%@page import="dto.Usuario"%>
<%
    Usuario user = (Usuario) request.getSession().getAttribute("usuario");
    String rol = (user.getRolId().getId() == 1) ? "director" : "docente";
%>
<div class=" h-100  sidebar sidebar-style-2">
    <div class=" h-100  sidebar-wrapper scrollbar scrollbar-inner">
        <div class="sidebar-content">
            <div class="user">
                <div class="avatar-sm float-left mr-2">
                    <img src="<%=request.getContextPath()%>/CSM_Software/assets/img/profile.jfif" alt="..."
                         class="avatar-img rounded-circle">
                </div>
                <div class="info">
                    <a data-toggle="collapse" href="#collapseExample" aria-expanded="true">
                        <span>
                            <%=user.getDocente().getNombre()%>
                            <span class="user-level"><%=user.getRolId().getRol()%></span>
                            <span class="caret"></span>
                        </span>
                    </a>
                    <div class="clearfix"></div>
                    <div class="collapse in" id="collapseExample">
                        <ul class="nav">
                            <li>
                                <a href="<%= request.getContextPath() + "/CSM_Software/CSM/" + rol + "/dashboard/perfil/profile.jsp"%>">
                                    <span class="link-collapse">Perfil</span>
                                </a>
                            </li>
                            <li>
                                <a href="<%= request.getContextPath() + "/CSM_Software/CSM/" + rol + "/dashboard/perfil/edit-profile.jsp"%>">
                                    <span class="link-collapse">Configuracion</span>
                                </a>
                            </li>
                            <li>
                                <a href="<%=request.getContextPath()%>/CSM_Software/CSM/sign-in/singin.jsp">
                                    <span class="link-collapse">Salir</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <ul class="nav nav-primary">
                <li class="nav-item" id="dashboard">
                    <a href="<%= request.getContextPath() + "/CSM_Software/CSM/" + rol + "/dashboard/dashboard.jsp"%>"
                       class="collapsed" aria-expanded="false">
                        <i class="fas fa-home"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                <li class="nav-section">
                    <span class="sidebar-mini-icon">
                        <i class="fa fa-ellipsis-h"></i>
                    </span>
                    <h4 class="text-section">Components</h4>
                </li>
                <li class="nav-item" id="pensum">
                    <a href="<%=request.getContextPath()%>/ControladorPensum?accion=listarPensum">
                        <i class="fas fa-layer-group"></i>
                        <p>Pensum</p>
                    </a>
                </li>
                <li class="nav-item active submenu" id="microcurriculo">
                    <a data-toggle="collapse" href="#sidebarLayouts">
                        <i class="fas fa-th-list"></i>
                        <p>Microcurriculo</p>
                        <span class="caret"></span>
                    </a>
                    <div class="collapse show" id="sidebarLayouts">
                        <ul class="nav nav-collapse">
                            <li id="consultar_microcurriculo">
                                <a href="<%=request.getContextPath()%>/ControladorMicrocurriculo?accion=listarTodos">
                                    <span class="sub-item">Consultar Microcurriculo</span>
                                </a>
                            </li>
                            <% if (rol.equals("director")) {%>
                            <li id="solicitudes_microcurriculo">
                                <a href="<%=request.getContextPath()%>/CSM_Software/CSM/director/dashboard/microcurriculo/solicitudes-microcurriculo.jsp">
                                    <span class="sub-item">Solicitudes Microcurriculo</span>
                                </a>
                            </li>
                            <%} else{%>
                            <li>
                                <a href="<%=request.getContextPath()%>/CSM_Software/CSM/docente/dashboard/microcurriculo/solicitud-microcurriculo.jsp">
                                    <span class="sub-item">Crear Solicitud Microcurriculo (No deberia estar aca)</span>
                                </a>
                            </li>
                            <li>
                                <a href="<%=request.getContextPath()%>/CSM_Software/CSM/docente/dashboard/microcurriculo/solicitudes-microcurriculo.jsp">
                                    <span class="sub-item">Mis Solicitudes Microcurriculo</span>
                                </a>
                            </li>
                            <%}%>
                        </ul>
                    </div>
                </li>
                <li class="nav-item" id="seguimiento">
                    <a href="<%= request.getContextPath() + "/CSM_Software/CSM/" + rol + "/dashboard/seguimiento.jsp"%>">
                        <i class="fas fa-chart-bar"></i>
                        <p>Seguimiento</p>
                    </a>
                </li>
                <% if (rol.equals("director")) {%>
                <li class="nav-item" id="grupos">
                    <a href="<%=request.getContextPath()%>/ControladorGrupos?accion=listar">
                        <i class="fas fa-table"></i>
                        <p>Grupos</p>
                    </a>
                </li>
                <li class="nav-item" id="docentes">
                    <a href="<%=request.getContextPath()%>/ControladorDocente?action=listarDocente">
                        <i class="fas fa-user-friends"></i>
                        <p>Docentes</p>
                    </a>
                </li>
                <%}%>
            </ul>
            <div class=" d-flex justify-content-center align-items-end w-100 logoUFPS">
                <img src="<%=request.getContextPath()%>/CSM_Software/assets/img/Logo-nuevo-vertical.png" alt="..."
                     class="avatar-img w-75">
            </div>
        </div>
    </div>
</div>
