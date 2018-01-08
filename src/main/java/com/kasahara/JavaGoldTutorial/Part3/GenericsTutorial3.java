package com.kasahara.JavaGoldTutorial.Part3;

// 「T」の候補となるオブジェクトを限定したい場合は、extendsを用いる。
//　以下の場合だと、呼び出し側ではHumanまたはHumanのサブクラスしか、<>内に入れることができなくなる。
public class GenericsTutorial3<T extends Human> {  //Humanはインターフェースだが、extendsを使う。

    public T method(T t){
        return t;
    }

    public static void main(String[] args) {
        Man man = new Man();
        Woman woman = new Woman();

        Man man1 = new GenericsTutorial3<Man>().method(man); //Manは<>内に入れることが可能
        man1.walk();
        man1.eat();

        Woman woman1 = new GenericsTutorial3<Woman>().method(woman); //Womanも<>内に入れる事が可能
        woman1.walk();
        woman1.eat();

        //String s = new GenericsTutorial3<String>().method("kasahara");
        // 上記はコンパイルエラー　　　StringはHumanのサブクラスではないので、<>内に代入不可

    }

}


interface Human{
    void walk();
    void eat();
}

class Man implements Human{
    @Override
    public void walk(){
        System.out.println("1メートル進んだ");
    }

    @Override
    public void eat(){
        System.out.println("肉を食べた");
    }

}

class Woman implements Human{
    @Override
    public void walk() {
        System.out.println("0.5メートル進んだ");
    }

    @Override
    public void eat() {
        System.out.println("マカロンを食べた");
    }



}