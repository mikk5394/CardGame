import java.util.ArrayList;

public class Player {

    private ArrayList<Card> currentHand;
    private ArrayList<Card> front;
    private ArrayList<Card> end;
    private String name;
    private int winCounter;

    Player(String name){
        currentHand = new ArrayList<>();
        front = new ArrayList<>();
        end = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCurrentHand() {
        return currentHand;
    }

    public Card getCurrentHand1(){
        return currentHand.get(0);
    }

    public Card getCurrentHand2(){
        return currentHand.get(1);
    }

    public Card getCurrentHand3(){
        return currentHand.get(2);
    }

    public ArrayList<Card> getFront() {
        return front;
    }

    public ArrayList<Card> getEnd() {
        return end;
    }

    public void getNewCard(Player player){
        double d = (Math.random()*Deck.deckSizeChecker());
        int randomCard = (int) d;

        player.getCurrentHand().add(Deck.getDeckOfCards().get(randomCard));
        System.out.println(player.getName() + ", received " + Deck.getDeckOfCards().get(randomCard));
        Deck.removeCard(randomCard);
    }

    @Override
    public String toString() {
        return name;
    }

}
