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

public class Controller {

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

    public Button btnDisconnect;

    public Button btnExit;
    @FXML
    public TableView productTable;
    @FXML
    public TableColumn tblID;
    @FXML
    public TableColumn tblName;
    @FXML
    public TableColumn tblFuel;
    @FXML
    public TableColumn tblCarcase;
    @FXML
    public TableColumn tblBattery;
    @FXML
    public TableColumn tblChassis;
    public Button btnCreate;
    public Button btnDelete;
    public Button btnAllQuality;
    public Button btnProductQuality;
    public Button btnEdit;
    public Button btnSaveTxt;
    public Button btnLoadTxt;
    public Button btnRefresh;

    Socket sock = null;

    @FXML
    public void onBtnLogIn(ActionEvent actionEvent) {
        nextWindow("MainFrame", btnLogIn, "Главный менеджер контроля качества");
    }

    @FXML
    public void onBtnConnect(ActionEvent actionEvent) {
        try {
            sock = new Socket(InetAddress.getByName(textIP.getText()), Integer.parseInt(textPort.getText()));
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
                out = new PrintWriter(sock.getOutputStream(), true);
                out.println("Disconnect");
                stage.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        stage.show();
    }

    @FXML
    public void onBtnDisconnect(ActionEvent actionEvent) {
        try {
            if (sock == null) {
                return;
            }
            try {
                PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
                out.println("Disconnect\n");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (NumberFormatException ignored) {
        }
    }

    public void onBtnExit(ActionEvent actionEvent) {
        //
    }
}