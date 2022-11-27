package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Objects;

public class Controller{

    public Button btnLogIn;

    public Button btnConnect;
    @FXML
    public TextField textLogin;
    @FXML
    public TextField textPassword;
    @FXML
    public TextField textPort;
    @FXML
    public TextField textIP;
    @FXML
    public Label labelLogin;
    @FXML
    public Label labelPassword;

    public Button btnExit;
    public Button btnCreate;
    public Button btnDelete;
    public Button btnAllQuality;
    public Button btnProductQuality;
    public Button btnEdit;
    public Button btnSaveTxt;
    public Button btnLoadTxt;
    public Button btnRefresh;
    @FXML
    public ComboBox textFuel;
    @FXML
    public ComboBox textBattery;
    @FXML
    public ComboBox textCarcase;
    @FXML
    public ComboBox textChassis;

    public Button btnSubmit;
    @FXML
    public TextField textName;
    public Button btnBack;


    public void onBtnLogIn(ActionEvent actionEvent) {
        nextWindow("MainFrame", btnLogIn, "Главное окно");
    }

    public void onBtnConnect(ActionEvent actionEvent) {
        try {
            MySocket.setSock(new Socket(InetAddress.getByName(textIP.getText()), Integer.parseInt(textPort.getText())));
            nextWindow("AuthView", btnConnect, "Авторизация");
        } catch (IOException e) {
            textPort.setText("");
            textPort.setPromptText("Порт введен не верно!");
        }
    }

    private void nextWindow(String view, Button button, String title) {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/client/" + view + ".fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) button.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setOnCloseRequest(windowEvent -> {
            PrintWriter out = null;
            try {
                out = new PrintWriter(MySocket.INSTANCE.getSock().getOutputStream(), true);
                out.println("Disconnect");
                stage.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        stage.show();
    }

    public void onBtnExit(ActionEvent actionEvent) {
        nextWindow("AuthView", btnExit, "Авторизация");
    }

    public void onBtnRefresh(ActionEvent actionEvent) {
    }

    public void onBtnCreate(ActionEvent actionEvent) {
        nextWindow("CreatingView", btnCreate, "Новый продукт");
    }

    public void onBtnDelete(ActionEvent actionEvent) {
    }

    public void onBtnAllQuality(ActionEvent actionEvent) {
    }

    public void onBtnProductQuality(ActionEvent actionEvent) {
    }

    public void onBtnEdit(ActionEvent actionEvent) {
    }

    public void onBtnSaveTxt(ActionEvent actionEvent) {
    }

    public void OnBtnLoadTxt(ActionEvent actionEvent) {
    }


    public void onBtnSubmit(ActionEvent actionEvent) {
        nextWindow("MainFrame", btnSubmit, "Главное окно");
    }

    public void onBtnBack(ActionEvent actionEvent) {
        nextWindow("MainFrame", btnBack, "Главное окно");
    }
}