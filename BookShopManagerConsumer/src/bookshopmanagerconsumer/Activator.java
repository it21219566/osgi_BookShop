package bookshopmanagerconsumer;

import bookshopserviceproducer.Book;
import bookshopserviceproducer.ManagerServicePublish;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	ServiceReference ManagerServiceReference;
	Scanner input = new Scanner(System.in);

	boolean exit = false;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("============Book Shop manager consumer started.============");
		ManagerServiceReference = context.getServiceReference(ManagerServicePublish.class.getName());
		@SuppressWarnings("unchecked")
		ManagerServicePublish managerService = (ManagerServicePublish) context.getService(ManagerServiceReference);
		
		do {
			int selection = 7;
			do {
				
				System.out.println("----------------------------Welcome Book Shop Manager-------------------------------");
				
				System.out.println("Please Select an option to continue.....");
				System.out.println("Options");
				System.out.println("1.Add a new Book ");
				System.out.println("2.Update a Book");
				System.out.println("3.Delete a Book");
				System.out.println("4.Book List");
				System.out.println("5.Search Book by name");
				System.out.println("6.Exit");
				
				System.out.println("Enter your selection...");
				 selection = input.nextInt();
				
				 input.nextLine();
				 if(selection == 6) {
					 exit = true;
				 }
				 
				 if(selection !=1 && selection !=2 && selection !=3 && selection !=4 && selection !=5 && selection!=6) {
					 System.out.println("Please enter a valid selection");
				 }	
			}while(selection !=1 && selection !=2 && selection !=3 && selection !=4 && selection !=5 && selection!=6 );
			
			String  backToHome = null;
			
			//adding new books to the book list
			if (selection == 1) {
				do {
				System.out.println("Book Name");
				String bookName = input.nextLine();

				System.out.println("Book Price");
				double bookPrice = input.nextDouble();

				System.out.println("Discount");
				double bookDiscount = input.nextDouble();
				input.nextLine();
				int result =managerService.addBooks(bookName, bookPrice, bookDiscount);//Consumes the ManagerService addBooks()
				 String msg = (result ==1) ? "Successfully added the Book!" :"please enter a valid name";
				   System.out.println(msg);
				System.out.println("Press 0 to navigate back to home or press any other key to continue....");
				
				backToHome=input.nextLine();
				
				}
				
				while(!(backToHome.equals("0")));

			}
			//updating book details
			else if (selection == 2) {
				do {
					System.out.println("Book Name");
					String updatedBookName = input.nextLine();

					System.out.println("Book Price");
					double updatedBookPrice = input.nextDouble();

					System.out.println("Discount");
					double updatedBookDiscount = input.nextDouble();
					input.nextLine();
				
				int result =managerService.updateBooks(updatedBookName,updatedBookPrice,updatedBookDiscount);//Consumes the ManagerService updateBooks()
				  String msg = (result ==1) ? "Successfully updated the Book!" :"please enter a valid name";
				   System.out.println(msg);
	           System.out.println("Press 0 to navigate back to home or press any other key to continue....");
				
				backToHome=input.nextLine();
				
				}while(!(backToHome.equals("0")));
				
			}
			//Deleting an exsisting Book
			else if (selection == 3) {
				do {
				System.out.println("Enter the Book name:");

				String bookName = input.nextLine();
				int result =managerService.removeBooks(bookName);//Consumes the ManagerService removeBooks()
				   String msg = (result ==1) ? "Successfully Removed the Book!" :"please enter a valid name";
				   System.out.println(msg);
	              System.out.println("Press 0 to navigate back to home or press any other key to continue....");
				
				backToHome=input.nextLine();
				
				}
				
				while(!(backToHome.equals("0")));

			}
			//displaying all books on the list
			else if(selection == 4) {
				do {
					List<Book> booksList =managerService.listBooks();//Consumes the ManagerService listBooks()
					System.out.println("-----------------------------------Book list--------------------------------------------");
					System.out.println("Book ID:"+"\t" +"Book Name:"+"\t" + "Book Price:"+"\t" + "Discount Percentage:"+"\t" + "Book Final Price:"+"\t");
					
				for(Book tempBook: booksList ) {
					System.out.println(tempBook.getBookId()+"\t         "+tempBook.getBookName()+"\t         "+tempBook.getBookPrice()+"\t         "+tempBook.getDiscount()+"\t                  "+tempBook.getFinalPrice()+"\t"+"\n");
									
				}
				System.out.println("-----------------------------------------------------------------------------------------");
	            System.out.println("Press 0 to navigate back to home or press any other key to continue....");
				
				backToHome=input.nextLine();
				
				}
				
				while(!(backToHome.equals("0")));

			}
			//search and finds a exsisting books form the book list
			else if(selection == 5) {
				do {
				
				System.out.println("Enter the Book name");
				

				String bookName = input.nextLine();
				int result =managerService.searchBooks(bookName);//Consumes the ManagerService searchBooks()
				 String msg = (result ==1) ? "Book found!" :"Book not found!";
				   System.out.println(msg);
				
				System.out.println("Press 0 to navigate back to home or press any other key to continue....");
				
				backToHome=input.nextLine();
				
				}
				
				while(!(backToHome.equals("0")));
			}
			//Exiting form the Book Shop Manager consumer program
			else if(selection == 6) {
				return;
			}
		}while(!exit);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("============Book shop manager consumer stopped.============");
		context.ungetService(ManagerServiceReference);
	}

}
