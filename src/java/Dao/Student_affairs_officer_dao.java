/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Avatar;
import Model.Student_affairs_officer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Student_affairs_officer_dao {

    public Student_affairs_officer_dao() {
    }

    public Student_affairs_officer checkLogin(String username, String password){
        Student_affairs_officer result=null;
        try {
            String query="Select * from Student_affairs_officer where username=? and password=?;";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs=pst.executeQuery();
            Avatar_dao avatar_dao=new Avatar_dao();
            if(rs.next()){
//                String avatar_path=null;
                String avatar_path="Avatar_user_upload\\avatar_default.jpg";
                Avatar avatar=avatar_dao.getAvatarByStaffID(rs.getString(1));
                if(avatar!=null){
                    avatar_path=avatar.getFile_path();
                }
                result=new Student_affairs_officer(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
                        rs.getString(9),rs.getString(10),rs.getString(11),
                        rs.getString(12),avatar_path);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public Student_affairs_officer checkAccount(String username, String email){
        Student_affairs_officer result=null;
        try {
            String query="Select * from Student_affairs_officer where username=? and email=?;";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, email);
            ResultSet rs=pst.executeQuery();
            Avatar_dao avatar_dao=new Avatar_dao();
            if(rs.next()){
//                String avatar_path=null;
                String avatar_path="Avatar_user_upload\\avatar_default.jpg";
                Avatar avatar=avatar_dao.getAvatarByStaffID(rs.getString(1));
                if(avatar!=null){
                    avatar_path=avatar.getFile_path();
                }
                result=new Student_affairs_officer(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
                        rs.getString(9),rs.getString(10),rs.getString(11),
                        rs.getString(12),avatar_path);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int editInforStaff(String id,String full_name,String dob,String gender,String email,String phone_number,String address){
        int result=0;
        String query="update Student_affairs_officer set full_name=?, dob=?, gender=?, email=?, phone_number=?, address=? where staff_id=?";
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, full_name);
            pst.setString(2, dob);
            pst.setString(3, gender);
            pst.setString(4, email);
            pst.setString(5, phone_number);
            pst.setString(6, address);
            pst.setString(7, id);
            result=pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public Student_affairs_officer getStaffById(String id){
        Student_affairs_officer result=null;
        try {
            String query="Select * from Student_affairs_officer where staff_id=?";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, id);
            ResultSet rs=pst.executeQuery();
            Avatar_dao avatar_dao=new Avatar_dao();
            if(rs.next()){
//                String avatar_path=null;
                String avatar_path="Avatar_user_upload\\avatar_default.jpg";
                Avatar avatar=avatar_dao.getAvatarByStaffID(rs.getString(1));
                if(avatar!=null){
                    avatar_path=avatar.getFile_path();
                }
                result=new Student_affairs_officer(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
                        rs.getString(9),rs.getString(10),rs.getString(11),
                        rs.getString(12),avatar_path);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int changePassword(String id,String new_pass){
        int result=0;
        String query="update Student_affairs_officer set password=? where staff_id=?";
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, new_pass);
            pst.setString(2, id);
            result=pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
