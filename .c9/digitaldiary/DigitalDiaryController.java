/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitaldiary;

import java.util.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.web.HTMLEditor;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
/**
 * FXML Controller class
 *
 * @author iosdev474
 */
public class DigitalDiaryController implements Initializable {

    @FXML
    private Label date_d;
    @FXML
    private Label time_d;
    @FXML
    private MenuItem saveMenItm;
    @FXML
    private HTMLEditor editor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        currentDate();
    }    
    
    private void currentDate() {
       
        Thread clock = new Thread() {
            public void run() {
                for (;;) {
                    try {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                //javaFX operations should go here
                                Calendar cal = new GregorianCalendar();
                                date_d.setText("Date " + cal.get(Calendar.DATE) + "\\" + cal.get(Calendar.MONTH) + "\\" + cal.get(Calendar.YEAR));
                                time_d.setText("Time " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND));
                            }
                        });

                        Thread.sleep(1000);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(DigitalDiaryController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        clock.start();
    }

    @FXML
    private void addNote(ActionEvent event) {
         String a = editor.getHtmlText();
         Date date = new Date();
         Date sessionStartTime = new Date();
         
        try{
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/notesystem","root","1234");
      Statement stmt = con.createStatement();
      String sql = "Insert into note(note,Birthday) values('"+a+"')";
      stmt.close();
      con.close();
   }
         catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }
}
  

