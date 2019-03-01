package se.kth.sda5.serena.todo.program;

import se.kth.sda5.serena.dto.*;
import se.kth.sda5.serena.hibernate.util.HibernateQuery;
import sun.java2d.StateTrackableDelegate;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class MainProgram {
    public static void main(String[] args) throws ParseException {
        Menu menu = new Menu();
        menu.run();

        /*User user = new User();
        user.setEmail("serenabarakat2@gmail.com");
        user.setFirstName("Serena");
        user.setLastName("Barakat");
        user.setPassword("1234");
        user.setCreated(new Date());
        HibernateQuery.addUser(user);

        User user2 = new User();
        user2.setEmail("serenabarakat2@gmail.com");
        user2.setFirstName("Serena");
        user2.setLastName("Barakat");
        user2.setPassword("1234");
        user2.setCreated(new Date());
        HibernateQuery.addUser(user2);

        Project project = new Project();
        project.setName("Build The Villa");
        project.setDescription("create a todo-list");
        HibernateQuery.addProject(project);

        Status status = new Status();
        status.setName("Done");
        HibernateQuery.addStatus(status);

        Status status1 = new Status();
        status1.setName("Pending");
        HibernateQuery.addStatus(status1);

        Member member = new Member();
        member.setUser(user);
        member.setProject(project);
        member.setOwner(true);
        HibernateQuery.addMember(member);

        Task task = new Task();
        task.setName("Select Land");
        task.setDescription("Choose the land to build on");
        task.setCreated(new Date());
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 7);
        date = c.getTime();
        task.setDueDate(date);
        task.setStatus(status);
        task.setProject(project);
        HibernateQuery.addTask(task);

        Subtask subtask = new Subtask();
        subtask.setName("Search the internet");
        subtask.setCreated(new Date());
        Date date1 = new Date();
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        c1.add(Calendar.DATE, 7);
        date = c1.getTime();
        System.out.println(date);
        subtask.setDueDate(date1);
        subtask.setStatus(status);
        subtask.setTask(task);
        HibernateQuery.addSubtask(subtask);

        HibernateQuery.displayRecords();

        HibernateQuery.readById(2); */
    }
}
