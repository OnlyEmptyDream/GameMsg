package com.kong.netty.basic;


import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * 用channel 将一个字符串写入txt文件中去
 */
public class NIOFileChannel01 {
    public static void main(String[] args) throws Exception{
        String word = "hello world";
        FileOutputStream fileOutputStream = new FileOutputStream("file01.txt");

        //
        FileChannel channel = fileOutputStream.getChannel();

        //byteBUff
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(word.getBytes());

        //输出写入
//        byteBuffer.flip();
//        List<Byte> show = new ArrayList<>();
//        while (byteBuffer.hasRemaining()){
//            show.add(byteBuffer.get());
//        }
//        byte[] showByte = new byte[show.size()];
//        for(int i = 0; i < show.size(); i++){
//            showByte[i] = show.get(i);
//        }
//        System.out.println(new String(showByte));

        //输出写入2
        byteBuffer.flip();

        byte[] showByte = new byte[byteBuffer.limit()];
        for(int i = 0; i < byteBuffer.limit(); i++){
            showByte[i] = byteBuffer.get();
        }
        System.out.println(new String(showByte));

        //写入字符到文件中
        byteBuffer.flip();
        channel.write(byteBuffer);
        fileOutputStream.close();
    }
}
