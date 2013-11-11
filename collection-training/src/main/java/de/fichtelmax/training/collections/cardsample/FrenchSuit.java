package de.fichtelmax.training.collections.cardsample;

import org.apache.commons.lang3.text.WordUtils;

public enum FrenchSuit implements Suit
{
    // french suits
    SPADES, CLUBS, HEARTS, DIAMONDS;
    
    public String toString()
    {
        return WordUtils.capitalizeFully( this.name() );
    }
}