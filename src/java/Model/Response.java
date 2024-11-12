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
public class Response {
    private String response_id;
    private String application_id;
    private String staff_id;
    private String staff_name;
    private String response_content;
    private String response_date;
    private ArrayList<Attachment> listAttachment;
    public Response() {
    }

    public Response(String response_id, String application_id, String staff_id, String response_content, String response_date) {
        this.response_id = response_id;
        this.application_id = application_id;
        this.staff_id = staff_id;
        this.response_content = response_content;
        this.response_date = response_date;
    }

    public Response(String response_id, String application_id, String staff_id, String response_content, String response_date, ArrayList<Attachment> listAttachment) {
        this.response_id = response_id;
        this.application_id = application_id;
        this.staff_id = staff_id;
        this.response_content = response_content;
        this.response_date = response_date;
        this.listAttachment = listAttachment;
    }

    public Response(String response_id, String application_id, String staff_id, String staff_name, String response_content, String response_date, ArrayList<Attachment> listAttachment) {
        this.response_id = response_id;
        this.application_id = application_id;
        this.staff_id = staff_id;
        this.staff_name = staff_name;
        this.response_content = response_content;
        this.response_date = response_date;
        this.listAttachment = listAttachment;
    }

    public String getResponse_id() {
        return response_id;
    }

    public String getApplication_id() {
        return application_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public String getResponse_content() {
        return response_content;
    }

    public String getResponse_date() {
        return response_date;
    }
    public String getResponse_year(){
        return response_date.substring(0, 4);
    }
    public String getResponse_month(){
        return response_date.substring(5, 7);
    }
    public String getResponse_day(){
        return response_date.substring(8, 10);
    }

    public ArrayList<Attachment> getListAttachment() {
        return listAttachment;
    }

    public void setResponse_id(String response_id) {
        this.response_id = response_id;
    }

    public void setApplication_id(String application_id) {
        this.application_id = application_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public void setResponse_content(String response_content) {
        this.response_content = response_content;
    }

    public void setResponse_date(String response_date) {
        this.response_date = response_date;
    }

    public void setListAttachment(ArrayList<Attachment> listAttachment) {
        this.listAttachment = listAttachment;
    }

}
