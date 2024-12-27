import java.util.ArrayList;
import java.util.Scanner;

public class TaskListApp {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        Scanner sc= new Scanner(System.in);

        while(true){
            System.out.println("<------ Task List Application ------>");
            System.out.println("1. Add a Task");
            System.out.println("2. Remove a Task");
            System.out.println("3. List All Tasks");
            System.out.println("4. Exit");
            System.out.println("Choose an Option ( 1 - 4 )");

            int choice:
            try{
                choice = Integer.parseInt(sc.nextLine());
            }catch (NumberFormatException e ){
                System.out.println("invalid Input! please enter the correct option: ");
                continue;
            }
            switch (choice){
                case 1:
                    System.out.println("Enter the Task Details : " );
                    String task = sc.nextLine();
                    tasks.add(task);
                    System.out.println("Task has been added successfully!");
                    break;
                case 2:
                    if(tasks.isEmpty()){
                        System.out.println("The Task list is empty!");
                        break;
                    }
                    System.out.println("Enter the number of Tasks to remove");
            }
        }
    }
}
