<%-- 
    Document   : Change_password
    Created on : Jun 27, 2024, 11:34:00 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đổi mật khẩu</title>
        <link href="CSS_folder/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">            
    </head>
    <body>
        <c:if test="${sessionScope.role_user=='staff'}">
            <%@ include file="header_Staff.jsp" %>
        </c:if>
        <c:if test="${sessionScope.role_user=='student'}">
            <%@ include file="header_Student.jsp" %>
        </c:if>
        <div class="content">
            <p class="title-page">Đổi mật khẩu</p>
            <form action="changepassword" method="post" class="form-simple">
                <div class="form-group">
                    <label>Mật khẩu cũ:</label>
                    <input type="password" name="old_password" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>Mật khẩu mới:</label>
                    <input type="password" name="new_password" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>Xác nhận mật khẩu mới:</label>
                    <input type="password" name="retype_new_password" class="form-control" required>
                </div>
                <input type="submit" name="change_pass" value="Lưu" class="btn btn-default">
                <c:if test="${requestScope.message!=null}">
                    <p id="message">${requestScope.message}</p>
                </c:if>
            </form>
        </div>
    </body>
</html>
