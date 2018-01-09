package com.kasahara.JavaGoldTutorial.Part3;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class ComparatorTutorial  {

    public static void main(String[] args) {
        //ComparableTutorialと同様の処理を、comparatorを使って行おうとした場合。
        //以下のコメントを外すと実行時エラー（コンパイルは通る）が発生
        //Cクラスは、Myruleクラスでcomparatorインターフェースを実装しているが、Cクラス自体はcomparableインターフェースを実装していないため、自然順序付けは定義されておらず、TreeSetには格納できない。
//        Set<C> setC = new TreeSet<>();
//        setC.add(new C(1));
//        setC.add(new C(10));
//        setC.add(new C(5));
//        System.out.println(setC);

        //comparatorインターフェースを使うのは、次のような場合。
    }
}




class C {
    private int i;

    public C(int i){
        this.i = i;
    }

    public int getI(){
        return this.i;
    }

    @Override
    public String toString(){
        return String.valueOf(i);
    }


}


class Myrule implements Comparator<C>{

    @Override
    public int compare(C c1, C c2) {
        return Integer.compare(c1.getI(), c2.getI());
    }
}