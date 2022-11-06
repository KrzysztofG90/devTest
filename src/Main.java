import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        UserInputService userInputService = new UserInputServiceImpl();
        boolean isChoosingType = true;

        while (isChoosingType) {
            System.out.println("Select an input type for numbers:");
            System.out.println("1. Enter '1' for input from keyboard");
            System.out.println("2. Enter '2' for input from file");
            String choice = scanner.nextLine();
            if (choice.matches("[0-9]+")) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                    case 2:
                        userInputService.printPairsOfGivenSum(Integer.parseInt(choice));
                        isChoosingType = false;
                        break;
                    default:
                        System.out.println("There is no such choice!");
                        break;
                }
            } else {
                System.out.println("Provide only numbers!");
            }
        }
    }
}
