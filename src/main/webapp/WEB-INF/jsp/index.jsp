<!doctype html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
  <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<c:choose>
    <c:when test="${pageContext.request.userPrincipal.name != null}">
        Current user: ${pageContext.request.userPrincipal.name} <br/>
        <a href="<c:url value="/j_spring_security_logout"/>"> Logout</a><br/>
    </c:when>
    <c:otherwise>
        <a href="<c:url value="/login"/>">Login</a><br/>
    </c:otherwise>
</c:choose>
<a href="<c:url value="/list"/>">List users</a><br/>
<a href="<c:url value="/create"/>">Add user</a><br/>
<a href="<c:url value="/subscription/order?endpointUrl=https://www.appdirect.com/rest/api/events/dummyOrder"/>">Dummy order subscription</a><br />
<a href="<c:url value="/subscription/change?endpointUrl=https://www.appdirect.com/rest/api/events/dummyChange"/>">Dummy change subscription</a><br />
<a href="<c:url value="/subscription/status?endpointUrl=https://www.appdirect.com/rest/api/events/dummyNotice"/>">Dummy notice subscription</a><br />
<a href="<c:url value="/user/assign?endpointUrl=https://www.appdirect.com/rest/api/events/dummyAssign"/>">Dummy assign user</a><br />
<a href="<c:url value="/user/unassign?endpointUrl=https://www.appdirect.com/rest/api/events/dummyUnassign"/>">Dummy unassign user</a><br />
<a href="<c:url value="/subscription/cancel?endpointUrl=https://www.appdirect.com/rest/api/events/dummyCancel"/>">Dummy cancel subscription</a><br />
</body>
</html>