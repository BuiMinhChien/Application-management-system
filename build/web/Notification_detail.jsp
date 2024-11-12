<%-- 
    Document   : Notification_detail
    Created on : Jun 28, 2024, 12:45:30 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chi tiết thông báo</title>
        <link href="CSS_folder/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">            
    </head>
    <body>
        <%@ include file="header_Student.jsp" %>
        <div class="content">
            <p class="title-page">Chi tiết thông báo</p>
            <div class="noti-detail">
                <label>Mã đơn:</label>
                <p>${requestScope.notification.getNotification_id()}</p>
                <label>Thời điểm gửi:</label>
                <p>${requestScope.notification.getNotification_date()}</p>
                <label>Nội dung:</label>
                <p>${requestScope.notification.getNotification_content()}</p>
                <c:if test="${requestScope.notification.getApplication_id()!=null}">
                    <label>Chuyển tiếp đến đơn: </label> <a href="getallapplication?gotoId=${requestScope.notification.getApplication_id()}">Nhấn ở đây</a>
                </c:if>
            </div>
        </div>
    </body>
</html>
