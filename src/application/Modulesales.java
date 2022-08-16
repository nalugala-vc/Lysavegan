package application;

public class Modulesales {
	
	String dateString;
	float price;
	float profit;
	
	public Modulesales(String i,float float1,float float2) {
		this.dateString=i;
		this.price=float1;
		this.profit=float2;
		
	}
	
	public String getDateString() {
		return dateString;
	}
	public float getPrice() {
		return price;
	}
	public float getProfit() {
		return profit;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public void setProfit(float profit) {
		this.profit = profit;
	}
	

}
