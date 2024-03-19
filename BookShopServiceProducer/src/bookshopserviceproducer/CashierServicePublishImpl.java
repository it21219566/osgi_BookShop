package bookshopserviceproducer;

import java.util.List;

public class CashierServicePublishImpl implements  CashierServicePublish{

	private List<Book> bookList = DataStor.booksList;//Item list details in the supermarket 
	private double bill;//to store the bill value
	private String[][] billdetails = new String[1000][4]; //To store the purchased item's details ,Assumption:only 1000 different items are allowed for an order
	private int count = -1; //to store the item count [starts from 0]
	
	@Override
	public List<Book> displayBooks() {
		return DataStor.booksList;
		
	}
	public int generateBill(int id,int qty) {
		
		boolean valid = false;
		Book currentBook = null;
		for (Book tempBook : bookList) {
			if(id == tempBook.getBookId()) {
				currentBook = tempBook;
				valid = true;
				count++;
				break;
			}
		}
		if(valid) {
			
		this.bill = this.bill + (currentBook.getFinalPrice() * qty); 
			
		billdetails[count][0]= Integer.toString(id);
		billdetails[count][1]= currentBook.getBookName();
		
		billdetails[count][2]= Integer.toString(qty);
		billdetails[count][3]= Double.toString((currentBook.getFinalPrice() * qty));
			
		return 1;
		}
		else {
			return -1;
		}
	}
	public double displayFinalBillAmount() {
		double finalBill = this.bill;
		newBill();
		
		return finalBill;
		
	}
	public String[][] dispalybillDetails(){
		
		return billdetails;
	}
	public void newBill() {//To reset all relevant fields to default values 
		this.bill = 0;
		this.count=-1;		
	}
}
