<%@page import="dto.Usuario"%>
<%
    Usuario user = (Usuario) request.getSession().getAttribute("usuario");
    String rol = (user.getRolId().getId() == 1) ? "director" : "docente";
%>
<div class="logo-header" data-background-color="red">
    <a href="<%= request.getContextPath() + "/CSM_Software/CSM/" + rol + "/dashboard/dashboard.jsp"%>" style="text-decoration: none;">
        <h2 class=" ml-3 text-white d-flex align-items-center">CSM Software</h2>
    </a>
    <button class="navbar-toggler sidenav-toggler ml-auto" type="button" data-toggle="collapse"
            data-target="collapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon">
            <i class="icon-menu"></i>
        </span>
    </button>
    <button class="topbar-toggler more"><i class="icon-options-vertical"></i></button>
    <div class="nav-toggle">
        <button class="btn btn-toggle toggle-sidebar">
            <i class="icon-menu"></i>
        </button>
    </div>
</div>
