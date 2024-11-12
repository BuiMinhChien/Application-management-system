/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Application_category {
    private String category_id;
    private String category_name;
    private String description;

    public Application_category() {
    }

    public Application_category(String category_id, String category_name, String description) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.description = description;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getDescription() {
        return description;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
