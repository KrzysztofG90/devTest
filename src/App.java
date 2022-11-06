import service.TaskService;
import service.TaskServiceImpl;

import java.io.IOException;
import java.util.Scanner;

public class App {
    private TaskService taskService = new TaskServiceImpl();

    public void selectTheTask() throws IOException {
        boolean isChoosingType = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------------------");
        System.out.println("Select the appropriate task by entering the correct number.");
        System.out.println("Enter 'exit' to close app.");
        System.out.println("--------------------------------------");
        while (isChoosingType) {
            System.out.println("---------- Main menu -----------------");
            System.out.println("Select the task:");
            System.out.println("1. Enter '1' for the first task.");
            System.out.println("2. Enter '2' for the second task.");
            System.out.println("2. Enter '3' for the third task.");
            System.out.println("--------------------------------------");
            String choice = scanner.nextLine();
            if (choice.matches("[0-9]+")) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        taskService.printSpecificInfoTask1();
                        break;
                    case 2:
                        taskService.printPairsOfGivenSumTask2();
                        break;
                    case 3:
                        taskService.printNumberOfGraphsTask3();
                        break;
                    default:
                        System.out.println("There is no such choice!");
                        break;
                }
            } else {
                if (choice.equals("exit")) {
                    isChoosingType = false;
                    System.exit(0);
                }
                else System.out.println("Provide only numbers!");
            }
        }
    }
}
