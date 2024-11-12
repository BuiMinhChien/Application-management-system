<%-- 
    Document   : Response_form
    Created on : Jul 1, 2024, 4:35:01 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tạo phản hồi</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">            
    </head>
    <body>
        <%@ include file="header_Staff.jsp" %>
        <div class="content">
            <div class="create-response">
                <p class="title-page">Chi tiết đơn của sinh viên</p>
                <label>Mã đơn:</label>
                ${requestScope.application.getApplication_id()}
                <br>
                <label>Tiêu đề:</label>
                ${requestScope.application.getTitle()}
                <br>
                <label>Nội dung:</label> <br>
                ${requestScope.application.getContent()}
                <br>
                <label>Người gửi:</label>
                ${requestScope.application.getStudent_name()}
                <br>
                <label>File đính kèm (nếu có):</label>
                <c:forEach items="${requestScope.application.getListAttachment()}" var="attachment">
                    <a href="${attachment.getFile_path()}" download>${attachment.getName_file()}</a>
                </c:forEach>
                <br>
                <label>Ngày gửi:</label>
                ${requestScope.application.getSubmission_date()}
            </div>
            <p class="title-page">Phần giải quyết của Trường Đại học FPT</p>
            <form action="getresponse" method="post" enctype="multipart/form-data" class="form-simple">
                <input type="hidden" name="appli_id" value="${requestScope.application.getApplication_id()}">
                <input type="hidden" name="student_id" value="${requestScope.application.getStudent_id()}">
                <input type="hidden" name="staff_id" value="${sessionScope.user.getStaff_id()}">
                <div class="form-group">
                    <label>Kết quả giải quyết</label> <br>
                    <textarea name="response_content" rows="4" cols="50" class="form-control" required></textarea>
                </div>
                <div class="form-group">
                    <label>File đính kèm (nếu có)</label> <br>
                    <input type="file" name="file" multiple class="form-control">
                </div>
                <input type="submit" name="submit" value="Gửi" class="btn btn-default">
            </form>
        </div>
    </body>
</html>

