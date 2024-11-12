
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
//            System.out.println("currentPath: " + currentPath);
        %>
        <div class="navbar">
            <a href="Home_page.jsp"><img src="Image_component/logo_fpt.png" alt=""/></a>
            <a href="createform" class="<%= currentPath.equals("/Application_form.jsp") ? "active" : "" %>">Gửi đơn</a>
            <a href="getallapplication" class="<%= currentPath.equals("/ViewAllApplication.jsp") ? "active" : "" %>">Xem đơn</a>
            <a href="getallnoti" class="number-noti-appli <%= currentPath.equals("/Notification.jsp") ? "active" : "" %>">
                Thông báo 
                <span class="number-noti-appli-badge ${sessionScope.numberUnreadNoti > 0 ? '' : 'empty'}">
                    ${sessionScope.numberUnreadNoti}
                </span>
            </a>
            <a href="Profile.jsp" class="<%= currentPath.equals("/Profile.jsp") ? "active" : "" %>">Xem thông tin cá nhân</a>
            <a href="EditProfile.jsp" class="<%= currentPath.equals("/EditProfile.jsp") ? "active" : "" %>">Sửa thông tin cá nhân</a>
            <a href="Change_password.jsp" class="<%= currentPath.equals("/Change_password.jsp") ? "active" : "" %>">Đổi mật khẩu</a>
            <a href="logout">Đăng xuất</a>
        </div>
    </body>
</html>
