package com.kong.netty.basic;

import java.nio.IntBuffer;

public class BasicBuff {
    public static void main(String[] args) {
        int[] hb = new int[5];
        IntBuffer intBuffer = IntBuffer.allocate(5);
        intBuffer.put(5);
        intBuffer.put(3);
        intBuffer.flip();
        while(intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
//        System.out.println(intBuffer.get(0));
//        System.out.println(intBuffer.get(1));
    }
}
