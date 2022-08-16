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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;



public class AdminDashboard extends Myfunctions {
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Label labl1;

    @FXML
    private Label labl2;

    @FXML
    private Label labl3;

    @FXML
    private Label labl4;

    @FXML
    private Button mybtn1;

    @FXML
    private Button mybtn2;

    @FXML
    private Button mybtn3;

    @FXML
    private Button mybtn4;
    
    Label label,label2,label3,label4,label5;
    
    public void getrowdb(String emp,String prod,String sales,String profit) {
    	labl1.setText("Total Users: "+ emp);
    	labl2.setText("Total products: "+ prod);
    	labl3.setText("Total sales: "+ sales);
    	labl4.setText("Total profit made: "+ profit);
		
	}
    
    public void employees(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    	newScreen(event, "Employees.fxml", "application.css");
    	
	}
    public void Sales(ActionEvent event) throws IOException {
    	newScreen(event, "salesadndprof.fxml", "application.css");
		
	}
    
    ImageView myView;
    
    Label labell,labell2,labell3,labell4;
    Image image;
    
    String string,string2,string3,string4,string5;
    
    public void products(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("products.fxml"));
		root= loader.load();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/lysavegan?","root","");
        Statement stmt=con.createStatement();
        String query="SELECT * FROM `products`";
		ResultSet resultSet=stmt.executeQuery(query);
		while (resultSet.next()) {
			string=resultSet.getString(2);
			string2=resultSet.getString(3);
			string3=resultSet.getString(4);
			string4=resultSet.getString(5);
			string5=resultSet.getString(6);
			image=new Image(getClass().getResourceAsStream(string3));
			myView=new ImageView(image);
			myView.setFitWidth(170);
			myView.setPreserveRatio(true);
			labell=new Label("Type: "+string);
			labell2=new Label("Name: "+string2);
			labell3=new Label("Price: "+string4);
			labell4=new Label("Discount: "+string5);
			
			Products myproducts=loader.getController();
			myproducts.displayProd(labell, labell2, labell3, labell4, image, myView);
			
		}
		String css=this.getClass().getResource("application.css").toExternalForm();
		
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();		
		
	}
    public void sales(ActionEvent event) throws IOException {
    	newScreen(event, "salesandprof.fxml", "application.css");
		
	}
    public void profit(ActionEvent event) throws IOException {
    	newScreen(event, "salesandprof.fxml", "application.css");
		
	}

}
