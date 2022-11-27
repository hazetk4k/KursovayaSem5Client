package client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

abstract public class BaseController {
    void nextWindow(String view, Button button, String title) {
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
}
