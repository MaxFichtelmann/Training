package bookstore.factory;

import bookstore.control.BookDatabase;
import bookstore.control.BookStore;
import bookstore.model.Book;

public class BookStoreFactory
{
    public BookStore createEmpty()
    {
        BookDatabase database = new BookDatabase();
        
        return new BookStore( database );
    }
    
    public BookStore createFantasyStore()
    {
        BookDatabase database = new BookDatabase();
        
        database.save( new Book( "The Hobbit", 20.0 ) );
        database.save( new Book( "The Lord of the Rings", 40.0 ) );
        database.save( new Book( "Harry Potter", 45.0 ) );
        
        return new BookStore( database );
    }
}
