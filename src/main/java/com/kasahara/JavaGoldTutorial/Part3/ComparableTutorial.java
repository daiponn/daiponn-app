package com.kasahara.JavaGoldTutorial.Part3;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class ComparableTutorial {
    public static void main(String[] args) {
        Set<A> setA = new TreeSet<>();
        setA.add(new A(1));
        setA.add(new A(10));
        setA.add(new A(5));
        System.out.println(setA);// 1,5,10の順で表示される。

        //以下のコメントを外すと実行時エラー（コンパイルは通る）が発生
        //Bクラスは、comparableインターフェースを実装していないため、自然順序付けが定義されておらず、TreeSetには格納できない。
//        Set<B> setB = new TreeSet<>();
//        setB.add(new B(1));
//        setB.add(new B(10));
//        setB.add(new B(5));
//        System.out.println(setB);


//      以下はイテレーターの練習をしたかっただけなので気にしないでOK
        Iterator<A> iterA = setA.iterator();
        while(iterA.hasNext()){
            System.out.println(iterA.next()); //1, 5, 10　の順で出力される。
        }
    }
}


class A implements Comparable<A>{
    private int i;

    public A(int i){
        this.i = i;
    }

    @Override
    public String toString(){
        return String.valueOf(i);
    }

    @Override
    public int compareTo(A anotherA) {
        return Integer.compare(this.i, anotherA.i);
//        Integerのcompareメソッドを使わないなら、自分で以下のように定義する。
//        return (this.i < anotherA.i) ? -1 : ((this.i == anotherA.i) ? 0 : 1);
    }
}

//Comparableインターフェースを実装していない以外は、クラスAと同じ内容のクラスB
class B {
    private int i;

    public B(int i){
        this.i = i;
    }

    @Override
    public String toString(){
        return String.valueOf(i);
    }
}