<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<form name='loginForm' action="<c:url value='j_spring_openid_security_check' />" method='POST'>
    <table>
        <tr>
            <td>OpenId<input id="openid_identifier" type='text' name='openid_identifier' maxlength="100" size="100"></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Sign On"/></td>
        </tr>
    </table>

</form>

</body>
</html>