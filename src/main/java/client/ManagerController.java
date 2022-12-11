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

public class ManagerController extends BaseController implements Initializable {
    public Button btnExit;
    public Button btnSaveTxt;
    public Button btnLoadTxt;
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

    public void onBtnToFuel(ActionEvent actionEvent) {
        BaseController.detail = "fuel";
        nextWindow("Detail",btnToFuel, "Модели топливной системы");
    }

    public void onBtnToBattery(ActionEvent actionEvent) {
        BaseController.detail = "battery";
        nextWindow("Detail",btnToBattery, "Модели аккумулятора");
    }

    public void onBtnOnCarcase(ActionEvent actionEvent) {
        BaseController.detail = "carcase";
        nextWindow("Detail",btnToCarcase, "Модели кузова");
    }

    public void onBtnToWheels(ActionEvent actionEvent) {
        BaseController.detail = "wheels";
        nextWindow("Detail",btnToWheels, "Модели колес");
    }
}
