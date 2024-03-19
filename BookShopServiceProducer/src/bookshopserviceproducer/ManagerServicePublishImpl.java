package bookshopserviceproducer;

import java.util.List;

public class ManagerServicePublishImpl implements ManagerServicePublish{

	@Override
	public synchronized int addBooks(String bookName, double bookPrice, double bookDiscount) {
		Book newBook = new Book(DataStor.booksList.size() + 1, bookName, bookPrice, bookDiscount);
		DataStor.booksList.add(newBook);

	return 1;
	}

	@Override
	public synchronized int updateBooks(String updatedBookName, double updatedBookPrice, double updatedBoookDiscount) {
		Book bookToBeUpdated = null;
		boolean invalid = true;
		int count = -1;
		for (Book tempItem : DataStor.booksList) {
			count++;
			if (tempItem.getBookName().equalsIgnoreCase(updatedBookName)) {

				bookToBeUpdated = tempItem;
				invalid = false;
				break;
			}
		
		}
		if (!invalid) {

			bookToBeUpdated.setBookName(updatedBookName);
			bookToBeUpdated.setBookPrice(updatedBookPrice);
			bookToBeUpdated.setDiscount(updatedBookPrice);
			bookToBeUpdated.calculateFinalPrice();

			DataStor.booksList.set(count, bookToBeUpdated);
			return 1;

		} else {
			return -1;
		}
	}

	@Override
	public synchronized int removeBooks(String bookName) {
		boolean invalid = true;
		int count = -1;
		for (Book tempItem :DataStor.booksList) {
			count++;
			if (tempItem.getBookName().equalsIgnoreCase(bookName)) {
			
				invalid = false;
				break;
			}
		}
		if (!invalid) {

			DataStor.booksList.remove(count);
			return 1;

		} else {
			return -1;
		}
	}

	@Override
	public synchronized int searchBooks(String bookName) {
		boolean valid = false;
		for (Book tempItem : DataStor.booksList) {
			if (tempItem.getBookName().equalsIgnoreCase(bookName)) {

				valid = true;
				break;

			}			
		}
		if(valid) {
			return 1;
			
		}
		else {
			return -1;
		}
	}

	@Override
	public List<Book> listBooks() {
		return DataStor.booksList;
	}
}
