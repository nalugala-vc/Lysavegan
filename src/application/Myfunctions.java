package application;

import java.io.IOException;
import java.security.ProtectionDomain;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import application.menuController;

public class Myfunctions {
	private Stage stage;
	private Scene scene;
	private Parent root;
	//ActionEvent event;
	
	public String fxml;
	public String Css;	
	
	

	public void newScreen(ActionEvent event,String fxml, String Css) throws IOException {
		
		Parent root=FXMLLoader.load(getClass().getResource(fxml));
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		String css=this.getClass().getResource(Css).toExternalForm();
		scene=new Scene(root);
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();	
		
	
	}
	
	public void changeVbox(Label label,ImageView image,Label label2,Label label3,Image image2,Label label4) {
		String imagString=image2.toString();
		String nameString=label3.getText();
		String priceString=label4.getText();
		image.setImage(image2);
		label.setText(nameString);
		label2.setText(priceString);
			
	}
	
	public void Insert(String ordername,int price,String image) throws SQLException, ClassNotFoundException  {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/lysavegan?","root","");
        Statement stmt=con.createStatement();
        //SELECT `OrderName` FROM `order details` WHERE `OrderName`="Perfect Vanilla";
        String query="SELECT count(`OrderName`) FROM `order details` WHERE `OrderName`='"+ordername+"'";
        ResultSet rSet= stmt.executeQuery(query);
        rSet.next();
        int countt =rSet.getInt(1);
        //System.out.println(countt);
       // String count=rSet.getString("count(OrderName)");
        
        if (countt>0) {
        	Alert alert=new Alert(AlertType.WARNING);
    		alert.setContentText("Order already exists");
    		alert.show();
        	
        }else {
        	String query2="INSERT INTO `order details`(`OrderName`, `Price`, `Image`) VALUES ('"+ordername+"','"+price+"','"+image+"')";
    		stmt.executeUpdate(query2);
    		Alert alert=new Alert(AlertType.CONFIRMATION);
    		alert.setContentText("Order added successfully");
    		alert.show();
        	
        }
        	
	}
	public void Insert1(String ordername,float price,int profit) throws SQLException, ClassNotFoundException  {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/lysavegan?","root","");
	        Statement stmt=con.createStatement();
        	String query2="INSERT INTO `sales`(`Name`, `Price`, `Profits`) VALUES ('"+ordername+"','"+price+"','"+profit+"')";
    		stmt.executeUpdate(query2);
    		Alert alert=new Alert(AlertType.CONFIRMATION);
    		alert.setContentText("Order added successfully");
    		alert.show();
        	
        }
	public void emptyCart() throws SQLException, ClassNotFoundException  {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/lysavegan?","root","");
        Statement stmt=con.createStatement();
    	String query2="DELETE FROM `order details`";
		stmt.executeUpdate(query2);
		Alert alert=new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Cart emptied sucessfully");
		alert.show();
    	
    }
	public void Insertcom(double sales,double comm,String username) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/lysavegan?","root","");
        Statement stmt=con.createStatement();
    	String query2="UPDATE `employees` SET `Total Sales` = '"+sales+"', `Commission` = '"+comm+"' WHERE `employees`.`Username` = '"+username+"'";
		stmt.executeUpdate(query2);
	}
	public float[] Select(String name) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/lysavegan?","root","");
        Statement stmt=con.createStatement();
    	String query2="SELECT * FROM `employees` WHERE Username='"+name+"'";
		ResultSet rSet=stmt.executeQuery(query2);
		rSet.next();
		String saleString =rSet.getString(7);
		String commString=rSet.getString(8);
		
		
		float sale=Float.parseFloat(saleString);
		float comm=Float.parseFloat(commString);
		
		float myarray[]= {sale,comm};
		
		return myarray;
		
		
	}
	
}
