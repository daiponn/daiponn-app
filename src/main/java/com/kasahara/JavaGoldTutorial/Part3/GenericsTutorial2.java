package com.kasahara.JavaGoldTutorial.Part3;

import java.util.ArrayList;
import java.util.List;

public class GenericsTutorial2 {
    public static void main(String[] args) {
        new Foo().method("kasahara");
        new Bar().method(20);
        new Fuga<String>().method("daisuke");
        new Fuga<Integer>().method(200);
    }
}

//ジェネリクスはインターフェースにも使用
interface Hoge <T>{
    public void method(T t);
}

//String限定でHogeインターフェースを実装
class Foo implements Hoge<String>{
    @Override
    public void method(String s){
        System.out.println(s);
    }
}


//Integer限定でHoheインターフェースを実装
class Bar implements Hoge<Integer>{
    @Override
    public void method(Integer i){
        System.out.println(i);
    }
}

//型を限定せず、「T」のままHogeインターフェースを実装
class Fuga<T> implements Hoge<T>{
    @Override
    public void method(T t){
        System.out.println(t);
    }
}



