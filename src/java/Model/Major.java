/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Major {
    private String id;
    private String name;
    private String branch_id;

    public Major() {
    }

    public Major(String id, String name, String branch_id) {
        this.id = id;
        this.name = name;
        this.branch_id = branch_id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBranch_id() {
        return branch_id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBranch_id(String branch_id) {
        this.branch_id = branch_id;
    }
    
}
