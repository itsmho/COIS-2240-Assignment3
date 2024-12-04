import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Test;

public class LibraryManagementTest {
	
	
	private Book testBook1;
    private Book testBook2;
    private Book testBook3;
    private Book testBook4;
    private Book testBook5;
    private Book testBook;
    private Member testMember;
    private Transaction transaction;
	
    
    @Before
    public void setUp() throws Exception {
        // Instantiate a Book and Member object
        testBook = new Book(100, "Math");
        testMember = new Member(1111, "George");
        transaction = Transaction.getTransaction();
    }
	@Test
	public void testBookId() {
		try {
			testBook1 = new Book(100, "Math");

		} catch (Exception ex) {
			// TODO Auto-generated catch block
			assertEquals("This should not have occurred ID is valid", ex.getMessage());
		}
		try {
			testBook2 = new Book(999, "Programming");
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			assertEquals("This should not have occurred ID is valid", ex.getMessage());
		}
		try {
			testBook3 = new Book(1000, "Running");
			fail("This should not have occured ID is Invalid");
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			assertEquals("Invalid Book ID, ID must be between 100 and 999.", ex.getMessage());
		}
	
		try {
			testBook4 = new Book(50, "Golfing");
			fail("This should not have occured ID is Invalid");
//		assertEquals(50,testBook1.getId());
//		assertEquals("Math", testBook4.getTitle());
		} catch (Exception ex) {
			assertEquals("Invalid Book ID, ID must be between 100 and 999.", ex.getMessage());
		} try {
			testBook5 = new Book(2000,"Fishing");
			fail("This should not have occured ID is Invalid");
		
//		assertEquals(2000,testBook2.getId());
//		assertEquals("Computer", testBook5.getTitle());
		} catch (Exception ex) {
			assertEquals("Invalid Book ID, ID must be between 100 and 999.", ex.getMessage());
	}
	}
public void testBorrowReturn() { 
	
	boolean borrowResults1 = transaction.borrowBook(testBook, testMember);
	assertTrue("Book is avaliable to borrow", testBook.isAvailable());
	assertTrue("Borrowing should be sucessful", borrowResults1);
	assertFalse("Book should be unavaliable", testBook.isAvailable());
	
}}
;
