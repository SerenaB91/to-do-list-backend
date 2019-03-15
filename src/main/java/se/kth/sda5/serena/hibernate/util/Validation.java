package se.kth.sda5.serena.hibernate.util;

import org.hibernate.validator.internal.constraintvalidators.EmailValidator;

import java.io.Console;

public class Validation {

    /**
     * Validates email pattern and checks if email is already in use.
     *
     * @param email
     * @return
     */
    public boolean validateEmail(String email) {
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
            return false;
        }
        return true;
    }

    /**
     * Hides password in terminal only, not in console.
     */
    public void passwordExample() {
        Console console = System.console();
        if (console == null) {
            System.out.println("Couldn't get Console instance");
            System.exit(0);
        }

        console.printf("Testing password%n");
        char passwordArray[] = console.readPassword("Enter your secret password: ");
        console.printf("Password entered was: %s%n", new String(passwordArray));

    }
}
