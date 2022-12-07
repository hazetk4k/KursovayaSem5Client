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

    public String[] parts = {"Топливная система\t", "Аккумулятор\t", "Кузов\t", "Колеса\t"};
    public void onBtnBack(ActionEvent actionEvent) {
        nextWindow("MainFrame", btnBack, "Главное окно");
    }

    public void onBtnSaveResult(ActionEvent actionEvent) {
        btnSaveResult.setDisable(true);
    }

    public void onBtnGraph(ActionEvent actionEvent) {
    }

    public String getFormula(String detail, String base, String result) {
        String formula = "";
        String[] details = detail.split("; ");
        String[] bases = detail.split("; ");
        String[] results = detail.split("; ");
        for (int i = 1; i < 4; i++) {
            formula += (details[i] + " / " + bases[i] + " = " + results[i-1]);
            formula += "///";
        }
        return formula;
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
            // 1) продукт ///2) фуел ///3) бэт ///4) кар ///5) колеса ///6) базовые1 ///7) базовые2 ///8) базовые3 ///9) базовые4 ///10) кач1 ///11) кач2 ///12) кач3 ///13) кач4 ///14) результат1 ///15) рузультат 2 ///16) рез3 ///17) рез4 ///
            listResults.getItems().add("Параматры продукта: " + lines[0]);
            listResults.getItems().add("Параметры топливной системы: \t" + lines[1]);
            listResults.getItems().add("Параметры аккумулятора: \t" + lines[2]);
            listResults.getItems().add("Параметры кузова: \t" + lines[3]);
            listResults.getItems().add("Параметры колес: \t" + lines[4]);
            listResults.getItems().add("Дифференциальныый метод расчета уровня качества: ");
            for (int i = 1; i < 5; i++) {
                listResults.getItems().add(parts[i - 1]);
                String[] formulas = getFormula(lines[i], lines[i + 5], lines[i + 9]).split("///");
                for(int j=0; j <3; j++){
                    listResults.getItems().add(formulas[j]);
                }
                listResults.getItems().add(lines[i + 13]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
