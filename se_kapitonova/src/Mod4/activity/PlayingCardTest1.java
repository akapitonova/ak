package Mod4.activity;

import junit.framework.TestCase;

public class PlayingCardTest1 extends TestCase {

    public void testPlayingCard() {
        PlayingCard playingCard = new PlayingCard();
        assertEquals("JOKER", playingCard.toString());
        assertEquals(PlayingCard.JOKER, playingCard.getNumber());
        assertEquals(PlayingCard.JOKER, playingCard.getSuit());

        PlayingCard playingCard1 = new PlayingCard(PlayingCard.FIVE, PlayingCard.HEARTS);
        assertEquals("FIVE HEARTS", playingCard1.toString());
        assertEquals(PlayingCard.FIVE, playingCard1.getNumber());
        assertEquals(PlayingCard.HEARTS, playingCard1.getSuit());

    }
}
