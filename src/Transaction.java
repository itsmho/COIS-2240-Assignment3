import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.util.Scanner;

public class Transaction {
	
	  private static Transaction transactionInstance = null;
	  private static String transactionFile = "transaction.txt";

	    
	    private Transaction() 
	    {
	    	createTransactionFile();
	    }
	    
	    public static synchronized Transaction getTransaction() {
	        if (transactionInstance == null) {
	        	transactionInstance = new Transaction();
	        	//createTransactionFile();
	        }
	        return transactionInstance;
	    }

    // Perform the borrowing of a book
    public static boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book); 
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
            saveTransaction(transactionDetails);
            System.out.println(transactionDetails);
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
    }

    // Perform the returning of a book
    public static void returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            saveTransaction(transactionDetails);
            System.out.println(transactionDetails);
        } else {
            System.out.println("This book was not borrowed by the member.");
        }
    }

    // Get the current date and time in a readable format
    private static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
    
    public static void createTransactionFile() 
    {
    	try 
    	{ 
    		File fileTxt = new File(transactionFile);
    		if (!fileTxt.exists()) 
    		{
    			fileTxt.createNewFile();
    		} 
    	
    	} catch (Exception ex) 
    	{
    		System.err.println("Error Creating File");
    	}
    }
  
	private static void saveTransaction(String transactionDetails) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(transactionFile, true))) {
            writer.write(transactionDetails);
            writer.newLine();
        } catch (Exception ex) {
            System.err.println("Error saving transaction to file: ");
        }
    }

	public static void displayTransactionHistory() {
		File fileTxt = new File(transactionFile);
        if (!fileTxt.exists()) 
        {
            System.out.println("Transaction history not found.");
            return;
        } 
       try ( Scanner readFile = new Scanner(fileTxt))
       {
            System.out.println("Transaction History");
            while (readFile.hasNextLine()) 
            
            {
            	String line = readFile.nextLine();
                System.out.println(line); 
            }
            readFile.close();
        } catch (Exception ex ) 
             {
            	 System.out.println("Cant read file");
             }
        
        
       }
	
	}

	    