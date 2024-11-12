/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Avatar;
import Model.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Admin
 */
public class Student_dao {

    public Student_dao() {
    }

    public Student checkLogin(String username, String password){
        Student result=null;
        try {
            String query="Select * from Student where username=? and password=?;";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs=pst.executeQuery();
            Major_dao major_dao=new Major_dao();
            Branch_dao branch_dao=new Branch_dao();
            Avatar_dao avatar_dao=new Avatar_dao();
            if(rs.next()){
//                String avatar_path=null;
                String avatar_path="Avatar_user_upload\\avatar_default.jpg";
                Avatar avatar=avatar_dao.getAvatarByStudentID(rs.getString(1));
                if(avatar!=null){
                    avatar_path=avatar.getFile_path();
                }
                result=new Student(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),
                        major_dao.getMajorByID(rs.getString(10)).getName(),branch_dao.getBranchByID(major_dao.getMajorByID(rs.getString(10)).getBranch_id()).getName(),
                        rs.getString(11),rs.getString(12),avatar_path);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public Student checkAccount(String username, String email){
        Student result=null;
        try {
            String query="Select * from Student where username=? and email=?;";
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, email);
            ResultSet rs=pst.executeQuery();
            Major_dao major_dao=new Major_dao();
            Branch_dao branch_dao=new Branch_dao();
            Avatar_dao avatar_dao=new Avatar_dao();
            if(rs.next()){
//                String avatar_path=null;
                String avatar_path="Avatar_user_upload\\avatar_default.jpg";
                Avatar avatar=avatar_dao.getAvatarByStudentID(rs.getString(1));
                if(avatar!=null){
                    avatar_path=avatar.getFile_path();
                }
                result=new Student(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),
                        major_dao.getMajorByID(rs.getString(10)).getName(),branch_dao.getBranchByID(major_dao.getMajorByID(rs.getString(10)).getBranch_id()).getName(),
                        rs.getString(11),rs.getString(12),avatar_path);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public ArrayList<Student> getAllStudent(){
        ArrayList<Student> list=new ArrayList<Student>();
        String query="select * from Student order by student_id asc";
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            ResultSet rs=pst.executeQuery();
            Major_dao major_dao=new Major_dao();
            Branch_dao branch_dao=new Branch_dao();
            Avatar_dao avatar_dao=new Avatar_dao();
            Avatar avatar=null;
//            String avatar_path=null;
            String avatar_path="Avatar_user_upload\\avatar_default.jpg";
            while(rs.next()){
                avatar=avatar_dao.getAvatarByStudentID(rs.getString(1));
                if(avatar!=null){
                    avatar_path=avatar.getFile_path();
                }
                list.add(new Student(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),
                        major_dao.getMajorByID(rs.getString(10)).getName(),branch_dao.getBranchByID(major_dao.getMajorByID(rs.getString(10)).getBranch_id()).getName(),
                        rs.getString(11),rs.getString(12),avatar_path));
                avatar_path="Avatar_user_upload\\avatar_default.jpg";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Student getStudentByID(String id){
        Student result=null;
        String query="select * from Student where student_id=?";
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, id);
            ResultSet rs=pst.executeQuery();
            Major_dao major_dao=new Major_dao();
            Branch_dao branch_dao=new Branch_dao();
            Avatar_dao avatar_dao=new Avatar_dao();
            while(rs.next()){
//                String avatar_path=null;
                String avatar_path="Avatar_user_upload\\avatar_default.jpg";
                Avatar avatar=avatar_dao.getAvatarByStudentID(rs.getString(1));
                if(avatar!=null){
                    avatar_path=avatar.getFile_path();
                }
                result=new Student(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),
                        major_dao.getMajorByID(rs.getString(10)).getName(),branch_dao.getBranchByID(major_dao.getMajorByID(rs.getString(10)).getBranch_id()).getName(),
                        rs.getString(11),rs.getString(12),avatar_path);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int editInforStudent(String id,String full_name,String dob,String gender,String email,String phone_number,String address,String status){
        int result=0;
        String query="update Student set full_name=?, dob=?, gender=?, email=?, phone_number=?, address=?, status=? where student_id=?";
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            pst.setString(1, full_name);
            pst.setString(2, dob);
            pst.setString(3, gender);
            pst.setString(4, email);
            pst.setString(5, phone_number);
            pst.setString(6, address);
            pst.setString(7, status);
            pst.setString(8, id);
            result=pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int editInforStudent(String id,String full_name,String dob,String gender,String email,String phone_number,String address){
        int result=0;
        String query="update Student set full_name=?, dob=?, gender=?, email=?, phone_number=?, address=? where student_id=?";
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
    public ArrayList<Student> searchStudentByIdAndName(String search){
        ArrayList<Student> list=new ArrayList<Student>();
        String query="select * from Student where full_name like ? or student_id like ? order by student_id asc";
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            String searchPattern = "%" + search + "%";
            pst.setString(1, searchPattern);
            pst.setString(2, searchPattern);
            ResultSet rs=pst.executeQuery();
            Major_dao major_dao=new Major_dao();
            Branch_dao branch_dao=new Branch_dao();
            Avatar_dao avatar_dao=new Avatar_dao();
//            String avatar_path=null;
            String avatar_path="Avatar_user_upload\\avatar_default.jpg";
            Avatar avatar=null;
            while(rs.next()){
                avatar=avatar_dao.getAvatarByStudentID(rs.getString(1));
                if(avatar!=null){
                    avatar_path=avatar.getFile_path();
                }
                list.add(new Student(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),
                        major_dao.getMajorByID(rs.getString(10)).getName(),branch_dao.getBranchByID(major_dao.getMajorByID(rs.getString(10)).getBranch_id()).getName(),
                        rs.getString(11),rs.getString(12),avatar_path));
                avatar_path="Avatar_user_upload\\avatar_default.jpg";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public int changePassword(String id,String new_pass){
        int result=0;
        String query="update Student set password=? where student_id=?";
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
    public int createNewStudentAccount(String full_name,String dob,String gender,String email,String phone_number,String address,String select_major){
        int result=0;
        //lay ra ngay gio hien tai
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //chinh lai format
        String formattedDateTime = currentDateTime.format(formatter); //day chinh la thoi diem hien tai sau khi format
        String query="insert into Student values(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst=new DBContext().connection.prepareStatement(query);
            String id=createStudentID(select_major);
            pst.setString(1, id);
            pst.setString(2, createUsername(id,full_name));
            pst.setString(3, generatePassword());
            pst.setString(4, full_name);
            pst.setString(5, dob);
            pst.setString(6, gender);
            pst.setString(7, email);
            pst.setString(8, phone_number);
            pst.setString(9, address);
            pst.setString(10, select_major);
            pst.setString(11, formattedDateTime);
            pst.setString(12, "Mở");
            result=pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Student_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
///////////////////////////////////////////////////////////////////////////////////////
    private String createStudentID(String major_id){
        StringBuffer result=new StringBuffer();
        Major_dao major_dao=new Major_dao();
        Branch_dao branch_dao=new Branch_dao();
        result.append(branch_dao.getBranchCode(major_dao.getMajorByID(major_id).getBranch_id()));
        //lay ra ngay gio hien tai
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy"); //chinh lai format
        String formattedDateTime = currentDateTime.format(formatter); //day chinh la thoi diem hien tai sau khi format
        int k=Integer.parseInt(formattedDateTime)-2022+18;
        if(k/10==0){
            result.append("0"+k+generateUniqueID());
        }
        else{
            result.append(""+k+generateUniqueID());
        }
        return result.toString();
    }
    public String generateUniqueID() {
        String result=null;
        // Lấy thời gian hiện tại (miliseconds)
        long currentTimeMillis = System.currentTimeMillis();
        while (currentTimeMillis <= 9999) {
            currentTimeMillis = System.currentTimeMillis();
        }
        // Chỉ lấy 3 chữ số cuối của thời gian hiện tại
        int uniqueID = (int) (currentTimeMillis % 10000);
        result=uniqueID+"";
        while(result.length()<4){
            result="0"+result.substring(0);
        }
        return result;
    }
    public String generatePassword() {
        StringBuilder sb = new StringBuilder(8);
        Random random = new Random();
        String LOWER = "abcdefghijklmnopqrstuvwxyz";
        String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String DIGITS = "0123456789";
        String SPECIAL = "!@#%&-_";
        String ALL_CHARS = LOWER + UPPER + DIGITS + SPECIAL;
        for (int i = 0; i < 8; i++) {
            // Sinh ngẫu nhiên một ký tự từ ALL_CHARS
            char randomChar = ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length()));
            sb.append(randomChar);
        }
        // Đảo ngẫu nhiên thứ tự các ký tự trong chuỗi sb
        for (int i = sb.length() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = sb.charAt(index);
            sb.setCharAt(index, sb.charAt(i));
            sb.setCharAt(i, temp);
        }
        return sb.toString();
    }
    // Hàm loại bỏ dấu tiếng Việt
    private String normalize(String input) {
        String regex = "\\p{InCombiningDiacriticalMarks}+";
        String temp = Normalizer.normalize(input, Normalizer.Form.NFD);
        return temp.replaceAll(regex, "").replaceAll("đ", "d").replaceAll("Đ", "D");
    }
    private String createUsername(String student_id,String full_name){
        // Chuyển tên thành chữ thường và xóa các dấu tiếng Việt
        full_name = normalize(full_name).toLowerCase().trim();
        // Tách họ và tên
        String[] parts = full_name.split("\\s+");
        String ho = parts[0]; // Họ
        String tenLot = ""; // Tên lót, mặc định là chuỗi rỗng
        String ten = ""; // Tên, mặc định là chuỗi rỗng
        // Nếu có tên lót và tên, gán giá trị cho biến
        if (parts.length > 1) {
            ten = parts[parts.length - 1]; // Lấy phần tử cuối cùng trong mảng là tên
            // Nếu chỉ có một phần tử trong mảng, nghĩa là tên lót không có
            if (parts.length > 2) {
                for (int i = 1; i < parts.length - 1; i++) {
                    tenLot += parts[i].charAt(0); // Lấy chữ cái đầu của từng tên lót
                }
            }
        }
        // Chữ cái đầu của họ và tên lót
        String firstHo = ho.substring(0, 1);
        String firstTenLot = tenLot.isEmpty() ? "" : tenLot; // Không cần substring vì đã lấy từ charAt
        // Ghép thành tên tài khoản theo yêu cầu
        String username = ten + firstHo + firstTenLot + student_id.toLowerCase();
        return username;
    }
}
