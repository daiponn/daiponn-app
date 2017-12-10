package com.kasahara;


import org.apache.commons.lang3.StringUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;


public class NetworkInterfaceTutorial {
    //ローカルの全てのインターフェースを取得し、その中から指定したインターフェースのIPアドレスを取得する。
    public static void main (String[] args) throws Exception {
        Enumeration enuIfs = NetworkInterface.getNetworkInterfaces();
        String ownIpAddress = null;
            if (enuIfs != null) {
            while (enuIfs.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface)enuIfs.nextElement();
                System.out.println(ni.getName());
                //lo0を指定
                if (StringUtils.equals("lo0", ni.getName()) ){
                    Enumeration enuAddress = ni.getInetAddresses();
                    while(enuAddress.hasMoreElements()){
                        InetAddress ip4 = (InetAddress)enuAddress.nextElement();
                        if (ip4 instanceof Inet4Address){
                            ownIpAddress = ip4.getHostAddress();
                            //正しければ、127.0.0.1が出力されるはず
                            System.out.println(ownIpAddress);
                        }
                    }

                }

            }

        }
    }
}
