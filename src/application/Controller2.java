package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

public class Controller2 extends Myfunctions {

    @FXML
    private Button button2;

    @FXML
    private Label hello;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private ImageView image4;

    @FXML
    private ImageView image5;

    @FXML
    private Label price;

    @FXML
    private Label special;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	Calendar cal =Calendar.getInstance();
	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
	Format format=new SimpleDateFormat("EEEE");
	String string=format.format(new Date());
	String dayString=string.toLowerCase();
	Image image11;
	
    public void getSpecial() {
    	switch (dayString) {
		case "monday": {
			image11=new Image(getClass().getResourceAsStream("maniac-monday.jpg"));
			image5.setImage(image11);
			special.setText("Todays House Special: Maniac Monday");
			price.setText("KES 120/-");
			break;
		}
		case "tuesday": {
			image11=new Image(getClass().getResourceAsStream("terrific-tuesday.jpg"));
			image5.setImage(image11);
			special.setText("Todays House Special: Terrific Tuesday");
			price.setText("KES 130/-");
			break;
		}
		case "wednesday": {
			image11=new Image(getClass().getResourceAsStream("wasted-wednesday.jpg"));
			image5.setImage(image11);
			special.setText("Todays House Special: Wasted Wednesday");
			price.setText("KES 140/-");
			break;
		}
		case "thursday": {
			image11=new Image(getClass().getResourceAsStream("thunder-thursday.jpg"));
			image5.setImage(image11);
			special.setText("Todays House Special: Thunder Thursday");
			price.setText("KES 150/-");
			break;
		}
		case "friday": {
			image11=new Image(getClass().getResourceAsStream("fruity friday.jpg"));
			image5.setImage(image11);
			special.setText("Todays House Special: Fruity Friday ");
			price.setText("KES 160/-");
			break;
		}
		case "saturday": {
			image11=new Image(getClass().getResourceAsStream("sassy-saturday.jpg"));
			image5.setImage(image11);
			special.setText("Todays House Special: Sassy Saturday");
			price.setText("KES 170/-");
			break;
		}
		case "sunday": {
			image11=new Image(getClass().getResourceAsStream("sunny-sunday.jpg"));
			image5.setImage(image11);
			special.setText("Todays House Special: Sunny Sunday");
			price.setText("KES 180/-");
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + dayString);
		}
    	
    	
		
	}
    String path;
    int pricee;
    String name;
    
    
    public void placeorder(ActionEvent event) throws ClassNotFoundException, SQLException {
    	switch (dayString) {
		case "monday": {
			path="maniac-monday.jpg";
			pricee=120;
			name="Maniac Monday";
			break;
		}
		case "tuesday": {
			path="terrific-tuesday.jpg";
			pricee=130;
			name="Terrific Tuesday";
			break;
		}
		case "wednesday": {
			path="wasted-wednesday.jpg";
			pricee=140;
			name="Wasted Wednesday";
			break;
		}
		case "thursday": {
			path="thunder-thursday.jpg";
			pricee=150;
			name="Thunder Thursday";
			break;
		}
		case "friday": {
			path="fruity friday.jpg";
			pricee=160;
			name="Fruity Friday ";
			break;
		}
		case "saturday": {
			path="sassy-saturday.jpg";
			pricee=170;
			name="Sassy Saturday";
			break;
		}
		case "sunday": {
			path="sunny-sunday.jpg";
			pricee=180;
			name="Sunny Sunday";
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + dayString);
		}
    	
    	Insert(name, pricee, path);
    	
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
    
    Button buttonn=new Button();
    Label myLabel4;
        	
    
    public void greeting(String name) {
    	hello.setText("Hello " + name);
    	
		
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
