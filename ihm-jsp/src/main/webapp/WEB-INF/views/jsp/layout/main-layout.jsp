<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!--
  Layout commun, en JSP pur (sans Tiles/Thymeleaf) : chaque vue "auteur/xxx.jsp"
  ne contient plus de <html>/<head>/<body> — elle se contente de poser
  "pageTitle" + "contentPage" en attributs de requête puis de forward()-er ici
  (voir auteur/list.jsp par exemple). Ce fichier est le seul à connaître le
  <head>/header/footer, exactement comme main-layout.jsp côté ihm-tiles ou
  layout/main.html côté ihm-thymeleaf.
-->
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title><c:out value="${pageTitle}" default="Bibliothèque"/></title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
<c:import url="/WEB-INF/views/jsp/common/header.jsp" />

<c:import url="${contentPage}" />

<c:import url="/WEB-INF/views/jsp/common/footer.jsp" />
</body>
</html>
