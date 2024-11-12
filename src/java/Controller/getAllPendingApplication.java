/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.Application_category_dao;
import Dao.Application_dao;
import Model.Application;
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
@WebServlet(name = "getAllPendingApplication", urlPatterns = {"/getallpendingapplication"})
public class getAllPendingApplication extends HttpServlet {

    //hàm lọc đơn
    private ArrayList<Application> fillterAppli(ArrayList<Application> applis, String stu_id, String appli_id) {
        ArrayList<Application> result1 = null;
        ArrayList<Application> result2 = null;
        if (stu_id != null) {
            result1 = new ArrayList();
            for (Application temp : applis) {
                if (temp.getStudent_id().contains(stu_id)) {
                    result1.add(temp);
                }
            }
        } else {
            result1 = applis;
        }
        if (appli_id != null && !appli_id.equals("default")) {
            result2 = new ArrayList();
            for (Application temp : result1) {
                if (temp.getCategory_id().equals(appli_id)) {
                    result2.add(temp);
                }
            }
        } else {
            result2 = result1;
        }
        return result2;
    }

    // Hàm phân trang
    private ArrayList<Application> getApplisByPage(ArrayList<Application> applis, int pageNumber, int pageSize) {
        int fromIndex = (pageNumber - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, applis.size());
        if (fromIndex >= applis.size() || fromIndex < 0) {
            return new ArrayList<>(); // Trả về danh sách rỗng nếu pageNumber không hợp lệ
        }
        List<Application> subList = applis.subList(fromIndex, toIndex);
        ArrayList<Application> result = new ArrayList<>(subList);
        return result;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            if (session != null && (boolean) session.getAttribute("checkLogin") == true && ((String) session.getAttribute("role_user")).equals("staff")) {
                Application_dao appli_dao = new Application_dao();
                String pageStr = request.getParameter("page"); //lấy ra số của trang mà người dùng đang chọn
                int pageNumber = (pageStr != null) ? Integer.parseInt(pageStr) : 1; //nếu người dùng chọn thì lấy, còn k chọn thì mặc định là 1
                int pageSize = 10;
                ArrayList<Application> list_appli = null;

                Application_category_dao cate_dao = new Application_category_dao();
                String stu_id = request.getParameter("stu_id");
                String appli_id = request.getParameter("appli_type");
                if(stu_id!=null&&stu_id.equals("")) stu_id=null;
                if(appli_id!=null&&appli_id.equals("")) appli_id=null;
                list_appli = fillterAppli(appli_dao.getAllPendingApplication(), stu_id, appli_id);
                request.setAttribute("searchStudentID", stu_id);
                request.setAttribute("cate_selected", appli_id);
                request.setAttribute("list_cate", cate_dao.getAllCategory());

                request.setAttribute("list_appli", getApplisByPage(list_appli, pageNumber, pageSize)); //danh sách student sau khi đã phân trang
                request.setAttribute("currentPage", pageNumber); //trả về trang đang chọn
                request.setAttribute("totalPages", (int) Math.ceil((double) list_appli.size() / pageSize)); //trả về tổng số trang
                request.getRequestDispatcher("ViewAllPendingApplication.jsp").forward(request, response);
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
