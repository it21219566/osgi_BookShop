package bookshopserviceproducer;

public class Book {

	private int bookId;
	private String bookName;
	private double bookPrice;
	private double discount;
	private double finalPrice;
	
	public Book(int bookId, String bookName, double bookPrice, double discount) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.discount = discount;
		calculateFinalPrice();
		
	}
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
		calculateFinalPrice();
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
		calculateFinalPrice();
	}
	public double getFinalPrice() {
		return finalPrice;
	}
	public void calculateFinalPrice() {
		this.finalPrice = bookPrice * ((100.00- discount)/100.00);
	}
}
