package com.company;

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

    public ArrayList<Card> getFront() {
        return front;
    }

    public ArrayList<Card> getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return name;
    }

}
