package client;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;


public class GraphController extends BaseController implements Initializable {

    public Button btnBack;
    @FXML
    public BarChart barChart;


    public void onBtnBack(ActionEvent actionEvent) {
        nextWindow("MainFrame", btnBack, "Главное окно");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        barChart.getData().clear();
        barChart.layout();
        series1.setName("Параметры с высоким качеством");
        series2.setName("Параметры с низким качеством");
        for (String args : BaseController.graphParams){
            String[] param = args.split(":");
            if (Float.parseFloat(param[1]) < 1){
                series2.getData().add(new XYChart.Data(param[0], Float.parseFloat(param[1])));
            }else{
                series1.getData().add(new XYChart.Data(param[0], Float.parseFloat(param[1])));
            }
        }
        barChart.getData().addAll(series1, series2);
        BaseController.graphParams.clear();
    }
}
