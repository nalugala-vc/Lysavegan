package application;

import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Iterator;

//import com.mysql.cj.jdbc.result.CachedResultSetMetaData;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CartController extends Myfunctions {
	private Stage stage;
	private Scene scene;
	private Parent root,root2;

    @FXML
    private Button mycart;

    @FXML
    private Button myhome;

    @FXML
    private Button mymenu;

    @FXML
    private Button myuser;

    @FXML
    private Label orderdetails;
    
    @FXML
    private VBox mypane;
    
    String myuserString;
    
   public CartController() {
    	super();
    	myuserString="Rwafubwa";
   }
    
    public String getuser(String user) {
		this.myuserString=user;
		System.out.println(myuserString);
		return myuserString;
	}
    
    
    public void dispalyItems (Label label, ImageView image ,Label label2,Label label3,Button Button,Button button2,Image image2) {
    	
    	mypane.getChildren().add(label);
    	image.setImage(image2);
    	image.setFitWidth(170);
    	image.setPreserveRatio(true);
        HBox vButtonsBox= new HBox();		
		vButtonsBox.setSpacing(10);
		vButtonsBox.setPadding(new Insets(0,0,0,0));
		vButtonsBox.getChildren().addAll(Button,label3,button2);
    	mypane.getChildren().add(image);
    	mypane.getChildren().add(label2);
    	mypane.getChildren().add(vButtonsBox);
    	       	
    	
    }
    
    public void BuYNow(Button button ,Label label) {
    	button.setPrefHeight(56);
    	button.setPrefWidth(196);
    	button.setText("Buy Now");
    	button.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				menuController controller=new menuController();
				DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				LocalDateTime now=LocalDateTime.now();
				String dateString=dateTimeFormatter.format(now);
		    	try {
					int[] myarray= controller.getValues();
					
					String []myarraStrings=Arrays.stream(myarray).mapToObj(String::valueOf).
							toArray(String[]::new);
					FXMLLoader loader=new FXMLLoader(getClass().getResource("Buynow.fxml"));
					FXMLLoader loader2=new FXMLLoader(getClass().getResource("LoginUser.fxml"));
					root= loader.load();
					root2=loader2.load();
					int titems=Integer.parseInt(myarraStrings[0]);
					int tprice=Integer.parseInt(myarraStrings[1]);
					int tcomm=titems*5;
					int tprof=titems*10;
					
					float[] myFloat=Select(myuserString);
					
					float newsaleFloat=myFloat[0] +tprice ;
					float newcommFloat=myFloat[1] + tcomm;
					
					
					
					
					
					buyNow bNow=loader.getController();
					bNow.getItems("Date: "+dateString, "Discount: KSH 0/-","Price: KSH "+ myarraStrings[1]+"/-",
							"Served by: "+myuserString, "Total Price: KSH "+ myarraStrings[1]+"/-", "Total Items: "+myarraStrings[0]);
					Insert1(dateString, myarray[1],tprof);
					System.out.println("user is " +myuserString);
					Insertcom(newsaleFloat, newcommFloat,myuserString );
					emptyCart();
					String css=this.getClass().getResource("application.css").toExternalForm();
					stage=(Stage)((Node)arg0.getSource()).getScene().getWindow();
					scene=new Scene(root);
					scene.getStylesheets().add(css);
					stage.setScene(scene);
					stage.show();	

				} catch (ClassNotFoundException | IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}
    	});
    	mypane.getChildren().add(label);
    	mypane.getChildren().add(button);
    	//button.setBackground(Color.pink);
		
	}
       public void add(ActionEvent event) {
      
    	
		
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
       public void products(ActionEvent event)throws IOException{
			Parent root=FXMLLoader.load(getClass().getResource("menu.fxml"));
			stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			String css=this.getClass().getResource("application.css").toExternalForm();
			scene=new Scene(root);
			scene.getStylesheets().add(css);
			stage.setScene(scene);
			stage.show();		
			
		}
    public void subtract(ActionEvent event) {
    	//get text of label ,turn to int then ad 1
    	
    }
	public void menu(ActionEvent event) throws IOException{
		newScreen(event,"menu.fxml", "application.css");
			
	}

}
