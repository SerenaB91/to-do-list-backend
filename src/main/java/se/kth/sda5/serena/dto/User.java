package se.kth.sda5.serena.dto;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Date creationDate;

    public User(){}

    public User(String email, String firstName, String lastName, String password){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Date getCreated() {
        return creationDate;
    }

    public void setCreated(Date creationDate) {
        this.creationDate = creationDate;
    }
}
