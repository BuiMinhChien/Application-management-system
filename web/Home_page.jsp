<%-- 
    Document   : Home_page_Staff
    Created on : Jun 19, 2024, 1:25:44 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Trang chủ</title>
        <link href="CSS_folder/style_home.css" rel="stylesheet" type="text/css"/>
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
            <!--<p class="title-page">TRƯỜNG ĐẠI HỌC FPT</p>-->
            <img class="img-background" src="Image_component/home_page_background.jpg" alt="anh-dai-hoc-fpt"/>
            <div class="infor">
                <div class="infor-part">
                    <div class="title-part">Thông tin liên hệ</div>
                    <div>
                        <label>Điện thoại: </label>
                        <a href="telto:02473001866" target="_blank">(024)7300.1866</a>
                    </div>
                    <div>
                        <label>Email: </label>
                        <a href="mailto:tuyensinh@hanoi.fpt.edu.vn" target="_blank">tuyensinh@hanoi.fpt.edu.vn</a>
                    </div>
                    <div>
                        <label>Website: </label>
                        <a href="https://hanoi.fpt.edu.vn" target="_blank">https://hanoi.fpt.edu.vn</a>
                    </div>
                </div>
                <div class="infor-part">
                    <div class="title-part">Quy định, quy chế</div>
                    <div>
                        <a href="https://fap.fpt.edu.vn/temp/Regulations/8.2.%20QD%201562%20-%20Quy%20t%E1%BA%AFc%20%E1%BB%A9ng%20x%E1%BB%AD%20tr%C6%B0%E1%BB%9Dng%20%C4%90H%20FPT%20(2017).PDF" target="_blank">Quy tắc ứng xử</a>
                    </div>
                    <div>
                        <a href="https://fap.fpt.edu.vn/temp/Regulations/QD%20138%20DHFPT%20Ban%20hanh%20Quy%20dinh%20khen%20thuong%20thanh%20tich%20nghien%20cuu%20KH%20cua%20CBGVSV%20To%20chuc%20Giao%20duc%20FPT.pdf" target="_blank">Quy định khen thưởng</a>
                    </div>
                    <div>
                        <a href="https://fap.fpt.edu.vn/temp/Regulations/Student_HandBook.pdf" target="_blank">Sổ tay sinh viên</a>
                    </div>
                    <div>
                        <a href="https://fap.fpt.edu.vn/temp/Regulations/M%C3%B4%20t%E1%BA%A3%20qu%C3%A1%20tr%C3%ACnh%20%C4%91%C3%A0o%20t%E1%BA%A1o.pdf" target="_blank">Mô tả quá trình đào tạo</a>
                    </div>
                </div>
                <div class="infor-part">
                    <div class="title-part">Liên kết</div>
                    <div>
                        <a href="https://hanoi.fpt.edu.vn/tuyen-sinh" target="_blank">Tuyển sinh</a>
                    </div>
                    <div>
                        <a href="https://hanoi.fpt.edu.vn/nganh-hoc" target="_blank">Ngành học</a>
                    </div>
                    <div>
                        <a href="https://hanoi.fpt.edu.vn/tin-tuc-su-kien" target="_blank">Tin tức - Sự kiện</a>
                    </div>
                    <div>
                        <a href="https://hanoi.fpt.edu.vn/doi-song-sinh-vien" target="_blank">Đời sống sinh viên</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
