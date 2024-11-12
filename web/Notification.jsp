<%-- 
    Document   : student_Notification
    Created on : Jun 19, 2024, 2:50:38 PM
    Author     : Admin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông báo</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">            
        <!--link đến css-->
        <link href="CSS_folder/style.css" rel="stylesheet" type="text/css"/>
        <!--link đến icon-->
        <script src="https://kit.fontawesome.com/aa4d95000f.js" crossorigin="anonymous"></script>
        <script>
            function submitFormByJS(option, notificationId) {
                if (option == 'view') {
                    // Tìm form có ID tương ứng và submit form
                    document.getElementById('view_detail_form_' + notificationId).submit();
                } else if (option == 'delete') {
                    // Tìm form có ID tương ứng và submit form
                    document.getElementById('delete_noti_form_' + notificationId).submit();
                }
            }
        </script>
        <style>
            .status-yes {
                color: green;
                font-weight: bold;
            }
            .status-no {
                color: red;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <%@ include file="header_Student.jsp" %>
        <div class="content">
            <p class="title-page">Thông báo</p>
            <div class="list">
                <table>
                    <tr>
                        <!--<th>ID</th>-->
                        <th>Thời điểm</th>
                        <th>Nội dung</th>
                        <th>Trạng thái</th>
                        <th colspan="2">Tùy chọn</th>
                    </tr>
                    <c:forEach var="noti" items="${requestScope.listNoti}">
                        <tr>
                            <!--<td>$ {noti.getNotification_id()}</td>-->
                            <td>${noti.getNotification_date()}</td>
                            <c:if test="${noti.getNotification_content().length()<=50}">
                                <td>${noti.getNotification_content()}</td>
                            </c:if>
                            <c:if test="${noti.getNotification_content().length()>50}">
                                <td>${noti.getNotification_content().substring(0, 51)}...</td>
                            </c:if>
                            <td class="${noti.getStatus() == 'Đã đọc' ? 'status-yes' : 'status-no'}">${noti.getStatus()}</td>
                            <!--form xem chi tiết thông báo-->
                            <td>
                                <!--viết id là "view_detail_form_$ {noti.getNotification_id()}" để phân biệt các form với nhau-->
                                <form id="view_detail_form_${noti.getNotification_id()}" action="readnotification" method="post">
                                    <button type="button" class="btn btn-default" onclick="submitFormByJS('view',${noti.getNotification_id()})">
                                        <i class="fa-solid fa-eye"></i> 
                                    </button>
                                    <input type="hidden" name="type" value="view">
                                    <input type="hidden" name="notification_id" value="${noti.getNotification_id()}">
                                </form>
                            </td>
                            <!--form xóa thông báo-->
                            <td>
                                <!--viết id là "delete_noti_form_$ {noti.getNotification_id()}" để phân biệt các form với nhau-->
                                <form id="delete_noti_form_${noti.getNotification_id()}" action="readnotification" method="post">
                                    <button type="button" class="btn btn-default" onclick="submitFormByJS('delete',${noti.getNotification_id()})">
                                        <i class="fa-solid fa-trash"></i>
                                    </button>
                                    <input type="hidden" name="type" value="delete">
                                    <input type="hidden" name="notification_id" value="${noti.getNotification_id()}">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="pagination">
                <c:if test="${requestScope.totalPages > 1}">
                    <c:if test="${requestScope.currentPage > 1}">
                        <a href="?page=${requestScope.currentPage - 1}">Trang trước</a>
                    </c:if>
                    <c:forEach var="i" begin="1" end="${requestScope.totalPages}">
                        <a href="?page=${i}" class="${i == requestScope.currentPage ? 'active' : ''}">${i}</a>
                    </c:forEach>
                    <c:if test="${requestScope.currentPage < requestScope.totalPages}">
                        <a href="?page=${requestScope.currentPage + 1}">Trang sau</a>
                    </c:if>
                </c:if>
            </div>
        </div>
    </body>
</html>
