/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package dashboard;

import Login.FXMLController;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class MainScreenController implements Initializable {
    @FXML
    private Label name;
    @FXML
    private Label body;
    public void setInfo(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
           String sql = "SELECT * FROM userdata WHERE AccountNo=?";
           ps =con.prepareStatement(sql);
           
           ps.setString(1, FXMLController.acc);
           
           rs = ps.executeQuery();
           if(rs.next()){
             name.setText(rs.getString("Name"));
            
           }
           else{
                Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Login Fail");
            a.setContentText("Your Account no.  or Pin is incorrect . Try Again !!!");
            a.showAndWait();
           }
           
           
        }catch(Exception e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Error is login account");
            a.setContentText("Your account is not login.There is some technical issue"+ e.getMessage());
            a.showAndWait();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        body.setText("Axis Bank Limited, formerly known as UTI Bank (1993–2007),\nis an Indian banking and financial services company headquartered in Mumbai,\nMaharashtra. It sells financial services to large and mid-size companies,\nSMEs and retail businesses.");
        setInfo();
    }      
}
