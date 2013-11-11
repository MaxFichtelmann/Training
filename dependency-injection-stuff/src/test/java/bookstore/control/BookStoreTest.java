package bookstore.control;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import bookstore.model.Book;

public class BookStoreTest
{
    BookStore    cut;
    
    // mock
    BookDatabase database;
    
    @Before
    public void setup()
    {
        database = mock( BookDatabase.class );
        cut = new BookStore( database );
    }
    
    @Test
    public void listShouldNotExceedLimit()
    {
        when( database.listTitles() ).thenReturn( Arrays.asList( "1", "2", "3" ) );
        when( database.load( anyString() ) ).thenReturn( new Book( "any", 1.0 ) );
        
        List<Book> list = cut.list( 2 );
        
        assertThat( list.size(), is( equalTo( 2 ) ) );
    }
    
    @Test( expected = NoSuchElementException.class )
    public void addingUnknownShouldThrow()
    {
        when( database.contains( anyString() ) ).thenThrow( new NoSuchElementException() );
        
        cut.addToCart( "any" );
    }
}
