<%-- 
    Document   : EditProfile
    Created on : Jul 2, 2024, 12:28:48 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chỉnh sửa thông tin cá nhân</title>
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
            <p class="title-page">Chỉnh sửa thông tin cá nhân</p>
            <p class="title-page" style="font-style: italic; font-size:20px; color:black;">Đây là những thông tin quan trọng, hãy nhập thông tin đầy đủ, chính xác</p>
            <div class="edit-profile">
                <form action="editprofile" method="post" class="form-simple">
                    <input type="hidden" name="role" value="${sessionScope.role_user}">
                    <c:if test="${sessionScope.role_user=='staff'}">
                        <input type="hidden" name="user_id" value="${sessionScope.user.getStaff_id()}">
                    </c:if>
                    <c:if test="${sessionScope.role_user=='student'}">
                        <input type="hidden" name="user_id" value="${sessionScope.user.getStudent_id()}">
                    </c:if>
                    <div class="form-group">
                        <label>Họ và tên:</label>
                        <input type="input" name="full_name" value="${sessionScope.user.getFull_name()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Ngày sinh:</label>
                        <input type="input" name="dob" value="${sessionScope.user.getDob()}" class="form-control" required>
                    </div>
                    <div class="" style="margin-bottom: 1px;">
                        <label style="display: block;">Giới tính:</label>
                        <div class="radio" style="display: inline;">
                            <label>
                                <input type="radio" name="gender" value="Nam" <c:if test="${sessionScope.user.getGender()=='Nam'}">checked</c:if>> <span>Nam</span>
                            </label>
                        </div>
                        <div class="radio" style="display: inline;">
                            <label>
                                <input type="radio" name="gender" value="Nữ" <c:if test="${sessionScope.user.getGender()=='Nữ'}">checked</c:if>> <span>Nữ</span>
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Email:</label>
                        <input type="input" name="email" value="${sessionScope.user.getEmail()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Số điện thoại:</label>
                        <input type="input" name="phone_number" value="${sessionScope.user.getPhone_number()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Địa chỉ:</label>
                        <input type="input" name="address" value="${sessionScope.user.getAddress()}" class="form-control" required>
                    </div>
                    <input type="submit" name="submit_new_user_infor" value="Lưu" class="btn btn-default">
                </form>
                <form action="editavatar" method="post" enctype="multipart/form-data" class="form-simple">
                    <input type="hidden" name="type" value="edit_profile">
                    <input type="hidden" name="role" value="${sessionScope.role_user}">
                    <c:if test="${sessionScope.role_user=='staff'}">
                        <input type="hidden" name="user_id" value="${sessionScope.user.getStaff_id()}">
                    </c:if>
                    <c:if test="${sessionScope.role_user=='student'}">
                        <input type="hidden" name="user_id" value="${sessionScope.user.getStudent_id()}">
                    </c:if>
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
