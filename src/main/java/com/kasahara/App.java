package com.kasahara;

import com.kasahara.lib.Lib;

/**
 * Hello world!
 *
 */
public class App {
//    public static void main( String[] args ) {
//        new App().printMessage("Taro");
//    }
//
//    public String getMessage(String name){
//        return "Hi," + name + ". Welcome to Maven World!";
//    }
//
//    public void printMessage(String name) {
//        System.out.println("¥n***** App class *****¥n");
//        System.out.println(this.getMessage(name));
//        System.out.println("¥n***** App class *****¥n");
//    }
      public static void main( String[] args ) {
          Lib lib = new Lib("Sachiko", "I am Happy");
          System.out.println(lib.getMessage());
      }
}

