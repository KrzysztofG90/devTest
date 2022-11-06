package service;

import model.UserInput;

import java.io.IOException;

public interface UserInputService {
    UserInput getInputFromUser(boolean isSecondTask) throws IOException;

    UserInput getInputFromFile() throws IOException;

    UserInput getInputFromKeyboard(boolean isSecondTask);
}
