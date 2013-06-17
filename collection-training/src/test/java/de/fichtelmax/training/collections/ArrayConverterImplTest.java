package de.fichtelmax.training.collections;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ArrayConverterImplTest
{
    ArrayConverter<Object> testObject;
    
    @Before
    public void setup()
    {
        testObject = new ArrayConverterImpl<Object>();
    }
    
    @Test
    public void testConvert()
    {
        Object[] input = { "a", "b", "c" };
        List<Object> expected = Arrays.asList( input );
        assertThat( testObject.convert( input ), is( equalTo( expected ) ) );
        
        input = new Integer[] { 1, 2, 3 };
        expected = Arrays.asList( input );
        assertThat( testObject.convert( input ), is( equalTo( expected ) ) );
        
        input = new Integer[] { 3, 2, 1 };
        expected = Arrays.asList( input );
        assertThat( testObject.convert( input ), is( equalTo( expected ) ) );
        
        input = new Object[] { new Object(), new Object(), new Object(), new Object() };
        expected = Arrays.asList( input );
        assertThat( testObject.convert( input ), is( equalTo( expected ) ) );
    }
    
    @Test
    public void testUniqueTArray()
    {
        Object[] input = { "a", "b", "c", "c" };
        Object[] expected = { "a", "b", "c" };
        Collection<Object> unique = testObject.unique( input );
        assertThat( unique.size(), is( 3 ) );
        assertThat( unique, containsInAnyOrder( expected ) );
        assertThat( Collections.frequency( unique, "c" ), is( 1 ) );
        
        input = new Integer[] { 1, 1 };
        expected = new Integer[] { 1 };
        unique = testObject.unique( input );
        assertThat( unique.size(), is( 1 ) );
        assertThat( unique, containsInAnyOrder( expected ) );
        assertThat( Collections.frequency( unique, 1 ), is( 1 ) );
        
        input = new Integer[] { 2, 2, 2 };
        expected = new Integer[] { 2 };
        unique = testObject.unique( input );
        assertThat( unique.size(), is( 1 ) );
        assertThat( unique, containsInAnyOrder( expected ) );
        assertThat( Collections.frequency( unique, 2 ), is( 1 ) );
        
        input = new Integer[] { 3, 2, 3 };
        expected = new Integer[] { 3, 2 };
        unique = testObject.unique( input );
        assertThat( unique.size(), is( 2 ) );
        assertThat( unique, containsInAnyOrder( expected ) );
        assertThat( Collections.frequency( unique, 3 ), is( 1 ) );
        
        input = new Integer[] { 4, 5, 4, 5, 4, 5 };
        expected = new Integer[] { 4, 5 };
        unique = testObject.unique( input );
        assertThat( unique.size(), is( 2 ) );
        assertThat( unique, containsInAnyOrder( expected ) );
        assertThat( Collections.frequency( unique, 4 ), is( 1 ) );
        assertThat( Collections.frequency( unique, 5 ), is( 1 ) );
    }
    
    @Test
    public void testUniqueCollectionOfT()
    {
        Collection<Object> input = Arrays.asList( (Object) "a", "b", "c", "c" );
        Object[] expected = { "a", "b", "c" };
        Collection<Object> unique = testObject.unique( input );
        assertThat( unique.size(), is( 3 ) );
        assertThat( unique, containsInAnyOrder( expected ) );
        assertThat( Collections.frequency( unique, "c" ), is( 1 ) );
        
        input = Arrays.asList( (Object) 1, 1 );
        expected = new Integer[] { 1 };
        unique = testObject.unique( input );
        assertThat( unique.size(), is( 1 ) );
        assertThat( unique, containsInAnyOrder( expected ) );
        assertThat( Collections.frequency( unique, 1 ), is( 1 ) );
        
        input = Arrays.asList( (Object) 2, 2, 2 );
        expected = new Integer[] { 2 };
        unique = testObject.unique( input );
        assertThat( unique.size(), is( 1 ) );
        assertThat( unique, containsInAnyOrder( expected ) );
        assertThat( Collections.frequency( unique, 2 ), is( 1 ) );
        
        input = Arrays.asList( (Object) 3, 2, 3 );
        expected = new Integer[] { 3, 2 };
        unique = testObject.unique( input );
        assertThat( unique.size(), is( 2 ) );
        assertThat( unique, containsInAnyOrder( expected ) );
        assertThat( Collections.frequency( unique, 3 ), is( 1 ) );
        
        input = Arrays.asList( (Object) 4, 5, 4, 5, 4, 5 );
        expected = new Integer[] { 4, 5 };
        unique = testObject.unique( input );
        assertThat( unique.size(), is( 2 ) );
        assertThat( unique, containsInAnyOrder( expected ) );
        assertThat( Collections.frequency( unique, 4 ), is( 1 ) );
        assertThat( Collections.frequency( unique, 5 ), is( 1 ) );
    }
    
    @Test
    public void testCombineTArrayTArray()
    {
        Object[] input1 = { "a", "b", "c" };
        Object[] input2 = { "a", "b", "c" };
        Object[] expectedElements = { "a", "b", "c", "a", "b", "c" };
        Collection<Object> combined = testObject.combine( input1, input2 );
        assertThat( combined.size(), is( input1.length + input2.length ) );
        assertThat( combined, containsInAnyOrder( expectedElements ) );
        assertThat( Collections.frequency( combined, "a" ), is( 2 ) );
        assertThat( Collections.frequency( combined, "b" ), is( 2 ) );
        assertThat( Collections.frequency( combined, "c" ), is( 2 ) );
        
        input1 = new Object[] { "a", "b", "c" };
        input2 = new Object[] { "d", "e" };
        expectedElements = new Object[] { "a", "b", "c", "d", "e" };
        combined = testObject.combine( input1, input2 );
        assertThat( combined.size(), is( input1.length + input2.length ) );
        assertThat( combined, containsInAnyOrder( expectedElements ) );
        
        input1 = new Object[] { 1, 2, 3 };
        input2 = new Object[] { 3, 4, 5, 6 };
        expectedElements = new Object[] { 1, 2, 3, 3, 4, 5, 6 };
        combined = testObject.combine( input1, input2 );
        assertThat( combined.size(), is( input1.length + input2.length ) );
        assertThat( combined, containsInAnyOrder( expectedElements ) );
        assertThat( Collections.frequency( combined, 3 ), is( 2 ) );
    }
    
    @Test
    public void testCombineCollectionOfTCollectionOfT()
    {
        Collection<Object> input1 = Arrays.asList( (Object) "a", "b", "c" );
        Collection<Object> input2 = Arrays.asList( (Object) "a", "b", "c" );
        Object[] expectedElements = { "a", "b", "c", "a", "b", "c" };
        Collection<Object> combined = testObject.combine( input1, input2 );
        assertThat( combined.size(), is( input1.size() + input2.size() ) );
        assertThat( combined, containsInAnyOrder( expectedElements ) );
        assertThat( Collections.frequency( combined, "a" ), is( 2 ) );
        assertThat( Collections.frequency( combined, "b" ), is( 2 ) );
        assertThat( Collections.frequency( combined, "c" ), is( 2 ) );
        
        input1 = Arrays.asList( (Object) "a", "b", "c" );
        input2 = Arrays.asList( (Object) "d", "e" );
        expectedElements = new Object[] { "a", "b", "c", "d", "e" };
        combined = testObject.combine( input1, input2 );
        assertThat( combined.size(), is( input1.size() + input2.size() ) );
        assertThat( combined, containsInAnyOrder( expectedElements ) );
        
        input1 = Arrays.asList( (Object) 1, 2, 3 );
        input2 = Arrays.asList( (Object) 3, 4, 5, 6 );
        expectedElements = new Object[] { 1, 2, 3, 3, 4, 5, 6 };
        combined = testObject.combine( input1, input2 );
        assertThat( combined.size(), is( input1.size() + input2.size() ) );
        assertThat( combined, containsInAnyOrder( expectedElements ) );
        assertThat( Collections.frequency( combined, 3 ), is( 2 ) );
    }
    
}
