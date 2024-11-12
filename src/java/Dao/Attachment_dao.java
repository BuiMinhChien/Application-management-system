/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Attachment;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Attachment_dao {
    private String getFileName(String filePath){
        String result=null;
        for(int i=filePath.length()-1;i>=0;i--){
            if(filePath.charAt(i)=='/'){
                result=filePath.substring(i+1, filePath.length());
                break;
            }
        }
        return result;
    }
    public Attachment_dao() {
    }
    public Attachment getAttachmentByAttachID(String attachId){
        Attachment result=null;
        String query="select * from Attachment where attachment_id=?";
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, attachId);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                result=new Attachment(rs.getString(1),getFileName(rs.getString(2)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public ArrayList<Attachment> getAllAttachmentByReportID(String type,String reportId){
        String query=null;
        ArrayList<Attachment> list=list=new ArrayList();
        if(type.equals("application")){
            query="select * from Attachment where application_id=?";
        }
        else if(type.equals("response")){
            query="select * from Attachment where response_id=?";
        }
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, reportId);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                list.add(new Attachment(rs.getString(1),getFileName(rs.getString(2)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public int deleteAttachmentByAttachId(String attachId){
        int result=0;
        String query="delete from Attachment where attachment_id=?";
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, attachId);
            result=pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int deleteAttachmentByReportId(String type,String reportId){
        int result=0;
        String query=null;
        if(type.equals("application")){
            query="delete from Attachment where application_id=?";
        }
        else if(type.equals("response")){
            query="delete from Attachment where response_id=?";
        }
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, reportId);
            result=pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int createAttachment(String file_path,String application_id,String response_id){
        int result=0;
        String query=null;
        PreparedStatement pst;
        //lay ra ngay gio hien tai
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //chinh lai format
        String formattedDateTime = currentDateTime.format(formatter); //day chinh la thoi diem hien tai sau khi format
        query="insert into Attachment values(?,?,?,?,?)";
        try {
            pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, generateUniqueID()+"");
            pst.setString(2, file_path);
            pst.setString(3, formattedDateTime);
            pst.setString(4, application_id);
            pst.setString(5, response_id);
            result=pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
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
}
