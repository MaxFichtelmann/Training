package bookstore.control;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import bookstore.model.Book;

public class BookDatabase
{
    Map<String, Book> books = new HashMap<String, Book>();
    
    public void save( Book book )
    {
        books.put( book.getTitle(), book );
    }
    
    public Book load( String title )
    {
        return books.get( title );
    }
    
    public Collection<String> listTitles()
    {
        return books.keySet();
    }
    
    public boolean contains( String title )
    {
        return books.containsKey( title );
    }
}
