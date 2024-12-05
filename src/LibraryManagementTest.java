import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class LibraryManagementTest {

	// test variables
	private Book testBook1;
	private Book testBook2;
	private Book testBook3;
	private Book testBook4;
	private Book testBook5;
	private Book testBook;
	private Member testMember1;
	private Member testMember2;
	private Transaction transaction;

	// setup test objects 
	@Before
	public void setUp() throws Exception {
		
		testBook = new Book(100, "Math");
		testMember1 = new Member(1111, "George");
		testMember2 = new Member(2222, "Anne");
		transaction = Transaction.getTransaction();

	}
	
	// test to validate BookID
	@Test
	public void testBookId() {
		
		// test bookId that is valid
		try {
			testBook1 = new Book(100, "Math");

		} catch (Exception ex) {
			
			assertEquals("This should not have occurred ID is valid", ex.getMessage());
		}
		
		// test bookId that is valid
		try {
			testBook2 = new Book(999, "Programming");
		} catch (Exception ex) {
			
			assertEquals("This should not have occurred ID is valid", ex.getMessage());
		}
		
		// test bookId that is invalid
		try {
			testBook3 = new Book(1000, "Running");
			fail("This should not have occured ID is Invalid");
		} catch (Exception ex) {
			
			assertEquals("Invalid Book ID, ID must be between 100 and 999.", ex.getMessage());
		}
		
		// test bookId that is invalid
		try {
			testBook4 = new Book(50, "Golfing");
			fail("This should not have occured ID is Invalid");
			
		} catch (Exception ex) {
			assertEquals("Invalid Book ID, ID must be between 100 and 999.", ex.getMessage());
		} 
		
		// test bookId that is invalid
		try {
			testBook5 = new Book(2000,"Fishing");
			fail("This should not have occured ID is Invalid");
	
		} catch (Exception ex) {
			assertEquals("Invalid Book ID, ID must be between 100 and 999.", ex.getMessage());
		}
	}

	
	// test borrowBook and returnBook method
	@Test
	public void testBorrowReturn() { 
		// check if book is available 
		assertTrue("Book is available to borrow", testBook.isAvailable());
		// first member borrows book
		boolean borrowResults1 = transaction.borrowBook(testBook, testMember1);

		// checks if book is borrowed successfully and see if unable to be borrowed 
		assertTrue("Borrowing should be sucessful", borrowResults1);
		assertFalse("Book should be unavaliable after borrowing", testBook.isAvailable());
		
		// member 2 attempt to borrow book that is already borrowed
		boolean borrowResults2 = transaction.borrowBook(testBook, testMember2);
		
		assertFalse("Borrowing should be unsucessful after borrowing", borrowResults2);

		// member 1 returns book and check if book is available to be borrowed
		boolean returnResults = transaction.returnBook(testBook, testMember1);
		assertTrue("Borrowing should be sucessful after returning",testBook.isAvailable());
		assertTrue("Returning should sucessful.", returnResults);
		
		// member 2 borrows book and book should be unavailable
		boolean borrowResults4 = transaction.borrowBook(testBook, testMember2);
		assertTrue("Borrowing should be sucessful", borrowResults4);
		assertFalse("Book should be unavaliable after borrowing", testBook.isAvailable());
		
		// member 2 returns book successful and book will be available 
		boolean returnResults2 = transaction.returnBook(testBook, testMember2);
		assertTrue("Book is avaliable to borrow", testBook.isAvailable());
		assertTrue("Returning should sucessful.", returnResults2);
		
		// member 2 returns book again but fails because they didn't borrow the book again
		boolean returnResults3 = transaction.returnBook(testBook, testMember1);
		assertFalse("Returning should fail for an already returned book.", returnResults3);



	}
	
	// test singleton for transaction 
	@Test
	public void testSingletonTransaction() throws Exception {

		Constructor<Transaction> constructor = Transaction.class.getDeclaredConstructor();
		constructor.setAccessible(true);

		int mod = constructor.getModifiers();

		assertEquals("Should be private", Modifier.PRIVATE, mod);
	}

}




