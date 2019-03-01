package se.kth.sda5.serena.hibernate.util;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.kth.sda5.serena.dto.*;

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
}
