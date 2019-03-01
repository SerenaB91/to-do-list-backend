package se.kth.sda5.serena.todo.program;

import se.kth.sda5.serena.dto.Project;
import se.kth.sda5.serena.dto.Status;
import se.kth.sda5.serena.dto.Task;
import se.kth.sda5.serena.dto.User;
import se.kth.sda5.serena.hibernate.util.HibernateQuery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Menu {
    Scanner keyboard = new Scanner(System.in);
    User user = new User();
    Project project = new Project();
    Status status = new Status();
    Task task = new Task();

    public void run() throws ParseException {
        int command;


        do{
            command = chooseCommand();
            switch (command){
                case 10:    // Quit the program.
                    System.out.println("Bye!");
                    break;
                case 1:  //add a new user
                    List<User> userInfo = new ArrayList<User>();

                    System.out.println("Enter your email");
                    keyboard.nextLine();
                    String email = keyboard.nextLine();
                    user.setEmail(email);

                    System.out.println("Enter your first name");
                    String firstName = keyboard.nextLine();
                    user.setFirstName(firstName);

                    System.out.println("Enter your last name");
                    String lastName = keyboard.nextLine();
                    user.setLastName(lastName);

                    System.out.println("Enter your password");
                    String password = keyboard.nextLine();
                    user.setPassword(password);

                    user.setCreated(new Date());

                    userInfo.add(user);
                    HibernateQuery.addObject(User.class,user);

                    break;

                case 2:  //add a new project
                    List<Project> projectInfo = new ArrayList<Project>();

                    System.out.println("Enter the project name: ");
                    keyboard.nextLine();
                    String name = keyboard.nextLine();
                    project.setName(name);

                    System.out.println("Enter project description: ");
                    String description = keyboard.nextLine();
                    project.setDescription(description);

                    projectInfo.add(project);
                    HibernateQuery.addObject(Project.class,project);
                    break;

                case 3:  //add statuses
                    List<Status> statusInfo = new ArrayList<Status>();

                    System.out.println("Add a valid status to be used: ");
                    keyboard.nextLine();
                    String statusName = keyboard.nextLine();
                    status.setName(statusName);

                    statusInfo.add(status);
                    HibernateQuery.addObject(Status.class,status);
                    break;

                case 4:  //add task
                    List<Task> taskInfo = new ArrayList<Task>();
                    List<Project> allData = HibernateQuery.getAllData(Project.class);

                    //list of available projects
                    System.out.println("Choose a project from the list to associate the task with, enter project ID:");
                    for (Project p:allData) {
                        System.out.println(p.getId() + " " + p.getName());
                    }
                    boolean isNotSelect = true;
                    do {
                        System.out.println("your selection: ");
                        Integer selection = keyboard.nextInt();
                        for (Project project : allData) {
                            if (selection == project.getId()) {
                                task.setProject(project);
                                isNotSelect = false;
                                break;
                            }
                        }
                    }while (isNotSelect);

                    System.out.println("Enter a task name: ");
                    keyboard.nextLine();
                    String taskName = keyboard.nextLine();
                    task.setName(taskName);

                    System.out.println("Enter a task description: ");
                    String taskDescription = keyboard.nextLine();
                    task.setDescription(taskDescription);

                    task.setCreated(new Date());

                    System.out.println("please enter the due date (yyyy-MM-dd)");
                    String dateString = keyboard.next();
                    //System.out.println(dateString);
                    SimpleDateFormat  formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = formatter.parse(dateString);
                    //System.out.println(date);
                    task.setDueDate(date);

                    List<Status> statusData = HibernateQuery.getAllData(Status.class);
                    System.out.println("Add a status for this task (select from list below):");
                    for (Status s:statusData){
                        System.out.println(s.getName());
                    }
                    boolean isNotSelected = true;
                    do {
                        System.out.println("your selection: ");
                        keyboard.nextLine();
                        String selectionS = keyboard.nextLine();
                        for (Status status : statusData) {
                            if (selectionS.equals(status.getName())) {
                                task.setStatus(status);
                                isNotSelected = false;
                                break;
                            }
                        }
                    }while (isNotSelected);


                    taskInfo.add(task);
                    HibernateQuery.addObject(Task.class,task);
                    break;


                default:    // Unknown command
                    System.out.println("The command number " + command + " is unknown.");

            }
        }while(command != 10);
    }

    private int chooseCommand(){
        System.out.println("10. Quit.");
        System.out.println("1. Add new user");
        System.out.println("2. Create a project");
        System.out.println("3. Set a list of valid statuses");
        System.out.println("4. Create a task");
        /*System.out.println("5. Filter tasks by project");
        System.out.println("5. Sort tasks by due date");
        System.out.println("6. Edit tasks");
            System.out.println("1. update");
            System.out.println("2. remove");
            System.out.println("3. update status");*/

        System.out.print("Your choice: ");
        return keyboard.nextInt();
    }
}
