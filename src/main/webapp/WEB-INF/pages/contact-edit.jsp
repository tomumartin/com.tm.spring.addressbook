<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <title>Edit Contact</title>
    </head>
    <body>
        <h1>Edit Contact</h1>
        <form:form method="POST" commandName="contact" action="${pageContext.request.contextPath}/contact/edit/${contact.id}" >
            <table>
                <tbody>
                    <tr>
                        <td>First name:</td>
                        <td><form:input path="firstName" /></td>
                        <td><form:errors path="firstName" cssStyle="color: red;"/></td>
                    </tr>
                    <tr>
                        <td>Last name:</td>
                        <td><form:input path="lastName" /></td>
                        <td><form:errors path="lastName" cssStyle="color: red;"/></td>
                    </tr>
                    <tr>
                        <td>Company:</td>
                        <td><form:input path="company" /></td>
                        <td><form:errors path="company" cssStyle="color: red;"/></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><form:input path="email" /></td>
                        <td><form:errors path="email" cssStyle="color: red;"/></td>
                    </tr>
                    <tr>
                        <td>Home Phone:</td>
                        <td><form:input path="homePhone" /></td>
                        <td><form:errors path="homePhone" cssStyle="color: red;"/></td>
                    </tr>
                    <tr>
                        <td>Work Phone:</td>
                        <td><form:input path="homePhone" /></td>
                        <td><form:errors path="homePhone" cssStyle="color: red;"/></td>
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
        <br/>
        <a href="${pageContext.request.contextPath}/contact">View Contacts</a>
        <br/>
        <form name="logout" method="POST" action="${pageContext.request.contextPath}/logout">
            <input type="submit" value="Logout" />
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
    </body>
</html>
