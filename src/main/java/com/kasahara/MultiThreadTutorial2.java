package com.kasahara;

public class MultiThreadTutorial2 implements Runnable {

    private String name;

    public MultiThreadTutorial2(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 3; i >= 0 ; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            System.out.println(name + " : " + i);
        }
    }

    public static void main(String[] args) {
        MultiThreadTutorial2 cdt1 = new MultiThreadTutorial2("thread 1");
        MultiThreadTutorial2 cdt2 = new MultiThreadTutorial2("thread 2");
        Thread t1 = new Thread(cdt1);
        Thread t2 = new Thread(cdt2);
        t1.start();
        t2.start();
    }
}
