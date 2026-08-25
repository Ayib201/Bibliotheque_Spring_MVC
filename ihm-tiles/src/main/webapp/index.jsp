<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
    // Page d'accueil de l'appli : même comportement que ihm-jsp, on redirige
    // directement vers la liste des auteurs.
    response.sendRedirect(request.getContextPath() + "/auteurs");
%>
