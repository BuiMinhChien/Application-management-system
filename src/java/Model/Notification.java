/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Notification {
    private String notification_id;
    private String notification_content;
    private String notification_date;
    private String status;
    private String student_id;
    private String application_id;
    private String response_id;

    public Notification() {
    }

    public Notification(String notification_id, String notification_content, String notification_date, String status, String student_id, String application_id, String response_id) {
        this.notification_id = notification_id;
        this.notification_content = notification_content;
        this.notification_date = notification_date;
        this.status = status;
        this.student_id = student_id;
        this.application_id = application_id;
        this.response_id = response_id;
    }

    public String getNotification_id() {
        return notification_id;
    }

    public String getNotification_content() {
        return notification_content;
    }

    public String getNotification_date() {
        return notification_date;
    }

    public String getStatus() {
        return status;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getApplication_id() {
        return application_id;
    }

    public String getResponse_id() {
        return response_id;
    }

    public void setNotification_id(String notification_id) {
        this.notification_id = notification_id;
    }

    public void setNotification_content(String notification_content) {
        this.notification_content = notification_content;
    }

    public void setNotification_date(String notification_date) {
        this.notification_date = notification_date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public void setApplication_id(String application_id) {
        this.application_id = application_id;
    }

    public void setResponse_id(String response_id) {
        this.response_id = response_id;
    }

}
