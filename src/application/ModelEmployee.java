package application;


public class ModelEmployee {
	int ID;
	String FirstName,lastName;
	double totalsales,commission;
	
	public ModelEmployee(int string,String string2,String string3,double string4,double string5) {
		this.ID=string;
		this.FirstName=string2;
		this.lastName=string3;
		this.totalsales=string4;
		this.commission=string5;
	}
	public int getID() {
		return ID;
	}
	public String getFirstName() {
		return FirstName;
	}
	public String getLastName() {
		return lastName;
	}
	public double getTotalsales() {
		return totalsales;
	}
	public double getCommission() {
		return commission;
	}
	public void setID(int iD) {
		this.ID = iD;
	}
	public void setFirstName(String firstName) {
		this.FirstName =firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setTotalsales(double totalsales) {
		this.totalsales =totalsales;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}

}
