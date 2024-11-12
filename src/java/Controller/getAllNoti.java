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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
@WebServlet(name = "getAllNoti", urlPatterns = {"/getallnoti"})
public class getAllNoti extends HttpServlet {

    // Hàm phân trang
    private ArrayList<Notification> getNotisByPage(ArrayList<Notification> notis, int pageNumber, int pageSize) {
        int fromIndex = (pageNumber - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, notis.size());
        if (fromIndex >= notis.size() || fromIndex < 0) {
            return new ArrayList<>(); // Trả về danh sách rỗng nếu pageNumber không hợp lệ
        }
        List<Notification> subList = notis.subList(fromIndex, toIndex);
        ArrayList<Notification> result = new ArrayList<>(subList);
        return result;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ArrayList<Notification> listNoti = new ArrayList();
            Notification_dao notidao = new Notification_dao();
            HttpSession session = request.getSession(false);
            if (session != null && (boolean) session.getAttribute("checkLogin") && ((String) session.getAttribute("role_user")).equals("student")) {
                String pageStr = request.getParameter("page"); //lấy ra số của trang mà người dùng đang chọn
                int pageNumber = (pageStr != null) ? Integer.parseInt(pageStr) : 1; //nếu người dùng chọn thì lấy, còn k chọn thì mặc định là 1
                int pageSize = 15;
                String student_id = ((Student) session.getAttribute("user")).getStudent_id();
                listNoti = notidao.getAllNotiByStudentId(student_id);
                session.setAttribute("numberUnreadNoti", notidao.getNumberUnreadNotiByStudentId(student_id)); //cập nhật lại số thông báo chưa đọc
                request.setAttribute("listNoti", getNotisByPage(listNoti, pageNumber, pageSize));
                request.setAttribute("currentPage", pageNumber); //trả về trang đang chọn
                request.setAttribute("totalPages", (int) Math.ceil((double) listNoti.size() / pageSize)); //trả về tổng số trang
                request.getRequestDispatcher("Notification.jsp").forward(request, response);
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
