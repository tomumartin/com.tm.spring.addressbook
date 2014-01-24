<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Addressbook: Login</title>
</head>
<body>
<h1>Addressbook: Login</h1>
<i>${message}</i>
<form:form method="POST" commandName="user" action="${pageContext.request.contextPath}/login" >
    <table>
        <tbody>
        <tr>
            <td>Username:</td>
            <td><form:input path="username" /></td>
            <td><form:errors path="username" cssStyle="color: red;"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><form:input type="password" path="password" /></td>
            <td><form:errors path="password" cssStyle="color: red;"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Log in" /></td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form:form>
</body>
</html>
