package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.RootPaneContainer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class menuController extends Myfunctions {
	
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Button b1;

    @FXML
    private Button b2;

    @FXML
    private Button b3;

    @FXML
    private Button b4;

    @FXML
    private Button b5;

    @FXML
    private Button b6;

    @FXML
    private Button b7;

    @FXML
    private Button b8;

    @FXML
	protected Button b9;
    
    
    
    public void b1(ActionEvent event) {
    	Image image=new Image(getClass().getResourceAsStream("perfectVanila.jpg"));
    	changeVbox(hlabel1, mainimage, hlabel2, l1, image, ll1);
		
	}
    public void b2(ActionEvent event) {
    	Image image=new Image(getClass().getResourceAsStream("fresh-straweberry.jpg"));
    	changeVbox(hlabel1, mainimage, hlabel2, l2, image, ll2);
		
	}
    public void b3(ActionEvent event) {
    	Image image=new Image(getClass().getResourceAsStream("moist-chocolate.jpg"));
    	changeVbox(hlabel1, mainimage, hlabel2, l3, image, ll3);
		
	}
    public void b4(ActionEvent event) {
    	Image image=new Image(getClass().getResourceAsStream("chocolate-chip.jpg"));
    	changeVbox(hlabel1, mainimage, hlabel2, l4, image, ll4);
		
	}
    public void b5(ActionEvent event) {
    	Image image=new Image(getClass().getResourceAsStream("cupids-cupcake.jpg"));
    	changeVbox(hlabel1, mainimage, hlabel2, l5, image, ll5);
		
	}
    public void b6(ActionEvent event) {
    	Image image=new Image(getClass().getResourceAsStream("carrot-cupcake.jpg"));
    	changeVbox(hlabel1, mainimage, hlabel2, l6, image, ll6);
		
	}
    public void b7(ActionEvent event) {
    	Image image=new Image(getClass().getResourceAsStream("Red-Velvet.jpg"));
    	changeVbox(hlabel1, mainimage, hlabel2, l7, image, ll7);
		
	}
    public void b8(ActionEvent event) {
    	Image image=new Image(getClass().getResourceAsStream("mint-chocolate.jpg"));
    	changeVbox(hlabel1, mainimage, hlabel2, l8, image, ll8);
		
	}
    public void b9(ActionEvent event) {
    	Image image=new Image(getClass().getResourceAsStream("Tripple-chocolate.jpg"));
    	changeVbox(hlabel1, mainimage, hlabel2, l9, image, ll9);
		
	}
    String ordernameString;
    String orderpricString;
    int price;
    
    @FXML
    Label myLabel,myLabel2,myLabel3;
    
    @FXML
    Button myButton,myButton2;
    
    @FXML
    ImageView imageView;
    
    Image image;
    
    String path;
    
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
    
    public void placeOrder(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
            ordernameString=hlabel2.getText();
            orderpricString=hlabel1.getText();
            String orderprice[]=orderpricString.split(" ");
            price =Integer.parseInt(orderprice[1]);
            
            
            
            switch (ordernameString) {
			case "Perfect Vanilla": {				
				image=new Image(getClass().getResourceAsStream("perfectVanila.jpg"));
				path="perfectVanila.jpg";
				break;
			}
			case "Fresh Strawberry": {				
			    image=new Image(getClass().getResourceAsStream("fresh-straweberry.jpg"));
			    path="fresh-straweberry.jpg";
				break;
			}
			case "Moist Chocolate": {				
				image=new Image(getClass().getResourceAsStream("moist-chocolate.jpg"));
				path="moist-chocolate.jpg";
				break;
			}
			case "Chocolate-chip": {				
				image=new Image(getClass().getResourceAsStream("chocolate-chip.jpg"));
				path="chocolate-chip.jpg";
				break;
			}
			case "Cupids Cupcake": {				
				image=new Image(getClass().getResourceAsStream("cupids-cupcake.jpg"));
				path="cupids-cupcake.jpg";
				break;
			}
			case "Carrot Cupcake": {				
				image=new Image(getClass().getResourceAsStream("carrot-cupcake.jpg"));
				path="carrot-cupcake.jpg";
				break;
			}case "Red Velvet": {				
				image=new Image(getClass().getResourceAsStream("Red-Velvet.jpg"));
				path="Red-Velvet.jpg";
				break;
			}case "Mint Chocolate": {				
				image=new Image(getClass().getResourceAsStream("mint-chocolate.jpg"));
				path="mint-chocolate.jpg";
				break;
			}case "Tripple Chocolate": {				
				image=new Image(getClass().getResourceAsStream("Tripple-chocolate.jpg"));
				path="Tripple-chocolate.jpg";
				//System.out.println(image);
				//image.getUrl();
				break;
			}
			
			default:
				System.err.println("Unexpected value: " + ordernameString);
			}
                     
            Insert(ordernameString, price, path);
            //newScreen(event, "alert.fxml", "application.css");
            
            
    		                    
    }
    String ordername;
    String orderprice;
    String orderimage;
    String orderquantity;
    
    int pricee;
    int quantity;
    
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
    
    public int[] getValues() throws IOException, ClassNotFoundException, SQLException {
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("Mycart.fxml"));
		root= loader.load();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/lysavegan?","root","");
        Statement stmt=con.createStatement();
        String query="SELECT * FROM `order details`";
		ResultSet resultSet=stmt.executeQuery(query);
		int totalprice=0;
		int numProd=0;
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
            numProd++;
		    
		    CartController mycart=loader.getController();
		    mycart.dispalyItems(myLabel, imageView, myLabel2, myLabel3, myButton, myButton2, image);		    
			
		}
		
		int myarray[]= {numProd,totalprice};
		return myarray;
    	
		
	}

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private ImageView c1;

    @FXML
    private ImageView c2;

    @FXML
    private ImageView c3;

    @FXML
    private ImageView c4;

    @FXML
    private ImageView c5;

    @FXML
    private ImageView c6;

    @FXML
    private ImageView c7;

    @FXML
    private ImageView c8;

    @FXML
    private ImageView c9;

    @FXML
    private Button hbutton;

    @FXML
    private Label hlabel1;

    @FXML
    private Label hlabel2;

    @FXML
    private Label l1;

    @FXML
    private Label l2;

    @FXML
    private Label l3;

    @FXML
    private Label l4;

    @FXML
    private Label l5;

    @FXML
    private Label l6;

    @FXML
    private Label l7;

    @FXML
    private Label l8;

    @FXML
    private Label l9;

    @FXML
    private Label ll1;

    @FXML
    private Label ll2;

    @FXML
    private Label ll3;

    @FXML
    private Label ll4;

    @FXML
    private Label ll5;

    @FXML
    private Label ll6;

    @FXML
    private Label ll7;

    @FXML
    private Label ll8;

    @FXML
    private Label ll9;

    @FXML
    private Label logolabel;

    @FXML
    private HBox mainbbbox;

    @FXML
    private ScrollPane mainhbox;

    @FXML
    private Label menu;

    @FXML
    private HBox menuhbox;

    @FXML
    private VBox myvbox;

    @FXML
    private VBox vertbox;
    
    @FXML
    private ImageView mainimage;
    
   

}
