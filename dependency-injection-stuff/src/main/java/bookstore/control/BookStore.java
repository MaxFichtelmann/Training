package bookstore.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import bookstore.model.Book;

public class BookStore
{
    BookDatabase database;
    
    List<Book>   cart = new ArrayList<Book>();
    
    public BookStore( BookDatabase database )
    {
        this.database = database;
    }
    
    public void addToCart( String bookTitle )
    {
        if ( !database.contains( bookTitle ) )
        {
            throw new NoSuchElementException();
        }
        cart.add( database.load( bookTitle ) );
    }
    
    public void removeFromCart( String bookTitle )
    {
        for ( int i = 0; i < cart.size(); i++ )
        {
            Book book = cart.get( i );
            if ( book.getTitle().equals( bookTitle ) )
            {
                cart.remove( i );
                break;
            }
        }
    }
    
    public List<Book> list( int limit )
    {
        Collection<String> titles = database.listTitles();
        
        List<Book> booksInDB = new ArrayList<Book>();
        
        for ( String title : titles )
        {
            booksInDB.add( database.load( title ) );
            if ( booksInDB.size() >= limit )
            {
                break;
            }
        }
        
        return booksInDB;
    }
    
    public double calculatePrice()
    {
        double sum = 0;
        for ( Book item : cart )
        {
            sum += item.getPrice();
        }
        
        return sum;
    }
}
