<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<div class="container py-4" style="max-width: 640px;">

    <h1 class="h3 mb-4"><c:out value="${auteur.nom}"/> <c:out value="${auteur.prenom}"/></h1>

    <dl class="row">
        <dt class="col-sm-3">Nom</dt>
        <dd class="col-sm-9"><c:out value="${auteur.nom}"/></dd>

        <dt class="col-sm-3">Prénom</dt>
        <dd class="col-sm-9"><c:out value="${auteur.prenom}"/></dd>

        <dt class="col-sm-3">Nationalité</dt>
        <dd class="col-sm-9"><c:out value="${auteur.nationalite}"/></dd>

        <dt class="col-sm-3">Livres</dt>
        <dd class="col-sm-9">
            <c:out value="${auteur.nombreLivres}"/> livre(s) associé(s)
        </dd>
    </dl>

    <a class="btn btn-outline-primary" href="<c:url value='/auteurs/${auteur.id}/edit'/>">Modifier</a>
    <a class="btn btn-link" href="<c:url value='/auteurs'/>">Retour à la liste</a>

</div>
