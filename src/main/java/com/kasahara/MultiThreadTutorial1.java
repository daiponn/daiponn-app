package com.kasahara;

public class MultiThreadTutorial1 extends Thread{

    private String name;

    public MultiThreadTutorial1(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 3; i >= 0 ; i--) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {}
            System.out.println(name + " : " + i);
        }
    }

    public static void main(String[] args) {
        MultiThreadTutorial1 t1 = new MultiThreadTutorial1("thread 1");
        MultiThreadTutorial1 t2 = new MultiThreadTutorial1("thread 2");
        t1.start();
        t2.start();
    }
}
