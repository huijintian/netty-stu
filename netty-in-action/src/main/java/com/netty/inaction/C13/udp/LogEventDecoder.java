package com.netty.inaction.C13.udp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;
import java.util.List;

/**
 * Created by mengtian on 2018/11/27
 */
public class LogEventDecoder extends MessageToMessageDecoder<DatagramPacket> {

    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket packet, List<Object> out)
            throws Exception {
        ByteBuf data = packet.content();
        int idx = data.indexOf(0, data.readableBytes(), LogEvent.SEPARATOE);
        String filename = data.slice(0, idx).toString(CharsetUtil.UTF_8);
        String logMsg = data.slice(idx + 1, data.readableBytes()).toString(CharsetUtil.UTF_8);

        LogEvent event = new LogEvent(packet.sender(), System.currentTimeMillis(), filename,
                logMsg);
        out.add(event);
    }
}
