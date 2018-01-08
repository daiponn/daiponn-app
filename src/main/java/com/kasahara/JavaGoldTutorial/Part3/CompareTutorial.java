package com.kasahara.JavaGoldTutorial.Part3;

import java.util.Arrays;
import java.util.Comparator;

public class CompareTutorial implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return s2.compareTo(s1);
    }

//    s1とs2の順番を入れ替えた場合は、降順となる。
//    @Override
//    public int compare(String s1, String s2) {
//        return s1.compareTo(s2);
//    }

    public static void main(String[] args) {
        String[] ary = {"100", "abc", "ABC"};
        Arrays.sort(ary , new CompareTutorial());
        for (String s : ary){
            System.out.println(s + " "); //100, ABC, abc の順となる。
                                         //s1とs2の順番を変えた場合は、逆になる。
        }
    }




}
