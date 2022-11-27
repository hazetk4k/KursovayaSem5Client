package client;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CreatingController extends BaseController {
    public ComboBox<String> textFuel;
    public ComboBox<String> textBattery;
    public ComboBox<String> textCarcase;
    public ComboBox<String> textChassis;
    public Button btnSubmit;
    public TextField textName;
    public Button btnBack;

    public void onBtnSubmit(ActionEvent actionEvent) {
    }

    public void onBtnBack(ActionEvent actionEvent) {
        nextWindow("MainFrame", btnBack, "Главное окно");
    }
}
