
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

            int choice;
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
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf("%d. %s%n", i+1,tasks.get(i));
                    }
                    System.out.println("NOTE ⚠️: Tasks will bew removed from the starting numbers!");
                    try{
                        int taskNumber = Integer.parseInt(sc.nextLine());
                        if(taskNumber<1||taskNumber>tasks.size()){
                            System.out.println("Invalid no.of tasks! re-enter!");
                        }else{
                            tasks.remove(taskNumber-1);
                            System.out.println("Tasks are removed successfully!");
                        }

                    }catch (NumberFormatException e ){
                        System.out.println("Invalid input! Please enter a valid number.");
                    }
                    break;
                case 3:
                    if(tasks.isEmpty()) {
                        System.out.println("You have not entered any tasks to display, the list is empty.");
                        break;
                    }else {
                        System.out.println("\n Your tasks are listed below ⬇️");
                        for (int i = 0; i <tasks.size(); i++) {
                            System.out.printf("%d. %s%n",i+1,tasks.get(i));
                        }
                    }
                    break;
                case 4:
                    System.out.println("Good Bye!");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Hey you have entered invalid choice, please enter something in 1-4. ");

            }
        }
    }
}
