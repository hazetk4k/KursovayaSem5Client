package client;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.awt.Desktop;



public class ProductQualityController extends BaseController implements Initializable {
    public ListView<String> listResults = new ListView<String>();
    public Button btnBack;
    public Button btnSaveResult;
    public Button btnGraph;
    public String product;
    public String[] parts = {"Топливная система\t", "Аккумулятор\t", "Кузов\t", "Колеса\t"};
    public String[] params = {"Объем бака", "Давление в топливной рампе", "Пробег", "Электроёмкость", "Напряжение", "Пробег", "Толщина кузова", "Толщина стекол", "Пробег", "Давление в шинах", "Ширина шины", "Пробег"};
    public Button btnOnDirectory;

    public void onBtnBack(ActionEvent actionEvent) {
        nextWindow("MainFrame", btnBack, "Главное окно");
    }

    public void onBtnSaveResult(ActionEvent actionEvent) {
        try {
            File f = new File("D:/уник/5 сем/псп/TkachukKursovayaClient/Results/" + product + ".txt");
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(f, false);
            for (int i = 0; i < listResults.getItems().size(); i++) {
                fw.write(listResults.getItems().get(i) + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        btnSaveResult.setDisable(true);
    }

    public void onBtnGraph(ActionEvent actionEvent) {

    }

    public String getFormula(String detail, String base, String result) {
        String formula = "";
        String[] details = detail.split("; ");
        String[] bases = base.split("; ");
        String[] results = result.split("; ");
        for (int i = 1; i < 4; i++) {
            formula += (details[i] + " / " + bases[i] + " = " + results[i - 1]);
            formula += "///";
        }
        return formula;
    }

    public String getNegatives(String result, int count) {
        String[] res = result.split(": ");
        String negatives = res[0] + ": ";
        for (int i = 1; i < res.length; i++) {
            if (Integer.parseInt(res[i]) == 0) {
                negatives += params[count - 3];
            }
            if (Integer.parseInt(res[i]) == 1) {
                negatives += params[count - 2];
            }
            if (Integer.parseInt(res[i]) == 2) {
                negatives += params[count - 3];
            }
            if (!(res.length - 1 == i)) {
                negatives += ", ";
            } else {
                negatives += ". ";
            }
        }
        return negatives;
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
            product = lines[0].split("; ")[1];
            listResults.getItems().add("Параметры топливной системы: \t" + lines[1]);
            listResults.getItems().add("Параметры аккумулятора: \t" + lines[2]);
            listResults.getItems().add("Параметры кузова: \t" + lines[3]);
            listResults.getItems().add("Параметры колес: \t" + lines[4]);
            listResults.getItems().add("Дифференциальныый метод расчета уровня качества: ");
            int count = 0;
            for (int i = 1; i < 5; i++) {
                listResults.getItems().add("* " + parts[i - 1] + ":");
                String[] formulas = getFormula(lines[i], lines[i + 4], lines[i + 8]).split("///");
                for (int j = 0; j < 3; j++) {
                    listResults.getItems().add("\t" + params[count] + ":\t" + formulas[j]);
                    count++;
                }
                String[] prices = lines[i + 16].split("; ");
                listResults.getItems().add("\tЦена детали: " + prices[1]);
                listResults.getItems().add("\tБазовая цена: " + prices[2]);
                listResults.getItems().add("\tОтчет о цене: " + prices[0]);
                if (lines[i + 12].matches("(.*)следующих(.*)")) {
                    String res = getNegatives(lines[i + 12], count);
                    listResults.getItems().add("\t" + res);
                } else {
                    listResults.getItems().add("\t" + lines[i + 12]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onBtnDirectory(ActionEvent actionEvent) throws IOException {
        Desktop.getDesktop().open(new File(System.getProperty("user.dir") +  "/Results/"));
    }
}
