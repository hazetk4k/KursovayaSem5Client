package client;

import client.ClassesForTables.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DetailController extends BaseController implements Initializable {
    public TableView<Detail> tableDetails;
    public TableColumn<Detail, String> textName;
    public TableColumn<Detail, String> text1;
    public TableColumn<Detail, String> text2;
    public TableColumn<Detail, String> price;

    public Button btnBack;
    public TableColumn<Detail, String> mileage;
    public Button btnCreate;
    public Button btnDelete;
    public Button btnEdit;
    public TextField textAnswer;

    ObservableList<Detail> modelDetail = FXCollections.observableArrayList();

    public void onBtnBack(ActionEvent actionEvent) {
        if (BaseController.ac_access == 1) {
            nextWindow("MainFrame", btnBack, "Главное окно");
        } else {
            nextWindow("ManagerView", btnBack, "Главное окно");
        }
    }

    public void initTableColumns() {
        textName.setCellValueFactory(new PropertyValueFactory<>("model_name"));
        text1.setCellValueFactory(new PropertyValueFactory<>("text1"));
        text2.setCellValueFactory(new PropertyValueFactory<>("text2"));
        mileage.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void updateDetail(BufferedReader in, String line) throws IOException {
        line = in.readLine();
        if (!Objects.equals(line, "Нет данных")) {
            String[] models = line.split("///");
            String[] model1;
            for (int i = 0; i < models.length; i++) {
                model1 = models[i].split("; ");
                Detail obj = new Detail(model1[0], Float.parseFloat(model1[1]), Float.parseFloat(model1[2]),
                        Integer.parseInt(model1[3]), Integer.parseInt(model1[4]));
                modelDetail.add(obj);
            }
            tableDetails.setItems(modelDetail);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTableColumns();
        PrintWriter out = null;
        BufferedReader in = null;
        String line = "";
        if (BaseController.ac_access == 1) {
            textAnswer.setVisible(false);
            btnCreate.setDisable(true);
            btnCreate.setVisible(false);
            btnEdit.setDisable(true);
            btnEdit.setVisible(false);
            btnDelete.setDisable(true);
            btnDelete.setVisible(false);
        }
        try {
            out = new PrintWriter(MySocket.INSTANCE.getSock().getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(MySocket.INSTANCE.getSock().getInputStream()));
            if (Objects.equals(BaseController.detail, "fuel")) {
                text1.setText("Объем бака");
                text2.setText("Давление в топливной рампе");
                out.println("Деталь; table_fuel");
            }
            if (Objects.equals(BaseController.detail, "battery")) {
                text1.setText("Электроёмкость");
                text2.setText("Напряжение");
                out.println("Деталь; table_battery");
            }
            if (Objects.equals(BaseController.detail, "carcase")) {
                text1.setText("Толщина кузова");
                text2.setText("Толщина стекол");
                out.println("Деталь; table_carcase");
            }
            if (Objects.equals(BaseController.detail, "wheels")) {
                text1.setText("Давление в шинах");
                text2.setText("Ширина шины");
                out.println("Деталь; table_wheels");
            }
            updateDetail(in, line);
            tableDetails.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    btnDelete.setDisable(false);
                    btnEdit.setDisable(false);
                } else {
                    btnEdit.setDisable(true);
                    btnDelete.setDisable(true);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onBtnCreate(ActionEvent actionEvent) {
        nextWindow("CreatingDetail", btnCreate, "Добавить деталь");
    }

    public void onBtnDelete(ActionEvent actionEvent) throws IOException {
        String modelDeleteName;
        modelDeleteName = String.valueOf(tableDetails.getSelectionModel().getSelectedItem().model_name);
        PrintWriter out = null;
        BufferedReader in = null;
        String line = "";
        out = new PrintWriter(MySocket.INSTANCE.getSock().getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(MySocket.INSTANCE.getSock().getInputStream()));
        out.println("Удалить деталь" + "; " + modelDeleteName + "; " + "table_" + BaseController.detail);
        System.out.println("Запрос отправлен");
        tableDetails.getItems().clear();
        String answer = in.readLine();
        if (Objects.equals(answer, "Ошибка")){
            textAnswer.setText("Эту деталь нельзя удалить!");
        } else {
            textAnswer.setText("Деталь удалена!");
        }
        updateDetail(in, line);
    }

    public void onBtnEdit(ActionEvent actionEvent) {
        BaseController.chosenName = tableDetails.getSelectionModel().getSelectedItem().model_name;
        nextWindow("EditingDetail", btnEdit, "Добавить деталь");
    }
}
