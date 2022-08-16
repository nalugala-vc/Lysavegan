package application;


	import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

	public class buyNow {
		private Stage stage;
		private Scene scene;
		private Parent root;

	    @FXML
	    private Label date;

	    @FXML
	    private Label discount;

	    @FXML
	    private Button mycart;

	    @FXML
	    private Button myhome;

	    @FXML
	    private Button mymenu;

	    @FXML
	    private Button myuser;

	    @FXML
	    private Label price;

	    @FXML
	    private Label receipt;

	    @FXML
	    private Label served;

	    @FXML
	    private Label titems;

	    @FXML
	    private Label tprice;
	    
	    @FXML
	    private AnchorPane mypane;
	    
	    public void getItems(String datee,String disc,String pricee,String serve,String totalprice,String totalitems) {
	    	date.setText(datee);
	    	discount.setText(disc);
	    	price.setText(pricee);
	    	served.setText(serve);
	    	titems.setText(totalitems);
	    	tprice.setText(totalprice);
			
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
	    
	    public void products(ActionEvent event)throws IOException{
			Parent root=FXMLLoader.load(getClass().getResource("menu.fxml"));
			stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			String css=this.getClass().getResource("application.css").toExternalForm();
			scene=new Scene(root);
			scene.getStylesheets().add(css);
			stage.setScene(scene);
			stage.show();		
			
		}
	    public void userprof(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
	    	//select from database then 
	    	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/lysavegan?","root","");
	        Statement stmt=con.createStatement();
	        String query="SELECT * FROM `employees` WHERE Username='Rwafubwa'";
			ResultSet resultSet=stmt.executeQuery(query);
			resultSet.next();
			String ID=resultSet.getString(1);
			String totalsaleString=resultSet.getString(7);
			String comm=resultSet.getString(8);
			
			DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now=LocalDateTime.now();
			String dateString=dateTimeFormatter.format(now);
			FXMLLoader loader=new FXMLLoader(getClass().getResource("Empprofile.fxml"));
	   		root= loader.load();

			Empprofile empprofile=loader.getController();
			empprofile.setlabels(dateString, ID, totalsaleString, comm);
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
			myLabel4=new Label("Total price: Ksh "+prixeString);
			CartController myCartController=loader.getController();
			myCartController.BuYNow(buttonn,myLabel4);
			stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			scene=new Scene(root);
			String css=this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);
			stage.setScene(scene);
			stage.show();
			
	    }

	}



