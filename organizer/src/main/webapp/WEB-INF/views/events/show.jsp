<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring MVC - Show event</title>
</head>
<body>

<h2>Un event by Id: ${id} </h2>
<spring:url value="/events/" var="allEventsUrl"/>
<a href="${allEventsUrl}">Retour</a>
<c:choose>
    <c:when test="${empty event}">
        aucun evenement ne correspond a cet Identifiant.
    </c:when>
    <c:otherwise>
        <table>
		<tr>
			<td>${event.description}</td>
			<td>${event.beginDateTime}</td>
			<td>${event.endDateTime}</td>
		</tr>
		</table>
    </c:otherwise>
</c:choose>

</body>
</html>