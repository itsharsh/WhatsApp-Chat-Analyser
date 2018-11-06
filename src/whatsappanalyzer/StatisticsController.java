/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whatsappanalyzer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Harsh Kumar Singh
 */
public class StatisticsController implements Initializable {

    @FXML
    void showLineChart(ActionEvent event) {
        try {
            Stage secondaryStage = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource("LineChart.fxml"));
            Scene scene = new Scene(parent);
            secondaryStage.setTitle("Line Chart (Monthwise)");
            secondaryStage.setScene(scene);
            secondaryStage.getIcons().add(new Image("Logo.png"));
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            secondaryStage.setX((primScreenBounds.getWidth() - secondaryStage.getWidth()) / 2);
            secondaryStage.setY((primScreenBounds.getHeight() - secondaryStage.getHeight()) / 2);
            secondaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex);
            Alert a = new Alert(Alert.AlertType.ERROR, "Some Error Occured");
            a.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
