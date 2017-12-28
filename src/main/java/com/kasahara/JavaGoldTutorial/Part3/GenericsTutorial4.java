package com.kasahara.JavaGoldTutorial.Part3;

import java.util.ArrayList;
import java.util.List;



public class GenericsTutorial4 {
    //このメソッドの実行に必要な引数は、「Xクラス、またはそのサブクラスが入ったリストです」と宣言している。
    //それ以外のオブジェクトが入ったリストを渡すとコンパイルエラーとなる。　→　33行目
    public static void method1(List<? extends X> list){
        System.out.println(list.get(0));
        //list.add(new X()); //コンパイルエラー　list内の型はXより小さい型（例えばZなど)の可能性もあるため、それより大きいXは入れられない　
        //list.add(new Y()); //コンパイルエラー　list内の型はYとは異なる型(例えばZなど)の可能性もあるため、Yは入れららない。
    }

    //このメソッドの実行に必要な引数は、「Yクラス、またはそのスーパークラスが入ったリストです」と宣言している。
    //それ以外のオブジェクトが入ったリストを渡すとコンパイルエラーとなる。
    public static void method2(List<? super Y> list){
        System.out.println(list.get(0));
        //list.add(new X()); //コンパイルエラー　list内の型はYである可能性もあるため、それより大きいXは入れられない。
        list.add(new Y()); //コンパイル成功　　list内の型はYもしくはYより大きいことが確定しているため、Yを入れる分には問題ないため。
        System.out.println(list.get(1));
    }

    public static void main(String[] args) {
        List<X> list1 = new ArrayList<>();  list1.add(new X());
        List<Y> list2 = new ArrayList<>();  list2.add(new Y());
        List<String> list3 = new ArrayList<>(); list3.add("kasahara");

        System.out.println("---method1の実行---");
        method1(list1);
        method1(list2);
        //method1(list3); コンパイルエラー　String型のリストは、引数に入れることができない。

        System.out.println("---method2の実行---");
        method2(list1);
        method2(list2);
        //method2(list3); コンパイルエラー　String型のリストは、引数に入れることができない
    }
}



class X {
    @Override
    public String toString() {
        return "X";
    }
}

class Y extends X{
    @Override
    public String toString() {
        return "Y";
    }
}

class Z extends X{
    @Override
    public String toString() {
        return "Z";
    }
}