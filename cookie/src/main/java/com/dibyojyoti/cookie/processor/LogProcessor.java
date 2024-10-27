package com.dibyojyoti.cookie.processor;

import java.util.List;

public abstract class LogProcessor<T, R> {
    public abstract void process(T data);

    public abstract List<R> getResult();
}
