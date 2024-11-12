<%-- 
    Document   : ViewAllApplication
    Created on : Jun 30, 2024, 9:41:13 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đơn đã gửi</title>
        <!--link đến bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">    
        <!--link đến css-->
        <link href="CSS_folder/style.css" rel="stylesheet" type="text/css"/>
        <!--link đến icon-->
        <script src="https://kit.fontawesome.com/aa4d95000f.js" crossorigin="anonymous"></script>
        <script>
            function submitFormByJS(form_id) {
                // Tìm form có ID tương ứng và submit form
                document.getElementById(form_id).submit();
            }
        </script>
        <!--hiệu ứng đổi màu nền-->
        <style>
            .highlight-background {
                animation: highlightAnimation 3s ease forwards;
            }
            @keyframes highlightAnimation {
                0% {
                    background-color: #FFFFFF; /* Màu trắng */
                }
                50% {
                    background-color: #CDF5FD; /* Màu xanh nhạt */
                }
                100% {
                    background-color: #FFFFFF; /* màu xanh đậm */
                }
            }
            .status-yes {
                color: green;
                font-weight: bold;
            }
            .status-no {
                color: red;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <%@ include file="header_Student.jsp" %>
        <div class="content">
            <p class="title-page">Đơn đã gửi</p>
            <div class="menu">
                <form action="getallapplication" method="post" class="form-inline search-form" id="select_type_appli">
                    <div class="form-group">
                        <select name="appli_type" class="form-control" onchange="submitFormByJS('select_type_appli')">
                            <option value="default">Phân loại theo loại đơn</option>
                            <c:forEach items="${requestScope.list_cate}" var="cate">
                                <option value="${cate.getCategory_id()}" 
                                        <c:if test="${requestScope.cate_selected==cate.getCategory_id()}">selected</c:if>
                                        >${cate.getCategory_name()}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
            <div class="list">
                <table>
                    <tr>
                        <th>Tiêu đề</th>
                        <th>Nội dung</th>
                        <th>Thời điểm tạo</th>
                        <th>Phản hồi</th>
                        <th>Thời điểm phản hồi</th>
                        <th>Trạng thái</th>
                        <th colspan="2">Tùy chọn</th>
                    </tr>
                    <c:forEach items="${requestScope.listAppli}" var="appli">
                        <!--thêm id để link từ thông báo nhảy đến-->
                        <tr id="${appli.getApplication_id()}">
                            <!--tên đơn-->
                            <td>${appli.getTitle()}</td>
                            <!--nội dung-->
                            <c:if test="${appli.getContent().length()<=100}">
                                <td>${appli.getContent()}</td>
                            </c:if>
                            <c:if test="${appli.getContent().length()>100}">
                                <td>${appli.getContent().substring(0, 101)}...</td>
                            </c:if>
                            <!--Thời điểm tạo-->
                            <td>${appli.getSubmission_date()}</td>
                            <!--lấy nội dung phản hồi-->
                            <td>
                                <c:if test="${appli.getResponse().getResponse_content().length()<=100}">
                                    ${appli.getResponse().getResponse_content()}
                                </c:if>
                                <c:if test="${appli.getResponse().getResponse_content().length()>100}">
                                    ${appli.getResponse().getResponse_content().substring(0, 101)}...
                                </c:if>
                            </td>
                            <!--lấy thời điểm phản hồi-->
                            <td>
                                ${appli.getResponse().getResponse_date()}
                            </td>
                            <!--trạng thái-->
                            <td class="${appli.getStatus() == 'Đã xử lý' ? 'status-yes' : 'status-no'}">${appli.getStatus()}</td>
                            <!--Tùy chọn-->
                            <c:if test="${appli.getStatus()=='Đang chờ tiếp nhận'}">
                                <td>
                                    <form action="getdetailapplication" method="post" id="view_appli_${appli.getApplication_id()}">
                                        <button type="button" class="btn btn-default" onclick="submitFormByJS('view_appli_${appli.getApplication_id()}')">
                                            <i class="fa-solid fa-eye"></i> 
                                        </button>
                                        <input type="hidden" name="appli_id" value="${appli.getApplication_id()}">
                                        <input type="hidden" name="option" value="view_detail">
                                    </form>
                                </td>
                                <td>
                                    <form action="getallapplication" method="post" id="delete_appli_${appli.getApplication_id()}">
                                        <button type="button" class="btn btn-default" onclick="submitFormByJS('delete_appli_${appli.getApplication_id()}')">
                                            <i class="fa-solid fa-trash"></i>
                                        </button>
                                        <input type="hidden" name="appli_type" value="${requestScope.cate_selected}">
                                        <input type="hidden" name="appli_id" value="${appli.getApplication_id()}">
                                        <input type="hidden" name="option" value="delete_appli">
                                    </form>
                                </td>
                            </c:if>
                            <c:if test="${appli.getStatus()!='Đang chờ tiếp nhận'}">
                                <td colspan="2">
                                    <form action="getdetailapplication" method="post" id="view_appli_${appli.getApplication_id()}">
                                        <button type="button" class="btn btn-default" onclick="submitFormByJS('view_appli_${appli.getApplication_id()}')">
                                            <i class="fa-solid fa-eye"></i> 
                                        </button>
                                        <input type="hidden" name="appli_id" value="${appli.getApplication_id()}">
                                        <input type="hidden" name="option" value="view_detail">
                                    </form>
                                </td>
                            </c:if>    
                        </tr>
                    </c:forEach>
                </table>
                <!--Mã JS để nhảy đến phần đơn theo link trong thông báo-->
                <c:if test="${not empty requestScope.gotoId}">
                    <script>
                        document.addEventListener("DOMContentLoaded", function () {
                            var highlightedElement = document.getElementById("${requestScope.gotoId}");
                            if (highlightedElement) {
                                highlightedElement.scrollIntoView({behavior: "smooth", block: "center"});
                                // Thêm lớp highlight-background vào phần tử
                                highlightedElement.classList.add("highlight-background");
                                // Loại bỏ lớp highlight-background sau 3 giây
                                setTimeout(function () {
                                    highlightedElement.classList.remove("highlight-background");
                                }, 3000);
                            }
                        });
                    </script>
                </c:if>
            </div>
            <div class="pagination">
                <c:if test="${requestScope.totalPages > 1}">
                    <c:if test="${requestScope.currentPage > 1}">
                        <a href="?page=${requestScope.currentPage - 1}&&appli_type=${requestScope.cate_selected}">Trang trước</a>
                    </c:if>
                    <c:forEach var="i" begin="1" end="${requestScope.totalPages}">
                        <a href="?page=${i}&&appli_type=${requestScope.cate_selected}" class="${i == requestScope.currentPage ? 'active' : ''}">${i}</a>
                    </c:forEach>
                    <c:if test="${requestScope.currentPage < requestScope.totalPages}">
                        <a href="?page=${requestScope.currentPage + 1}&&appli_type=${requestScope.cate_selected}">Trang sau</a>
                    </c:if>
                </c:if>
            </div>
        </div>
    </body>
</html>
