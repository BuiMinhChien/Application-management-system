/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.Avatar_dao;
import Dao.Student_affairs_officer_dao;
import Dao.Student_dao;
import Model.Student;
import Model.Student_affairs_officer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Admin
 */
@WebServlet(name = "editAvatar", urlPatterns = {"/editavatar"})
//@MultipartConfig(
//          fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
//          maxFileSize = 1024 * 1024 * 10,      // 10 MB
//          maxRequestSize = 1024 * 1024 * 100   // 100 MB
//)
@MultipartConfig()
public class editAvatar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //hàm xóa các file ảnh đã có trong thư mục của userid
    public int deleteFilesNameHasUserId(String directoryPath, String userId) {
        int result = 0;
        // Tạo đối tượng File để đại diện cho thư mục
        File directory = new File(directoryPath);
        // Kiểm tra thư mục có tồn tại không và là thư mục hợp lệ
        if (directory.exists() && directory.isDirectory()) {
            // Lấy tất cả các file trong thư mục
            File[] files = directory.listFiles();
            // Kiểm tra có file nào trong thư mục không
            if (files != null) {
                // Duyệt qua các file
                for (File file : files) {
                    // Kiểm tra nếu file là file và tên file bắt đầu bằng userId
                    if (file.isFile() && file.getName().startsWith(userId)) {
                        // Thử xóa file
                        boolean deleted = file.delete();
                        if (deleted) {
                            result++;
                        }
                    }
                }
            }
        }
        return result;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* Receive file uploaded to the Servlet from the HTML5 form */
            HttpSession session = request.getSession(false);
            if (session!=null&&(boolean) session.getAttribute("checkLogin")) {
                String uploadDirectory = request.getServletContext().getRealPath("")+File.separator+"Avatar_user_upload"; //lấy ra đường dẫn của project sau đó vào thư mục Avatar_user_upload
                //D:\SAVE_CODE_PROJECT_JAVA\Final_project\build\web\Avatar_user_upload (đường dẫn của uploadDirectory)
                // Tạo thư mục nếu nó không tồn tại
                Path uploadPath = Paths.get(uploadDirectory);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Part filePart = request.getPart("file"); //lấy ra file theo tên field(trong form html) là file
                if(filePart!=null){
                    String role = request.getParameter("role");
                    String userID = request.getParameter("user_id");
                    String fileName = userID + "_avatar_" + filePart.getSubmittedFileName(); //tạo tên file VD: HE186719_avatar_IMG7073.jpg
                    String filePath = uploadDirectory + File.separator + fileName; //File.separator là tạo ra dấu \ (tạo đường dẫn cho file này)
                    String uploadDirectoryForSQL = "Avatar_user_upload" + File.separator + fileName; //tạo đường dẫn khi lưu vào database(chỉ cần tên folder và tên file)
                    deleteFilesNameHasUserId(uploadDirectory, userID); //xóa file cũ của user đó vì một user chỉ được có 1 file
                    filePart.write(filePath); //lưu file vào thư mục theo đường dẫn là filePath
                    Avatar_dao avatar_dao = new Avatar_dao();
                    avatar_dao.deleteAvatarByUserId(role, userID); //xóa avatar cũ trong database
                    avatar_dao.createAvatarForUser(role, userID, uploadDirectoryForSQL); //lưu đường dẫn vào database
                }
                if(request.getParameter("type")!=null&&request.getParameter("type").equals("edit_profile")){
                    //cập nhật ảnh xong thì phải cập nhật lại session user để session có ảnh mới
                    String role=(String) session.getAttribute("role_user");
                    if(role.equals("student")){
                        Student student=(Student) session.getAttribute("user");
                        Student_dao stu_dao=new Student_dao();
                        session.setAttribute("user", stu_dao.getStudentByID(student.getStudent_id()));
                    }
                    else if(role.equals("staff")){
                        Student_affairs_officer staff=(Student_affairs_officer) session.getAttribute("user");
                        Student_affairs_officer_dao staff_dao=new Student_affairs_officer_dao();
                        session.setAttribute("user", staff_dao.getStaffById(staff.getStaff_id()));
                    }
                    request.getRequestDispatcher("Profile.jsp").forward(request, response);
                }
                else request.getRequestDispatcher("getallstudent").forward(request, response);
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
