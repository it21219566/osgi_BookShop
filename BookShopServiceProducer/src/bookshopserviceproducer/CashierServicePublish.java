package bookshopserviceproducer;

import java.util.List;

public interface CashierServicePublish {

	public List<Book> displayBooks();//returns the list of Books with details
	public int generateBill(int id,int qty);//calculates the on going bill continuously
	public double displayFinalBillAmount();//displays the final bill amount
	public String[][] dispalybillDetails();//returns the purchased list with details
	
}
