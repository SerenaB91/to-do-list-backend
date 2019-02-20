package se.kth.sda5.serena.hibernate.util;

import org.hibernate.Session;
import org.hibernate.Transaction;
import se.kth.sda5.serena.dto.*;

public class HibernateQuery {

    public static void addUser(User user){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer id = null;
        transaction = session.beginTransaction();
        id = (Integer) session.save(user);
        transaction.commit();
        session.close();
    }

    public static void addProject(Project project){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer id = null;
        transaction = session.beginTransaction();
        id = (Integer) session.save(project);
        transaction.commit();
        session.close();
    }

    public static void addStatus(Status status){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer id = null;
        transaction = session.beginTransaction();
        id = (Integer) session.save(status);
        transaction.commit();
        session.close();
    }

    public static void addMember(Member member){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer id = null;
        transaction = session.beginTransaction();
        id = (Integer) session.save(member);
        transaction.commit();
        session.close();
    }

    public static void addTask(Task task){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer id = null;
        transaction = session.beginTransaction();
        id = (Integer) session.save(task);
        transaction.commit();
        session.close();
    }

    public static void addSubtask(Subtask subtask){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer id = null;
        transaction = session.beginTransaction();
        id = (Integer) session.save(subtask);
        transaction.commit();
        session.close();
    }

}
