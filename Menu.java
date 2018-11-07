package com.company;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in);
    private ArrayList<Player> numOfPlayers = new ArrayList<>();

    public Menu(){
        System.out.println("Welcome to SHITFACE! \nSelect the number of players (4 is max):");

        int numPlayers;
        while (true){
            try {

                numPlayers = sc.nextInt();
                if (numPlayers >= 2 && numPlayers <= 4){
                    System.out.println("Number of players: " + numPlayers);
                    System.out.println();
                    break;
                }
                System.out.println("Number of players must be between 2 and 4.");

            } catch (InputMismatchException ime){
                System.out.println("Input must be a number.");
                sc.nextLine();
            }
        }

        //setupGame(numPlayers);
        // HARDCODER 2 SPILLERE TIL TEST
        Player player1 = new Player("Mikkel");
        numOfPlayers.add(player1);
        Player player2 = new Player("Anders");
        numOfPlayers.add(player2);

        dealStart(numOfPlayers);

        //Makes sure every player gets the option to change their front cards.
        for (Player p: numOfPlayers){
            changeFrontStart(p);
        }


        System.out.println("Player: " + checkWhoStarts(numOfPlayers) + " starts as he has the lowest card.");

        playTillNoDeck(numOfPlayers);

    }
    //missing functionality for 3 and 4 players
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

        boolean cardsLeft = true;
        while (cardsLeft){

        }
    }
}
