package client;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreatingDetailController extends BaseController implements Initializable {
    public TextField name;
    public TextField text1;
    public TextField text2;
    public TextField mileage;
    public TextField price;
    public Button btnAdd;
    public Label label1;
    public Label label2;

    public Button btnBack;
    public String table;

    public void onBtnBack(ActionEvent actionEvent) {
        nextWindow("Detail", btnBack, "Детали");
    }

    public void onBtnAdd(ActionEvent actionEvent) throws IOException {
        if (Objects.equals(name.getText(), "") || Objects.equals(text1.getText(), "") || Objects.equals(text2.getText(), "") || Objects.equals(mileage.getText(), "") || Objects.equals(price.getText(), "")) {
            name.setText("");
            text1.setText("");
            text2.setText("");
            mileage.setText("");
            price.setText("");
            name.setPromptText("Все поля должны быть заполнены!");
        } else {
            PrintWriter out = null;
            BufferedReader in = null;
            String line;
            out = new PrintWriter(MySocket.INSTANCE.getSock().getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(MySocket.INSTANCE.getSock().getInputStream()));
            out.println("Новая деталь; " + table + "; " + name.getText() + "; " + text1.getText() + "; " + text2.getText() + "; " + mileage.getText() + "; " + price.getText());
            line = in.readLine();
            if (Objects.equals(line, "added")) {
                name.setText("");
                text1.setText("");
                text2.setText("");
                mileage.setText("");
                price.setText("");
                name.setPromptText("Запись добавлена!");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Objects.equals(BaseController.detail, "fuel")) {
            table = "table_fuel";
            label1.setText("Объем бака (л.)");
            label2.setText("Давление в топливной рампе (бар.)");
        }
        if (Objects.equals(BaseController.detail, "battery")) {
            table = "table_battery";
            label1.setText("Электроёмкость (А*ч.)");
            label2.setText("Напряжение (В.)");
        }
        if (Objects.equals(BaseController.detail, "carcase")) {
            table = "table_carcase";
            label1.setText("Толщина кузова (мм.)");
            label2.setText("Толщина стекол (мм.)");
        }
        if (Objects.equals(BaseController.detail, "wheels")) {
            table = "table_wheels";
            label1.setText("Давление в шинах (бар.)");
            label2.setText("Ширина шины (мм.)");
        }
    }
}
