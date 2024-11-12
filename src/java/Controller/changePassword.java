/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.Student_affairs_officer_dao;
import Dao.Student_dao;
import Model.Student;
import Model.Student_affairs_officer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "changePassword", urlPatterns = {"/changepassword"})
public class changePassword extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session=request.getSession(false);
            if(session!=null&&(boolean)session.getAttribute("checkLogin")){
                if(request.getParameter("change_pass")!=null&&request.getParameter("change_pass").equals("Lưu")){
                    String old_pass=request.getParameter("old_password");
                    String new_pass=request.getParameter("new_password");
                    String retype_new_pass=request.getParameter("retype_new_password");
                    String message=null;
                    if(((String)session.getAttribute("role_user")).equals("staff")){
                        Student_affairs_officer staff=(Student_affairs_officer)session.getAttribute("user");
                        if(staff.getPassword().equals(old_pass)==false){
                            message="Mật khẩu cũ sai, hãy nhập lại";
                        }
                        else{
                            if(!new_pass.equals(retype_new_pass)){
                                message="Xác nhận khẩu mới không trùng khớp";
                            }
                            else{
                                Student_affairs_officer_dao staff_dao=new Student_affairs_officer_dao();
                                if(staff_dao.changePassword(staff.getStaff_id(), new_pass)!=0){
                                    message="Đổi mật khẩu thành công";
                                    session.setAttribute("user", staff_dao.getStaffById(staff.getStaff_id()));
                                    request.setAttribute("message", message);
                                    request.getRequestDispatcher("Change_password.jsp").forward(request, response);
                                    return;
                                }
                                else message="Đổi mật khẩu không thành công";
                            }
                        }
                    }
                    else if(((String)session.getAttribute("role_user")).equals("student")){
                        Student student=(Student)session.getAttribute("user");
                        if(student.getPassword().equals(old_pass)==false){
                            message="Mật khẩu cũ sai, hãy nhập lại";
                        }
                        else{
                            if(!new_pass.equals(retype_new_pass)){
                                message="Xác nhận khẩu mới không trùng khớp";
                            }
                            else{
                                Student_dao student_dao=new Student_dao();
                                if(student_dao.changePassword(student.getStudent_id(), new_pass)!=0){
                                    message="Đổi mật khẩu thành công";
                                    session.setAttribute("user", student_dao.getStudentByID(student.getStudent_id()));
                                    request.setAttribute("message", message);
                                    request.getRequestDispatcher("Change_password.jsp").forward(request, response);
                                    return;
                                }
                                else message="Đổi mật khẩu không thành công";
                            }
                        }
                    }
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("Change_password.jsp").forward(request, response);
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
