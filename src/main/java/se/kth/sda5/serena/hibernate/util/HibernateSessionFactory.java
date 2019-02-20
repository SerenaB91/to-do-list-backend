package se.kth.sda5.serena.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import se.kth.sda5.serena.dto.*;

public class HibernateSessionFactory {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml)
            // config file.
            sessionFactory = new AnnotationConfiguration().configure()
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Project.class)
                    .addAnnotatedClass(Status.class)
                    .addAnnotatedClass(Task.class)
                    .addAnnotatedClass(Member.class)
                    .addAnnotatedClass(Subtask.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception.
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}
