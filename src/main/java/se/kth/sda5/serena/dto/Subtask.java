package se.kth.sda5.serena.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Subtask implements Serializable{

    @Id
    @Column(name = "subtask_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "created")
    private Date creationDate;
    @Column(name = "due")
    private Date dueDate;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public Subtask(){}

    public Subtask(String name){
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
