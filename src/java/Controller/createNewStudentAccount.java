/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.Major_dao;
import Dao.Student_dao;
import Model.Branch;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
@WebServlet(name = "createNewStudentAccount", urlPatterns = {"/createnewstudentaccount"})
public class createNewStudentAccount extends HttpServlet {

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
            if(session!=null&&(boolean)session.getAttribute("checkLogin")&&((String)session.getAttribute("role_user")).equals("staff")){
                String create_new_account=request.getParameter("create_new_account");
                if(create_new_account!=null&&create_new_account.equals("Lưu")){
                    String full_name=request.getParameter("full_name");
                    String dob=request.getParameter("dob");
                    String gender=request.getParameter("gender");
                    String email=request.getParameter("email");
                    String phone_number=request.getParameter("phone_number");
                    String address=request.getParameter("address");
                    String select_major=request.getParameter("select_major");
                    Student_dao student_dao=new Student_dao();
                    student_dao.createNewStudentAccount(full_name, dob, gender, email, phone_number, address, select_major);
                    request.getRequestDispatcher("getallstudent").forward(request, response);
                }
                else{
                    Major_dao major_dao=new Major_dao();
                    request.setAttribute("listMajor", major_dao.getAllMajor());
                    request.getRequestDispatcher("Create_new_Student_account.jsp").forward(request, response);
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
