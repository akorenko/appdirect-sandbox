<!doctype html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<c:if test="${!empty userList}">
    <table>
        <tbody>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>
                    <table class="">
                        <tbody>
                        <tr>
                            <td>Open Id :</td>
                            <td>${user.openId}</td>
                        </tr>
                        <tr>
                            <td>Company :</td>
                            <td>${user.company}</td>
                        </tr>
                        <tr>
                            <td>First Name :</td>
                            <td>${user.firstName}</td>
                        </tr>
                        <tr>
                            <td>Last Name :</td>
                            <td>${user.lastName}</td>
                        </tr>
                        <tr>
                            <td>Content :</td>
                            <td width="800" height="60">${user.content}</td>
                        </tr>
                        </tbody>
                    </table>
                </td>
                <td>
                    <c:url value="/edit" var="editUserURL">
                    </c:url>
                    <form action="${editUserURL}" method="post">
                        <input type="hidden" name="userOpenId" value="${user.openId}"/>
                        <input type="submit" value="Edit"/>
                    </form>
                </td>
                <td>
                    <c:url value="/delete/${user.id}" var="deleteUserURL">
                    </c:url>
                    <form action="${deleteUserURL}" method="post">
                        <input type="submit" value="Delete"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>