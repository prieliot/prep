package documents;

public class Book extends ADocument {

	public Book(int number, String title) {
		super(number, title);
	}
	
	@Override
	public String toString() {
		return "Book [" + super.toString() + "]";
	}

}
