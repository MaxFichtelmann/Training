package de.fichtelmax.training.collections.cardsample;

import org.apache.commons.lang3.text.WordUtils;

public enum DefaultRank implements Rank
{
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;
    
    public String toString()
    {
        return WordUtils.capitalizeFully( this.name() );
    }
}