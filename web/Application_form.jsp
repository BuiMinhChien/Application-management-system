<%-- 
    Document   : Application_form
    Created on : Jun 19, 2024, 10:09:15 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gửi đơn</title>
        <link href="CSS_folder/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">            
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const selectElement = document.querySelector('select[name="application_type"]');
                selectElement.addEventListener('change', function () {
                    document.forms['form_select'].submit();
                });
            });
        </script>
    </head>
    <body>
        <%@ include file="header_Student.jsp" %>
        <div class="content">
            <p class="title-page">Gửi đơn cho Phòng quản lý đào tạo</p>
            <form action="createform" method="post" name="form_select" class="form-simple">
                <div class="form-group">
                    <label>Loại đơn</label>
                    <select name="application_type" class="form-control">
                        <option value="default">Hãy chọn loại đơn</option>
                        <c:forEach items="${requestScope.listCategory}" var="cate">
                            <option value="${cate.getCategory_id()}" 
                                    <c:if test="${requestScope.category_selected.getCategory_id()==cate.getCategory_id()}">selected</c:if>
                                    >${cate.getCategory_name()}</option>
                        </c:forEach>
                    </select>
                </div>
            </form>
            <!--viết form mẫu cho các loại đơn-->
            <c:if test="${requestScope.category_selected.getCategory_name()=='Đề nghị miễn điểm danh'}">
                <div id="appli-discription">
                    <label>Mô tả:</label> 
                    ${requestScope.category_selected.getDescription()}
                </div>
                <form action="getapplication" method="post" enctype="multipart/form-data" class="form-simple">
                    <input type="hidden" name="type_application" value="${requestScope.category_selected.getCategory_id()}">
                    <div class="form-group">
                        <label>Mã sinh viên</label>
                        <input type="text" name="id" value="${sessionScope.user.getStudent_id()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Họ và tên</label>
                        <input type="text" name="fullname" value="${sessionScope.user.getFull_name()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" name="email" value="${sessionScope.user.getEmail()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Số điện thoại</label>
                        <input type="text" name="phonenumber" value="${sessionScope.user.getPhone_number()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Lý do nộp đơn</label> <br>
                        <textarea name="reason" rows="2" cols="50" class="form-control" required></textarea>
                    </div>
                    <div class="form-group">
                        <label>Đề nghị của sinh viên</label> <br>
                        <textarea name="suggestion" rows="4" cols="50" class="form-control" required></textarea>
                    </div>
                    <div class="form-group">
                        <label>File đính kèm (nếu có)</label> <br>
                        <input type="file" name="file" multiple class="form-control">
                    </div>
                    <input type="submit" name="submit" value="Gửi" class="btn btn-default">
                </form>
            </c:if>
            <c:if test="${requestScope.category_selected.getCategory_name()=='Đăng ký thi cải thiện điểm'}">
                <div id="appli-discription">
                    <label>Mô tả:</label> 
                    ${requestScope.category_selected.getDescription()}
                </div>
                <form action="getapplication" method="post" class="form-simple">
                    <input type="hidden" name="type_application" value="${requestScope.category_selected.getCategory_id()}">
                    <div class="form-group">
                        <label>Mã sinh viên</label>
                        <input type="text" name="id" value="${sessionScope.user.getStudent_id()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Họ và tên</label>
                        <input type="text" name="fullname" value="${sessionScope.user.getFull_name()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" name="email" value="${sessionScope.user.getEmail()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Số điện thoại</label>
                        <input type="text" name="phonenumber" value="${sessionScope.user.getPhone_number()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Môn thi</label>
                        <input type="text" name="course" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Lý do</label> <br>
                        <textarea name="reason" rows="2" cols="50" class="form-control" required></textarea>
                    </div>
                    <input type="submit" name="submit" value="Gửi" class="btn btn-default">
                </form>
            </c:if>
            <c:if test="${requestScope.category_selected.getCategory_name()=='Đề nghị phúc tra'}">
                <div id="appli-discription">
                    <label>Mô tả:</label> 
                    ${requestScope.category_selected.getDescription()}
                </div>
                <form action="getapplication" method="post" class="form-simple">
                    <input type="hidden" name="type_application" value="${requestScope.category_selected.getCategory_id()}">
                    <div class="form-group">
                        <label>Mã sinh viên</label>
                        <input type="text" name="id" value="${sessionScope.user.getStudent_id()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Họ và tên</label>
                        <input type="text" name="fullname" value="${sessionScope.user.getFull_name()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" name="email" value="${sessionScope.user.getEmail()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Số điện thoại</label>
                        <input type="text" name="phonenumber" value="${sessionScope.user.getPhone_number()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Học kỳ</label>
                        <input type="text" name="semester" placeholder="VD:Spring2024" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Môn thi</label>
                        <input type="text" name="course" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Kỳ thi</label>
                        <select name="examtype" class="form-control" required>
                            <option value="FE">FE</option>   
                            <option value="PE">PE</option> 
                            <option value="2NDFE">2NDFE</option> 
                            <option value="2NDPE">2NDPE</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Điểm thi</label>
                        <input type="text" name="grade" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Nội dung phúc tra</label> <br>
                        <textarea name="content" rows="4" cols="50" class="form-control" required></textarea>
                    </div>
                    <input type="submit" name="submit" value="Gửi" class="btn btn-default">
                </form>
            </c:if>
            <c:if test="${requestScope.category_selected.getCategory_name()=='Đơn khiếu nại điểm danh'}">
                <div id="appli-discription">
                    <label>Mô tả:</label> 
                    ${requestScope.category_selected.getDescription()}
                </div>
                <form action="getapplication" method="post" class="form-simple">
                    <input type="hidden" name="type_application" value="${requestScope.category_selected.getCategory_id()}">
                    <div class="form-group">
                        <label>Mã sinh viên</label>
                        <input type="text" name="id" value="${sessionScope.user.getStudent_id()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Họ và tên</label>
                        <input type="text" name="fullname" value="${sessionScope.user.getFull_name()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" name="email" value="${sessionScope.user.getEmail()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Số điện thoại</label>
                        <input type="text" name="phonenumber" value="${sessionScope.user.getPhone_number()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Thông tin buổi học điểm danh sai (gồm ngày, môn, slot, tên giảng viên)</label> <br>
                        <textarea name="content" rows="2" cols="50" class="form-control" required></textarea>
                    </div>
                    <div class="form-group">
                        <label>Sau khi phát hiện sai sót, em đã</label> <br>
                        <textarea name="do" rows="4" cols="50" class="form-control" required></textarea>
                    </div>
                    <input type="submit" name="submit" value="Gửi" class="btn btn-default">
                </form>
            </c:if>
            <c:if test="${requestScope.category_selected.getCategory_name()=='Các loại đơn khác'}">
                <div id="appli-discription">
                    <label>Mô tả:</label> 
                    ${requestScope.category_selected.getDescription()}
                </div>
                <form action="getapplication" method="post" enctype="multipart/form-data" class="form-simple">
                    <input type="hidden" name="type_application" value="${requestScope.category_selected.getCategory_id()}">
                    <div class="form-group">
                        <label>Mã sinh viên</label>
                        <input type="text" name="id" value="${sessionScope.user.getStudent_id()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Họ và tên</label>
                        <input type="text" name="fullname" value="${sessionScope.user.getFull_name()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" name="email" value="${sessionScope.user.getEmail()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Số điện thoại</label>
                        <input type="text" name="phonenumber" value="${sessionScope.user.getPhone_number()}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Tên đơn</label>
                        <input type="text" name="name_application" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Lý do nộp đơn</label> <br>
                        <textarea name="reason" rows="2" cols="50" class="form-control" required></textarea>
                    </div>
                    <div class="form-group">
                        <label>Đề nghị của sinh viên</label> <br>
                        <textarea name="suggestion" rows="4" cols="50" class="form-control" required></textarea>
                    </div>
                    <div class="form-group">
                        <label>File đính kèm (nếu có)</label> <br>
                        <input type="file" name="file" multiple class="form-control">
                    </div>
                    <input type="submit" name="submit" value="Gửi" class="btn btn-default">
                </form>
            </c:if>
        </div>
    </body>
</html>
