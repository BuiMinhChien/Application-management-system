/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Notification;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;
/**
 *
 * @author Admin
 */
public class Notification_dao {

    public Notification_dao() {
    }
    
    public ArrayList<Notification> getAllNotiByStudentId(String studentid){
        ArrayList<Notification> list=new ArrayList();
        try {
            String query="Select * from Notification where student_id=? order by notification_date desc";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, studentid);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                list.add(new Notification(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Notification_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public int getNumberUnreadNotiByStudentId(String studentid){
        int result=0;
        try {
            String query="Select count(*) from Notification where student_id=? and status=?";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, studentid);
            pst.setString(2, "Chưa đọc");
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                result=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Notification_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public Notification getNotiId(String notiId){
        Notification result=null;
        try {
            String query="Select * from Notification where notification_id=?";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, notiId);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                result=new Notification(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Notification_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int createNotification(String content, String studentid, String applicationid, String responseid){
        int result=0;
        try {
            String query=null;
            PreparedStatement pst;
            ResultSet rs;
            //lay ra ngay gio hien tai
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); //chinh lai format
            String formattedDateTime = currentDateTime.format(formatter); //day chinh la thoi diem hien tai sau khi format
            //trang thai cua thong bao mac dinh la chua doc
            String default_status="Chưa đọc";
            //tao cau lenh insert into
            query="Insert into Notification values (?,?,?,?,?,?,?)";
            pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, ""+generateUniqueID());
            pst.setString(2, content);
            pst.setString(3, formattedDateTime);
            pst.setString(4, default_status);
            pst.setString(5, studentid);
            pst.setString(6, applicationid);
            pst.setString(7, responseid);
            result=pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Notification_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int changeStatusNoti(String notiid){
        int result=0;
        try {
            String query="update Notification set status=? where notification_id=?";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, "Đã đọc");
            pst.setString(2, notiid);
            result=pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Notification_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int deleteNoti(String notiId){
        int result=0;
        try {
            String query="delete from Notification where notification_id=?";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, notiId);
            result=pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Notification_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int generateUniqueID() {
        AtomicInteger counter = new AtomicInteger();
        int MAX_ID = 9999999;
        // Lấy thời gian hiện tại theo mili giây và thêm vào bộ đếm
        long currentTimeMillis = System.currentTimeMillis();
        int uniqueID = (int) (currentTimeMillis % MAX_ID + counter.incrementAndGet());
        return uniqueID;
    }
//    public static void main(String[] args) {
//        Notification_dao noti_dao=new Notification_dao();
//        System.out.println(noti_dao.createNotification("4 nè", "HE186719", null, null));
//    }
}
