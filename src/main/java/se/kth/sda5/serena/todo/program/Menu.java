package se.kth.sda5.serena.todo.program;

import se.kth.sda5.serena.dto.*;
import se.kth.sda5.serena.hibernate.util.HibernateQuery;
import se.kth.sda5.serena.hibernate.util.Validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Menu {
    Scanner keyboard = new Scanner(System.in);
    User user = new User();
    Project project = new Project();
    Status status = new Status();
    Task task = new Task();
    Subtask subtask = new Subtask();
    Member member = new Member();
    Validation validation = new Validation();

    public static User loginUser;

    List<Project> allData = HibernateQuery.getAllData(Project.class);
    List<Status> statusData = HibernateQuery.getAllData(Status.class);
    List<Task> taskData = HibernateQuery.getAllData(Task.class);
    List<User> userData = HibernateQuery.getAllData(User.class);

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     * Menu functionality for todo list
     *
     * @throws ParseException
     */
    public void run() throws ParseException {
        int command;
        //if a user already exists in database then, if not then create user
        if (!userData.isEmpty()) {
            System.out.println("Welcome back, login to your account: ");
            boolean emailValid = false;
            do {
                System.out.println("enter your email (example@something.com): ");
                String emailLogin = keyboard.nextLine();
                System.out.println("enter you password: ");
                String passwordLogin = keyboard.nextLine();
                boolean loginAccepted = HibernateQuery.validateLogin(emailLogin, passwordLogin);
                if (loginAccepted) {
                    do {
                        command = chooseCommand();
                        switch (command) {
                            case 10:    // Quit the program.
                                System.out.println("Bye!");
                                break;
                            case 1:  //add a new user
                                if (loginUser.getRole().equals("1")) {
                                    List<User> userInfo = new ArrayList<User>();

                                    boolean isValid = false;
                                    do {
                                        System.out.println("Enter your email (example@something.com):");
                                        keyboard.nextLine();
                                        String email = keyboard.nextLine();
                                        if (validation.validateEmail(email)) {
                                            user.setEmail(email);
                                            isValid = true;
                                        }
                                    } while (!isValid);

                                    System.out.println("Enter your first name:");
                                    String firstName = keyboard.nextLine();
                                    user.setFirstName(firstName);

                                    System.out.println("Enter your last name:");
                                    String lastName = keyboard.nextLine();
                                    user.setLastName(lastName);

                                    System.out.println("Enter your password:");
                                    String password = keyboard.nextLine();
                                    user.setPassword(password);

                                    System.out.println("What is this user's role? Enter 1 for administrator, 2 for project manager or 3 for employee: ");
                                    String projOwner = keyboard.nextLine();
                                    user.setRole(projOwner);

                                    user.setCreated(new Date());

                                    userInfo.add(user);
                                    HibernateQuery.addObject(User.class, user);

                                } else {
                                    System.out.println("You do not have access to this menu option.");
                                }
                                break;

                            case 2:  //add a new project
                                if (loginUser.getRole().equals("2")) {
                                    List<Project> projectInfo = new ArrayList<Project>();

                                    System.out.println("Enter the project name: ");
                                    keyboard.nextLine();
                                    String name = keyboard.nextLine();
                                    project.setName(name);

                                    System.out.println("Enter project description: ");
                                    String description = keyboard.nextLine();
                                    project.setDescription(description);

                                    //associate user to the project
                                    System.out.println("Choose a user to associate with this project, enter user ID from the list: ");
                                    userData = HibernateQuery.getAllData(User.class);
                                    for (User u : userData) {
                                        System.out.println(u.getId() + " " + u.getFirstName() + " " + u.getLastName());
                                    }
                                    boolean userNotSelected1 = true;
                                    do {
                                        System.out.println("your selection: ");
                                        Integer userSelection = keyboard.nextInt();

                                        for (User user : userData) {
                                            if (userSelection == user.getId()) {
                                                project.setUser(user);
                                                member.setUser(user);
                                                userNotSelected1 = false;
                                                break;
                                            }
                                        }
                                    } while (userNotSelected1);

                                    project.setCreated(new Date());

                                    System.out.println("is this user the project owner? enter true or false: ");
                                    boolean isOwner = keyboard.nextBoolean();
                                    member.setOwner(isOwner);


                                    projectInfo.add(project);
                                    member.setProject(project);
                                    HibernateQuery.addObject(Project.class, project);
                                    HibernateQuery.addObject(Member.class, member);
                                } else {
                                    System.out.println("You do not have access to this menu option.");
                                }
                                break;

                            case 3:  //add statuses
                                if (loginUser.getRole().equals("1")) {
                                    List<Status> statusInfo = new ArrayList<Status>();

                                    System.out.println("Add a valid status  to be used by project managers and employees: ");
                                    keyboard.nextLine();
                                    String statusName = keyboard.nextLine();
                                    status.setName(statusName);

                                    statusInfo.add(status);
                                    HibernateQuery.addObject(Status.class, status);
                                } else {
                                    System.out.println("You do not have access to this menu option.");
                                }
                                break;

                            case 4:  //add task
                                if (loginUser.getRole().equals("2") || loginUser.getRole().equals("3")) {
                                    List<Task> taskInfo = new ArrayList<Task>();
                                    //list of available projects
                                    System.out.println("Choose a project from the list to associate the task with, enter project ID:");
                                    allData = HibernateQuery.getAllData(Project.class);

                                    for (Project p : allData) {
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
                                    } while (isNotSelect);

                                    //associate user to task
                                    System.out.println("Choose a user to associate with this task, enter user ID from the list: ");
                                    userData = HibernateQuery.getAllData(User.class);
                                    for (User u : userData) {
                                        System.out.println(u.getId() + " " + u.getFirstName() + " " + u.getLastName());
                                    }
                                    boolean userNotSelected = true;
                                    do {
                                        System.out.println("your selection: ");
                                        Integer userSelection = keyboard.nextInt();

                                        for (User user : userData) {
                                            if (userSelection == user.getId()) {
                                                task.setUser(user);
                                                userNotSelected = false;
                                                break;
                                            }
                                        }
                                    } while (userNotSelected);

                                    System.out.println("Enter a task name: ");
                                    keyboard.nextLine();
                                    String taskName = keyboard.nextLine();
                                    task.setName(taskName);

                                    System.out.println("Enter a task description: ");
                                    String taskDescription = keyboard.nextLine();
                                    task.setDescription(taskDescription);

                                    task.setCreated(new Date());

                                    System.out.println("Enter days required to finish task: ");
                                    int numberOfDays = keyboard.nextInt();

                                    Date currentDate = new Date();
                                    System.out.println("Today's date: " + dateFormat.format(currentDate));

                                    // convert date to calendar
                                    Calendar c = Calendar.getInstance();
                                    c.setTime(currentDate);

                                    // manipulate date
                                    c.add(Calendar.DAY_OF_MONTH, numberOfDays);

                                    // convert calendar to date
                                    Date currentDatePlus = c.getTime();

                                    System.out.println("Due date: " + dateFormat.format(currentDatePlus));

                                    task.setDueDate(currentDatePlus);


                                    System.out.println("Add a status for this task, enter status ID from list below:");
                                    statusData = HibernateQuery.getAllData(Status.class);
                                    statusData.stream().forEach(System.out::println);

                                    boolean isNotSelected = true;
                                    do {
                                        System.out.println("your selection (by ID): ");
                                        keyboard.nextLine();
                                        int selectionS = keyboard.nextInt();

                                        Status statusById = (Status) HibernateQuery.getObjectById(Status.class, selectionS);
                                        if (statusById != null && statusById.getId() > 0) {
                                            task.setStatus(statusById);
                                            isNotSelected = false;
                                            break;
                                        }
                                    } while (isNotSelected);

                                    taskInfo.add(task);
                                    HibernateQuery.addObject(Task.class, task);
                                } else {
                                    System.out.println("You do not have access to this menu option.");
                                }
                                break;

                            case 5:  //create a subtask
                                if (loginUser.getRole().equals("2") || loginUser.getRole().equals("3")) {
                                    List<Subtask> subtaskInfo = new ArrayList<Subtask>();
                                    //list of available tasks
                                    System.out.println("Choose a task from the list to associate the subtask with, enter task ID:");
                                    taskData = HibernateQuery.getAllData(Task.class);
                                    taskData.stream().forEach(System.out::println);

                                    boolean taskNotSelected = true;
                                    do {
                                        System.out.println("your selection: ");
                                        Integer selection = keyboard.nextInt();

                                        Task taskById = (Task) HibernateQuery.getObjectById(Task.class, selection);
                                        if (taskById != null) {
                                            subtask.setTask(taskById);
                                            taskNotSelected = false;
                                            break;
                                        }

                                    } while (taskNotSelected);

                                    System.out.println("Enter a subtask name: ");
                                    keyboard.nextLine();
                                    String subaskName = keyboard.nextLine();
                                    subtask.setName(subaskName);

                                    subtask.setCreated(new Date());

                                    System.out.println("Enter days required to finish task: ");
                                    int numOfDays = keyboard.nextInt();

                                    Date todayDate = new Date();
                                    System.out.println("Today's date: " + dateFormat.format(todayDate));

                                    // convert date to calendar
                                    Calendar cl = Calendar.getInstance();
                                    cl.setTime(todayDate);

                                    // manipulate date
                                    cl.add(Calendar.DAY_OF_MONTH, numOfDays);

                                    // convert calendar to date
                                    Date todayDatePlus = cl.getTime();

                                    System.out.println("Due date: " + dateFormat.format(todayDatePlus));

                                    subtask.setDueDate(todayDatePlus);

                                    System.out.println("Add a status with one of the following: ");
                                    statusData.stream().forEach(System.out::println);
                                    boolean noSelection = true;
                                    do {
                                        System.out.println("your selection (enter ID): ");
                                        keyboard.nextLine();

                                        int statusSelection = keyboard.nextInt();

                                        Status statusById = (Status) HibernateQuery.getObjectById(Status.class, statusSelection);

                                        if (statusById != null && statusById.getId() > 0) {
                                            subtask.setStatus(statusById);
                                            noSelection = false;
                                            break;
                                        }
                                    } while (noSelection);

                                    subtaskInfo.add(subtask);
                                    HibernateQuery.addObject(Subtask.class, subtask);
                                } else {
                                    System.out.println("You do not have access to this menu option.");
                                }
                                break;

                            case 6:  //filter tasks by project
                                if (loginUser.getRole().equals("2") || loginUser.getRole().equals("3")) {
                                    System.out.println("Filter tasks list by project");
                                    System.out.println("Select a project to view its tasks: ");

                                    allData = HibernateQuery.getAllData(Project.class);
                                    for (Project p : allData) {
                                        System.out.println(p.getId() + " " + p.getName());
                                    }
                                    boolean notSelect = true;
                                    do {
                                        System.out.println("your selection: ");
                                        Integer selection = keyboard.nextInt();
                                        Project projectById = (Project) HibernateQuery.getObjectById(Project.class, selection);
                                        if (projectById != null) {
                                            List<Task> byProID = HibernateQuery.getByProID(projectById);
                                            if (byProID != null && byProID.size() > 0) {
                                                byProID.stream().forEach(System.out::println);
                                                notSelect = false;
                                            }
                                        }
                                    } while (notSelect);
                                } else {
                                    System.out.println("You do not have access to this menu option.");
                                }
                                break;

                            case 7:  //sort tasks  by due date
                                if (loginUser.getRole().equals("2") || loginUser.getRole().equals("3")) {
                                    System.out.println("Tasks filtered by due date: ");
                                    List<Task> byDate = HibernateQuery.getByDate(task);
                                    for (Task task : byDate) {
                                        System.out.println(task.getId() + " " + task.getName() + " " + task.getProject().getName() + " " + task.getDueDate().toString().split(" ")[0]);
                                    }
                                } else {
                                    System.out.println("You do not have access to this menu option.");
                                }
                                break;

                            case 8:  //filter projects and tasks by user
                                if (loginUser.getRole().equals("2")) {
                                    System.out.println("Please select a user to view its tasks and projects: ");
                                    userData = HibernateQuery.getAllData(User.class);
                                    for (User u : userData) {
                                        System.out.println(u.getId() + " " + u.getFirstName() + " " + u.getLastName());
                                    }
                                    boolean noUserSelect = true;
                                    do {
                                        System.out.println("your selection: ");
                                        Integer uSelection = keyboard.nextInt();
                                        User userById = (User) HibernateQuery.getObjectById(User.class, uSelection);
                                        if (userById != null) {
                                            List<Task> byUserID = HibernateQuery.getByUserID(userById);
                                            if (byUserID != null && byUserID.size() > 0) {
                                                for (Task task : byUserID) {
                                                    System.out.println(task.getUser().getFirstName() + "'s tasks: ");
                                                    System.out.println("task: " + task.getName() + ", task description: " + task.getDescription() + ", due: " + task.getDueDate().toString().split(" ")[0] + ", project: " + task.getProject().getName());
                                                }
                                                noUserSelect = false;
                                            }
                                        }
                                    } while (noUserSelect);

                                } else {
                                    System.out.println("You do not have access to this menu option.");
                                }
                                break;

                            case 9: //edit tasks
                                if (loginUser.getRole().equals("2") || loginUser.getRole().equals("3")) {
                                    System.out.println("Choose a task to edit by entering the task id: ");
                                    taskData = HibernateQuery.getAllData(Task.class);
                                    for (Task t : taskData) {
                                        System.out.println(t.getId() + " " + t.getName() + " " + t.getDescription() + " " + t.getStatus().getName() + " " + t.getCreated().toString().split(" ")[0] + " " + t.getDueDate().toString().split(" ")[0]);
                                    }

                                    System.out.print("Your choice: ");
                                    Integer select = keyboard.nextInt();

                                    Task task1 = (Task) HibernateQuery.getObjectById(Task.class, select);


                                    System.out.println("What do you want to do: ");
                                    System.out.println("1. update task status");
                                    System.out.println("2. remove");

                                    System.out.print("Your choice: ");
                                    Integer selection = keyboard.nextInt();
                                    if (selection == 1) {
                                        System.out.println("Update the status with one of the following: ");
                                        statusData.stream().forEach(System.out::println);

                                        boolean statusNoSelection = true;
                                        do {
                                            System.out.println("your selection: by ID");
                                            keyboard.nextLine();

                                            int statusSelection = keyboard.nextInt();

                                            Status statusById = (Status) HibernateQuery.getObjectById(Status.class, statusSelection);

                                            if (statusById != null && statusById.getId() > 0) {
                                                task1.setStatus(statusById);
                                                HibernateQuery.updateStatus(task1);
                                                statusNoSelection = false;
                                                break;
                                            }
                                        } while (statusNoSelection);
                                    } else if (selection == 2) {
                                        HibernateQuery.deleteTask(task1.getId());
                                        break;
                                    }
                                } else {
                                    System.out.println("You do not have access to this menu option.");
                                }
                                break;


                            default:    // Unknown command
                                System.out.println("The command number " + command + " is unknown.");


                        }
                    } while (command != 10);
                    emailValid = true;
                } else {
                    System.out.println("credentials not valid");
                }

            } while (!emailValid);

        } else {

            System.out.println("Welcome, create an account: ");
            List<User> newUserInfo = new ArrayList<User>();

            boolean isValid = false;
            do {
                System.out.println("Enter your email");
                String email = keyboard.nextLine();
                if (validation.validateEmail(email)) {
                    user.setEmail(email);
                    isValid = true;
                }
            } while (!isValid);

            System.out.println("Enter your first name");
            String firstName = keyboard.nextLine();
            user.setFirstName(firstName);

            System.out.println("Enter your last name");
            String lastName = keyboard.nextLine();
            user.setLastName(lastName);

            System.out.println("Enter your password");
            String password = keyboard.nextLine();
            user.setPassword(password);

            System.out.println("Is this user a project owner? enter 1 for administrator, 2 for project manager or 3 for employee: ");
            String projOwner = keyboard.nextLine();
            user.setRole(projOwner);

            user.setCreated(new Date());

            newUserInfo.add(user);
            HibernateQuery.addObject(User.class, user);
        }

    }

    private int chooseCommand() {
        System.out.println("10. Quit.");
        if (loginUser.getRole().equals("1")) {
            System.out.println("1. Add new user");
            System.out.println("3. Set a list of valid statuses");
        } else if (loginUser.getRole().equals("2")) {
            System.out.println("2. Create a project");
            System.out.println("4. Create a task");
            System.out.println("5. Create a subtask");
            System.out.println("6. Filter tasks by project");
            System.out.println("7. Sort tasks by due date");
            System.out.println("8. Filter projects and tasks by user");
            System.out.println("9. Edit tasks");
        } else if (loginUser.getRole().equals("3")) {
            System.out.println("4. Create a task");
            System.out.println("5. Create a subtask");
            System.out.println("6. Filter tasks by project");
            System.out.println("7. Sort tasks by due date");
            System.out.println("9. Edit tasks");
        }
        System.out.print("Your choice: ");
        return keyboard.nextInt();
    }


}
