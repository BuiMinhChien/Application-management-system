/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Avatar {
    private String id;
    private String file_path;
    private String upload_date;
    private String student_id;
    private String staff_id;

    public Avatar() {
    }

    public Avatar(String id, String file_path, String upload_date, String student_id, String staff_id) {
        this.id = id;
        this.file_path = file_path;
        this.upload_date = upload_date;
        this.student_id = student_id;
        this.staff_id = staff_id;
    }

    public String getId() {
        return id;
    }

    public String getFile_path() {
        return file_path;
    }

    public String getUpload_date() {
        return upload_date;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }
    
}
