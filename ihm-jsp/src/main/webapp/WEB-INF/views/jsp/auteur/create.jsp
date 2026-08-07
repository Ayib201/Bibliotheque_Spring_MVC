<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter un auteur</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container py-4" style="max-width: 640px;">

    <h1 class="h3 mb-4">Ajouter un auteur</h1>

    <form action="<c:url value='/auteurs'/>" method="post">

        <div class="mb-3">
            <label for="nom" class="form-label">Nom</label>
            <input type="text" class="form-control" id="nom" name="nom"
                   value="${auteurCreateDto.nom}" required>
        </div>

        <div class="mb-3">
            <label for="prenom" class="form-label">Prénom</label>
            <input type="text" class="form-control" id="prenom" name="prenom"
                   value="${auteurCreateDto.prenom}" required>
        </div>

        <div class="mb-3">
            <label for="nationalite" class="form-label">Nationalité</label>
            <input type="text" class="form-control" id="nationalite" name="nationalite"
                   value="${auteurCreateDto.nationalite}">
        </div>

        <button type="submit" class="btn btn-primary">Enregistrer</button>
        <a class="btn btn-link" href="<c:url value='/auteurs'/>">Annuler</a>
    </form>

</div>
</body>
</html>
