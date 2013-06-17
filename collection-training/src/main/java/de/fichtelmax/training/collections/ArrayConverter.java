package de.fichtelmax.training.collections;

import java.util.Collection;
import java.util.List;

/**
 * Interface for training tasks using collections to represent array structures.
 * 
 * @param <T>
 *            The type used by the arrays/Collections.
 */
public interface ArrayConverter<T>
{
    /**
     * Convert an array to a {@link List}. The List should contain all elements of the array in the same order.
     * 
     * @param array
     *            The array to be converted.
     * 
     * @return The {@link List} containing the data of the given Array.
     */
    List<T> convert( T[] array );
    
    /**
     * Extract all unique elements from the given data.
     * 
     * @param input
     *            the input data
     * 
     * @return the unique elements of the input data.
     */
    Collection<T> unique( T[] input );
    
    /**
     * Extract all unique elements from the given data.
     * 
     * @param input
     *            the input data
     * 
     * @return the unique elements of the input data.
     */
    Collection<T> unique( Collection<T> input );
    
    /**
     * Combine the given arrays and return the union.
     * 
     * @param first
     *            The first array
     * @param second
     *            The second array
     * 
     * @return The union of the given arrays.
     */
    Collection<T> combine( T[] first, T[] second );
    
    /**
     * Combine the given collections and return the union.
     * 
     * @param first
     *            The first collection
     * @param second
     *            The second collection
     * 
     * @return The union of the given collections.
     */
    Collection<T> combine( Collection<T> first, Collection<T> second );
}
