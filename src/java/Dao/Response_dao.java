/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Response;
import Model.Attachment;
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
public class Response_dao {

    public Response_dao() {
    }
    public String createResponse(String application_id, String staff_id, String content){
        String result=null;
        int resultQuery=0;
        String query=null;
        PreparedStatement pst;
        query="insert into Response values(?,?,?,?,?)";
        try {
            pst=new DBContext().connection.prepareStatement(query);
            String id=generateUniqueID()+"";
            pst.setString(1, id);
            pst.setString(2, application_id);
            pst.setString(3, staff_id);
            pst.setString(4, content);
            pst.setString(5, getDate());
            resultQuery=pst.executeUpdate();
            if(resultQuery>0) result=id;
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public ArrayList<Response> getAllResponse(){
        ArrayList<Response> list=new ArrayList();
        try {
            String query="select * from Response order by response_date desc";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            ResultSet rs=pst.executeQuery();
            Attachment_dao attach_dao=new Attachment_dao();
            Student_affairs_officer_dao staff_dao=new Student_affairs_officer_dao();
            while(rs.next()){
                ArrayList<Attachment> listAttach=attach_dao.getAllAttachmentByReportID("response", rs.getString(1));
                list.add(new Response(rs.getString(1),rs.getString(2),rs.getString(3),staff_dao.getStaffById(rs.getString(3)).getFull_name(),
                        rs.getString(4),rs.getString(5),listAttach));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Notification_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Response getResponseByAppliId(String appliId){
        Response result=null;
        try {
            String query="Select * from Response where application_id=?";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, appliId);
            ResultSet rs=pst.executeQuery();
            Attachment_dao attach_dao=new Attachment_dao();
            Student_affairs_officer_dao staff_dao=new Student_affairs_officer_dao();
            while(rs.next()){
                ArrayList<Attachment> listAttach=attach_dao.getAllAttachmentByReportID("response", rs.getString(1));
                result=new Response(rs.getString(1),rs.getString(2),rs.getString(3),staff_dao.getStaffById(rs.getString(3)).getFull_name(),
                        rs.getString(4),rs.getString(5),listAttach);
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
