package service;

import model.UserInput;

import java.io.IOException;

public interface UserInputService {
    UserInput getInputFromUser(boolean isSecondTask, String fileSrc) throws IOException;

    UserInput getInputFromFile(String fileSrc) throws IOException;

    UserInput getInputFromKeyboard(boolean isSecondTask);
}
