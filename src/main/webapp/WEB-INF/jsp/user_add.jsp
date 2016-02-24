<!doctype html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<c:url value="/add" var="addUrl"/>
<form:form method="post" action="${addUrl}" commandName="user" class="form-vertical">
    <form:label path="openId">Open Id</form:label>
    <form:input path="openId"/>
    <br/>
    <form:label path="firstName">First Name</form:label>
    <form:input path="firstName"/>
    <br/>
    <form:label path="lastName">Last Name</form:label>
    <form:input path="lastName"/>
    <br/>
    <input type="submit" value="Add User" class="btn"/>
</form:form>
</body>
</html>