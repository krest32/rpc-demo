package com.krest.rpc;

public class JUnitTestCustomException extends RuntimeException {
    private static final long serialVersionUID = 591530421634999576L;

    public JUnitTestCustomException() {
        super("CustomException");
    }
}
