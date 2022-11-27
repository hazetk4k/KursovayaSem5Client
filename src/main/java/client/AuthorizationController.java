package client;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AuthorizationController extends BaseController{
    public Button btnLogIn;
    public Label labelLogin;
    public Label labelPassword;
    public TextField textLogin;
    public TextField textPassword;

    public void onBtnLogIn(ActionEvent actionEvent) {
        nextWindow("MainFrame", btnLogIn, "Главное окно");
    }
}
