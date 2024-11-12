/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.Branch_dao;
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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
@WebServlet(name = "getAllStudent", urlPatterns = {"/getallstudent"})
public class getAllStudent extends HttpServlet {

    //hàm lọc đơn
    private ArrayList<Student> fillterBranchStudent(ArrayList<Student> students, String name_id, String branch_name) {
        ArrayList<Student> result1 = null;
        ArrayList<Student> result2 = null;
        if (name_id != null) {
            result1 = new ArrayList();
            for (Student temp : students) {
                if (temp.getStudent_id().contains(name_id)||temp.getFull_name().contains(name_id)) {
                    result1.add(temp);
                }
            }
        } else {
            result1 = students;
        }
        if (branch_name != null && !branch_name.equals("default")) {
            result2 = new ArrayList();
            for (Student temp : result1) {
                if (temp.getBranch_name().equals(branch_name)) {
                    result2.add(temp);
                }
            }
        } else {
            result2 = result1;
        }
        return result2;
    }

    // Hàm phân trang
    private ArrayList<Student> getStudentsByPage(ArrayList<Student> students, int pageNumber, int pageSize) {
        int fromIndex = (pageNumber - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, students.size());
        if (fromIndex >= students.size() || fromIndex < 0) {
            return new ArrayList<>(); // Trả về danh sách rỗng nếu pageNumber không hợp lệ
        }
        List<Student> subList = students.subList(fromIndex, toIndex);
        ArrayList<Student> result = new ArrayList<>(subList);
        return result;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            if (session != null && (boolean) session.getAttribute("checkLogin") == true && ((String) session.getAttribute("role_user")).equals("staff")) {
                Student_dao student_dao = new Student_dao();
                String pageStr = request.getParameter("page"); //lấy ra số của trang mà người dùng đang chọn
                int pageNumber = (pageStr != null) ? Integer.parseInt(pageStr) : 1; //nếu người dùng chọn thì lấy, còn k chọn thì mặc định là 1
                int pageSize = 10; // Số lượng sinh viên trên mỗi trang
                ArrayList<Student> listStudent = null;
                
                Branch_dao branch_dao = new Branch_dao();
                String name_id = request.getParameter("search_student");
                String branch_name = request.getParameter("branch_name");
                if(name_id!=null&&name_id.equals("")) name_id=null;
                if(branch_name!=null&&branch_name.equals("")) branch_name=null;
                //lấy danh sách sinh viên sau khi lọc tên-id và ngành học
                listStudent = fillterBranchStudent(student_dao.getAllStudent(), name_id, branch_name);
                request.setAttribute("searchString", name_id);
                request.setAttribute("searchBranch", branch_name);
//                    request.setAttribute("listStudent", getStudentsByPage(listStudent, pageNumber, pageSize)); //danh sách student sau khi đã phân trang
//                    request.setAttribute("currentPage", pageNumber); //trả về trang đang chọn
//                    request.setAttribute("totalPages", (int) Math.ceil((double) listStudent.size() / pageSize)); //trả về tổng số trang
//                    request.getRequestDispatcher("Show_all_Student.jsp").forward(request, response);
                request.setAttribute("list_branch", branch_dao.getAllBranch());

                request.setAttribute("listStudent", getStudentsByPage(listStudent, pageNumber, pageSize)); //danh sách student sau khi đã phân trang
                request.setAttribute("currentPage", pageNumber); //trả về trang đang chọn
                request.setAttribute("totalPages", (int) Math.ceil((double) listStudent.size() / pageSize)); //trả về tổng số trang
                request.getRequestDispatcher("Show_all_Student.jsp").forward(request, response);
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
