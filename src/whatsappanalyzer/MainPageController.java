/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whatsappanalyzer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import static whatsappanalyzer.WhatsappAnalyzer.file;
import static whatsappanalyzer.WhatsappAnalyzer.selectedFile;

/**
 * FXML Controller class
 *
 * @author Harsh Kumar Singh
 */
public class MainPageController implements Initializable {

    @FXML
    private Button btnAnalyze;

    @FXML
    private TextField txtFilePath, txtFileName;

    @FXML
    public void chooseFileAction(ActionEvent Event) {
        file.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt"));
        selectedFile = file.showOpenDialog(null);
        if (selectedFile != null) {
            txtFilePath.setText(selectedFile.getParent());
            txtFileName.setText(selectedFile.getName());
            btnAnalyze.setDisable(false);
            txtFilePath.setDisable(false);
            txtFileName.setDisable(false);
        } else {
            txtFilePath.setText("");
            txtFileName.setText("");
            btnAnalyze.setDisable(true);
            txtFilePath.setDisable(true);
            txtFileName.setDisable(true);
        }
    }

    @FXML
    public void analyzeAction(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("Statistics.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.setTitle("WhatsApp Statistics");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("Logo.png"));
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        primaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAnalyze.setDisable(true);
        txtFilePath.setDisable(true);
        txtFileName.setDisable(true);
        txtFilePath.setEditable(false);
        txtFileName.setEditable(false);
    }

}
