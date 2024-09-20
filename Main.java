import java.util.Date;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static String[] mainMenuItems = new String[]{"Login as student", "Login as teacher", "Register", "Exit"};
    private static String[] studentMenuItems = new String[]{"Read Blogs", "Add Attendance", "Eat Lunch", "Go back to main menu"};
    private static String[] teacherMenuItems = new String[]{"Add Blog", "Update Blog", "Review Attendance List", "Review Lunch List", "Go back to main menu"};

    private static String[] attendanceList = new String[100];
    private static int attendanceCount = 0;

    private static String[] lunchList = new String[100];
    private static int lunchCount = 0;

    private static String[] blogTitles = new String[100];
    private static String[] blogContents = new String[100];
    private static int blogCount = 0;

    private static int[] userTypes = new int[100];
    private static String[] userNames = new String[100];
    private static String[] userPasswords = new String[100];
    private static int userCount = 0;

    public static void main(String[] args) {
        mainMenu();
    }
    static void printMenuItems(String[] items){
        for(int i = 0 ; i < items.length; i++){
            System.out.println((i+1) +"- " + items[i]);
        }
    }

    static void register(){
        System.out.print("User type (1-Teacher, 2-Student):");
        int type = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Username:");
        String username = scanner.nextLine();
        System.out.print("Password:");
        String password = scanner.nextLine();

        //System.out.println(type + " " + username + " " + password);
        userTypes[userCount] = type;
        userNames[userCount] = username;
        userPasswords[userCount] = password;
        userCount++;

    }

    static String login(int type){
        System.out.print("Username:");
        String username = scanner.nextLine();
        System.out.print("Password:");
        String password = scanner.nextLine();

        for(int i = 0; i < userCount; i++){
            if(userTypes[i] == type && userNames[i].equals(username) && userPasswords[i].equals(password)){
              System.out.println("Login Successful Welcome "+username +"!");
              return username;
            }
        }
        return null;
    }

    static void addAttendance(String username){
        String dateString = new Date().toString();

        attendanceList[attendanceCount] = username + " -> " +dateString;
        attendanceCount++;

        System.out.println("Thank you for adding attendance!");
    }

    static void eatLunch(String username){
        String dateString = new Date().toString();
        System.out.print("What is your meal : ");
        String meal = scanner.nextLine();

        lunchList[lunchCount] = username + " ate " + meal + " -> " + dateString;
        lunchCount++;
    }

    static void printBlogTitles(){
        for (int i = 0 ; i < blogCount;i++){
            System.out.println((i+1) + "- " + blogTitles[i]);
        }
    }

    static void printBlog(int blogIndex){
        if(blogIndex >= blogCount  || blogIndex < 0){
            System.out.println("Wrong index!");
            return;
        }

        System.out.println(blogTitles[blogIndex] + "\n\n");
        System.out.println(blogContents[blogIndex]);

    }

    static void readBlogs(){
        printBlogTitles();

        System.out.print("Which blog would you like to read :");
        int choice = scanner.nextInt();
        scanner.nextLine();

        printBlog(choice - 1);

    }

    static void studentMenu(String username){
        while (true){
            printMenuItems(studentMenuItems);

            System.out.print("Please choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    readBlogs();
                    break;
                case 2:
                    addAttendance(username);
                    break;
                case 3:
                    eatLunch(username);
                    break;
                case 4:
                    System.out.println("Thank you for visiting our school, Going back to main menu");
                    return;
                default:
                    System.out.println("Wrong Choice");
            }
        }
    }

    static void reviewAttendanceList(){
        for (int i = 0 ; i < attendanceCount; i++){
            System.out.println(attendanceList[i]);
        }
    }

    static void reviewLunchList(){
        for (int i = 0 ; i < lunchCount; i++){
            System.out.println(lunchList[i]);
        }
    }

    static void addBlog(){
        System.out.print("Blog Title : ");
        String title = scanner.nextLine();

        System.out.print("Content : ");
        String content = scanner.nextLine();

        blogTitles[blogCount] = title;
        blogContents[blogCount] = content;
        blogCount++;

        System.out.println("Blog added Successfully");
    }

    static void updateBlog(){
        printBlogTitles();

        System.out.print("Which blog you want to change : ");
        int blogIndex = scanner.nextInt();
        System.out.print("Do you want to change title(1,0) : ");
        int changeTitle = scanner.nextInt();
        System.out.print("Do you want to change content(1,0) : ");
        int changeContent = scanner.nextInt();
        scanner.nextLine();

        if(changeTitle == 1){
            System.out.print("Blog Title : ");
            String title = scanner.nextLine();
            blogTitles[blogIndex-1] = title;
        }
        if(changeContent == 1) {
            System.out.print("Content : ");
            String content = scanner.nextLine();
            blogContents[blogIndex-1] = content;
        }

        System.out.println("Blog updated Successfully");
    }

    static void teacherMenu(String username){
        while (true){
            printMenuItems(teacherMenuItems);

            System.out.print("Please choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    addBlog();
                    break;
                case 2:
                    updateBlog();
                    break;
                case 3:
                    reviewAttendanceList();
                    break;
                case 4:
                    reviewLunchList();
                    break;
                case 5:
                    System.out.println("Thank you for visiting our school, Going back to main menu");
                    return;
                default:
                    System.out.println("Wrong Choice");
            }
        }
    }

    static void mainMenu(){
        while (true){
            printMenuItems(mainMenuItems);

            System.out.print("Please choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            String username;
            switch (choice){
                case 1:
                    username = login(2);
                    if(username != null){
                        studentMenu(username);
                    }
                    else{
                        System.out.println("Wrong credentials");
                    }
                    break;
                case 2:
                    username = login(1);
                    if(username != null){
                        teacherMenu(username);
                    }
                    else{
                        System.out.println("Wrong credentials");
                    }
                    break;
                case 3:
                    register();
                    break;
                case 4:
                    System.out.println("Thank you for visiting our school, Good by");
                    return;
                default:
                    System.out.println("Wrong Choice");
            }
        }

    }
}
/*
  Main Menu:
   1. Login as User
      -Prompt for a username and password.
      -If the credentials are correct, proceed to the User Menu.
      -If the credentials are incorrect, return to the Main Menu.
   2. Login as Teacher
      -Prompt for a username and password.
      -If the credentials are correct, proceed to the Teacher Menu.
      -If the credentials are incorrect, return to the Main Menu.
   3. Register
      -Prompt for a username, password, and user type (User or Teacher).
      -Register the new user with the provided details.
      -Return to the Main Menu.
   4. Exit
    -Close the application.


   Student Menu:
   1.Read Blogs
      -Display a list of all available blog titles.
      -Allow the user to select and read the content of any blog.
      -Return to the User Menu.
   2.Add Attendance
      -Record the user's attendance by saving their username and the current date to the attendance list.
      -Confirm the attendance has been added.
      -Return to the User Menu.
   3.Eat Lunch
      -Allow the user to record their lunch details by entering the meal name.
      -Save the username, meal name, and current date to the lunch list.
      -Confirm the lunch has been recorded.
      -Return to the User Menu.
   4.Return to Main Menu
      -Navigate back to the Main Menu.
        Teacher Menu:
   1.Add Blog
      -Prompt for a blog title and content.
      -Add the new blog to the blog list.
      -Confirm the blog has been added.
      -Return to the Teacher Menu.
   2.Update Blog
      -Display a list of all blog titles, each with a corresponding number.
      -Allow the teacher to select a blog by entering the number.
      -Prompt the teacher to either change the title or the content of the selected blog.
      -Update the blog accordingly.
      -Confirm the blog has been updated.
      -Return to the Teacher Menu.
   3.Review Attendance List
      -Display the attendance list with each entry showing the username and date of attendance.
      -Return to the Teacher Menu.
   4.Review Lunch List
      -Display the lunch list with each entry showing the username, meal name, and date.
      -Return to the Teacher Menu.
   5.Return to Main Menu
      -Navigate back to the Main Menu.
*/

