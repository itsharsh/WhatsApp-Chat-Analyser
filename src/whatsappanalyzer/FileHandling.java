/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whatsappanalyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harsh Kumar Singh
 */
public class FileHandling {

    private BufferedReader buff = null;
    private final List<String> userName = new ArrayList<>();
    public static List<UserData> userData = new ArrayList<UserData>();
    private int lastIndex = -1;

    public void readFile() {
        try {
            String line;
            buff = new BufferedReader(new FileReader(WhatsappAnalyzer.selectedFile.getAbsolutePath()));
            userName.add("Encryption Msg");
            while ((line = buff.readLine()) != null) {
                extractMessage(line);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);

        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            try {
                buff.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

    public void extractMessage(String line) {
        int startIndex = 0, endIndex = 0;
        UserData temp = new UserData();

        String tempName = null;
        String tempDate = null;
        String tempMessage;
        int changeData = 0;//0 for adding new data and 1 for modifying data and 2 for not adding data;
        try {
            if (line.length() > 0) {
                int firstSlash = line.indexOf("/");
                int secondSlash = line.indexOf("/", firstSlash + 1);
                if (secondSlash - firstSlash == 3) {               //extracting date
                    startIndex = line.indexOf("/") - 2;
                    endIndex = line.indexOf("-") - 1;
                    tempDate = line.substring(startIndex, endIndex);
                    line = line.replace(line.substring(startIndex, endIndex + 1), "");

                    startIndex = line.indexOf("-");     //extracting person name
                    endIndex = line.indexOf(":");
                    if (startIndex >= 0) {
                        if (endIndex >= 1) {
                            startIndex += 2;
                            tempName = line.substring(startIndex, endIndex);
                            if (!userName.contains(tempName)) {
                                userName.add(tempName);
                            }
                            startIndex -= 2;            //for encryption message
                        } else {                        //no person found, so message is info
                            changeData = 2;
                            endIndex++;
                            tempName = "Encryption Msg";
                        }
                        endIndex += 2;                  //for only message lines
                    }
                } else {                               //contd. message
                    if (changeData != 2) {
                        changeData = 1;
                    }
                }

                line = line.replace(line.substring(startIndex, endIndex), "");
                tempMessage = line;                     //extracting message

                switch (changeData) {
                    case 0:                             //add new data
                        temp.setDate(tempDate);
                        temp.setName(tempName);
                        temp.setMessage(tempMessage);
                        userData.add(temp);
                        lastIndex = userData.indexOf(temp);
                        break;
                    case 1:                             //modify data
                        temp = userData.get(lastIndex);
                        temp.setMessage(temp.getMessage() + " " + tempMessage);
                        userData.set(lastIndex, temp);
                        break;
                    case 2:                             //ignore data
                        break;
                    default:
                        break;
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
}
