<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <title>Addressbook: Accounts</title>
    </head>
    <body>
        <h1>Addressbook: Accounts</h1>
        <i>${message}</i>
        <table style="text-align: center;" border="1px" cellpadding="0" cellspacing="0" >
            <thead>
                <tr>
                    <th width="150px">Username</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/user/edit/${user.id}">
                            <c:out value="${user.username}"/>
                        </a>
                    </td>
                    <td>
                        <form name="logout" method="POST" action="${pageContext.request.contextPath}/user/delete/${user.id}">
                            <input type="submit" value="X" />
                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>
                        </form>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <br/>
        <a href="${pageContext.request.contextPath}/user/create">New account</a>
        <br/>
        <form name="logout" method="POST" action="${pageContext.request.contextPath}/logout">
            <input type="submit" value="Logout" />
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
    </body>
</html>
