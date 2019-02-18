package se.kth.sda5.serena.dto;

import java.io.Serializable;

public class Member implements Serializable {
    private int id;
    private User user;
    private Project project;
    private boolean owner;

    public Member(){}

    public Member(boolean owner){
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public boolean getOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }
}
