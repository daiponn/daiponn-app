package com.kasahara;

import com.kasahara.enums.Card1;

public class EnumTutorial {

    public static void main(String[] args){
        EnumTutorial enumTutorial = new EnumTutorial();
        enumTutorial.TestEnumSwitch();
        System.out.println(" ");
        enumTutorial.TestEnum();
    }

    //swich文でのEnumの使い方
    public void TestEnumSwitch(){
        //Card1はEnumとして定義
        Card1 card = Card1.SPADES;
        switch(card){
            case SPADES:
            case CLUBS:
                System.out.println("black");
                break;
            case DIAMONDS:
            case HEARTS:
                System.out.println("red");
                break;
        }
    }

    public void TestEnum(){
        Card1 card = Card1.HEARTS;
        System.out.println(card);
        System.out.println(card.getA());
        System.out.println(card.ordinal());
        for(Card1 obj : Card1.values() ){
            System.out.println(obj + " ");
        }
        card.hoge();
    }

}
