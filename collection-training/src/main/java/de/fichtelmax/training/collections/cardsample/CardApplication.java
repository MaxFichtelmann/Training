package de.fichtelmax.training.collections.cardsample;

public class CardApplication
{
    public static void main( String[] args )
    {
        Deck skatDeck = Deck.newSkatDeck();
        skatDeck.shuffle();
        
        for ( int i = 0; i < 27; i++ )
        {
            skatDeck.draw();
        }
        
        System.out.println( skatDeck.size() );
        System.out.println( skatDeck );
        
    }
}
