/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.Student_dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

@MultipartConfig
@WebServlet(name = "importStudentFromCSV", urlPatterns = {"/importstudentfromcsv"})
public class importStudentFromCSV extends HttpServlet {

    private static final long serialVersionUID = 1L;
    // Hàm để phân tích dòng CSV và giữ nguyên dấu phẩy
    private ArrayList<String> parseCsvLine(String line) {
        ArrayList<String> fields = new ArrayList<>();
        StringBuilder field = new StringBuilder();
        boolean inQuotes = false;
        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(field.toString());
                field.setLength(0); // Reset StringBuilder
            } else {
                field.append(c);
            }
        }
        // Add last field
        fields.add(field.toString());
        return fields;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            if (session != null && (boolean) session.getAttribute("checkLogin") && ((String) session.getAttribute("role_user")).equals("staff")) {
                // Đọc file từ request
                Part filePart = request.getPart("file"); //lấy file từ trong request với tên thuộc tính là file (name của thẻ input)
                InputStream fileContent = filePart.getInputStream(); //lấy ra inputstream của file được upload, đây là 1 luồng dữ liệu byte thô của file, dùng nó để đọc và xử lý
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent, StandardCharsets.UTF_8));
                Student_dao stu_dao = new Student_dao();
                String line;
                boolean isFirstLine = true; // Biến để bỏ qua dòng đầu tiên (tiêu đề)
                ArrayList<String> fields = new ArrayList();
                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue; // Bỏ qua dòng đầu tiên
                    }
                    fields = parseCsvLine(line);
                    stu_dao.createNewStudentAccount(fields.get(0), fields.get(1), fields.get(2), fields.get(3), fields.get(4), fields.get(5), fields.get(6));
                }
                // Redirect hoặc hiển thị thông báo thành công sau khi ghi dữ liệu xong
                response.sendRedirect("getallstudent");
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
