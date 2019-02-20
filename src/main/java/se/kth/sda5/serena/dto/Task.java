package se.kth.sda5.serena.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

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
}
