/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whatsappanalyzer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import static whatsappanalyzer.FileHandling.userData;

public class LineChartController implements Initializable {
    
    @FXML
    Text lblFileName, lblMonth;
    
    @FXML
    ComboBox<String> comboYear, comboMonth;
    
    @FXML
    LineChart<String, Number> lineChart;
    
    ObservableList<String> monthAvailable = FXCollections.observableArrayList();
    ObservableList<String> yearAvailable = FXCollections.observableArrayList();
    XYChart.Series<String, Number> series = new XYChart.Series<>();
    
    void getMonthYear() {
        int month, year;
        for (UserData i : userData) {
            month = i.getDate().getMonth() + 1;
            year = i.getDate().getYear() + 1900;
            if (!monthAvailable.contains(month + "")) {
                monthAvailable.add(month + "");
            }
            if (!yearAvailable.contains(year + "")) {
                yearAvailable.add(year + "");
            }
        }

//        monthAvailable = monthAvailable.sorted();
        Collections.sort(monthAvailable);
    }
    
    @FXML
    void drawLineChart(ActionEvent event) {
        int date, month, year;
        int dateIndex;
        int dateCounter;
        ArrayList<Integer> dateForGraphCounter = new ArrayList<>();
        ObservableList<String> dateAvailable = FXCollections.observableArrayList();
        series.getData().clear();
        lineChart.getData().clear();
        
        dateAvailable.removeAll();
        try {
            month = Integer.parseInt(comboMonth.getValue()) - 1;
            year = Integer.parseInt(comboYear.getValue()) - 1900;
            for (int i = 1; i <= 31; i++) {
                if (!dateAvailable.contains(i + "")) {
                    dateAvailable.add(i + "");
                    dateIndex = dateAvailable.indexOf(i + "");
                    dateForGraphCounter.add(dateIndex, 0);
                }
            }
            for (UserData i : userData) {
                if (i.getDate().getYear() == year && i.getDate().getMonth() == month) {
                    date = i.getDate().getDate();
                    dateIndex = dateAvailable.indexOf(date + "");
                    dateCounter = dateForGraphCounter.get(dateIndex) + 1;
                    dateForGraphCounter.set(dateIndex, dateCounter);
                }
            }
            month++;
            year += 1900;
            lblMonth.setText(month + "/" + year);
            
            for (String i : dateAvailable) {
                int index = dateAvailable.indexOf(i);
                series.getData().add(new XYChart.Data<>(dateAvailable.get(index) + "", dateForGraphCounter.get(index)));
            }
            
            lineChart.getData().add(series);
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @FXML
    void startScreen(ActionEvent e) {
        series.getData().clear();
        lineChart.getData().clear();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblFileName.setText(WhatsappAnalyzer.selectedFile.getName());
        FileHandling newFile = new FileHandling();
        newFile.readFile();
        this.getMonthYear();
        comboMonth.setItems(monthAvailable);
        comboYear.setItems(yearAvailable);
    }
    
}
