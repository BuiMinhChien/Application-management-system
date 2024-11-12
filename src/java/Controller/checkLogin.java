/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.Application_dao;
import Dao.Notification_dao;
import Dao.Student_affairs_officer_dao;
import Dao.Student_dao;
import Model.Student;
import Model.Student_affairs_officer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "checkLogin", urlPatterns = {"/checklogin"})
public class checkLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("login") != null && request.getParameter("login").equals("Đăng nhập")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String role = request.getParameter("role");
                boolean check = false;
                //THÔNG TIN ĐĂNG NHẬP BỊ THIẾU
                if (username == null || password == null || username.equals("") || password.equals("")) {
                    request.setAttribute("notification", "Hãy điền đầy đủ thông tin đăng nhập");
                    request.getRequestDispatcher("Login.jsp").include(request, response);
                } else {
                    //NGƯỜI DÙNG ĐÃ ĐIỀN ĐỦ THÔNG TIN
                    Student_dao studentdao = new Student_dao();
                    Student student = studentdao.checkLogin(username, password);
                    //CHECK XEM CÓ PHẢI STUDENT KHÔNG
                    if (student != null) {
                        if (student.getStatus().equals("Mở")) {
                            //ĐĂNG NHẬP THÀNH CÔNG
                            check = true;
                            HttpSession session = request.getSession(true);
                            session.setAttribute("checkLogin", true);
                            session.setAttribute("user", student);
                            session.setAttribute("role_user", "student");
                            //tạo cookie nhớ thông tin đăng nhập
                            if (request.getParameter("remember") != null && request.getParameter("remember").equals("yes")) {
                                Cookie u = new Cookie("username", username);
                                Cookie p = new Cookie("password", password);
//                                u.setMaxAge(60 * 60 * 24);
//                                p.setMaxAge(60 * 60 * 24);
                                u.setMaxAge(60);
                                p.setMaxAge(60);
                                response.addCookie(u);
                                response.addCookie(p);
                            }
                            //lấy ra số thông báo chưa đọc của studen đó
                            Notification_dao noti_dao = new Notification_dao();
                            session.setAttribute("numberUnreadNoti", noti_dao.getNumberUnreadNotiByStudentId(student.getStudent_id()));
//                            request.getRequestDispatcher("Home_page.jsp").include(request, response);
                            response.sendRedirect("Home_page.jsp");
                            return;
                        } else {
                            //TÀI KHOẢN BỊ KHÓA
                            request.setAttribute("notification", "Tài khoản của bạn đã bị đóng");
                            request.getRequestDispatcher("Login.jsp").include(request, response);
                            return;
                        }
                    } //CHECK XEM CÓ PHẢI STAFF KHÔNG
                    if (check == false) {
                        Student_affairs_officer_dao studentaffairsofficerdao = new Student_affairs_officer_dao();
                        Student_affairs_officer student_affairs_officer = studentaffairsofficerdao.checkLogin(username, password);
                        if (student_affairs_officer != null) {
                            //ĐĂNG NHẬP THÀNH CÔNG
                            if (student_affairs_officer.getStatus().equals("Mở")) {
                                check = true;
                                HttpSession session = request.getSession(true);
                                session.setAttribute("checkLogin", true);
                                session.setAttribute("user", student_affairs_officer);
                                session.setAttribute("role_user", "staff");
                                //tạo cookie nhớ thông tin đăng nhập
                                if (request.getParameter("remember") != null && request.getParameter("remember").equals("yes")) {
                                    Cookie u = new Cookie("username", username);
                                    Cookie p = new Cookie("password", password);
//                                    u.setMaxAge(60 * 60 * 24);
//                                    p.setMaxAge(60 * 60 * 24);
                                    u.setMaxAge(60);
                                    p.setMaxAge(60);
                                    response.addCookie(u);
                                    response.addCookie(p);
                                }
                                //lấy ra số đơn chưa xử lý
                                Application_dao appli_dao = new Application_dao();
                                session.setAttribute("numberPendingAppli", appli_dao.getNumberOfPendingApplication());
//                                request.getRequestDispatcher("Home_page.jsp").include(request, response);
                                response.sendRedirect("Home_page.jsp");
                                return;
                            } else {
                                //TÀI KHOẢN BỊ KHÓA
                                request.setAttribute("notification", "Tài khoản của bạn đã bị đóng");
                                request.getRequestDispatcher("Login.jsp").include(request, response);
                                return;
                            }
                        }
                    } //KHÔNG PHẢI STUDENT VÀ STAFF THÌ LÀ SAI TÀI KHOẢN MẬT KHẨU
                    if (check == false) {
                        request.setAttribute("notification", "Tên đăng nhập hoặc mật khẩu không đúng");
                        request.getRequestDispatcher("Login.jsp").include(request, response);
                        return;
                    }
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
