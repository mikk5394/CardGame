import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in);
    private ArrayList<Player> numOfPlayers = new ArrayList<>();

    public Menu(){

        // HARDCODER 2 SPILLERE TIL TEST
        Player player1 = new Player("Mikkel");
        numOfPlayers.add(player1);
        Player player2 = new Player("TestSpiller");
        numOfPlayers.add(player2);

        dealStart(numOfPlayers);

        //Makes sure every player gets the option to change their front cards.
        for (Player p: numOfPlayers){
            changeFrontStart(p);
        }

        System.out.println("Player: " + checkWhoStarts(numOfPlayers) + " starts as he has the lowest card.");

        playTillNoDeck(numOfPlayers);
    }

    public void setupGame(int numPlayers){
        Scanner nameInput = new Scanner(System.in);
        if(numPlayers == 2){

            System.out.println("Type a name for player 1");
            String name1 = nameInput.nextLine();
            Player player1 = new Player(name1);
            System.out.println("Type a name for player 2");
            String name2 = nameInput.nextLine();
            Player player2 = new Player(name2);

            System.out.println("Players in the game:");
            System.out.println(player1.getName() + ", " + player2.getName());
            numOfPlayers.add(player1);
            numOfPlayers.add(player2);

        }

    }

    public void dealStart(ArrayList<Player> list){

        int numOfPlayers = list.size();

        for (int i = 0; i < numOfPlayers; i++){
            Deck.giveCardEnd(list.get(i));
            Deck.giveCardFront(list.get(i));
            System.out.println("Front 3 cards for " + list.get(i).getName() + " are: " + list.get(i).getFront());
            Deck.giveCardHand(list.get(i),3);
            System.out.println("Starting hand for " + list.get(i).getName() + " is the following: " + list.get(i).getCurrentHand());
            System.out.println();
        }
    }

    public void changeFrontStart(Player player){
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);

        System.out.println(player.getName() + ", do you wish to change your front? yes/no");

        String answer;
        String cardNumber;
        String cardInHand;
        boolean dealDone = false;
        boolean cardReplaceDone = false;
        boolean cardReplaceDone2 = false;

        while (!dealDone){

            answer = sc.nextLine();
            if (answer.equalsIgnoreCase("yes")){

                while (!cardReplaceDone) {
                    System.out.println("Which card do you want to change? " + player.getFront() + " Press 1 for the first card, 2 for the second, 3 for the third and Q to quit.");
                    cardNumber = sc2.nextLine();

                    if (cardNumber.equals("1")) {
                        System.out.println("What do you want to replace " + player.getFront().get(0) + " with?");
                        System.out.println("You have the following 3 options: " + player.getCurrentHand() + ". Press 1 for the first card, 2 for the second, 3 for the third and Q to quit.");

                        while (!cardReplaceDone2) {
                                cardInHand = sc3.nextLine();
                                if (cardInHand.equals("1")) {
                                    //Adds the card to the front and the front card gets added to the hand
                                    player.getFront().add(0, player.getCurrentHand().get(0));
                                    player.getCurrentHand().add(player.getFront().get(1));
                                    //removing duplicates
                                    player.getFront().remove(1);
                                    player.getCurrentHand().remove(0);
                                    System.out.println("Change done.\nCurrent front: " + player.getFront() + " and current hand: " + player.getCurrentHand());
                                    break;

                                } else if (cardInHand.equals("2")) {
                                    player.getFront().add(0, player.getCurrentHand().get(1));
                                    player.getCurrentHand().add(player.getFront().get(1));
                                    player.getFront().remove(1);
                                    player.getCurrentHand().remove(1);
                                    System.out.println("Change done.\nCurrent front: " + player.getFront() + " and current hand: " + player.getCurrentHand());
                                    break;

                                } else if (cardInHand.equals("3")) {
                                    player.getFront().add(0, player.getCurrentHand().get(2));
                                    player.getCurrentHand().add(player.getFront().get(1));
                                    player.getFront().remove(1);
                                    player.getCurrentHand().remove(2);
                                    System.out.println("Change done.\nCurrent front: " + player.getFront() + " and current hand: " + player.getCurrentHand());
                                    break;

                                } else {
                                    System.out.println("Selected card has to be between 1 and 3.");
                                }
                            }
                    } else if (cardNumber.equals("2")) {
                        System.out.println("What do you want to replace " + player.getFront().get(1) + " with?");
                        System.out.println("You have the following 3 options: " + player.getCurrentHand() + ". Press 1 for the first card, 2 for the second, 3 for the third and Q to quit.");

                        while (!cardReplaceDone2) {
                                cardInHand = sc3.nextLine();
                                if (cardInHand.equals("1")) {

                                    player.getFront().add(1, player.getCurrentHand().get(0));
                                    player.getCurrentHand().add(player.getFront().get(2));
                                    player.getFront().remove(2);
                                    player.getCurrentHand().remove(0);
                                    System.out.println("Change done.\nCurrent front: " + player.getFront() + " and current hand: " + player.getCurrentHand());
                                    break;

                                } else if (cardInHand.equals("2")) {
                                    player.getFront().add(1, player.getCurrentHand().get(1));
                                    player.getCurrentHand().add(player.getFront().get(2));
                                    player.getFront().remove(2);
                                    player.getCurrentHand().remove(1);
                                    System.out.println("Change done.\nCurrent front: " + player.getFront() + " and current hand: " + player.getCurrentHand());
                                    break;

                                } else if (cardInHand.equals("3")) {
                                    player.getFront().add(1, player.getCurrentHand().get(2));
                                    player.getCurrentHand().add(player.getFront().get(2));
                                    player.getFront().remove(2);
                                    player.getCurrentHand().remove(2);
                                    System.out.println("Change done.\nCurrent front: " + player.getFront() + " and current hand: " + player.getCurrentHand());
                                    break;

                                } else {
                                    System.out.println("Selected card has to be between 1 and 3.");
                                }
                            }
                    } else if (cardNumber.equals("3")) {
                        System.out.println("What do you want to replace " + player.getFront().get(2) + " with?");
                        System.out.println("You have the following 3 options: " + player.getCurrentHand() + ". Press 1 for the first card, 2 for the second, 3 for the third and Q to quit.");

                        while (!cardReplaceDone2) {
                            cardInHand = sc3.nextLine();
                            if (cardInHand.equals("1")) {

                                player.getFront().add(2, player.getCurrentHand().get(0));
                                player.getCurrentHand().add(player.getFront().get(3));
                                player.getFront().remove(3);
                                player.getCurrentHand().remove(0);
                                System.out.println("Change done.\nCurrent front: " + player.getFront() + " and current hand: " + player.getCurrentHand());
                                break;

                            } else if (cardInHand.equals("2")) {
                                player.getFront().add(2, player.getCurrentHand().get(1));
                                player.getCurrentHand().add(player.getFront().get(3));
                                player.getFront().remove(3);
                                player.getCurrentHand().remove(1);
                                System.out.println("Change done.\nCurrent front: " + player.getFront() + " and current hand: " + player.getCurrentHand());
                                break;

                            } else if (cardInHand.equals("3")) {
                                player.getFront().add(2, player.getCurrentHand().get(2));
                                player.getCurrentHand().add(player.getFront().get(3));
                                player.getFront().remove(3);
                                player.getCurrentHand().remove(2);
                                System.out.println("Change done.\nCurrent front: " + player.getFront() + " and current hand: " + player.getCurrentHand());
                                break;

                            } else {
                                System.out.println("Selected card has to be between 1 and 3.");
                            }
                        }
                    } else {
                        if (cardNumber.equals("Q")) {
                            cardReplaceDone = true;
                            dealDone = true;
                        } else {
                            System.out.println("Selected card has to be between 1 and 3.");
                        }
                    }
                }
            } else if (answer.equalsIgnoreCase("no")){
                break;
            } else {
                if (dealDone == true){
                    break;
                }
                System.out.println("Your answer has to be either yes or no.");
            }
        }
        System.out.println(player.getName() + "s current front is now: " + player.getFront() + " and his hand is the following: " + player.getCurrentHand() + "\n");
    }

    public Player checkWhoStarts(ArrayList<Player> list) {

        for (int i = 0; i < list.size(); i++) {
            for (int k = 0; k < list.get(i).getCurrentHand().size(); k++) {
                if (list.get(i).getCurrentHand().get(k).getRank().equals(Ranks.FOUR))
                    return list.get(i);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            for (int k = 0; k < list.get(i).getCurrentHand().size(); k++) {
                if (list.get(i).getCurrentHand().get(k).getRank().equals(Ranks.FIVE))
                    return list.get(i);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            for (int k = 0; k < list.get(i).getCurrentHand().size(); k++) {
                if (list.get(i).getCurrentHand().get(k).getRank().equals(Ranks.SIX))
                    return list.get(i);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            for (int k = 0; k < list.get(i).getCurrentHand().size(); k++) {
                if (list.get(i).getCurrentHand().get(k).getRank().equals(Ranks.SEVEN))
                    return list.get(i);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            for (int k = 0; k < list.get(i).getCurrentHand().size(); k++) {
                if (list.get(i).getCurrentHand().get(k).getRank().equals(Ranks.EIGHT))
                    return list.get(i);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            for (int k = 0; k < list.get(i).getCurrentHand().size(); k++) {
                if (list.get(i).getCurrentHand().get(k).getRank().equals(Ranks.NINE))
                    return list.get(i);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            for (int k = 0; k < list.get(i).getCurrentHand().size(); k++) {
                if (list.get(i).getCurrentHand().get(k).getRank().equals(Ranks.JACK))
                    return list.get(i);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            for (int k = 0; k < list.get(i).getCurrentHand().size(); k++) {
                if (list.get(i).getCurrentHand().get(k).getRank().equals(Ranks.QUEEN))
                    return list.get(i);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            for (int k = 0; k < list.get(i).getCurrentHand().size(); k++) {
                if (list.get(i).getCurrentHand().get(k).getRank().equals(Ranks.KING))
                    return list.get(i);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            for (int k = 0; k < list.get(i).getCurrentHand().size(); k++) {
                if (list.get(i).getCurrentHand().get(k).getRank().equals(Ranks.TWO))
                    return list.get(i);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            for (int k = 0; k < list.get(i).getCurrentHand().size(); k++) {
                if (list.get(i).getCurrentHand().get(k).getRank().equals(Ranks.THREE))
                    return list.get(i);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            for (int k = 0; k < list.get(i).getCurrentHand().size(); k++) {
                if (list.get(i).getCurrentHand().get(k).getRank().equals(Ranks.TEN))
                    return list.get(i);
            }
        }
        return null;
    }

    public void playTillNoDeck(ArrayList<Player> list){

        //makes sure the player with the lowest card starts.
        ArrayList<Player> players = new ArrayList<>();
        players.add(list.get(0));
        players.add(list.get(1));
        Player player1 = checkWhoStarts(players);
        players.remove(player1);
        Player player2 = players.get(0);

        newLine();

        while (!Deck.getDeckOfCards().isEmpty()){

            throwCardInPile(player1);
            player1.getNewCard(player1);
            newLine();
            System.out.println("Top card in pile: " + Deck.getPileOfCards().get(Deck.getPileOfCards().size()-1));

            throwCardInPile(player2);
            player1.getNewCard(player2);
            newLine();
            System.out.println("Top card in pile: " + Deck.getPileOfCards().get(Deck.getPileOfCards().size()-1));

        }
    }

    public void throwCardInPile(Player player) {

        Scanner input = new Scanner(System.in);
        String answer;
        System.out.println(player.getName() + ", pick a card to throw in the pile: " + player.getCurrentHand() + ":");

        while (true) {

            answer = input.nextLine();

            if (answer.equals("1")) {
                if (!Deck.getPileOfCards().isEmpty()) {
                    if ((Deck.getTopCard().getRank().getNumber() <=
                            player.getCurrentHand1().getRank().getNumber())) {

                        if (player.getCurrentHand1().getRank().equals(Ranks.TWO)) {
                            Deck.getPileOfCards().add(player.getCurrentHand1());
                            player.getCurrentHand().remove(0);
                            Deck.getTopCard().getRank().setNumber(1);
                        } else if (player.getCurrentHand1().getRank().equals(Ranks.THREE)) {
                            Deck.getPileOfCards().add(player.getCurrentHand1());
                            player.getCurrentHand().remove(0);
                            //sets the three to whatever value was on top of the pile before it was thrown
                            Deck.getTopCard().getRank().setNumber(Deck.getPileOfCards().get(Deck.getPileOfCards().size() - 2).getRank().getNumber());
                        } else if (player.getCurrentHand1().getRank().equals(Ranks.TEN)) {
                            Deck.getPileOfCards().add(player.getCurrentHand1());
                            player.getCurrentHand().remove(0);
                            //removes the pile as the card TEN removes/resets the pile
                            Deck.getPileOfCards().removeAll(Deck.getPileOfCards());
                            System.out.println("Pile reset. " + player.getName() + " gets to throw another card in the pile.");
                            throwCardInPile(player);
                        } else if (player.getCurrentHand1().getRank().equals(Ranks.EIGHT)) {
                            Deck.getPileOfCards().add(player.getCurrentHand1());
                            player.getCurrentHand().remove(0);
                            //player gets another turn
                            System.out.println(player.getName() + "'s turn again.");
                            throwCardInPile(player);
                        }

                        //Code below executes if TWO, THREE, EIGHT or TEN isn't thrown in the pile.
                        Deck.getPileOfCards().add(player.getCurrentHand1());
                        player.getCurrentHand().remove(0);
                        break;
                    } else {
                        System.out.println("Selected card must be of the same value or higher, or be a 2, 3 or 10.");
                    }
                } else {
                    Deck.getPileOfCards().add(player.getCurrentHand1());
                    player.getCurrentHand().remove(0);
                    break;
                }

            } else if (answer.equals("2")) {
                if (!Deck.getPileOfCards().isEmpty()) {
                    if ((Deck.getTopCard().getRank().getNumber() <=
                            player.getCurrentHand2().getRank().getNumber())) {

                        if (player.getCurrentHand2().getRank().equals(Ranks.TWO)) {
                            Deck.getPileOfCards().add(player.getCurrentHand2());
                            player.getCurrentHand().remove(1);
                            Deck.getTopCard().getRank().setNumber(1);
                        } else if (player.getCurrentHand2().getRank().equals(Ranks.THREE)) {
                            Deck.getPileOfCards().add(player.getCurrentHand2());
                            player.getCurrentHand().remove(1);
                            //sets the three to whatever value was on top of the pile before it was thrown
                            Deck.getTopCard().getRank().setNumber(Deck.getPileOfCards().get(Deck.getPileOfCards().size() - 2).getRank().getNumber());
                        } else if (player.getCurrentHand2().getRank().equals(Ranks.TEN)) {
                            Deck.getPileOfCards().add(player.getCurrentHand2());
                            player.getCurrentHand().remove(1);
                            //removes the pile as the card TEN removes/resets the pile
                            Deck.getPileOfCards().removeAll(Deck.getPileOfCards());
                            System.out.println("Pile reset. " + player.getName() + " gets to throw another card in the pile.");
                            throwCardInPile(player);
                        } else if (player.getCurrentHand2().getRank().equals(Ranks.EIGHT)) {
                            Deck.getPileOfCards().add(player.getCurrentHand2());
                            player.getCurrentHand().remove(1);
                            //player gets another turn
                            System.out.println(player.getName() + "'s turn again.");
                            throwCardInPile(player);
                        }

                        //Code below executes if TWO, THREE, EIGHT or TEN isn't thrown in the pile.
                        Deck.getPileOfCards().add(player.getCurrentHand2());
                        player.getCurrentHand().remove(1);
                        break;
                    } else {
                        System.out.println("Selected card must be of the same value or higher, or be a 2, 3 or 10.");
                    }
                } else {
                    Deck.getPileOfCards().add(player.getCurrentHand2());
                    player.getCurrentHand().remove(1);
                    break;
                }

            } else if (answer.equals("3")) {
                if (!Deck.getPileOfCards().isEmpty()) {
                    if ((Deck.getTopCard().getRank().getNumber() <=
                            player.getCurrentHand3().getRank().getNumber())) {

                        if (player.getCurrentHand3().getRank().equals(Ranks.TWO)) {
                            Deck.getPileOfCards().add(player.getCurrentHand3());
                            player.getCurrentHand().remove(2);
                            Deck.getTopCard().getRank().setNumber(1);
                        } else if (player.getCurrentHand3().getRank().equals(Ranks.THREE)) {
                            Deck.getPileOfCards().add(player.getCurrentHand3());
                            player.getCurrentHand().remove(2);
                            //sets the three to whatever value was on top of the pile before it was thrown
                            Deck.getTopCard().getRank().setNumber(Deck.getPileOfCards().get(Deck.getPileOfCards().size() - 2).getRank().getNumber());
                        } else if (player.getCurrentHand3().getRank().equals(Ranks.TEN)) {
                            Deck.getPileOfCards().add(player.getCurrentHand3());
                            player.getCurrentHand().remove(2);
                            //removes the pile as the card TEN removes/resets the pile
                            Deck.getPileOfCards().removeAll(Deck.getPileOfCards());
                            System.out.println("Pile reset. " + player.getName() + " gets to throw another card in the pile.");
                            throwCardInPile(player);
                        } else if (player.getCurrentHand3().getRank().equals(Ranks.EIGHT)) {
                            Deck.getPileOfCards().add(player.getCurrentHand1());
                            player.getCurrentHand().remove(2);
                            //player gets another turn
                            System.out.println(player.getName() + "'s turn again.");
                            throwCardInPile(player);
                        }

                        //Code below executes if TWO, THREE, EIGHT or TEN isn't thrown in the pile.
                        Deck.getPileOfCards().add(player.getCurrentHand3());
                        player.getCurrentHand().remove(2);
                        break;
                    } else {
                        System.out.println("Selected card must be of the same value or higher, or be a 2, 3 or 10.");
                    }
                } else {
                    Deck.getPileOfCards().add(player.getCurrentHand3());
                    player.getCurrentHand().remove(2);
                    break;
                }
            }
        }
    }

    public void newLine (){
        System.out.println();
    }
}