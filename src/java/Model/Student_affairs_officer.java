/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Student_affairs_officer {
    private String staff_id;
    private String username;
    private String password;
    private String full_name;
    private String dob;
    private String gender;
    private String email;
    private String phone_number;
    private String address;
    private String department;
    private String registration_date;
    private String status;
    private String avatar_path;

    public Student_affairs_officer() {
    }

    public Student_affairs_officer(String staff_id, String username, String password, String full_name, String dob, String gender, String email, String phone_number, String address, String department, String registration_date, String status, String avatar_path) {
        this.staff_id = staff_id;
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.department = department;
        this.registration_date = registration_date;
        this.status = status;
        this.avatar_path = avatar_path;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getAddress() {
        return address;
    }

    public String getDepartment() {
        return department;
    }
    
    public String getRegistration_date() {
        return registration_date;
    }

    public String getStatus() {
        return status;
    }

    public String getAvatar_path() {
        return avatar_path;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAvatar_path(String avatar_path) {
        this.avatar_path = avatar_path;
    }
}
