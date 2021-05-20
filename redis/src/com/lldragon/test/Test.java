package com.lldragon.test;


import redis.clients.jedis.Jedis;

public class Test {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.214.128",6379);
         jedis.set("a", "a");
        String s = jedis.get("a");
        System.out.println(s);
    }
}
