<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <title>Edit Account</title>
    </head>
    <body>
        <h1>Edit Account</h1>
        <form:form method="POST" commandName="user" action="${pageContext.request.contextPath}/user/edit/${user.id}" >
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
                        <td><input type="submit" value="Save" /></td>
                        <td></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form:form>
        <br />
        <a href="${pageContext.request.contextPath}/user">View Accounts</a>
        <br/>
        <form name="logout" method="POST" action="${pageContext.request.contextPath}/logout">
            <input type="submit" value="Logout" />
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
    </body>
</html>