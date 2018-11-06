/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whatsappanalyzer;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Harsh Kumar Singh
 */
public class WhatsappAnalyzer extends Application {

    public static FileChooser file = new FileChooser();
    public static File selectedFile;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("WhatsApp Statistics");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("Logo.png"));
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - 400) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - 200) / 2);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
