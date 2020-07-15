package com.kong.nio.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 利用channel 实现一个文件复制功能
 */
public class NIOFileChannel03 {
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("file01.txt");
        FileChannel channel = fileInputStream.getChannel();

        FileOutputStream fileInputStream2 = new FileOutputStream("2.txt");
        FileChannel channel2 = fileInputStream2.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(3);

        while (true){
            int i = 0;
            i++;
            System.out.println(i);
            byteBuffer.clear();
            int read = channel.read(byteBuffer);
            if(read == -1){
                break;
            }
            byteBuffer.flip();
            channel2.write(byteBuffer);
        }
    }
}
