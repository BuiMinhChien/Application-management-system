
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">    
        <link href="CSS_folder/style_login.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="login-page container-fluid">
            <div class="form-login row">
                <div class="image-fpt col-sm-5">
                    <img src="./Image_component/bg-form-login.png" alt=""/>
                </div>
                <div class="main-form-login col-sm-3">
                    <img src="./Image_component/logo_fpt.png" alt=""/>
                    <p id="login_text">ĐĂNG NHẬP</p>
                    <div class="form">
                        <form action="checklogin" method="post">
                            <div class="form-group">
                                <label>Tên đăng nhập:</label>
                                <input type="text" name="username" class="form-control" value="${cookie.username.value}" required>
                            </div>
                            <div class="form-group">
                                <label>Mật khẩu:</label>
                                <input type="password" name="password" class="form-control" value="${cookie.password.value}" required>
                            </div>
                            <div class="form-group">
                                <div class="checkbox">
                                    <label><input type="checkbox" name="remember" value="yes">Lưu thông tin đăng nhập</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <input type="submit" name="login" value="Đăng nhập" class="btn btn-default btn-lg">
                            </div>
                            <c:if test="${requestScope.notification!=null}">
                                <div>
                                    <p id="message">${requestScope.notification}</p>
                                </div>
                            </c:if>
                        </form>
                    </div>
                    <div>
                        <a href="Forget_password.jsp">Quên mật khẩu?</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
