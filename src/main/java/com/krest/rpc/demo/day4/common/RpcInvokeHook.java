package com.krest.rpc.demo.day4.common;

/**
 * 代理钩子函授
 */
public interface RpcInvokeHook {

    void beforeInvoke(String methodName, Object[] args);

    void afterInvoke(String methodName, Object[] args);

}
