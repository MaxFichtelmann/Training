package de.fichtelmax.training.collections.cardsample;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Card implements Comparable<Card>
{
    public static final Card JOKER = new Card();
    
    private Suit             suit;
    private Rank             rank;
    
    private boolean          joker;
    
    private Card()
    {
        this.joker = true;
    }
    
    public Card( Rank rank, Suit suit )
    {
        this.suit = suit;
        this.rank = rank;
        
        this.joker = false;
    }
    
    public Suit getSuit()
    {
        return suit;
    }
    
    public Rank getRank()
    {
        return rank;
    }
    
    public boolean isJoker()
    {
        return joker;
    }
    
    public int compareTo( Card that )
    {
        // primary indicator: suit
        if ( this.suit != that.suit )
        {
            return this.suit.ordinal() - that.suit.ordinal();
        }
        
        // secondary indicator: rank
        return this.rank.ordinal() - that.rank.ordinal();
    }
    
    @Override
    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode( this );
    }
    
    @Override
    public boolean equals( Object that )
    {
        return EqualsBuilder.reflectionEquals( this, that );
    }
    
    @Override
    public String toString()
    {
        return rank + " of " + suit;
    }
}
