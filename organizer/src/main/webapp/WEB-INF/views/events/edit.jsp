<%@page   language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"				%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" 		%>
<%@taglib prefix="form"   uri="http://www.springframework.org/tags/form" 	%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring MVC - Edit/Create event</title>
</head>
<body>

<spring:url value="/events" var="submitUrl"/>
<form:form modelAttribute="event" method="POST" action="${submitUrl}">
	<form:hidden path="id"/>
	<p>
	<form:label path="description">Descriptionll</form:label>
	<form:input path="description" />
	<form:errors path="description"></form:errors>
	</p>
	<p>
	<form:label path="description">debut</form:label>
	<form:input path="beginDateTime"/>
	</p>
	<p>
	<form:label path="description">fin</form:label>
	<form:input path="endDateTime"/>
	</p>
	
	<br>
	<p>
	<button type="submit">Enoyer</button>
	
	<form:errors/>
	
	</p>
</form:form>



</body>
</html>