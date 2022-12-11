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

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainFrameController extends BaseController implements Initializable {
    public Button btnExit;
    public Button btnCreate;
    public Button btnDelete;
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

    public void OnBtnLoadTxt(ActionEvent actionEvent) throws IOException {
        ObservableList<Product> strList = FXCollections.observableArrayList();
        FileReader fr = new FileReader("D:/уник/5 сем/псп/TkachukKursovayaClient/Products/Products.txt");
        Scanner scan = new Scanner(fr);
        while (scan.hasNextLine()) {
            String str = scan.nextLine();
            String[] thisStr = str.split("; ");
            Product product = new Product(Integer.parseInt(thisStr[0]), thisStr[1], thisStr[2], thisStr[3], thisStr[4], thisStr[5]);
            strList.add(product);
            productTable.setItems(strList);
        }
        fr.close();
        btnSaveTxt.setDisable(true);
        btnProductQuality.setDisable(true);
        btnLoadTxt.setDisable(true);
    }
    public void onBtnSaveTxt(ActionEvent actionEvent) {
        try {
            File f = new File("D:/уник/5 сем/псп/TkachukKursovayaClient/Products/Products.txt");
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(f, false);
            for (int i = 0; i < productList.size(); i++) {
                fw.write(productList.get(i).getModelID() + "; " + productList.get(i).getModelName() + "; " +
                        productList.get(i).getModelFuel() + "; " + productList.get(i).getModelBattery() + "; " +
                        productList.get(i).getModelCarcase() + "; " + productList.get(i).getModelWheels() + "; \n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        btnSaveTxt.setDisable(true);
    }

    public void onBtnProductQuality(ActionEvent actionEvent) {
        BaseController.chosenID = String.valueOf(productTable.getSelectionModel().getSelectedItem().modelID);
        nextWindow("ProductQualityView", btnProductQuality, "Отчет по уровню качества продукта");
    }

    public void onBtnEdit(ActionEvent actionEvent) {
        BaseController.chosenID = String.valueOf(productTable.getSelectionModel().getSelectedItem().modelID);
        nextWindow("UpdateProduct", btnEdit, "Изменение продукта");
    }

    public void onBtnRefresh(ActionEvent actionEvent) {
        nextWindow("RegView", btnRefresh, "Регистрация нвого пользователя");
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
                btnProductQuality.setDisable(false);
            } else {
                btnEdit.setDisable(true);
                btnDelete.setDisable(true);
                btnProductQuality.setDisable(true);
            }
        });
    }

    public void onBtnToFuel(ActionEvent actionEvent) {
        BaseController.detail = "fuel";
        nextWindow("Detail",btnToFuel, "Модели топливной системы");
    }

    public void OnBtnToBattery(ActionEvent actionEvent) {
        BaseController.detail = "battery";
        nextWindow("Detail",btnToBattery, "Модели аккумулятора");
    }

    public void OnBtnToCarcase(ActionEvent actionEvent) {
        BaseController.detail = "carcase";
        nextWindow("Detail",btnToCarcase, "Модели кузова");
    }

    public void OnBtnToWheels(ActionEvent actionEvent) {
        BaseController.detail = "wheels";
        nextWindow("Detail",btnToWheels, "Модели колес");
    }
}
