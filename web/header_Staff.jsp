
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS_folder/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            String contextPath = request.getContextPath();
            String currentPath = request.getRequestURI().substring(contextPath.length());
        %>
        <div class="navbar">
            <a href="Home_page.jsp"><img src="Image_component/logo_fpt.png" alt=""/></a>
            <a href="getallstudent" class="<%= currentPath.equals("/Show_all_Student.jsp") ? "active" : "" %>">Danh sách sinh viên</a>
            <a href="createnewstudentaccount" class="<%= currentPath.equals("/Create_new_Student_account.jsp") ? "active" : "" %>">Tạo tài khoản sinh viên</a>
            <a href="getallpendingapplication" class="number-noti-appli <%= currentPath.equals("/ViewAllPendingApplication.jsp") ? "active" : "" %>">
                Đơn chưa xử lý 
                <span class="number-noti-appli-badge ${sessionScope.numberPendingAppli > 0 ? '' : 'empty'}">
                    ${sessionScope.numberPendingAppli}
                </span>
            </a>
            <a href="getallprocessedapplication" class="<%= currentPath.equals("/ViewAllProcessedApplication.jsp") ? "active" : "" %>">Đơn đã xử lý</a>
            <a href="Profile.jsp" class="<%= currentPath.equals("/Profile.jsp") ? "active" : "" %>">Xem thông tin cá nhân</a>
            <a href="EditProfile.jsp" class="<%= currentPath.equals("/EditProfile.jsp") ? "active" : "" %>">Sửa thông tin cá nhân</a>
            <a href="Change_password.jsp" class="<%= currentPath.equals("/Change_password.jsp") ? "active" : "" %>">Đổi mật khẩu</a>
            <a href="logout">Đăng xuất</a>
        </div>
    </body>
</html>
