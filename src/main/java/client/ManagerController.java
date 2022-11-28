package client;

import client.ClassesForTables.Product;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ManagerController extends BaseController {
    public Button btnExit;
    public Button btnCreate;
    public Button btnAllQuality;
    public Button btnProductQuality;
    public Button btnSaveTxt;
    public Button btnLoadTxt;
    public Button btnRefresh;
    public TableView<Product> productTable;
    public TableColumn<Product, Integer> productID;
    public TableColumn<Product, String> productName;
    public TableColumn<Product, String> productFuel;
    public TableColumn<Product, String> productBattery;
    public TableColumn<Product, String> productCarcase;
    public TableColumn<Product, String> productChassis;
    public Button btnToFuel;
    public Button btnToBattery;
    public Button btnToCarcase;
    public Button btnToChassis;

    public void onBtnRefresh(ActionEvent actionEvent) {
    }

    public void OnBtnLoadTxt(ActionEvent actionEvent) {
    }

    public void onBtnSaveTxt(ActionEvent actionEvent) {
    }

    public void onBtnProductQuality(ActionEvent actionEvent) {
    }

    public void onBtnAllQuality(ActionEvent actionEvent) {
    }

    public void onBtnCreate(ActionEvent actionEvent) {
        nextWindow("CreatingView", btnCreate, "Добавление новой модели");
    }

    public void onBtnExit(ActionEvent actionEvent) {
        nextWindow("AuthView", btnExit, "Авторизация");
    }
}
