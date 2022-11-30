package client;

import client.ClassesForTables.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainFrameController extends BaseController implements Initializable {
    public Button btnExit;
    public Button btnCreate;
    public Button btnDelete;
    public Button btnAllQuality;
    public Button btnProductQuality;
    public Button btnEdit;
    public Button btnSaveTxt;
    public Button btnLoadTxt;
    public Button btnRefresh;
    public TableView<Product> productTable;
    public TableColumn<Product, Integer> productID;
    public TableColumn<Product, String> productName;
    public TableColumn<Product, String> productFuel;
    public TableColumn<Product, String> productBattery;
    public TableColumn<Product, String> productCarcase;
    public TableColumn<Product, String> productWheels;
    public Button btnToFuel;
    public Button btnToBattery;
    public Button btnToCarcase;
    public Button btnToWheels;


    public void onBtnRefresh(ActionEvent actionEvent) {
        nextWindow("RegView", btnRefresh, "Регистрация нвого пользователя");
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

    public void onBtnExit(ActionEvent actionEvent) {
        nextWindow("AuthView", btnExit, "Авторизация");
    }

    ObservableList<Product> productList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("modelID"));
        productName.setCellValueFactory(new PropertyValueFactory<Product, String>("modelName"));
        productFuel.setCellValueFactory(new PropertyValueFactory<Product, String>("modelFuel"));
        productBattery.setCellValueFactory(new PropertyValueFactory<Product, String>("modelBattery"));
        productCarcase.setCellValueFactory(new PropertyValueFactory<Product, String>("modelCarcase"));
        productWheels.setCellValueFactory(new PropertyValueFactory<Product, String>("modelWheels"));
        PrintWriter out = null;
        BufferedReader in = null;
        String line;
        try {
            out = new PrintWriter(MySocket.INSTANCE.getSock().getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(MySocket.INSTANCE.getSock().getInputStream()));
            out.println("Продукты");
            line = in.readLine();
            if (!Objects.equals(line, "Нет данных")) {
                String[] models = line.split("///"); ///models = {поля, поля, поля}
                String[] model1;
                for (int i = 0; i < models.length; i++) {
                    model1 = models[i].split("; ");
                    Product product = new Product(Integer.parseInt(model1[0]), model1[1], model1[2], model1[3], model1[4], model1[5]);
                    productList.add(product);
                }
                productTable.setItems(productList);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
