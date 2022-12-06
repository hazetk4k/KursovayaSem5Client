package client;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductQualityController extends BaseController implements Initializable {
    public ListView<String> listResults = new ListView<String>();
    public Button btnBack;
    public Button btnSaveResult;
    public Button btnGraph;

    public void onBtnBack(ActionEvent actionEvent) {
        nextWindow("MainFrame", btnBack, "Главное окно");
    }

    public void onBtnSaveResult(ActionEvent actionEvent) {
        btnSaveResult.setDisable(true);
    }

    public void onBtnGraph(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BufferedReader in = null;
        PrintWriter out = null;
        String line = "";
        try {
            out = new PrintWriter(MySocket.INSTANCE.getSock().getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(MySocket.INSTANCE.getSock().getInputStream()));
            out.println("Отчет" + "; " + BaseController.chosenID);
            line = in.readLine();
            String[] lines = line.split("///");
            listResults.getItems().add("Параматры продукта: " + lines[0]);
            listResults.getItems().add("Параметры топливной системы: " + lines[1]);
            listResults.getItems().add("Параметры аккумулятора: " + lines[2]);
            listResults.getItems().add("Параметры кузова: " + lines[3]);
            listResults.getItems().add("Параметры колес: " + lines[4]);
            listResults.getItems().add("Дифференциальныый метод расчета уровня качества: ");
            listResults.getItems().add("BaseFuel: " + lines[5]);
            listResults.getItems().add("BaseBattery: " + lines[6]);
            listResults.getItems().add("BaseCarcase: " + lines[7]);
            listResults.getItems().add("BaseWheels: " + lines[8]);
            listResults.getItems().add("FuelRes: " + lines[9]);
            listResults.getItems().add("BatteryRes: " + lines[10]);
            listResults.getItems().add("CarcaseRes: " + lines[11]);
            listResults.getItems().add("WheelsRes: " + lines[12]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
