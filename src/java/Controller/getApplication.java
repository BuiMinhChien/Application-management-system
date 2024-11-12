/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.Application_dao;
import Dao.Attachment_dao;
import Dao.Notification_dao;
import Model.Application;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
@MultipartConfig()
@WebServlet(name = "getApplication", urlPatterns = {"/getapplication"})
public class getApplication extends HttpServlet {
    //Lưu các file của người dùng và trả về tên các file đó
    private ArrayList<String> processFile(HttpServletRequest request, HttpServletResponse response){
        ArrayList<String> listFileNames = null;
        try {
            String UPLOAD_DIR = "File_student_upload";
            // Đường dẫn tuyệt đối tới thư mục lưu trữ file trên server
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadFilePath = applicationPath + UPLOAD_DIR;
            // Tạo thư mục nếu nó không tồn tại
            Path uploadPath = Paths.get(uploadFilePath);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            //TẢI NHIỀU FILE
            Collection<Part> fileParts=null; // Lấy tất cả các phần tệp từ request
            try {
                fileParts = request.getParts();
            } catch (ServletException ex) {
                Logger.getLogger(getApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
            listFileNames = new ArrayList<>();
            String fileName = null;
            String originalFileName = null;
            String filePath = null;
            for (Part filePart : fileParts) {
                if (filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {
                    originalFileName = filePart.getSubmittedFileName();
                    fileName = originalFileName;
                    filePath = uploadFilePath + File.separator + fileName;
                    int count = 1;
                    // Kiểm tra nếu tên file đã tồn tại trong thư mục, thêm số thứ tự vào tên file mới
                    while (Files.exists(Paths.get(filePath))) {
                        // Tách phần đuôi và phần tên cơ bản của file
                        String extension = "";
                        String baseName = originalFileName;
                        int dotIndex = originalFileName.lastIndexOf(".");
                        if (dotIndex != -1) {
                            baseName = originalFileName.substring(0, dotIndex);
                            extension = originalFileName.substring(dotIndex);
                        }
                        // Tạo tên file mới với số thứ tự
                        fileName = baseName + "(" + count + ")" + extension;
                        filePath = uploadFilePath + File.separator + fileName;
                        // Tăng số thứ tự để chuẩn bị kiểm tra tên file tiếp theo
                        count++;
                    }
                    filePart.write(filePath); // Lưu từng tệp vào thư mục đã chỉ định
                    listFileNames.add(request.getContextPath() + "/" + UPLOAD_DIR + "/" + fileName); //thêm đường dẫn tải file vào danh sách
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(getApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listFileNames;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            if (session!=null&&(boolean) session.getAttribute("checkLogin")&&((String)session.getAttribute("role_user")).equals("student")) {
                Application_dao appli_dao=new Application_dao();
                Attachment_dao attach_dao=new Attachment_dao();
                String type_application=request.getParameter("type_application");
                String student_id=request.getParameter("id");
                String fullname=request.getParameter("fullname");
                String email=request.getParameter("email");
                String phonenumber=request.getParameter("phonenumber");
                String title=null;
                String appliId=null; //id này là id của đơn mới tạo, dùng để tạo thông báo
                StringBuffer content=new StringBuffer();
                content.append("Kính gửi: Phòng quản lý đào tạo"+"<br>");
                content.append("Tên em là: "+fullname+"<br>");
                content.append("Mã số sinh viên: "+student_id+"<br>");
                content.append("Số điện thoại: "+phonenumber+"<br>");
                content.append("Email: "+email+"<br>");
                switch(type_application){
                    case "appli001":{
                        title="Đề nghị phúc tra";
                        content.append("Em kính mong nhà trường và tổ bộ môn xem lại điểm bài thi của em:"+"<br>");
                        content.append("Học kỳ: "+request.getParameter("semester")+"<br>");
                        content.append("Môn thi: "+request.getParameter("course")+"<br>");
                        content.append("Kỳ thi: "+request.getParameter("examtype")+"<br>");
                        content.append("Điểm thi: "+request.getParameter("grade")+"<br>");
                        content.append("Nội dung phúc tra: "+request.getParameter("content")+"<br>");
                        content.append("Em xin chân thành cảm ơn.");
                        appliId=appli_dao.createApplication(student_id, type_application, title, content.toString());
                        break;
                    }
                    case "appli002":{
                        title=request.getParameter("name_application");
                        content.append("Lý do nộp đơn: "+request.getParameter("reason")+"<br>");
                        content.append("Đề nghị của sinh viên: "+request.getParameter("suggestion")+"<br>");
                        content.append("Em xin chân thành cảm ơn.");
                        appliId=appli_dao.createApplication(student_id, type_application, title, content.toString());
                        if(appliId!=null){
                            //xử lý file 
                            ArrayList<String> fileUser=processFile(request,response);
                            for(String nameFile:fileUser){
                                attach_dao.createAttachment(nameFile, appliId, null);
                            }
                        }
                        break;
                    }
                    case "appli003":{
                        title="Đơn khiếu nại điểm danh";
                        content.append("Kính gửi: Phòng quản lý đào tạo"+"<br>");
                        content.append("Thông tin buổi học bị điểm danh sai: "+"<br>"+request.getParameter("content")+"<br>");
                        content.append("Sau khi phát hiện sai sót trong quá trình điểm danh, em đã: "+request.getParameter("do")+"<br>");
                        content.append("Em xin đề nghị được điểm danh buổi học trên.");
                        appliId=appli_dao.createApplication(student_id, type_application, title, content.toString());
                        break;
                    }
                    case "appli004":{
                        title="Đăng ký thi cải thiện điểm";
                        content.append("Em xin đăng ký thi lần 2 để cải thiện điểm:"+"<br>");
                        content.append("Môn thi: "+request.getParameter("course")+"<br>");
                        content.append("Lý do: "+request.getParameter("reason")+"<br>");
                        content.append("Em xin cam kết tuân thủ quy định của Quy chế đào tạo, điểm thi lần 1 bị hủy, điểm thi lần 2 là điểm thi cuối cùng.");
                        appliId=appli_dao.createApplication(student_id, type_application, title, content.toString());
                        break;
                    }
                    case "appli005":{
                        title="Đề nghị miễn điểm danh";
                        content.append("Lý do nộp đơn: "+request.getParameter("reason")+"<br>");
                        content.append("Đề nghị của sinh viên: "+request.getParameter("suggestion")+"<br>");
                        content.append("Em xin chân thành cảm ơn.");
                        appliId=appli_dao.createApplication(student_id, type_application, title, content.toString());
                        if(appliId!=null){
                            //xử lý file 
                            ArrayList<String> fileUser=processFile(request,response);
                            for(String nameFile:fileUser){
                                attach_dao.createAttachment(nameFile, appliId, null);
                            }
                        }
                        break;
                    }
                }
                //tạo thông báo cho đơn đã gửi thành công
                if(appliId!=null){
                    Notification_dao noti_dao=new Notification_dao();
                    Application newAppli=appli_dao.getApplicationByID(appliId);
                    noti_dao.createNotification("Đơn \""+newAppli.getTitle()+"\" của bạn đã được gửi thành công vào lúc "+newAppli.getSubmission_date(), student_id, appliId, null);
                    //đếm lại số thông báo chưa đọc của student
                    session.setAttribute("numberUnreadNoti",noti_dao.getNumberUnreadNotiByStudentId(student_id));
                }
                request.getRequestDispatcher("createform").forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
