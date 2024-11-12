/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Application;
import Model.Attachment;
import Model.Response;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Application_dao {

    public Application_dao() {
    }
    public int editInforApplication(String appliId,String title,String content){
        int result=0;
        String query="update Application set title=?, content=?, submission_date=? where application_id=?";
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, title);
            pst.setString(2, content);
            pst.setString(3, getDate());
            pst.setString(4, appliId);
            result=pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int changeStatusApplication(String appliId,String status){
        int result=0;
        String query="update Application set status=? where application_id=?";
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, status);
            pst.setString(2, appliId);
            result=pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int deleteApplicationByAppliId(String appliId){
        int result=0;
        String query1="delete from Attachment where application_id=?";
        String query2="delete from Notification where application_id=?";
        String query3="delete from Response where application_id=?";
        String query4="delete from Application where application_id=?";
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query1);
            pst.setString(1, appliId);
            result=pst.executeUpdate();
            pst=new DBContext().connection.prepareStatement(query2);
            pst.setString(1, appliId);
            result=pst.executeUpdate();
            pst=new DBContext().connection.prepareStatement(query3);
            pst.setString(1, appliId);
            result=pst.executeUpdate();
            pst=new DBContext().connection.prepareStatement(query4);
            pst.setString(1, appliId);
            result=pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public String createApplication(String student_id,String category_id,String title,String content){
        int resultQuery=0;
        String result=null;
        String query=null;
        PreparedStatement pst;
        query="insert into Application values(?,?,?,?,?,?,?)";
        try {
            pst=new DBContext().connection.prepareStatement(query);
            String id=generateUniqueID()+"";
            pst.setString(1, id);
            pst.setString(2, student_id);
            pst.setString(3, category_id);
            pst.setString(4, title);
            pst.setString(5, content);
            pst.setString(6, getDate());
            pst.setString(7, "Đang chờ tiếp nhận");
            resultQuery=pst.executeUpdate();
            if(resultQuery>0) result=id;
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public ArrayList<Application> getAllApplicationByStudentID(String stuId){
        ArrayList<Application> list=new ArrayList();
        try {
            String query="Select * from Application where student_id=? order by submission_date desc";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, stuId);
            ResultSet rs=pst.executeQuery();
            Attachment_dao attach_dao=new Attachment_dao();
            Response_dao resposne_dao=new Response_dao();
            Student_dao student_dao=new Student_dao();
            while(rs.next()){
                ArrayList<Attachment> listAttach=attach_dao.getAllAttachmentByReportID("application", rs.getString(1));
                Response response=resposne_dao.getResponseByAppliId(rs.getString(1));
                list.add(new Application(rs.getString(1),rs.getString(2),student_dao.getStudentByID(rs.getString(2)).getFull_name(),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),listAttach,response));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Notification_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public ArrayList<Application> getAllPendingApplication(){
        ArrayList<Application> list=new ArrayList();
        try {
            String query="Select * from Application where status=N'Đang chờ tiếp nhận' order by submission_date desc";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            ResultSet rs=pst.executeQuery();
            Attachment_dao attach_dao=new Attachment_dao();
            Response_dao resposne_dao=new Response_dao();
            Student_dao student_dao=new Student_dao();
            while(rs.next()){
                ArrayList<Attachment> listAttach=attach_dao.getAllAttachmentByReportID("application", rs.getString(1));
                Response response=resposne_dao.getResponseByAppliId(rs.getString(1));
                list.add(new Application(rs.getString(1),rs.getString(2),student_dao.getStudentByID(rs.getString(2)).getFull_name(),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),listAttach,response));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Notification_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public int getNumberOfPendingApplication(){
        int result=0;
        try {
            String query="Select count(*) from Application where status=N'Đang chờ tiếp nhận'";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            ResultSet rs=pst.executeQuery();
            Attachment_dao attach_dao=new Attachment_dao();
            Response_dao resposne_dao=new Response_dao();
            Student_dao student_dao=new Student_dao();
            while(rs.next()){
                result=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Notification_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public ArrayList<Application> getAllProcessedApplication(){
        ArrayList<Application> list=new ArrayList();
        try {
            String query="Select * from Application where status=N'Đã xử lý' order by submission_date desc";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            ResultSet rs=pst.executeQuery();
            Attachment_dao attach_dao=new Attachment_dao();
            Response_dao resposne_dao=new Response_dao();
            Student_dao student_dao=new Student_dao();
            while(rs.next()){
                ArrayList<Attachment> listAttach=attach_dao.getAllAttachmentByReportID("application", rs.getString(1));
                Response response=resposne_dao.getResponseByAppliId(rs.getString(1));
                list.add(new Application(rs.getString(1),rs.getString(2),student_dao.getStudentByID(rs.getString(2)).getFull_name(),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),listAttach,response));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Notification_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Application getApplicationByID(String appliId){
        Application result=null;
        try {
            String query="Select * from Application where application_id=?";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, appliId);
            ResultSet rs=pst.executeQuery();
            Attachment_dao attach_dao=new Attachment_dao();
            Response_dao resposne_dao=new Response_dao();
            Student_dao student_dao=new Student_dao();
            while(rs.next()){
                ArrayList<Attachment> listAttach=attach_dao.getAllAttachmentByReportID("application", rs.getString(1));
                Response response=resposne_dao.getResponseByAppliId(rs.getString(1));
                result=new Application(rs.getString(1),rs.getString(2),student_dao.getStudentByID(rs.getString(2)).getFull_name(),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),listAttach,response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Notification_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    private int generateUniqueID() {
        AtomicInteger counter = new AtomicInteger();
        int MAX_ID = 9999999;
        // Lấy thời gian hiện tại theo mili giây và thêm vào bộ đếm
        long currentTimeMillis = System.currentTimeMillis();
        int uniqueID = (int) (currentTimeMillis % MAX_ID + counter.incrementAndGet());
        return uniqueID;
    }
    private String getDate() {
        String result=null;
        //lay ra ngay gio hien tai
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); //chinh lai format
        result = currentDateTime.format(formatter); //day chinh la thoi diem hien tai sau khi format
        return result;
    }
}
