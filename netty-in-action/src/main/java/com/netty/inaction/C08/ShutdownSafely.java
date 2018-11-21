package com.netty.inaction.C08;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.Future;

/**
 * Created by mengtian on 2018/11/16
 * 优雅关闭
 */
public class ShutdownSafely {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class);

        Future<?> future = group.shutdownGracefully();
        ((io.netty.util.concurrent.Future<?>) future).syncUninterruptibly();

    }
}
