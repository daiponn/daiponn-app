package com.kasahara.JavaGoldTutorial.Part3;

public class GenericsTutorial <T> {
    private T var1;
    //private T var2;   コンパイルエラー（staticなメンバ変数には「T」を使用できない）

    public GenericsTutorial(T var1){ this.var1 = var1; }

    public T getVar1(){ return this.var1; }

    public void setVar1(T var1){ this.var1 = var1; }

    //ジェネリクスは、メソッドでも定義可能
    //クラス定義で利用した同じ「T」で宣言可能っぽい。
    // その場合、仮にクラスを<String>でnewしていても、本メソッドの引数には別の型（<Interger>などを可能　→　36行目
    public <T> T method(T value){return value;}


    public static void main(String[] args) {
        GenericsTutorial<String> genstring = new GenericsTutorial<>("kasahara");
        System.out.println(genstring.getVar1());
        genstring.setVar1("daisuke");
        System.out.println(genstring.getVar1());

        GenericsTutorial<Integer> genint = new GenericsTutorial<>(1);
        System.out.println(genint.getVar1());
        genint.setVar1(2);
        System.out.println(genint.getVar1());

        GenericsTutorial<String> genericsTutorial = new GenericsTutorial<>("daiponn");
        System.out.println(genericsTutorial.getVar1());
        System.out.println(genericsTutorial.method("hoge"));
        //冗長だが、以下のような書き方も可能
        System.out.println(genericsTutorial.<String>method("hoge"));
        //methodメソッドは、<String>以外でも利用できる
        System.out.println(genericsTutorial.method(20));


    }
}
