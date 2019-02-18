package se.kth.sda5.serena.dto;

import java.io.Serializable;
import java.util.Date;

public class Project implements Serializable {
    private int id;
    private String name;
    private String description;

    public Project(){}

    public Project(String name, String description, Date creationDate){
        this.name = name;
        this.description = description;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }


}
