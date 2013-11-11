package de.fichtelmax.training.collections.cardsample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class Deck
{
    private List<Card> internalDeck;
    
    public Deck( Set<DefaultRank> usedRanks, Set<? extends Suit> usedSuits, int numberOfJokers )
    {
        List<Card> deck = new ArrayList<Card>();
        
        for ( Suit suit : usedSuits )
        {
            for ( DefaultRank rank : usedRanks )
            {
                deck.add( new Card( rank, suit ) );
            }
        }
        
        for ( int i = 0; i < numberOfJokers; i++ )
        {
            deck.add( Card.JOKER );
        }
        
        this.internalDeck = deck;
    }
    
    @Override
    public String toString()
    {
        return internalDeck.toString();
    }
    
    public void shuffle()
    {
        Collections.shuffle( internalDeck );
    }
    
    public boolean isEmpty()
    {
        return internalDeck.isEmpty();
    }
    
    public int size()
    {
        return internalDeck.size();
    }
    
    public Card draw()
    {
        return internalDeck.remove( 0 );
    }
    
    public static Deck newSkatDeck()
    {
        Set<DefaultRank> skatRanks = EnumSet.range( DefaultRank.SEVEN, DefaultRank.ACE );
        Set<GermanSuit> skatSuits = EnumSet.allOf( GermanSuit.class );
        
        return new Deck( skatRanks, skatSuits, 0 );
    }
}
