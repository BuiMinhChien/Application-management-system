/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Branch;
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
public class Branch_dao {
    public Branch_dao() {
    }
    
    public Branch getBranchByID(String id){
        Branch result=null;
        String query="select * from Branch where branch_id=?";
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, id);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                result=new Branch(rs.getString(1),rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public ArrayList<Branch> getAllBranch(){
        ArrayList<Branch> list=new ArrayList();
        try {
            String query="Select * from Branch";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                list.add(new Branch(rs.getString(1),rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Notification_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public String getBranchCode(String branch_id){
        String result=null;
        switch(branch_id){
            case "BIT":
                result="HE";
                break;
            case "BBA":
                result="HS";
                break;
            case "BEN":
            case "BJP":
            case "BKR":
                result="HA";
                break;
        }
        return result;
    }
}
