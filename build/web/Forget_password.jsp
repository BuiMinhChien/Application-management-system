<%-- 
    Document   : Forget_password
    Created on : Jul 5, 2024, 9:35:09 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quên mật khẩu</title>
        <!--<link href="CSS_folder/style_forgetpassword.css" rel="stylesheet" type="text/css"/>-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">    
        <style>
            body {
                font-family: sans-serif;
                /*background-color: #FEF4EA;*/
                /*width: 100%;*/
                /*min-height: 100vh;*/
                background-image: url('./Image_component/bg-login.jpg');
                background-size: cover;
                background-position: top-left;
                background-repeat: repeat;
                display: flex;
                justify-content: center;
            }
            .btn.btn-default{
                background-color: #E04826;
                color: white;
                font-weight: bold;
                border-radius: 15px;
            }
            .content{
                margin:20px;
                width: 50%;
                background-color: white;
                border: 1px solid #E04826;
                border-radius: 20px;
                line-height: 30px;
            }
            .form-simple{
                margin: 20px;
                width: 40%;
                word-wrap: break-word;
            }
            #message{
                margin: 20px;
                font-weight: bold;
                font-style: italic;
                color:#FF4701;
                font-size: 15px;
                word-wrap: break-word;
            }
            .title-page{
                margin: 20px auto;
                font-size: 35px;
                color: #E04826;
                font-weight: bold;
                text-align: center;
                word-wrap: break-word;
            }
            .backtologin{
                font-size: 15px;
                margin: 20px;
                word-wrap: break-word;
            }
        </style>
    </head>
    <body>
        <div class="content">
            <div class="title-page">Quên mật khẩu</div>
            <div class="form-simple">
                <form action="forgetpassword" method="post">
                    <div class="form-group">
                        <label>Tên đăng nhập:</label>
                        <input type="text" name="username" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Email:</label>
                        <input type="text" name="email" class="form-control">
                    </div>
                    <input type="submit" name="submit" value="Gửi" class="btn btn-default">
                </form>
            </div>
            <c:if test="${requestScope.message!=null}">
                <div>
                    <p id="message">${requestScope.message}</p>
                </div>
            </c:if>
            <div class="backtologin">
                <a href="<%= request.getContextPath()%>">Quay lại trang đăng nhập</a>
            </div>
        </div>
    </body>
</html>
