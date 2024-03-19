package bookshopserviceproducer;

import java.util.List;

public interface ManagerServicePublish {

	public  int  addBooks(String bookName,double bookPrice,double bookDiscount);//Adds new books to the list
	public   int  updateBooks(String updatedBookName,double updatedBookPrice,double updatedBoookDiscount);//Updates the book details
	public   int removeBooks(String bookName);//Removes the books from the list
	public   int searchBooks(String bookName);//Searches and books by name
	public   List<Book> listBooks();//Returns the book list
	
}
