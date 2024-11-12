/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Model.Major;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Admin
 */
public class Major_dao {
    public Major_dao() {
    }
    
    public Major getMajorByID(String id){
        Major result=null;
        String query="select * from Major where major_id=?";
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, id);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                result=new Major(rs.getString(1),rs.getString(2),rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public ArrayList<Major> getAllMajor(){
        ArrayList<Major> list=new ArrayList();
        try {
            String query="Select * from Major order by branch_id asc";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                list.add(new Major(rs.getString(1),rs.getString(2),rs.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Notification_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public ArrayList<Major> getAllMajorInBranch(String branch_id){
        ArrayList<Major> list=new ArrayList();
        try {
            String query="Select * from Major where branch_id=?";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, branch_id);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                list.add(new Major(rs.getString(1),rs.getString(2),rs.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Notification_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
