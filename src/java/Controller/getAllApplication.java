/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.Application_category_dao;
import Dao.Application_dao;
import Dao.Notification_dao;
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
@WebServlet(name = "getAllApplication", urlPatterns = {"/getallapplication"})
public class getAllApplication extends HttpServlet {
    //hàm lọc đơn
    private ArrayList<Application> fillterAppli(ArrayList<Application> applis, String appli_id) {
        ArrayList<Application> result = null;
        if (appli_id != null && !appli_id.equals("default")) {
            result = new ArrayList();
            for (Application temp : applis) {
                if (temp.getCategory_id().equals(appli_id)) {
                    result.add(temp);
                }
            }
        } else {
            result = applis;
        }
        return result;
    }
    
    private int findPageForGotoId(ArrayList<Application> listAppli, String gotoId, int pageSize) {
        // Tìm chỉ số của gotoId trong danh sách
        int index = -1; // Biến để lưu chỉ số tìm thấy
        // Sử dụng vòng lặp forEach để duyệt qua danh sách và tìm chỉ số
        for (int i = 0; i < listAppli.size(); i++) {
            Application app = listAppli.get(i);
            if (app.getApplication_id().equals(gotoId)) {
                index = i;
                break; // Thoát khỏi vòng lặp khi tìm thấy
            }
        }
        if (index == -1) {
            // Nếu gotoId không tồn tại trong listAppli, trả về -1 hoặc giá trị nào đó phù hợp
            return -1;
        }
        // Tính số trang, page đầu tiên là 1, nên phải +1
        return (index / pageSize) + 1;
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
            if (session != null && (boolean) session.getAttribute("checkLogin") == true && session.getAttribute("role_user").equals("student")) {
                Application_dao appli_dao = new Application_dao();
                //lấy toàn bộ đơn của sinh viên
                ArrayList<Application> listAppli = null;
                String gotoId = request.getParameter("gotoId"); //lấy id mà sinh viên muốn đến
                String pageStr = request.getParameter("page"); //lấy ra số của trang mà người dùng đang chọn
                int pageNumber = (pageStr != null) ? Integer.parseInt(pageStr) : 1; //nếu người dùng chọn thì lấy, còn k chọn thì mặc định là 1
                int pageSize = 10;
                
                Application_category_dao cate_dao = new Application_category_dao();
                String appli_id = request.getParameter("appli_type"); //lấy loại đơn đang được lọc
                if(appli_id!=null&&appli_id.equals("")) appli_id=null;
                request.setAttribute("list_cate", cate_dao.getAllCategory()); //trả về danh sách các loại đơn
                request.setAttribute("cate_selected", appli_id);
                listAppli=fillterAppli(appli_dao.getAllApplicationByStudentID(((Student) session.getAttribute("user")).getStudent_id()),appli_id); //lấy tất cả đơn và lọc đơn theo yêu cầu
                
                if (request.getParameter("option") != null && request.getParameter("option").equals("delete_appli")) {
                    Application appli = appli_dao.getApplicationByID(request.getParameter("appli_id"));
                    Notification_dao noti_dao = new Notification_dao();
                    noti_dao.createNotification("Bạn đã xóa đơn \"" + appli.getTitle() + "\" thành công", ((Student) session.getAttribute("user")).getStudent_id(), null, null);
                    appli_dao.deleteApplicationByAppliId(appli.getApplication_id()); //xóa đơn
                    listAppli = fillterAppli(appli_dao.getAllApplicationByStudentID(((Student) session.getAttribute("user")).getStudent_id()),appli_id); //cập nhật lại danh sách đơn và lọc
                    session.setAttribute("numberUnreadNoti", noti_dao.getNumberUnreadNotiByStudentId(((Student) session.getAttribute("user")).getStudent_id())); //cập nhật lại số thông báo chưa đọc
                }
                
                //xác định xem giá trị gotoId có null k, nếu null thì gán pageNumber lấy theo người dùng chọn, nếu k thì pageNumber phải đổi giá trị thành trang có gotoId
                if (gotoId == null) {
                    pageNumber = (pageStr != null) ? Integer.parseInt(pageStr) : 1;
                } else {
                    pageNumber = findPageForGotoId(listAppli, gotoId, pageSize);
                }
                request.setAttribute("gotoId", gotoId); //nhảy đến đơn đã nhấn link
                request.setAttribute("listAppli", getApplisByPage(listAppli, pageNumber, pageSize)); //danh sách appli sau khi đã phân trang
                request.setAttribute("currentPage", pageNumber); //trả về trang đang chọn
                request.setAttribute("totalPages", (int) Math.ceil((double) listAppli.size() / pageSize)); //trả về tổng số trang
                request.getRequestDispatcher("ViewAllApplication.jsp").forward(request, response);
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
