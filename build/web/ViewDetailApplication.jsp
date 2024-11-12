<%-- 
    Document   : ViewDetailApplication
    Created on : Jul 1, 2024, 1:41:55 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chi tiết đơn</title>
        <link href="CSS_folder/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">            
        <!--thêm thư viện html2pdf.js-->
        <script src="https://raw.githack.com/eKoopmans/html2pdf/master/dist/html2pdf.bundle.js"></script>
        <script>
            function generatePDF(fileName) {
                const element = document.getElementById("getHTML");
                // Thiết lập các tùy chọn in PDF
                const options = {
                    margin: 0.3, // Khoảng cách lề 
                    filename: fileName + '.pdf', // Tên file tải về
                    image: {type: 'jpeg', quality: 1}, // Định dạng hình ảnh
                    html2canvas: {scale: 2}, // Tỉ lệ hiển thị cho canvas
                    jsPDF: {unit: 'in', format: 'a4', orientation: 'portrait'}  // Định dạng trang A4
                };
                // Xuất PDF từ element và lưu
                html2pdf().set(options).from(element).save();
            }
        </script>
    </head>
    <body>
        <c:if test="${sessionScope.role_user=='staff'}">
            <%@ include file="header_Staff.jsp" %>
        </c:if>
        <c:if test="${sessionScope.role_user=='student'}">
            <%@ include file="header_Student.jsp" %>
        </c:if>
        <div class="content">
            <div class="button-view-appli">
                <div>
                    <a href="File_staff_upload/Mau_don_Dai_hoc_FPT.zip" download class="btn btn-default">Tải tất cả mẫu đơn</a>
                </div>
                <div id="exportPDF">
                    <button onclick="generatePDF('${requestScope.application.getApplication_id()}_${requestScope.application.getStudent_id()}')" class="btn btn-default">Xuất đơn thành .pdf</button>
                </div>
            </div>
            <div class="contain-appli">
                <div class="view-appli" id="getHTML">
                    <div class="student-part">
                        <div class="header">
                            <img src="Image_component/logo_fpt.png" alt="logo_dai_hoc_fpt"/>
                            <div class="quoc-hieu-tieu-ngu">
                                <p id="quoc-hieu">CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM</p>
                                <p id="tieu-ngu">Độc lập - Tự do - Hạnh phúc</p>
                            </div>
                        </div>
                        <div class="title">
                            ${requestScope.application.getTitleStandardFormat()}
                        </div>
                        <div class="content-appli">
                            <div class="infor">
                                ${requestScope.application.getContent()}
                            </div>
                            <div class="file">
                                <p>File đính kèm (nếu có):</p>
                                <c:forEach items="${requestScope.application.getListAttachment()}" var="attachment">
                                    <a href="${attachment.getFile_path()}" download>${attachment.getName_file()}</a>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="footer">
                            <div class="date">
                                Hà Nội, ngày ${requestScope.application.getSubmission_day()}, tháng ${requestScope.application.getSubmission_month()}, năm ${requestScope.application.getSubmission_year()}
                            </div>
                            <div class="sign">
                                <p>NGƯỜI LÀM ĐƠN</p>
                                ${requestScope.application.getStudent_name()}
                            </div>
                        </div>
                    </div>

                    <c:if test="${requestScope.application.getResponse()!=null}">
                        <div class="staff-part">
                            <div class="title">
                                Phần giải quyết của Trường Đại học FPT
                            </div>
                            <div class="content-appli">
                                <div class="infor">
                                    Kết quả giải quyết: ${requestScope.application.getResponse().getResponse_content()}
                                </div>
                                <div class="file">
                                    <p>File đính kèm (nếu có):</p>
                                    <c:forEach items="${requestScope.application.getResponse().getListAttachment()}" var="attachment">
                                        <a href="${attachment.getFile_path()}" download>${attachment.getName_file()}</a>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="footer">
                                <div class="date">
                                    Hà Nội, ngày ${requestScope.application.getResponse().getResponse_day()}, tháng ${requestScope.application.getResponse().getResponse_month()}, năm ${requestScope.application.getResponse().getResponse_year()}
                                </div>
                                <div class="sign">
                                    <p>NGƯỜI GIẢI QUYẾT</p>
                                    ${requestScope.application.getResponse().getStaff_name()}
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
