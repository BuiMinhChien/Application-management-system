<%-- 
    Document   : Error
    Created on : Jul 4, 2024, 3:53:48 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1>Oops! Something went wrong.</h1>
        <h2>${requestScope.message}</h2>
    </body>
</html>
