/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Application {
    private String application_id;
    private String student_id;
    private String student_name;
    private String category_id;
    private String title;
    private String content;
    private String submission_date;
    private String status;
    private ArrayList<Attachment> listAttachment;
    private Response response;

    public Application() {
    }

    public Application(String application_id, String student_id, String category_id, String title, String content, String submission_date, String status) {
        this.application_id = application_id;
        this.student_id = student_id;
        this.category_id = category_id;
        this.title = title;
        this.content = content;
        this.submission_date = submission_date;
        this.status = status;
    }

    public Application(String application_id, String student_id, String category_id, String title, String content, String submission_date, String status, ArrayList<Attachment> listAttachment) {
        this.application_id = application_id;
        this.student_id = student_id;
        this.category_id = category_id;
        this.title = title;
        this.content = content;
        this.submission_date = submission_date;
        this.status = status;
        this.listAttachment = listAttachment;
    }

    public Application(String application_id, String student_id, String category_id, String title, String content, String submission_date, String status, ArrayList<Attachment> listAttachment, Response response) {
        this.application_id = application_id;
        this.student_id = student_id;
        this.category_id = category_id;
        this.title = title;
        this.content = content;
        this.submission_date = submission_date;
        this.status = status;
        this.listAttachment = listAttachment;
        this.response = response;
    }

    public Application(String application_id, String student_id, String student_name, String category_id, String title, String content, String submission_date, String status, ArrayList<Attachment> listAttachment, Response response) {
        this.application_id = application_id;
        this.student_id = student_id;
        this.student_name = student_name;
        this.category_id = category_id;
        this.title = title;
        this.content = content;
        this.submission_date = submission_date;
        this.status = status;
        this.listAttachment = listAttachment;
        this.response = response;
    }

    

    public String getApplication_id() {
        return application_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getTitle() {
        return title;
    }
    public String getTitleStandardFormat(){
        return title.toUpperCase();
    }

    public String getContent() {
        return content;
    }

    public String getSubmission_date() {
        return submission_date;
    }
    public String getSubmission_year(){
        return submission_date.substring(0, 4);
    }
    public String getSubmission_month(){
        return submission_date.substring(5, 7);
    }
    public String getSubmission_day(){
        return submission_date.substring(8, 10);
    }
    
    public String getStatus() {
        return status;
    }

    public ArrayList<Attachment> getListAttachment() {
        return listAttachment;
    }

    public Response getResponse() {
        return response;
    }

    public void setApplication_id(String application_id) {
        this.application_id = application_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSubmission_date(String submission_date) {
        this.submission_date = submission_date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setListAttachment(ArrayList<Attachment> listAttachment) {
        this.listAttachment = listAttachment;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
