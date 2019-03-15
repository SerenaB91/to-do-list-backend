package se.kth.sda5.serena.hibernate.util;


import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import se.kth.sda5.serena.dto.*;
import se.kth.sda5.serena.todo.program.Menu;

import java.util.List;

public class HibernateQuery {

    /**
     * Used to insert an object in the database.
     *
     * @param className
     * @param object
     */
    public static void addObject(Class className, Object object) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer id = null;
        transaction = session.beginTransaction();
        if (className.getName().equals("se.kth.sda5.serena.dto.Task")) {
            Task task = (Task) object;
            id = (Integer) session.save(task);
        } else if (className.getName().equals("se.kth.sda5.serena.dto.Subtask")) {
            Subtask subtask = (Subtask) object;
            id = (Integer) session.save(subtask);
        } else if (className.getName().equals("se.kth.sda5.serena.dto.Member")) {
            Member member = (Member) object;
            id = (Integer) session.save(member);
        } else if (className.getName().equals("se.kth.sda5.serena.dto.Status")) {
            Status status = (Status) object;
            id = (Integer) session.save(status);
        } else if (className.getName().equals("se.kth.sda5.serena.dto.User")) {
            User user = (User) object;
            id = (Integer) session.save(user);
        } else if (className.getName().equals("se.kth.sda5.serena.dto.Project")) {
            Project project = (Project) object;
            id = (Integer) session.save(project);
        }
        transaction.commit();
        session.close();
    }

    /**
     * Used to get all data of a specific class from the database and stores it as a list.
     *
     * @param c
     * @return list
     */
    public static List getAllData(Class c) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List list = session.createCriteria(c).list();
        return list;
    }

    /**
     * Used to retrieve an object from the database based on a specific id.
     *
     * @param className
     * @param id
     * @return object
     */
    public static Object getObjectById(Class className, int id) {
        Session session = null;
        Object object = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            if (className.getName().equals("se.kth.sda5.serena.dto.Task")) {

                object = session.load(Task.class, id);

            } else if (className.getName().equals("se.kth.sda5.serena.dto.Subtask")) {
                object = session.load(Subtask.class, id);
            } else if (className.getName().equals("se.kth.sda5.serena.dto.Member")) {
                object = session.load(Member.class, id);
            } else if (className.getName().equals("se.kth.sda5.serena.dto.Status")) {
                object = session.load(Status.class, id);
            } else if (className.getName().equals("se.kth.sda5.serena.dto.User")) {
                object = session.load(User.class, id);
            } else if (className.getName().equals("se.kth.sda5.serena.dto.Project")) {
                object = session.load(Project.class, id);
            }

            Hibernate.initialize(object);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return object;

    }

    /**
     * Updates the status of a task.
     *
     * @param task
     */
    public static void updateStatus(Task task) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(task);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Deletes a task.
     *
     * @param taskID
     */
    public static void deleteTask(int taskID) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Task t = (Task) session.get(Task.class, taskID);
        session.delete(t);
        session.getTransaction().commit();
        System.out.println(transaction.wasCommitted());
        session.close();
    }

    /**
     * Deletes a user.
     *
     * @param email
     * @param password
     */
    public static void deleteUser(String email, String password) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        validateLogin(email, password);
        session.delete(Menu.loginUser);
        session.getTransaction().commit();
        System.out.println(transaction.wasCommitted());
        session.close();
    }

    /**
     * Criteria query to list tasks specific to a certain project.
     *
     * @param project
     * @return
     */
    public static List<Task> getByProID(Project project) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Task.class);
        cr.add(Restrictions.eq("project", project));

        return cr.list();
    }

    /**
     * Criteria query to list tasks specific to a certain user.
     *
     * @param user
     * @return
     */
    public static List<Task> getByUserID(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Task.class);
        cr.add(Restrictions.eq("user", user));

        return cr.list();
    }

    /**
     * Criteria query to list projects specific to a certain user.
     *
     * @param user
     * @return
     */
    public static List<Project> projectByUserID(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Project.class);
        cr.add(Restrictions.eq("user", user));

        return cr.list();
    }

    /**
     * Criteria query that lists tasks in ascending order of due date.
     *
     * @param task
     * @return
     */
    public static List<Task> getByDate(Task task) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Task.class);
        cr.addOrder(Order.asc("dueDate"));
        return cr.list();
    }

    /**
     * Checks if email already exists in the database.
     *
     * @param email
     * @return
     */
    public static boolean checkEmailExist(String email) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(User.class);
        List email1 = cr.add(Restrictions.eq("email", email)).list();
        if (email1.size() != 0) {
            return true;
        }
        return false;
    }


    /**
     * Validates login credentials.
     *
     * @param email
     * @param password
     * @return
     */
    public static boolean validateLogin(String email, String password) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(User.class);
        List emailLogin = cr.add(Restrictions.eq("email", email)).list();
        if (emailLogin.size() != 0) {
            List passwordLogin = cr.add(Restrictions.eq("password", password)).list();
            if (passwordLogin.size() != 0) {
                Menu.loginUser = (User) passwordLogin.get(0);
                return true;
            }
            return false;
        }
        return false;
    }

}
