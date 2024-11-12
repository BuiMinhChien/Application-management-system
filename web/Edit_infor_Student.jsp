<%-- 
    Document   : EditInforStudent
    Created on : Jun 26, 2024, 12:45:19 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chỉnh sửa thông tin sinh viên</title>
        <link href="CSS_folder/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"> 
    </head>
    <body>
        <%@ include file="header_Staff.jsp" %>
        <div class="content">
            <p class="title-page">Chỉnh sửa thông tin sinh viên</p>
            <div class="edit-profile">
                <form action="editstudentinfor" method="post" class="form-simple">
                    <div class="form-group">
                        <label>Mã sinh viên:</label>
                        <input type="input" name="id" value="${requestScope.student.getStudent_id()}" class="form-control" readonly>
                    </div>
                    <div class="form-group">
                        <label>Tên tài khoản:</label>
                        <input type="input" name="username" value="${requestScope.student.getUsername()}" class="form-control" readonly>
                    </div>
                    <div class="form-group">
                        <label>Họ và tên:</label>
                        <input type="input" name="full_name" value="${requestScope.student.getFull_name()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Ngày sinh:</label>
                        <input type="input" name="dob" value="${requestScope.student.getDob()}" class="form-control" required>
                    </div>
                    <div class="" style="margin-bottom: 1px;">
                        <label style="display: block;">Giới tính:</label>
                        <div class="radio" style="display: inline;">
                            <label>
                                <input type="radio" name="gender" value="Nam" <c:if test="${requestScope.student.getGender()=='Nam'}">checked</c:if>> <span>Nam</span>
                                </label>
                            </div>
                            <div class="radio" style="display: inline;">
                                <label>
                                    <input type="radio" name="gender" value="Nữ" <c:if test="${requestScope.student.getGender()=='Nữ'}">checked</c:if>> <span>Nữ</span>
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Email:</label>
                            <input type="input" name="email" value="${requestScope.student.getEmail()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Số điện thoại:</label>
                        <input type="input" name="phone_number" value="${requestScope.student.getPhone_number()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Địa chỉ:</label>
                        <input type="input" name="address" value="${requestScope.student.getAddress()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Khối ngành:</label>
                        <input type="input" name="branch" value="${requestScope.student.getBranch_name()}" class="form-control" readonly>
                    </div>
                    <div class="form-group">
                        <label>Chuyên ngành:</label>
                        <input type="input" name="major" value="${requestScope.student.getMajor_name()}" class="form-control" readonly>
                    </div>
                    <div class="form-group">
                        <label>Ngày tạo tài khoản:</label>
                        <input type="input" name="regis_date" value="${requestScope.student.getRegistration_date()}" class="form-control" readonly>
                    </div>
                    <div class="" style="margin-bottom: 1px;">
                        <label style="display: block;">Trạng thái tài khoản:</label>
                        <div class="radio" style="display: inline;">
                            <label>
                                <input type="radio" name="status" value="Mở" <c:if test="${requestScope.student.getStatus()=='Mở'}">checked</c:if>> <span>Mở</span>
                                </label>
                            </div>
                            <div class="radio" style="display: inline;">
                                <label>
                                    <input type="radio" name="status" value="Đóng" <c:if test="${requestScope.student.getStatus()=='Đóng'}">checked</c:if>> <span>Đóng</span>
                                </label>
                            </div>
                        </div>
                        <input type="submit" name="submit_new_infor_student" value="Lưu" class="btn btn-default">
                    </form>
                <form action="editavatar" method="post" enctype="multipart/form-data" class="form-simple">
                    <input type="hidden" name="role" value="student">
                    <input type="hidden" name="user_id" value="${requestScope.student.getStudent_id()}">
                    <div class="form-group">
                        <label>Chỉnh sửa ảnh đại diện</label>
                        <input type="file" name="file" accept=".jpg, .jpeg, .png" class="form-control">
                    </div>
                    <input type="submit" value="Tải lên" class="btn btn-default">
                </form>
            </div>
        </div>
    </body>
</html>
