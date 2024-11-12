/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Attachment {
    private String id;
    private String name_file;
    private String file_path;
    private String upload_date;
    private String application_id;
    private String response_id;

    public Attachment() {
    }

    public Attachment(String id, String name_file, String file_path, String upload_date, String application_id, String response_id) {
        this.id = id;
        this.name_file = name_file;
        this.file_path = file_path;
        this.upload_date = upload_date;
        this.application_id = application_id;
        this.response_id = response_id;
    }

    public Attachment(String id, String file_path, String upload_date, String application_id, String response_id) {
        this.id = id;
        this.file_path = file_path;
        this.upload_date = upload_date;
        this.application_id = application_id;
        this.response_id = response_id;
    }

    public String getId() {
        return id;
    }

    public String getName_file() {
        return name_file;
    }

    public String getFile_path() {
        return file_path;
    }

    public String getUpload_date() {
        return upload_date;
    }

    public String getApplication_id() {
        return application_id;
    }

    public String getResponse_id() {
        return response_id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName_file(String name_file) {
        this.name_file = name_file;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
    }

    public void setApplication_id(String application_id) {
        this.application_id = application_id;
    }

    public void setResponse_id(String response_id) {
        this.response_id = response_id;
    }
    
}
