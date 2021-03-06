package com.krest.rpc.demo.day3.server;

import com.krest.rpc.demo.day3.common.RpcInvokeHook;
import com.krest.rpc.demo.day3.common.RpcRequest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class RpcServerRequestHandler {
    private Class<?> interfaceClass;
    private Object serviceProvider;
    private RpcInvokeHook rpcInvokeHook;

    private int threads;
    private ExecutorService threadPool;
    private BlockingQueue<RpcRequest> requestQueue = new LinkedBlockingQueue<RpcRequest>();

    public RpcServerRequestHandler(Class<?> interfaceClass, Object serviceProvider, int threads,
                                   RpcInvokeHook rpcInvokeHook) {
        this.interfaceClass = interfaceClass;
        this.serviceProvider = serviceProvider;
        this.threads = threads;
        this.rpcInvokeHook = rpcInvokeHook;
    }

    public void start() {
        threadPool = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < threads; i++) {
            threadPool.execute((Runnable) new RpcServerRequestHandleRunnable(interfaceClass,
                    serviceProvider, rpcInvokeHook, requestQueue));
        }
    }

    public void addRequest(RpcRequest rpcRequest) {
        try {
            requestQueue.put(rpcRequest);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
