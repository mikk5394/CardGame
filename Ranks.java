public enum Ranks {

    TWO (11),
    THREE (11),
    FOUR (1),
    FIVE (2),
    SIX (3),
    SEVEN (4),
    EIGHT (5),
    NINE (6),
    TEN (11),
    JACK (7),
    QUEEN (8),
    KING (9),
    ACE (10);

    private int number;

    Ranks(int number){
        this.number = number;
    }

    public int getNumber(){
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
