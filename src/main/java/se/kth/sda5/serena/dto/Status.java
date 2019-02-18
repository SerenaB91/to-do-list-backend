package se.kth.sda5.serena.dto;

import java.io.Serializable;

public class Status implements Serializable {
    private int id;
    private String name;

    public Status(){}

    public Status(String name){
        this.name = name;
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
}
