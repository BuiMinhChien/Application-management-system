/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.Student_dao;
import Model.Student;
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
@WebServlet(name = "editStudentInfor", urlPatterns = {"/editstudentinfor"})
public class editStudentInfor extends HttpServlet {

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
            HttpSession session=request.getSession(false);
            if(session!=null&&(boolean)session.getAttribute("checkLogin")==true&&((String)session.getAttribute("role_user")).equals("staff")){
                if(request.getParameter("submit")!=null&&request.getParameter("submit").equals("edit_infor_student")){
                    String studentID=request.getParameter("id");
                    Student_dao student_dao=new Student_dao();
                    Student student=student_dao.getStudentByID(studentID);
                    request.setAttribute("student", student);
                    request.getRequestDispatcher("Edit_infor_Student.jsp").forward(request, response);
                }
                else if(request.getParameter("submit_new_infor_student")!=null&&request.getParameter("submit_new_infor_student").equals("Lưu")){
                    Student_dao student_dao=new Student_dao();
                    String id=request.getParameter("id");
                    String full_name=request.getParameter("full_name");
                    String dob=request.getParameter("dob");
                    String gender=request.getParameter("gender");
                    String email=request.getParameter("email");
                    String phone_number=request.getParameter("phone_number");
                    String address=request.getParameter("address");
                    String status=request.getParameter("status");
                    student_dao.editInforStudent(id, full_name, dob, gender, email, phone_number, address, status);
                    request.getRequestDispatcher("getallstudent").forward(request, response);
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
