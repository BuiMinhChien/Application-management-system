<%-- 
    Document   : Create_new_Student_account
    Created on : Jun 26, 2024, 12:45:05 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cấp mới tài khoản sinh viên</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">            
    </head>
    <body>
        <%@ include file="header_Staff.jsp" %>
        <div class="content">
            <p class="title-page">Cấp mới tài khoản sinh viên</p>
            <div class="import-student">
                <form action="createnewstudentaccount" method="post" class="form-simple">
                    <div class="form-group">
                        <label>Họ và tên:</label>
                        <input type="input" name="full_name" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Ngày sinh:</label>
                        <input type="input" name="dob" class="form-control" required>
                    </div>
                    <div class="" style="margin-bottom: 1px;">
                        <label style="display: block;">Giới tính:</label>
                        <div class="radio" style="display: inline;">
                            <label>
                                <input type="radio" name="gender" value="Nam"> <span>Nam</span>
                            </label>
                        </div>
                        <div class="radio" style="display: inline;">
                            <label>
                                <input type="radio" name="gender" value="Nữ"> <span>Nữ</span>
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Email:</label>
                        <input type="input" name="email" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Số điện thoại:</label>
                        <input type="input" name="phone_number" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Địa chỉ:</label>
                        <input type="input" name="address" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Chuyên ngành:</label>
                        <select name="select_major" class="form-control" required>
                            <c:forEach items="${requestScope.listMajor}" var="major">
                                <option value="${major.getId()}">${major.getName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <input type="submit" name="create_new_account" value="Lưu" class="btn btn-default">
                </form>
                <form action="importstudentfromcsv" method="post" class="form-simple" enctype="multipart/form-data">
                    <div class="form-group">
                        <label>Nhập vào từ file .csv</label>
                        <input type="file" name="file" accept=".csv" class="form-control">
                    </div>
                    <input type="submit" value="Tải lên" class="btn btn-default">
                </form>
            </div>
        </div>
    </body>
</html>
