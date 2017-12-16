package com.kasahara.enums;

public enum Card1 {
    SPADES(3){ public void hoge(){ System.out.println("hoge spades"); } },
    CLUBS(1){ public void hoge(){ System.out.println("hoge clubs"); } },
    DIAMONDS(4){ public void hoge(){ System.out.println("hoge diamonds"); } },
    HEARTS(2){ public void hoge(){ System.out.println("hoge heats"); } };
    private int a;

    //コンストラクタを定義可能
    Card1(int a){ this.a = a; }

    //メソッドも定義可能
    public int getA(){ return a; }

    //抽象メソッドも定義可能（ただし、上記のようにオーバーライドが必要）
    public abstract void hoge();

}
