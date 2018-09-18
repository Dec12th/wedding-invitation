//package com.baily.template.springcloud.eureka.test;
//
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.ByteBuffer;
//import java.nio.channels.FileChannel;
//import java.util.Calendar;
//
///**
// * @ClassName: Test
// * @Description:
// * @author:YB
// * @date:2018年07月09日 10:23
// */
//public class Test {
//
//    public static void write() throws IOException {
//        //声明源文件和目标文件
//        String src = "E:\\home\\test.txt";
//        FileInputStream fi = new FileInputStream(new File(src));
//        //获得传输通道channel
//        FileChannel inChannel = fi.getChannel();
//        //获得容器buffer
//        ByteBuffer buffer = ByteBuffer.allocate(1024000);
//        for (int i = 0; i < 1000; i++) {
//            //开始写
//            buffer.put(("测试性能" + i).getBytes());
//
//        }
//        inChannel.write(buffer);
//        //写完要重置buffer，重设position=0,limit=capacity
//        buffer.clear();
//
//        inChannel.close();
//
//        fi.close();
//
//    }
//
//    public static void readAndWrite() throws IOException {
//
//        write();
//        //声明源文件和目标文件
//        String src = "E:\\home\\test.txt";
//        String dst = "E:\\home\\test_copy.txt";
//        FileInputStream fi = new FileInputStream(new File(src));
//        FileOutputStream fo = new FileOutputStream(new File(dst));
//        //获得传输通道channel
//        FileChannel inChannel = fi.getChannel();
//        FileChannel outChannel = fo.getChannel();
//        //获得容器buffer
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        while (true) {
//            //判断是否读完文件
//            int eof = inChannel.read(buffer);
//            if (eof == -1) {
//                break;
//            }
//            //重设一下buffer的position=0，limit=position
//            buffer.flip();
//            //开始写
//            outChannel.write(buffer);
//            //写完要重置buffer，重设position=0,limit=capacity
//            buffer.clear();
//        }
//        inChannel.close();
//        outChannel.close();
//        fi.close();
//        fo.close();
//
//    }
//
//    public static void leftMove() {
//        System.out.println(1 << 12L);
//        long sequence = 0L;
//        System.out.println(System.currentTimeMillis());
//        Calendar calendar = Calendar.getInstance();
//        System.out.println((System.currentTimeMillis() -calendar.getTimeInMillis())  << 22L);
//        System.out.println((1L)  << 22L | 1L << 12L | 0);
////        System.out.println(1L << 12L);
//        DefaultKeyGenerator generator = new DefaultKeyGenerator();
//        Long userId = generator.generateKey().longValue();
//        System.out.println(userId);
//    }
//
//
//
//    public static void main(String[] args) throws IOException {
////        leftMove();
//        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar.getTimeInMillis());
//        System.out.println(1L << 41L);
//        System.out.println(Long.toBinaryString(calendar.getTimeInMillis()));
//
//        System.out.println((1L << 38) / (1000L * 60 * 60 * 24 * 365));
//
//    }
//}
