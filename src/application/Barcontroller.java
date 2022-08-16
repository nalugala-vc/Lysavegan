package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Barcontroller extends Myfunctions implements Initializable {
    ImageView myView;
    
    Label labell,labell2,labell3,labell4;
    Image image;
    
    String string,string2,string3,string4,string5;
    
    private Stage stage;
	private Scene scene;
	private Parent root;


    @FXML
    private Label bar;

    @FXML
    private BarChart<String, Number> mybar;

    @FXML
    private CategoryAxis naempp;

    @FXML
    private NumberAxis nasales;
    
    public void addBars() throws ClassNotFoundException, SQLException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/lysavegan?","root","");
        Statement stmt=con.createStatement();
    	String query2="SELECT * FROM `employees`";
		ResultSet rSet=stmt.executeQuery(query2);
		XYChart.Series<String, Number> series=new XYChart.Series<>();
		series.setName("Employee Sales");
		while (rSet.next()) {
			String nameString=rSet.getString(6);
			String saleString=rSet.getString(7);
			
		    
			double mysale=Double.parseDouble(saleString);
			series.getData().add(new XYChart.Data<>(nameString,mysale))	;
		}
		mybar.getData().add(series);
	
		
	}
    public void openProd(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
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
    public void admdash(ActionEvent event) throws IOException, ClassNotFoundException, SQLException{
		//newScreen(event,"AdminDashboard.fxml","application.css");
		
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/lysavegan?","root","");
        Statement stmt=con.createStatement();

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
		
    public void sales(ActionEvent event) throws IOException {
    	newScreen(event, "salesandprof.fxml", "application.css");
		
	}	
    public void Comm(ActionEvent event) throws IOException {
		newScreen(event, "Barchartcomm.fxml", "application.css");
	}
    
    public void Viewemp(ActionEvent event) throws IOException {
    	newScreen(event, "Employees.fxml", "application.css");
		
	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
			
			try {
				addBars();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
	}

}