package client;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ProductQualityController extends BaseController {
    public ListView<String> listResults = new ListView<String>();
    public Button btnBack;
    public Button btnSaveResult;
    public Button btnGraph;

    public void onBtnBack(ActionEvent actionEvent) {
        nextWindow("MainFraim", btnBack, "Главное окно");
    }

    public void onBtnSaveResult(ActionEvent actionEvent) {
        btnSaveResult.setDisable(true);
    }

    public void onBtnGraph(ActionEvent actionEvent) {
    }
}
