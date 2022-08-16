package application;

import application.Myfunctions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller extends Myfunctions{
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	
	ImageView image;
	Button button;
	
	 @FXML
	 private TextField mytext1;

	 @FXML
	 private PasswordField mytext3;
	 
	 @FXML
	    private TextField mytxt1;

	    @FXML
	    private PasswordField mytxt2;

	
	Image myImage=new Image(getClass().getResourceAsStream("cupcake logo.png"));
	
	
	public void displayImage() {
		image.setImage(myImage);
		
		
	}
	
	public void admin(ActionEvent event) throws IOException{
		newScreen(event,"LoginAdmin.fxml", "application.css");
			
	}
	public void user(ActionEvent event) throws IOException {
		newScreen(event,"LoginUser.fxml", "application.css");
			
	}
	
	public void loginEmp(ActionEvent event) throws IOException, ClassNotFoundException, SQLException{
		String username=mytext1.getText();
		String password=mytext3.getText();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/lysavegan?","root","");
        Statement stmt=con.createStatement();
        String query="SELECT * FROM `employees` WHERE Username='"+username+"' AND password='"+password+"'";
		ResultSet resultSet=stmt.executeQuery(query);
		
		if(resultSet.next()) {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("homepageUser.fxml"));
			root= loader.load();
			
			Controller2 controller2=loader.getController();
			controller2.greeting(username);
			controller2.getSpecial();
			
	        String css=this.getClass().getResource("application.css").toExternalForm();
			
			stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			scene=new Scene(root);
			scene.getStylesheets().add(css);
			stage.setScene(scene);
			stage.show();
			
		}
		else {
	    	Alert alert=new Alert(Alert.AlertType.ERROR);
	    	alert.setContentText("LOGIN NOT SUCCESSFUL:INVALID CREDENTIALS");
	    	alert.show();
			
		}
		
		
	}
	public void loginAdm(ActionEvent event) throws IOException, ClassNotFoundException, SQLException{
		//newScreen(event,"AdminDashboard.fxml","application.css");
		String username=mytxt1.getText();
		String password=mytxt2.getText();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/lysavegan?","root","");
        Statement stmt=con.createStatement();
        String query="SELECT * FROM `admin` WHERE Username='"+username+"' AND password='"+password+"'";
		ResultSet resultSet=stmt.executeQuery(query);
		
		if(resultSet.next()) {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
			root= loader.load();
			
			String query2="SELECT * FROM `employees`";
			ResultSet resultSet2=stmt.executeQuery(query2);
			
			int employees=0;
			while(resultSet2.next()) {
				employees=employees+1;
				
			}
			String empString=Integer.toString(employees);
			
			
			String query3="SELECT * FROM `products`";
			ResultSet resultSet3=stmt.executeQuery(query3);
			resultSet3.next();
			int products=1;
			while (resultSet3.next()) {
				products=products+1;
				
			}
			String prodString=Integer.toString(products);
			
			String query4="SELECT * FROM `sales`";
			ResultSet resultSet4=stmt.executeQuery(query4);
			resultSet4.next();
			float price;
			float profit;
			
			float totalprice=0;
			float totalprofit=0;
			
			String priceString;
			String profitString;
			while (resultSet4.next()) {
				priceString=resultSet4.getString(2);
				profitString=resultSet4.getString(3);
				
				price=Float.parseFloat(priceString);
				profit=Float.parseFloat(profitString);
				
				totalprice=totalprice+price;
				totalprofit=totalprofit+profit;    
				
				
			}
			String mysaleString=Float.toString(totalprice);
			String myprofString=Float.toString(totalprofit);
			
			AdminDashboard dashboard=loader.getController();
			dashboard.getrowdb(empString, prodString, mysaleString, myprofString);
			
			//CartController mController=new CartController(username)
			
	        String css=this.getClass().getResource("application.css").toExternalForm();
			
			stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			scene=new Scene(root);
			scene.getStylesheets().add(css);
			stage.setScene(scene);
			stage.show();
			
		}
		else {
	    	Alert alert=new Alert(Alert.AlertType.ERROR);
	    	alert.setContentText("LOGIN NOT SUCCESSFUL:INVALID CREDENTIALS");
	    	alert.show();
			
		}
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}