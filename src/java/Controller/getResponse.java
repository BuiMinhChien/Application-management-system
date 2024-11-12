/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.Application_dao;
import Dao.Attachment_dao;
import Dao.Notification_dao;
import Dao.Response_dao;
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
@WebServlet(name = "getResponse", urlPatterns = {"/getresponse"})
public class getResponse extends HttpServlet {
//Lưu các file của người dùng và trả về tên các file đó

    private ArrayList<String> processFile(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> listFileNames = null;
        try {
            String UPLOAD_DIR = "File_staff_upload";
            // Đường dẫn tuyệt đối tới thư mục lưu trữ file trên server
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadFilePath = applicationPath + UPLOAD_DIR;
            // Tạo thư mục nếu nó không tồn tại
            Path uploadPath = Paths.get(uploadFilePath);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            //TẢI NHIỀU FILE
            Collection<Part> fileParts = null; // Lấy tất cả các phần tệp từ request
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
            if (session != null && (boolean) session.getAttribute("checkLogin") == true && ((String) session.getAttribute("role_user")).equals("staff")) {
                if (request.getParameter("submit") != null && request.getParameter("submit").equals("Gửi")) {
                    String staff_id = request.getParameter("staff_id");
                    String appli_id = request.getParameter("appli_id");
                    String student_id = request.getParameter("student_id");
                    String response_content = request.getParameter("response_content");
                    Response_dao response_dao = new Response_dao();
                    Attachment_dao attach_dao = new Attachment_dao();
                    String responseId = response_dao.createResponse(appli_id, staff_id, response_content);
                    if (responseId != null) {
                        //xử lý file 
                        ArrayList<String> fileUser = processFile(request, response);
                        for (String nameFile : fileUser) {
                            attach_dao.createAttachment(nameFile, null, responseId);
                        }
                        Application_dao appli_dao = new Application_dao();
                        //thay đổi trạng thái đơn thành đã xử lý
                        appli_dao.changeStatusApplication(appli_id, "Đã xử lý");
                        Application application = appli_dao.getApplicationByID(appli_id);
                        //tạo thông báo cho đơn đã gửi thành công
                        Notification_dao noti_dao = new Notification_dao();
                        noti_dao.createNotification("Đơn \"" + application.getTitle() + "\" của bạn đã được xử lý", student_id, application.getApplication_id(), responseId);
                        //đếm lại số thông báo chưa đọc của student
                        session.setAttribute("numberUnreadNoti", noti_dao.getNumberUnreadNotiByStudentId(student_id));
                        //đếm lại số đơn chưa xử lý
                        session.setAttribute("numberPendingAppli", appli_dao.getNumberOfPendingApplication());
                    }
                    request.getRequestDispatcher("getallpendingapplication").forward(request, response);
                }
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
