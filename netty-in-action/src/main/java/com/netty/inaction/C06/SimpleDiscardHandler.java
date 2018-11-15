package com.netty.inaction.C06;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by mengtian on 2018/11/15
 * 使用SimpleChannelInboundHandler来避免显示释放资源
 */
@ChannelHandler.Sharable
public class SimpleDiscardHandler extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        //这里不需要任何显示的资源释放，因为SimpleChannelInboundHandler会自动释放资源
        //释放资源的操作可以从源码中看出
        //if (autoRelease && release) {
        //    ReferenceCountUtil.release(msg);
        //}
    }
}
