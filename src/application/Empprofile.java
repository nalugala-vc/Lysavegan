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

public class Empprofile {
	
	private Stage stage;
	private Scene scene;
	private Parent root;


    @FXML
    private Label comm;

    @FXML
    private Label date;

    @FXML
    private Button home;

    @FXML
    private Label items;

    @FXML
    private Button cart;

    @FXML
    private Label price;

    @FXML
    private Button products;

    @FXML
    private Label prof;

    @FXML
    private Button user;
    
    public void setlabels(String myLabel,String myLabel2,String mylabel3,String myLabel4) {
    	date.setText("Date: "+myLabel);
    	items.setText("Employee ID Number: "+myLabel2);
    	price.setText("Total Price: "+mylabel3);
    	comm.setText("Total commission: "+myLabel4);
    	prof.setText("Rwafubwa's Profile");
		
	}
    
    public void products(ActionEvent event)throws IOException{
		Parent root=FXMLLoader.load(getClass().getResource("menu.fxml"));
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		String css=this.getClass().getResource("application.css").toExternalForm();
		scene=new Scene(root);
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();		
		
	}
    public void home(ActionEvent event) throws IOException{
   		FXMLLoader loader=new FXMLLoader(getClass().getResource("homepageUser.fxml"));
   		root= loader.load();
   		
   		Controller2 controller2=loader.getController();
   		controller2.getSpecial();
   		
           String css=this.getClass().getResource("application.css").toExternalForm();
   		
   		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
   		scene=new Scene(root);
   		scene.getStylesheets().add(css);
   		stage.setScene(scene);
   		stage.show();
   	}
    String ordername;
    String orderprice;
    String orderimage;
    String orderquantity;
    @FXML
    Label myLabel,myLabel2,myLabel3;
    
    @FXML
    Button myButton,myButton2;
    
    @FXML
    ImageView imageView;
    
    Image image;
    int quantity;
    int pricee;
    
    
    
    Button buttonn=new Button();
    Label myLabel4;
	public void Cart(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    	//(event, "Mycart.fxml", "application.css");
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("Mycart.fxml"));
		root= loader.load();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/lysavegan?","root","");
        Statement stmt=con.createStatement();
        String query="SELECT * FROM `order details`";
		ResultSet resultSet=stmt.executeQuery(query);
		int totalprice=0;
		while(resultSet.next()) {
			ordername=resultSet.getString(2);
			orderprice=resultSet.getString(3);
			orderimage=resultSet.getString(4);
		    orderquantity=resultSet.getString(5);
		    
		    myLabel=new Label(ordername);
            myLabel2=new Label("Ksh "+orderprice);
            myLabel3=new Label(orderquantity);
            myButton=new Button("+");
            myButton2=new Button("-");
            imageView=new ImageView();
            
            image=new Image(getClass().getResourceAsStream(orderimage));
            
            pricee=Integer.parseInt(orderprice);
            
            totalprice = pricee+totalprice;
		    
		    CartController mycart=loader.getController();
		    mycart.dispalyItems(myLabel, imageView, myLabel2, myLabel3, myButton, myButton2, image);
			
		}
		System.out.println(totalprice);
		String prixeString=String.valueOf(totalprice);
		myLabel4=new Label("Ksh"+prixeString);
		CartController myCartController=loader.getController();
		myCartController.BuYNow(buttonn,myLabel4);
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
		
    }

}
