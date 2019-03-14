package se.kth.sda5.serena.hibernate.util;

import com.sun.tools.javac.jvm.CRTable;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import se.kth.sda5.serena.dto.*;
import se.kth.sda5.serena.todo.program.Menu;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

public class HibernateQuery {

    public static void addObject(Class className, Object object){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer id = null;
        transaction = session.beginTransaction();
        if(className.getName().equals("se.kth.sda5.serena.dto.Task")){
            Task task = (Task) object;
            id = (Integer) session.save(task);
        }else if(className.getName().equals("se.kth.sda5.serena.dto.Subtask")){
            Subtask subtask = (Subtask) object;
            id = (Integer) session.save(subtask);
        }
        else if(className.getName().equals("se.kth.sda5.serena.dto.Member")){
            Member member = (Member) object;
            id = (Integer) session.save(member);
        }
        else if(className.getName().equals("se.kth.sda5.serena.dto.Status")){
            Status status = (Status) object;
            id = (Integer) session.save(status);
        }
        else if(className.getName().equals("se.kth.sda5.serena.dto.User")){
            User user = (User) object;
            id = (Integer) session.save(user);
        }
        else if(className.getName().equals("se.kth.sda5.serena.dto.Project")){
            Project project = (Project) object;
            id = (Integer) session.save(project);
        }
        transaction.commit();
        session.close();
    }

    public static List getAllData(Class c) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List list = session.createCriteria(c).list();
        return list;
    }

    public static Task getTaskById(Integer taskId) {
        Session session = null;
        Task task = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            task = (Task) session.load(Task.class, taskId);
            Hibernate.initialize(task);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return task;
    }

    public static Status getStatusById(Integer sId) {
        Session session = null;
        Status status = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            status = (Status) session.load(Status.class, sId);
            Hibernate.initialize(status);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return status;
    }

    public static Project getProjectById(Integer pId) {
        Session session = null;
        Project project = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            project = (Project) session.load(Project.class, pId);
            Hibernate.initialize(project);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return project;
    }
    public static User getUserById(Integer userId) {
        Session session = null;
        User user = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            user = (User) session.load(User.class, userId);
            Hibernate.initialize(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }
    public static void updateStatus(Task task){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(task);
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteTask(int taskID) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Task t =(Task) session.get(Task.class,taskID);
        // t.getSubtask().remove(0);
        session.delete(t);
        session.getTransaction().commit();
        System.out.println(transaction.wasCommitted());
        session.close();
    }

    public static void deleteUser(String email, String password){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        validateLogin(email, password);
        session.delete(Menu.loginUser);
        session.getTransaction().commit();
        System.out.println(transaction.wasCommitted());
        session.close();
    }

    public static List<Task> getByProID(Project project){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Task.class);
        cr.add(Restrictions.eq("project", project));

        return cr.list();
    }

    public static List<Task> getByUserID(User user){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Task.class);
        cr.add(Restrictions.eq("user", user));

        return cr.list();
    }

    public static List<Project> projectByUserID(User user){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Project.class);
        cr.add(Restrictions.eq("user", user));

        return cr.list();
    }

    public static List<Task> getByDate(Task task){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Task.class);
        cr.addOrder(Order.asc("dueDate"));
        return cr.list();
    }

    public static boolean checkEmailExist(String email){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(User.class);
        List email1 = cr.add(Restrictions.eq("email", email)).list();
        if(email1.size() != 0) {
            return true;
        }
        return false;
    }

    /*public static  boolean validateLogin(String email, String password){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(User.class);
        List login = cr.add(Restrictions.eq("email", email)).add(Restrictions.eq("password", password)).list();
        if(login.size() != 0){
            return true;
        }return false;
    }*/
    public static  boolean validateLogin(String email, String password){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(User.class);
        List emailLogin = cr.add(Restrictions.eq("email", email)).list();
        if(emailLogin.size() != 0) {
            List passwordLogin = cr.add(Restrictions.eq("password", password)).list();
            if(passwordLogin.size() != 0){
                Menu.loginUser = (User) passwordLogin.get(0);
                return true;
            }return false;
        }
        return false;
    }

}
