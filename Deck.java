import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private static final Deck deckObject = new Deck();
    private static ArrayList<Card> deckOfCards = new ArrayList<>();
    private static ArrayList<Card> pileOfCards = new ArrayList<>();

    private Deck (){ }

    public static Deck getDeck(){

        for (Suits s : Suits.values()){
            for (Ranks r : Ranks.values()){
                Card c = new Card(s,r);
                deckOfCards.add(c);
            }
        }

        Collections.shuffle(deckOfCards);

        return deckObject;
    }

    public static void giveCardEnd(Player player){

        for (int i = 0; i < 3; i++){

            double d = (Math.random()*Deck.deckSizeChecker());
            int randomCard = (int) d;

            player.getEnd().add(Deck.getDeckOfCards().get(randomCard));
            Deck.removeCard(randomCard);
        }
    }

    public static void giveCardFront(Player player){

        for (int i= 0; i < 3; i++){

            double d = (Math.random()*Deck.deckSizeChecker());
            int randomCard = (int) d;

            player.getFront().add(Deck.getDeckOfCards().get(randomCard));
            Deck.removeCard(randomCard);
        }
    }

    public static void giveCardHand(Player player, int numberOfCards){

        for (int i= 0; i < numberOfCards; i++){

            double d = (Math.random()*Deck.deckSizeChecker());
            int randomCard = (int) d;

            player.getCurrentHand().add(Deck.getDeckOfCards().get(randomCard));
            Deck.removeCard(randomCard);
        }
    }

    public static ArrayList<Card> getDeckOfCards() {
        return deckOfCards;
    }

    public static ArrayList<Card> getPileOfCards() {
        return pileOfCards;
    }

    public static void removeCard(int index){
        deckOfCards.remove(index);
    }

    public static int deckSizeChecker(){
        return Deck.getDeckOfCards().size();
    }

    public static Card getTopCard (){
        return Deck.getPileOfCards().get(Deck.getPileOfCards().size()-1);
    }

    @Override
    public String toString() {
        return "Deck " + deckOfCards;
    }
}
