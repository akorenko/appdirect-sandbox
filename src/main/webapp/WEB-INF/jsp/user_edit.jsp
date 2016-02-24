<!doctype html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<c:url value="/update" var="updateUrl"/>
<form:form method="post" action="${updateUrl}" commandName="user" class="form-vertical">
    <form:hidden path="id"/>
    <form:hidden path="content"/>
    <form:label path="openId">Open Id</form:label>
    <form:input path="openId"/><br/>
    <form:label path="company">Company</form:label>
    <form:input path="company"/><br/>
    <form:label path="firstName">First Name</form:label>
    <form:input path="firstName"/><br/>
    <form:label path="lastName">Last Name</form:label>
    <form:input path="lastName"/><br/>
    <input type="submit" value="Save User" class="btn"/>
</form:form>
</body>
</html>