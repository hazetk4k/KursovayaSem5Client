package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectionController extends BaseController{
    public Button btnConnect;
    @FXML
    public TextField textPort;
    @FXML
    public TextField textIP;

    public void onBtnConnect(ActionEvent actionEvent) {
        try {
            MySocket.setSock(new Socket(InetAddress.getByName(textIP.getText()), Integer.parseInt(textPort.getText())));
            nextWindow("AuthView", btnConnect, "Авторизация");
        } catch (IOException e) {
            textPort.setText("");
            textPort.setPromptText("Порт введен не верно!");
        }
    }
}