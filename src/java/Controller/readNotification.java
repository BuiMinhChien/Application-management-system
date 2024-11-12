/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.Notification_dao;
import Model.Notification;
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
@WebServlet(name = "readNotification", urlPatterns = {"/readnotification"})
public class readNotification extends HttpServlet {

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
            HttpSession session = request.getSession(false);
            if (session != null && (boolean) session.getAttribute("checkLogin") && ((String) session.getAttribute("role_user")).equals("student")) {
                Notification_dao noti_dao = new Notification_dao();
                String notiId = request.getParameter("notification_id");
                String type = request.getParameter("type");
                if (type != null && type.equals("view")) {
                    //đổi trạng thái thành đã đọc
                    noti_dao.changeStatusNoti(notiId);
                    //đếm lại số thông báo chưa đọc của student
                    Student student = (Student) session.getAttribute("user");
                    session.setAttribute("numberUnreadNoti", noti_dao.getNumberUnreadNotiByStudentId(student.getStudent_id()));
                    //show noti đã được chọn lên
                    Notification noti = noti_dao.getNotiId(notiId);
                    request.setAttribute("notification", noti);
                    request.getRequestDispatcher("Notification_detail.jsp").forward(request, response);
                } else if (type != null && type.equals("delete")) {
                    //xóa noti và quay trở lại trang list noti
                    noti_dao.deleteNoti(notiId);
                    //đếm lại số thông báo chưa đọc của student
                    Student student = (Student) session.getAttribute("user");
                    session.setAttribute("numberUnreadNoti", noti_dao.getNumberUnreadNotiByStudentId(student.getStudent_id()));
//                    request.getRequestDispatcher("getallnoti").forward(request, response);
                    response.sendRedirect("getallnoti");
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
