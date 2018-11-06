/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whatsappanalyzer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harsh Kumar Singh
 */
public class UserData {

    private Date date;
    private String name;
    private String message;
    private final SimpleDateFormat dateFormat;

    public UserData() {
        date = new Date();
        name = null;
        message = null;
        dateFormat = new SimpleDateFormat("dd/MM/yy, hh:mm aa");
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String strDate) {
        try {
            Date newDate = dateFormat.parse(strDate);
            this.date = newDate;
        } catch (ParseException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
