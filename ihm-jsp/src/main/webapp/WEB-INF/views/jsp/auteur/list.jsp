<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des auteurs</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container py-4">

    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1 class="h3 mb-0">Liste des auteurs</h1>
        <a class="btn btn-primary" href="<c:url value='/auteurs/create'/>">Ajouter un auteur</a>
    </div>

    <c:if test="${empty auteurs}">
        <p class="text-muted">Aucun auteur enregistré pour le moment.</p>
    </c:if>

    <c:if test="${not empty auteurs}">
        <table class="table table-striped align-middle">
            <thead>
            <tr>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Nationalité</th>
                <th class="text-end">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="auteur" items="${auteurs}">
                <tr>
                    <td><c:out value="${auteur.nom}"/></td>
                    <td><c:out value="${auteur.prenom}"/></td>
                    <td><c:out value="${auteur.nationalite}"/></td>
                    <td class="text-end">
                        <a class="btn btn-sm btn-outline-secondary"
                           href="<c:url value='/auteurs/${auteur.id}'/>">Voir</a>
                        <a class="btn btn-sm btn-outline-primary"
                           href="<c:url value='/auteurs/${auteur.id}/edit'/>">Modifier</a>
                        <form action="<c:url value='/auteurs/${auteur.id}/delete'/>" method="post"
                              class="d-inline"
                              onsubmit="return confirm('Supprimer cet auteur ?');">
                            <button type="submit" class="btn btn-sm btn-outline-danger">Supprimer</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

</div>
</body>
</html>
