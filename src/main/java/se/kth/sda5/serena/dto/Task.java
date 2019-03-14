package se.kth.sda5.serena.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Task implements Serializable {

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "created")
    private Date creationDate;
    @Column(name = "due")
    private Date dueDate;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private Status status;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private Project project;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "task", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Subtask> subtask = new ArrayList<Subtask>();

    public Task(){}

    public Task(String name, String description){
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

    public Date getCreated() {

        return creationDate;
    }

    public void setCreated(Date creationDate) {

        this.creationDate = creationDate;
    }

    public Date getDueDate(){

        return dueDate;
    }

    public void setDueDate(Date dueDate){

        this.dueDate = dueDate;
    }

    public Status getStatus(){

        return status;
    }

    public void setStatus(Status status){

        this.status = status;
    }

    public Project getProject(){

        return project;
    }

    public void setProject(Project project){

        this.project = project;
    }

    public User getUser(){

        return user;
    }

    public void setUser(User user){

        this.user = user;
    }
}
