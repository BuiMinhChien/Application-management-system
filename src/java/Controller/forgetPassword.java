/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.Student_affairs_officer_dao;
import Dao.Student_dao;
import Model.Student;
import Model.Student_affairs_officer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author Admin
 */
@WebServlet(name = "forgetPassword", urlPatterns = {"/forgetpassword"})
public class forgetPassword extends HttpServlet {

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
            String user = request.getParameter("username");
            String email = request.getParameter("email"); //email người nhận
            //check xem có đúng là user này tồn tại trong DB không
            Student_dao stu_dao = new Student_dao();
            Student_affairs_officer_dao staff_dao = new Student_affairs_officer_dao();
            Student student = stu_dao.checkAccount(user, email);
            Student_affairs_officer staff = staff_dao.checkAccount(user, email);
            String newpass = null;
            String name=null;
            if (student != null) {
                name=student.getFull_name();
                //đổi mật khẩu student, trả về mật khẩu mới
                newpass = stu_dao.generatePassword();
                stu_dao.changePassword(student.getStudent_id(), newpass);
            } else if (staff != null) {
                name=staff.getFull_name();
                //đổi mật khẩu staff, trả về mật khẩu mới
                newpass = stu_dao.generatePassword(); //dùng tạm hàm tạo mật khẩu của student_dao
                staff_dao.changePassword(staff.getStaff_id(), newpass);
            } else {
                //thông báo rằng thông tin sai
                request.setAttribute("message", "Thông tin sai, hãy kiểm tra lại tên đăng nhập và email");
                request.getRequestDispatcher("Forget_password.jsp").forward(request, response);
                return;
            }

            // Định nghĩa các thuộc tính SMTP
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com"); //địa chỉ máy chủ SMTP của Gmail
            properties.put("mail.smtp.port", "587"); //cổng 587 là cổng tiêu chuẩn cho giao thức SMTP
            properties.put("mail.smtp.auth", "true"); //sử dụng xác thực để đăng nhập -> cần cung cấp tk mk
            properties.put("mail.smtp.starttls.enable", "true"); //sẽ sử dụng STARTTLS (mở rộng của SMTP) để mã hóa mail

            // Xác thực tài khoản Gmail
            String username = "fapbmcprj301@gmail.com";
            String password = "sldkvkxhhxoesaod"; //mật khẩu cho ứng dụng finalproject

            // Tạo session phiên làm việc với SMTP và cung cấp thông tin xác thực
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            try {
                String content = "<html><body>"
                        + "<p style='font-size: 20px;'>Xin chào <span style='font-weight: bold;'>"+name+"</span>,</p>"
                        + "<p>Chúng tôi đã nhận được yêu cầu cấp lại mật khẩu FAP_BMC của bạn.</p>"
                        + "<p>Đây là mật khẩu mới của bạn: <span style='font-weight: bold;'>"+newpass+"</span></p>"
                        + "<p>Vui lòng không chia sẻ mật khẩu này cho người khác.</p>"
                        + "<p>Chúc bạn một ngày tốt lành.</p>"
                        + "</body></html>";
                // Tạo đối tượng MimeMessage
                Message message = new MimeMessage(session);
                message.addHeader("Content-type", "text/HTML; charset=UTF-8");
                message.setFrom(new InternetAddress(username)); //thiết lập địa chỉ email người gửi
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false)); //thiết lập địa chỉ email người nhận
                message.setSubject(MimeUtility.encodeText("Cấp lại mật khẩu", "UTF-8", "B")); // Sử dụng MimeUtility để mã hóa tiêu đề với UTF-8
                message.setSentDate(new Date()); //cài đặt ngày gửi
                // Sử dụng MimeBodyPart để thiết lập nội dung với mã hóa UTF-8
                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(content, "text/html; charset=UTF-8");
                // Tạo multipart để chứa các phần của email
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);
                message.setContent(multipart); //chứa nội dung của email
                // Gửi email
                Transport.send(message);
                //thông báo rằng mọi thứ đã thành công
                request.setAttribute("message", "Mật khẩu mới đã gửi đến email của bạn. Vui lòng kiểm tra và đăng nhập lại");
                request.getRequestDispatcher("Forget_password.jsp").forward(request, response);
                return;
            } catch (MessagingException e) {
                throw new RuntimeException(e);
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
