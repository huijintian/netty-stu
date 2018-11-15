package com.netty.inaction.C05;

import io.netty.buffer.*;

/**
 * Created by mengtian on 2018/11/13
 */
public class NettyByteBuf {

    /**
     * 堆缓冲区例子
     */
    public void heapByteBuf() {
        ByteBuf heapBuf = Unpooled.buffer();
        //检查ByteBuf是否有一个支撑数组
        if (heapBuf.hasArray()) {
            byte[] array = heapBuf.array();
            int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
            int length = heapBuf.readableBytes();
            handleArray(array, offset, length);
        }
    }

    /**
     * 直接缓冲区例子
     */
    public void directByteBuf() {
        ByteBuf directBuf = Unpooled.directBuffer();
        //检查ByteBuf是否有数组支撑。如果不是，则这是一个直接缓冲区
        if (!directBuf.hasArray()) {
            int length = directBuf.readableBytes();
            byte[] array = new byte[length];
            directBuf.getBytes(directBuf.readerIndex(), array);//复制数据到数组
            handleArray(array, 0, length);
        }
    }

    /**
     * 复合缓冲区例子，在传递组合消息时很有用
     */
    public void compositeByteBuf() {
        CompositeByteBuf messageBuf = Unpooled.compositeBuffer();
        ByteBuf headerBuf = Unpooled.buffer();
        ByteBuf bodyBuf = Unpooled.buffer();
        messageBuf.addComponents(headerBuf, bodyBuf);//将ByteBuf追加到CompositeByteBuf

        messageBuf.removeComponent(0);
        for (ByteBuf buf : messageBuf) {
            System.out.println(buf.toString());
        }

        //访问数据，类似于访问直接缓冲区
        CompositeByteBuf comBuf = Unpooled.compositeBuffer();
        int length = comBuf.readableBytes();
        byte[] array = new byte[length];
        comBuf.getBytes(comBuf.readerIndex(), array);
        handleArray(array, 0, length);
    }

    private void handleArray(byte[] array, int offset, int length) {
    }
}
