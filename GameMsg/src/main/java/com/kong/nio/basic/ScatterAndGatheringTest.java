package com.kong.nio.basic;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 用buffer数组去写入 或读取数据
 * Scattering : 将数据写入到buffer时， 可以采用buffer数组，依次写入
 * Gathering : 从buffer读取数据时，可以采用buffer数组，依次读
 */
public class ScatterAndGatheringTest {
    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        //绑定端口至socket,并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

        //创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等待客户端连接（telnet)
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;//接收的字节数

        while (true){
            int byteRead = 0;

            while (byteRead < messageLength){
                long l = socketChannel.read(byteBuffers);
                byteRead += l;
                System.out.println("byteRead=" + byteRead);
                Arrays.asList(byteBuffers).stream().map(t -> "position"+ t.position() + " limit" + t.limit()).forEach(System.out::println);
            }
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());

            //返回客户端的
            long byteWrite = 0;
            while(byteWrite < messageLength){
                long l = socketChannel.write(byteBuffers);
                byteWrite += l;
            }
            Arrays.asList(byteBuffers).stream().map(t -> new String(t.array())).forEach(System.out::print);
//            System.out.println(new String(byteBuffers[0].array()));
            Arrays.asList(byteBuffers).forEach((byteBuffer -> byteBuffer.clear()));
            System.out.println("write = " + byteWrite);
        }
    }
}
