package application;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Products extends Myfunctions  {
	
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Button empl;

    @FXML
    private Button prod;

    @FXML
    private Label products;

    @FXML
    private Button prof;

    @FXML
    private Button sales;

    @FXML
    private VBox vpane;
   
    public void displayProd(Label label,Label label2,Label label3,Label label4, Image image,ImageView myView) {
    	
			HBox myBox=new HBox();
			myBox.setSpacing(20);
			myBox.setPadding(new Insets(5,5,5,5));
			
			
			VBox myVBox=new VBox();
			myBox.setSpacing(10);	
			myVBox.getChildren().addAll(myView,label);
			
			VBox myVBox2=new VBox();
			myVBox2.setSpacing(10);
			myVBox2.getChildren().addAll(label2,label3,label4);
			
			myBox.getChildren().add(myVBox);
			myBox.getChildren().add(myVBox2);
			
			
			vpane.getChildren().add(myBox);
					
			
			
		}
    public void admdash(ActionEvent event) throws IOException, ClassNotFoundException, SQLException{
		//newScreen(event,"AdminDashboard.fxml","application.css");
		
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/lysavegan?","root","");
        Statement stmt=con.createStatement();

			FXMLLoader loader=new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
			root= loader.load();
			
			String query2="SELECT * FROM `employees`";
			ResultSet resultSet2=stmt.executeQuery(query2);
			
			int employees=1;
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
		
    public void Viewemp(ActionEvent event) throws IOException {
    	newScreen(event, "Employees.fxml", "application.css");
		
	}
    public void sales(ActionEvent event) throws IOException {
    	newScreen(event, "salesandprof.fxml", "application.css");
		
	}
    	
    	
		

		
	

}
