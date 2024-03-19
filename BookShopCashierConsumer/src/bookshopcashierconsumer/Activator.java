package bookshopcashierconsumer;

import bookshopserviceproducer.Book;
import bookshopserviceproducer.CashierServicePublish;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	ServiceReference cashierServiceReference;
	private boolean exit =false;
	Scanner input = new Scanner(System.in);
	
	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("============Book shop cashier consumer started.============");
		cashierServiceReference = context.getServiceReference(CashierServicePublish.class.getName());
		@SuppressWarnings("unchecked")
		CashierServicePublish cashierService =  (CashierServicePublish)context.getService(cashierServiceReference);	
		
		do {
			int selection = 4;
			
			do {
				
				System.out.println("----------------------------Welcome to Billing-------------------------------");
				
				System.out.println("Please Select an option to continue.....");
				System.out.println("Options");
				System.out.println("1.View Book List");
				System.out.println("2.Generate customer bills");
				System.out.println("3.Exit");
				
				System.out.println("Enter your selection...");
				 selection = input.nextInt();
				
				 input.nextLine();
				 if(selection == 3) {//Exits from the cashier consumer program
					 exit = true;
				 }
				 
				 if(selection !=1 && selection !=2 && selection !=3 ) {
					 System.out.println("Please enter a valid selection");
				 }
				
			}while(selection !=1 && selection !=2 && selection !=3);
			
			String  backToHome = null;
			String calculateFinalBill = null;
			int bookCount = -1;
			
			//viewing process of Book list
			if (selection == 1) {
				do {
					
				List<Book> booksList =cashierService.displayBooks();//Consumes the CashierService displayItems()
				System.out.println("-----------------------------------Book list--------------------------------------------");
				System.out.println("Book ID:"+"\t" +"Book Name:"+"\t" + "Book Price:"+"\t" + "Discount Percentage:"+"\t" + "Book Final Price:"+"\t");
				
			for(Book tempBook: booksList ) {
				System.out.println(tempBook.getBookId()+"\t         "+tempBook.getBookName()+"\t         "+tempBook.getBookPrice()+"\t         "+tempBook.getDiscount()+"\t                  "+tempBook.getFinalPrice()+"\t"+"\n");
						
			
			System.out.println("-----------------------------------------------------------------------------------------");
			
				}

			System.out.println("Press 0 to navigate back to home or press any other key to continue....");
				
				backToHome=input.nextLine();
				}
				
				while(!(backToHome.equals("0")));
				
			}
			
			//billing process
			else if(selection == 2){
				
				do {
					do {
						
						System.out.println("---------------------Generate Bills --------------------" + "\n");
						System.out.println("Enter the book id");
						int id = input.nextInt();
						
						System.out.println("Enter quantity");
						int qty = input.nextInt();
							
						int result =cashierService.generateBill(id, qty);//Consumes the CashierService generateBill()
						if(result == -1) {
							 System.out.println("Please enter a valid book id!!");
						}
						else {
							bookCount++;
						}
						input.nextLine();
						System.out.println("Press y to calclate the totoal bill or any other key to continue the billing....");	
						calculateFinalBill=input.nextLine();
						
					}while(!(calculateFinalBill.equals("y")));
					
					System.out.println("------------------------------------------Receipt----------------------------------------");
					String[][] billDetails= cashierService.dispalybillDetails();//Consumes the cashierService displaybillDetails()
					
					String format = "%-20s";
					System.out.printf(format, "Book ID:");
					System.out.printf(format, "Book Name:");
					System.out.printf(format, "Price:");
					System.out.printf(format,"Total:");
					System.out.println("");
					for (int i=0; i<=bookCount; i++) {
						  for (int j=0; j<billDetails[i].length; j++) {
					
						System.out.printf(format,billDetails[i][j]);
						 
						  }
						  System.out.println("");
						  }
					System.out.println("                                                          ----------");
					System.out.println("Subtotal:                                                   " + cashierService.displayFinalBillAmount());//Consumes the cashierService displayFinalBillAmount()
					System.out.println("                                                          ----------");	  
					System.out.println("                                                          ----------");
					System.out.println("-------------------------------------------------------------------------------------------");
					
					bookCount=-1;
					
					System.out.println("Press 0 to navigate back to home or press any other key to continue....");
					
					backToHome=input.nextLine();
					
				}while(!(backToHome.equals("0")));
			}
			
			//exiting from the book shop cashier consumer program
			else if(selection == 3) {
				return;
			}
			
		}while(!exit);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("============Book Shop cashier consumer stopeed.============");
		context.ungetService(cashierServiceReference);
	}

}
