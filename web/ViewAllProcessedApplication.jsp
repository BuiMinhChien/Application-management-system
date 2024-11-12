<%-- 
    Document   : ViewAllProcessedApplication
    Created on : Jul 1, 2024, 9:58:41 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đơn đã xử lý</title>
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
    </head>
    <body>
        <%@ include file="header_Staff.jsp" %>
        <div class="content">
            <p class="title-page">Đơn đã xử lý</p>
            <div class="menu">
                <form action="getallprocessedapplication" method="post" class="form-inline search-form">
                    <div class="form-group">
                        <input type="text" name="stu_id" placeholder="Tìm kiếm theo mã SV" value="${requestScope.searchStudentID}" class="form-control">
                    </div>
                    <div class="form-group">
                        <select name="appli_type" class="form-control">
                            <option value="default">Phân loại theo loại đơn</option>
                            <c:forEach items="${requestScope.list_cate}" var="cate">
                                <option value="${cate.getCategory_id()}" 
                                        <c:if test="${requestScope.cate_selected==cate.getCategory_id()}">selected</c:if>
                                        >${cate.getCategory_name()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <input type="submit" name="submit_search" value="Tìm kiếm" class="btn btn-default">
                </form>
            </div>
            <div class="list">
                <table>
                    <tr>
                        <th>Tiêu đề</th>
                        <th>Mã sinh viên</th>
                        <th>Nội dung</th>
                        <th>Thời điểm gửi</th>
                        <th>Nội dung phản hồi</th>
                        <th>Người phản hồi</th>
                        <th>Thời điểm phản hồi</th>
                        <th>Chi tiết</th>
                    </tr>
                    <c:forEach items="${requestScope.list_appli}" var="appli">
                        <tr>
                            <!--tiêu đề-->
                            <td>${appli.getTitle()}</td>
                            <!--mã sinh viên-->
                            <td>${appli.getStudent_id()}</td>
                            <!--nội dung-->
                            <c:if test="${appli.getContent().length()<=100}">
                                <td>${appli.getContent()}</td>
                            </c:if>
                            <c:if test="${appli.getContent().length()>100}">
                                <td>${appli.getContent().substring(0, 101)}...</td>
                            </c:if>
                            <!--Thời điểm gửi-->
                            <td>${appli.getSubmission_date()}</td>
                            <!--Nội dung phản hồi-->
                            <td>
                                <c:if test="${appli.getResponse().getResponse_content().length()<=100}">
                                    ${appli.getResponse().getResponse_content()}
                                </c:if>
                                <c:if test="${appli.getResponse().getResponse_content().length()>100}">
                                    ${appli.getResponse().getResponse_content().substring(0, 101)}...
                                </c:if>
                            </td>
                            <!--Người phản hồi-->
                            <td>
                                ${appli.getResponse().getStaff_name()}
                            </td>
                            <!--Thời điểm phản hồi-->
                            <td>
                                ${appli.getResponse().getResponse_date()}
                            </td>
                            <!--Xem chi tiết-->
                            <td>
                                <form action="getdetailapplication" method="post" id="view_appli_${appli.getApplication_id()}">
                                    <button type="button" class="btn btn-default" onclick="submitFormByJS('view_appli_${appli.getApplication_id()}')">
                                        <i class="fa-solid fa-eye"></i> 
                                    </button>
                                    <input type="hidden" name="appli_id" value="${appli.getApplication_id()}">
                                    <input type="hidden" name="option" value="view_detail">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="pagination">
                <c:if test="${requestScope.totalPages > 1}">
                    <c:if test="${requestScope.currentPage > 1}">
                        <a href="?page=${requestScope.currentPage - 1}&&stu_id=${requestScope.searchStudentID}&&appli_type=${requestScope.cate_selected}">Trang trước</a>
                    </c:if>
                    <c:forEach var="i" begin="1" end="${requestScope.totalPages}">
                        <a href="?page=${i}&&stu_id=${requestScope.searchStudentID}&&appli_type=${requestScope.cate_selected}" class="${i == requestScope.currentPage ? 'active' : ''}">${i}</a>
                    </c:forEach>
                    <c:if test="${requestScope.currentPage < requestScope.totalPages}">
                        <a href="?page=${requestScope.currentPage + 1}&&stu_id=${requestScope.searchStudentID}&&appli_type=${requestScope.cate_selected}">Trang sau</a>
                    </c:if>
                </c:if>
            </div>
        </div>
    </body>
</html>
