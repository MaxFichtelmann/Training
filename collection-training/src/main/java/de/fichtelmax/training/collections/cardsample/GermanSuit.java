package de.fichtelmax.training.collections.cardsample;

import org.apache.commons.lang3.text.WordUtils;

public enum GermanSuit implements Suit
{
    // german suits
    ACORNS, LEAVES, HEARTS, BELLS;
    
    public String toString()
    {
        return WordUtils.capitalizeFully( this.name() );
    }
}
