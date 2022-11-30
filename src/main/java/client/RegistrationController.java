package client;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Objects;

public class RegistrationController extends BaseController{
    public Button btnSubmit;
    public TextField textLogin;
    public TextField textPassword;
    public CheckBox checkAcLevel;
    public Button btnBack;

    String check = "";

    String answer = "";

    public void onBtnSubmit(ActionEvent actionEvent) {
        if (Objects.equals(textLogin.getText(), "") || Objects.equals(textPassword.getText(), "")) {
            textPassword.setText("");
            textLogin.setText("");
            textLogin.setPromptText("Заполните оба поля!");
        } else {
            PrintWriter out = null;
            BufferedReader in = null;
            String line;
            try {
                if (checkAcLevel.isSelected()) {
                    check = "1";
                } else {
                    check = "2";
                } ;
                out = new PrintWriter(MySocket.INSTANCE.getSock().getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(MySocket.INSTANCE.getSock().getInputStream()));
                out.println("Регистрация" + "; " + textLogin.getText() + "; " + textPassword.getText() + "; " + check);
                line = in.readLine();
                answer = line;
                if (Objects.equals(answer, "added")){
                    textPassword.setText("");
                    textLogin.setText("");
                    textLogin.setPromptText("Запись добавлена!");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onBtnBack(ActionEvent actionEvent) {
        nextWindow("MainFrame", btnBack, "Главное окно");
    }
}
