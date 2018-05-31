<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap-3.3.7-
dist/css/bootstrap.css">
</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">LOG</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<a class="nav-item nav-link"
					href="<c:url value = "/mvc/employes/creer"/>">Employes</a> <a
					class="nav-item nav-link"
					href="<c:url value = "/mvc/bulletins/creer"/>">Bulletins</a>
			</div>
		</div>
	</nav>
<c:forEach var="bulletin" items="${bulletin}">
	<div class="container">
		<div class="text-center">
			<h1>Bulletin de salaire</h1>
		</div>

		<div class="row">
			<div class="col-sm-offset-6" style="margin-left: 80%">
				<b>Période</b><br> ${bulletin.key.periode.dateDebut} au
				${bulletin.key.periode.dateFin}<br> <br>
			</div>

			<div class="col-sm-offset-6" style="margin-left: 80%">
				<b>Matricule : ${bulletin.key.remunerationEmploye.matricule}</b><br>
			</div>

			<div class="#" style="margin-top: -5%">
				<p>
					<b>Entreprise</b>
				</p>
				<p style="text-transform: uppercase">${bulletin.key.remunerationEmploye.entreprise.denomination}</p>
				<p style="text-transform: uppercase">siret :
					${bulletin.key.remunerationEmploye.entreprise.siret}</p>
			</div>

			<div style="margin-top: 5%">
				<p>
					<b>Salaire</b>
				</p>
				<table class="table table-hover table-bordered">
					<thead>
						<tr>
							<th>Rubriques</th>
							<th>Base</th>
							<th>Taux Salarial</th>
							<th>Montant Salarial</th>
							<th>Taux Patronal</th>
							<th>Cot. patronal</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="bul" var="toc">
							<tr>
								<td>Salaire de base</td>
								<td>${bulletin.key.remunerationEmploye.grade.nbHeuresBase}</td>
								<td>${bulletin.key.remunerationEmploye.grade.tauxBase}</td>
								<td>${bulletin.key.remunerationEmploye.grade.tauxBase.multiply(bulletin.key.remunerationEmploye.grade.nbHeuresBase)}</td>
								<td>...</td>
								<td>...</td>
							</tr>
							<tr>
								<td>Prime Exceptionnelle</td>
								<td>...</td>
								<td>...</td>
								<td>${bulletin.key.primeExceptionnelle}</td>
								<td>...</td>
								<td>...</td>
							</tr>
							<tr>
								<td></td>
								<td>...</td>
								<td>...</td>
								<td>...</td>
								<td>...</td>
								<td>...</td>
							</tr>
							<tr>
								<td>Salaire Brut</td>
								<td>...</td>
								<td>...</td>
								<td>${bulletin.key.primeExceptionnelle.add(bulletin.key.remunerationEmploye.grade.tauxBase.multiply(bulletin.key.remunerationEmploye.grade.nbHeuresBase))}</td>
								<td>...</td>
								<td>...</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div style="margin-top: 5%">
				<p>
					<b>Cotisations</b>
				</p>
				<table class="table table-hover table-bordered">
					<thead>
						<tr>
							<th>Rubriques</th>
							<th>Base</th>
							<th>Taux Salarial</th>
							<th>Montant Salarial</th>
							<th>Taux Patronal</th>
							<th>Cot. patronal</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cotisationNonImposable" items="${bulletin.key.remunerationEmploye.profilRemuneration.cotisationsNonImposables}">
							<c:set var="montantSalarialCotisation"
								value="${paieUtils.formaterBigDecimal(cotisationNonImposable.tauxSalarial * bulletin.value.salaireBrut)}" />
							<c:set var="montantPatronalCotisation"
								value="${paieUtils.formaterBigDecimal(cotisationNonImposable.tauxPatronal * bulletin.value.salaireBrut)}" />
							<tr>
								<td><c:out value="${cotisationNonImposable.code} ${cotisationNonImposable.libelle}" /></td>
								<td><c:out value="${bulletin.value.salaireBrut}" /></td>
								<td><c:out value="${cotisationNonImposable.tauxSalarial}" /></td>
								<td><c:out value="${montantSalarialCotisation}" /></td>
								<td><c:out value="${cotisationNonImposable.tauxPatronal}" /></td>
								<td><c:out value="${montantPatronalCotisation}" /></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>

		
			<div style="margin-top: 5%">
				<p>
					<b>Net Imposables : </b>
				</p>
				<table class="table table-hover table-bordered">
					<thead>
						<tr>
							<th>Rubriques</th>
							<th>Base</th>
							<th>Taux Salarial</th>
							<th>Montant Salarial</th>
							<th>Taux Patronal</th>
							<th>Cot. patronal</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>...</td>
							<td>...</td>
							<td>...</td>
							<td>...</td>
							<td>...</td>
							<td>...</td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="col-sm-offset-6" style="margin-left: 80%">
				<b>Net à payer : </b>
			</div>

		</div>
	</div>
	</c:forEach>
</body>

</html>

