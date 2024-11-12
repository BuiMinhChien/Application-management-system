<%-- 
    Document   : Profile
    Created on : Jun 19, 2024, 1:51:16 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sessionScope.user.getFull_name()}</title>
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
            <div class="all-infor">
                <div class="main-infor-user">
                    <img src="${sessionScope.user.getAvatar_path()}">
                    <div class="name-id-user">
                        <label id="name-user">${sessionScope.user.getFull_name()}</label>
                        <c:if test="${sessionScope.role_user=='staff'}">
                            <label id="id-user">ID: ${sessionScope.user.getStaff_id()}</label>
                        </c:if>
                        <c:if test="${sessionScope.role_user=='student'}">
                            <label id="id-user">ID: ${sessionScope.user.getStudent_id()}</label>
                        </c:if>
                    </div>
                </div>
                <div class="sub-infor-user">
                    <div class="group-infor">
                        <div><label>Ngày sinh:</label>${sessionScope.user.getDob()}</div>
                        <div><label>Giới tính:</label>${sessionScope.user.getGender()}</div>
                        <div><label>Địa chỉ:</label>${sessionScope.user.getAddress()}</div>
                        <div><label>Email:</label>${sessionScope.user.getEmail()}</div>
                        <div><label>Số điện thoại:</label>${sessionScope.user.getPhone_number()}</div>
                    </div>
                    <div class="group-infor">
                        <c:if test="${sessionScope.role_user=='student'}">
                            <div><label>Ngành học:</label>${sessionScope.user.getBranch_name()}</div>
                            <div><label>Chuyên ngành:</label>${sessionScope.user.getMajor_name()}</div>
                        </c:if>
                        <c:if test="${sessionScope.role_user=='staff'}">
                            <div><label>Phòng ban:</label>${sessionScope.user.getDepartment()}</div>
                        </c:if>
                        <div><label>Tên tài khoản:</label>${sessionScope.user.getUsername()}</div>
                        <div><label>Ngày tạo tài khoản:</label>${sessionScope.user.getRegistration_date()}</div>
                        <div><label>Trạng thái tài khoản:</label>${sessionScope.user.getStatus()}</div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
