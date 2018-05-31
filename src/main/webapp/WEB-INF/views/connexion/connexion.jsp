<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Paie - App</title>
        <link rel="stylesheet" href='<c:url value="/bootstrap-3.3.7-dist/css/bootstrap.min.css"></c:url>'>
    </head>
    <body class="container">        

        <h1>Connexion</h1>

        <form method="post">
            <input name="username">
            <input type="password" name="password">
            <input type="submit" value="Se connecter">
            <sec:csrfInput/>
        </form>

        <c:if test="${param.error !=null}">
            Erreur d'authentification
        </c:if>
    </body>
</html>