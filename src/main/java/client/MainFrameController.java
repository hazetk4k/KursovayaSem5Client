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
    ObservableList<Product> productList = FXCollections.observableArrayList();

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
        String modelDeleteId;
        modelDeleteId = String.valueOf(productTable.getSelectionModel().getSelectedItem().modelID);
        PrintWriter out = null;
        try {
            out = new PrintWriter(MySocket.INSTANCE.getSock().getOutputStream(), true);
            out.println("Удаление" + "; " + modelDeleteId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        productTable.getItems().clear();
        updateTable();
    }

    public void updateTable() {
        BufferedReader in = null;
        String line;
        try {
            in = new BufferedReader(new InputStreamReader(MySocket.INSTANCE.getSock().getInputStream()));
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

    public void onBtnCreate(ActionEvent actionEvent) {
        nextWindow("CreatingView", btnCreate, "Добавление новой модели");
    }

    public void onBtnExit(ActionEvent actionEvent) {
        nextWindow("AuthView", btnExit, "Авторизация");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productID.setCellValueFactory(new PropertyValueFactory<>("modelID"));
        productName.setCellValueFactory(new PropertyValueFactory<>("modelName"));
        productFuel.setCellValueFactory(new PropertyValueFactory<>("modelFuel"));
        productBattery.setCellValueFactory(new PropertyValueFactory<>("modelBattery"));
        productCarcase.setCellValueFactory(new PropertyValueFactory<>("modelCarcase"));
        productWheels.setCellValueFactory(new PropertyValueFactory<>("modelWheels"));
        PrintWriter out = null;
        try {
            out = new PrintWriter(MySocket.INSTANCE.getSock().getOutputStream(), true);
            out.println("Продукты");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        updateTable();
        productTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnDelete.setDisable(false);
                btnEdit.setDisable(false);
            } else {
                btnEdit.setDisable(true);
                btnDelete.setDisable(true);
            }
        });
    }
}
