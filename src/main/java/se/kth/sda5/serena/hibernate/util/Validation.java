package se.kth.sda5.serena.hibernate.util;

import org.hibernate.validator.internal.constraintvalidators.EmailValidator;

public class Validation {
    public boolean validateEmail(String email){
        EmailValidator a = new EmailValidator();
        if (!a.isValid(email, null))
            return false;

       try {
            boolean emailExist = HibernateQuery.checkEmailExist(email);
            if (emailExist) {
                System.out.println("email already in use");
                return false;
            }
        } catch (Exception e) {
           System.out.println(e);
           return false;}
return true;
    }
}
