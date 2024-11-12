<%-- 
    Document   : ViewDetailStudent
    Created on : Jul 3, 2024, 12:43:10 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${requestScope.student.getFull_name()}</title>
        <link href="CSS_folder/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    </head>
    <body>
        <%@ include file="header_Staff.jsp" %>
        <div class="content">
            <p class="title-page">Thông tin sinh viên</p>
            <div class="all-infor">
                <div class="main-infor-user">
                    <img src="${requestScope.student.getAvatar_path()}">
                    <div class="name-id-user">
                        <label id="name-user">${requestScope.student.getFull_name()}</label>
                        <label id="id-user">ID: ${requestScope.student.getStudent_id()}</label>
                    </div>
                </div>
                <div class="sub-infor-user">
                    <div class="group-infor">
                        <div><label>Ngày sinh:</label>${requestScope.student.getDob()}</div>
                        <div><label>Giới tính:</label>${requestScope.student.getGender()}</div>
                        <div><label>Địa chỉ:</label>${requestScope.student.getAddress()}</div>
                        <div><label>Email:</label>${requestScope.student.getEmail()}</div>
                        <div><label>Số điện thoại:</label>${requestScope.student.getPhone_number()}</div>
                    </div>
                    <div class="group-infor">
                        <div><label>Ngành học:</label>${requestScope.student.getBranch_name()}</div>
                        <div><label>Chuyên ngành:</label>${requestScope.student.getMajor_name()}</div>
                        <div><label>Tên tài khoản:</label>${requestScope.student.getUsername()}</div>
                        <div><label>Ngày tạo tài khoản:</label>${requestScope.student.getRegistration_date()}</div>
                        <div><label>Trạng thái tài khoản:</label>${requestScope.student.getStatus()}</div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
