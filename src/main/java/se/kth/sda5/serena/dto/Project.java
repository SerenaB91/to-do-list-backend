package se.kth.sda5.serena.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Project implements Serializable {

    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "created")
    private Date creationDate;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "member")
    private Member member;

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

    public Date getCreated() {

        return creationDate;
    }

    public void setCreated(Date creationDate) {

        this.creationDate = creationDate;
    }

    public User getUser(){

        return user;
    }

    public void setUser(User user){

        this.user = user;
    }

    public Member getMember(){

        return member;
    }

    public void setMember(Member member){

        this.member = member;
    }

}
