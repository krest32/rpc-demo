package com.krest.rpc.demo.day4.server;

import com.krest.rpc.demo.day4.common.RpcRequest;
import com.krest.rpc.demo.day4.common.RpcRequestWrapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class RpcServerDispatchHandler extends ChannelInboundHandlerAdapter {
    private RpcServerRequestHandler rpcServerRequestHandler;

    public RpcServerDispatchHandler(
            RpcServerRequestHandler rpcServerRequestHandler) {
        this.rpcServerRequestHandler = rpcServerRequestHandler;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        RpcRequest rpcRequest = (RpcRequest) msg;
        RpcRequestWrapper rpcRequestWrapper = new RpcRequestWrapper(rpcRequest, ctx.channel());

        rpcServerRequestHandler.addRequest(rpcRequestWrapper);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {

    }

}
