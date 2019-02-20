package se.kth.sda5.serena.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Status implements Serializable {

    @Id
    @Column(name = "status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
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
