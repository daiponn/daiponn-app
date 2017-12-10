package com.kasahara;

import redis.clients.jedis.Jedis;

public class JedisTutorial {
    //redis初期化のためのパラメータを準備
    private static final String LOCAL_REDIS_IP_ADDRESS = "localhost";
    private static final int REDIS_PORT = 6379; //デフォルトだとこのポート
    private static final int REDIS_CONNECT_TIMEOUT = 1000;
    private static Jedis jedis;

    public JedisTutorial(){
        this.jedis = new Jedis(LOCAL_REDIS_IP_ADDRESS, REDIS_PORT, REDIS_CONNECT_TIMEOUT);
    }

    public static void main (String[] args) throws Exception{
        JedisTutorial jedisTutorial = new JedisTutorial();
        jedis.connect();
        String resultString = jedis.set("testKey", "testValue");
        System.out.println(resultString);


    }

}
