
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách sinh viên</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">            
        <!--link đến css-->
        <link href="CSS_folder/style.css" rel="stylesheet" type="text/css"/>
        <!--link đến icon-->
        <script src="https://kit.fontawesome.com/aa4d95000f.js" crossorigin="anonymous"></script>
        <style>
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
        <%@ include file="header_Staff.jsp" %>
        <div class="content">
            <p class="title-page">Danh sách sinh viên</p>
            <div class="menu">
                <form action="getallstudent" method="post" class="form-inline search-form">
                    <div class="form-group">
                        <input type="text" name="search_student" placeholder="Tìm kiếm ID hoặc họ tên" value="${requestScope.searchString}" class="form-control">
                    </div>
                    <div class="form-group">
                        <select name="branch_name" class="form-control">
                            <option value="default">Tất cả ngành học</option>
                            <c:forEach items="${requestScope.list_branch}" var="branch">
                                <option value="${branch.getName()}" 
                                        <c:if test="${requestScope.searchBranch==branch.getName()}">selected</c:if>
                                        >${branch.getName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <input type="submit" name="submit_search_student" value="Tìm kiếm" class="btn btn-default">
                </form>
                <div class="export-button">
                    <form action="exporttocsv" method="post">
                        <button type="submit" name="export-btn" value="exportCSV" class="btn btn-default">Xuất danh sách thành .csv</button>
                    </form>
                </div>
            </div>
            <div class="list">
                <table>
                    <thead>
                    <th>Ảnh đại diện</th>
                    <th>Mã SV</th>
                    <th>Họ tên</th>
                    <th>Ngày sinh</th>
                    <th>Giới tính</th>
                    <th>Email</th>
                    <th>Số điện thoại</th>
                    <th>Địa chỉ</th>
                    <th>Chuyên ngành</th>
                    <th>Trạng thái</th>
                    <th colspan="2">Tùy chọn</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.listStudent}" var="student">
                            <tr>
                                <td><img src="${student.getAvatar_path()}" width="120" height="140"></td>
                                <td>${student.getStudent_id()}</td>
                                <td>${student.getFull_name()}</td>
                                <td>${student.getDob()}</td>
                                <td>${student.getGender()}</td>
                                <td>${student.getEmail()}</td>
                                <td>${student.getPhone_number()}</td>
                                <td>${student.getAddress()}</td>
                                <td>${student.getMajor_name()}</td>
                                <td class="${student.getStatus() == 'Mở' ? 'status-yes' : 'status-no'}">${student.getStatus()}</td>
                                <td>
                                    <form action="viewdetailstudent" method="post">
                                        <input type="hidden" name="id" value="${student.getStudent_id()}">
                                        <button type="submit" name="submit" value="view_detail_student" class="btn btn-default">
                                            <i class="fa-solid fa-eye"></i>
                                        </button>
                                    </form>
                                </td>
                                <td>
                                    <form action="editstudentinfor" method="post">
                                        <input type="hidden" name="id" value="${student.getStudent_id()}">
                                        <button type="submit" name="submit" value="edit_infor_student" class="btn btn-default">
                                            <i class="fa-solid fa-pen-to-square"></i>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="pagination">
                <c:if test="${requestScope.totalPages > 1}">
                    <c:if test="${requestScope.currentPage > 1}">
                        <a href="?page=${requestScope.currentPage - 1}&&search_student=${requestScope.searchString}&&branch_name=${requestScope.searchBranch}">Trang trước</a>
                    </c:if>
                    <c:forEach var="i" begin="1" end="${requestScope.totalPages}">
                        <a href="?page=${i}&&search_student=${requestScope.searchString}&&branch_name=${requestScope.searchBranch}" class="${i == requestScope.currentPage ? 'active' : ''}">${i}</a>
                    </c:forEach>
                    <c:if test="${requestScope.currentPage < requestScope.totalPages}">
                        <a href="?page=${requestScope.currentPage + 1}&&search_student=${requestScope.searchString}&&branch_name=${requestScope.searchBranch}">Trang sau</a>
                    </c:if>
                </c:if>
            </div>            
        </div>
    </body>
</html>
