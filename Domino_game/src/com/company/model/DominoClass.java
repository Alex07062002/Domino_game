package com.company.model;

public class DominoClass {
    private int number1;
    private int number2;
    private final int point;

    public DominoClass(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
        this.point = number1+number2;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public int getPoint() {
        return point;
    }

    @Override
    public String toString(){ return (getNumber1()+"/"+getNumber2()); }

    public void isReverse(){
        int reverse = this.getNumber1();
        this.setNumber1(this.getNumber2());
        this.setNumber2(reverse);
    }
    public Boolean isHave(int number){
        return (number == getNumber1()) || (number == getNumber2());
    }

    public boolean equals(DominoClass second) {
        return ((this.getNumber1() == second.getNumber1()) && (this.getNumber2() == second.getNumber2()));
    }
}
