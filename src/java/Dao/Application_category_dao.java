/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Application_category;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Admin
 */
public class Application_category_dao {

    public Application_category_dao() {
    }

    public ArrayList<Application_category> getAllCategory(){
        ArrayList<Application_category> list=new ArrayList<Application_category>();
        try {
            String query="select * from Application_category order by category_name";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                list.add(new Application_category(rs.getString(1),rs.getString(2),rs.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Application_category_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Application_category getCategoryById(String category_id){
        Application_category result=null;
        String query="select * from Application_category where category_id=?";
        try {
            PreparedStatement pst = new DBContext().connection.prepareStatement(query);
            pst.setString(1, category_id);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                result=new Application_category(rs.getString(1),rs.getString(2),rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Application_category_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
