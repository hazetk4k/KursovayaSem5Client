package client;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AuthorizationController extends BaseController {
    public Button btnLogIn;
    public Label labelLogin;
    public Label labelPassword;
    public TextField textLogin;
    public TextField textPassword;

    String answer = "";

    public void onBtnLogIn(ActionEvent actionEvent) {
        if (Objects.equals(textLogin.getText(), "") || Objects.equals(textPassword.getText(), "")) {
            this.textPassword.setText("");
            this.textLogin.setText("");
            this.textLogin.setPromptText("Заполните оба поля!");
        } else {
            PrintWriter out = null;
            BufferedReader in = null;
            String line;
            try {
                out = new PrintWriter(MySocket.INSTANCE.getSock().getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(MySocket.INSTANCE.getSock().getInputStream()));
                out.println("Авторизация" + "; " + textLogin.getText() + "; " + textPassword.getText());
                line = in.readLine();
                answer = line;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (line.equals("")) {
                textLogin.setText("");
                textPassword.setText("");
                textLogin.setPromptText("Не найден пользователь!");
            } else if (line.equals("1")) {
                nextWindow("MainFrame", btnLogIn, "Главное окно");
            } else if (line.equals("2")) {
                nextWindow("ManagerView", btnLogIn, "Главное окно");
            }


        }
    }
}
