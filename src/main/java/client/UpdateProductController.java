package client;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UpdateProductController extends BaseController implements Initializable {
    public ComboBox<String> textFuel;
    public ComboBox<String> textBattery;
    public ComboBox<String> textCarcase;
    public ComboBox<String> textWheels;
    public Button btnSubmit;
    public TextField textName;
    public Button btnBack;

    public void onBtnSubmit(ActionEvent actionEvent) throws IOException{
        if (Objects.equals(textFuel.getValue(), "") || Objects.equals(textBattery.getValue(), "") || Objects.equals(textCarcase.getValue(), "") || Objects.equals(textWheels.getValue(), "") || Objects.equals(textName.getText(), "")) {
            textName.setText("");
            textName.setPromptText("Все поля должны быть заполнены!");
        } else {
            PrintWriter out = null;
            BufferedReader in = null;
            String line;
            out = new PrintWriter(MySocket.INSTANCE.getSock().getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(MySocket.INSTANCE.getSock().getInputStream()));
            out.println("Изменение" + "; " + textName.getText() + "; " + textFuel.getValue() + "; " + textBattery.getValue() + "; " + textCarcase.getValue() + "; " + textWheels.getValue() + "; " + String.valueOf(BaseController.chosenID));
            line = in.readLine();
            if (Objects.equals(line, "updated")) {
                textName.setText("");
                textName.setPromptText("Запись обновлена!");
                btnSubmit.setDisable(true);
            }
        }

    }

    public void onBtnBack(ActionEvent actionEvent) {
        nextWindow("MainFrame", btnBack, "Главное окно");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PrintWriter out = null;
        BufferedReader in = null;
        String line;
        try {
            out = new PrintWriter(MySocket.INSTANCE.getSock().getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(MySocket.INSTANCE.getSock().getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        out.println("Модели");
        try {
            line = in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] models = line.split("///"); ///models = {table, table, table, table}
        String[] model1 = models[0].split("; "); ///model1 = {el, el el}
        for (int i = 1; i < model1.length; i++) {
            textFuel.getItems().add(model1[i]);
        }
        model1 = models[1].split("; ");
        for (int i = 1; i < model1.length; i++) {
            textBattery.getItems().add(model1[i]);
        }
        model1 = models[2].split("; ");
        for (int i = 1; i < model1.length; i++) {
            textCarcase.getItems().add(model1[i]);
        }
        model1 = models[3].split("; ");
        for (int i = 1; i < model1.length; i++) {
            textWheels.getItems().add(model1[i]);
        }
    }
}

