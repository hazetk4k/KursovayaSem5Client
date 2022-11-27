package client;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class MainFrameController extends BaseController {
    public Button btnExit;
    public Button btnCreate;
    public Button btnDelete;
    public Button btnAllQuality;
    public Button btnProductQuality;
    public Button btnEdit;
    public Button btnSaveTxt;
    public Button btnLoadTxt;
    public Button btnRefresh;
    public ListView<String> productArea = new ListView<>();

    public void onBtnRefresh(ActionEvent actionEvent) {
    }

    public void OnBtnLoadTxt(ActionEvent actionEvent) {
    }

    public void onBtnSaveTxt(ActionEvent actionEvent) {
    }

    public void onBtnEdit(ActionEvent actionEvent) {
    }

    public void onBtnProductQuality(ActionEvent actionEvent) {
    }

    public void onBtnAllQuality(ActionEvent actionEvent) {
    }

    public void onBtnDelete(ActionEvent actionEvent) {
    }

    public void onBtnCreate(ActionEvent actionEvent) {
        nextWindow("CreatingView", btnCreate, "Добавление новой модели");
    }

    public void onBtnExit(ActionEvent actionEvent){
        nextWindow("AuthView", btnExit, "Авторизация");
    }
}
