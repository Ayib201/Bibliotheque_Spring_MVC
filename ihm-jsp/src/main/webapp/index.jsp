<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
    // Page d'accueil de l'appli : on redirige directement vers la liste des auteurs,
    // qui est aujourd'hui le seul module fonctionnel (Service/Controller/vues).
    response.sendRedirect(request.getContextPath() + "/auteurs");
%>
