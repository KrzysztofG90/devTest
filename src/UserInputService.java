import model.UserInput;
import java.io.IOException;

public interface UserInputService {
    UserInput getInputFromFile() throws IOException;
    UserInput getInputFromKeyboard();
    void printPairsOfGivenSum(int choice) throws IOException;
}
