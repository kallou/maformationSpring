<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring MVC - liste des evenements</title>
</head>
<body>

<spring:url value="/events/new" var="createEventUrl"/>
<h2>liste des evenements</h2>
	<a href="${createEventUrl}"> Create</a>
	<table>
		<c:forEach var="event" items="${listOfEvents}">
			<spring:url value="/events/${event.id}" var="showEventUrl"/>
			<spring:url value="/events/${event.id}/edit" var="editEventUrl"/>
			<tr>
				<td>${event.id}</td>
				<td>${event.description}</td>
				<td>${event.beginDateTime}</td>
				<td>${event.endDateTime}</td>
				<td><a href="${showEventUrl}"> Voir</a></td>
				<td><a href="${editEventUrl}"> Editer </a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>