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
    }
}
