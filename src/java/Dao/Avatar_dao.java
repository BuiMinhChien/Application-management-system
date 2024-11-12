/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Avatar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Admin
 */
public class Avatar_dao {

    public Avatar_dao() {
    }
    public Avatar getAvatarByAvatarID(String avaId){
        Avatar result=null;
        String query="select * from Avatar where avatar_id=?";
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, avaId);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                result=new Avatar(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public Avatar getAvatarByStudentID(String stuId){
        Avatar result=null;
        String query="select * from Avatar where student_id=?";
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, stuId);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                result=new Avatar(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public Avatar getAvatarByStaffID(String staffId){
        Avatar result=null;
        String query="select * from Avatar where staff_id=?";
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, staffId);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                result=new Avatar(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int deleteAvatarByAvatarId(String avaId){
        int result=0;
        String query="delete from Avatar where avatar_id=?";
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, avaId);
            result=pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int deleteAvatarByUserId(String role,String userId){
        int result=0;
        String query=null;
        if(role.equals("student")){
            query="delete from Avatar where student_id=?";
        }
        else if(role.equals("staff")){
            query="delete from Avatar where staff_id=?";
        }
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, userId);
            result=pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int createAvatarForUser(String role,String user_id,String file_path){
        int result=0;
        String query=null;
        PreparedStatement pst;
        //lay ra ngay gio hien tai
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //chinh lai format
        String formattedDateTime = currentDateTime.format(formatter); //day chinh la thoi diem hien tai sau khi format
        query="insert into Avatar values(?,?,?,?,?)";
        try {
            pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, generateUniqueID()+"");
            pst.setString(2, file_path);
            pst.setString(3, formattedDateTime);
            if(role.equals("student")){
                pst.setString(4, user_id);
                pst.setString(5, null);
            }
            else if(role.equals("staff")){
                pst.setString(4, null);
                pst.setString(5, user_id);
            }
            result=pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
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
}
