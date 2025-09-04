package com.jwtvalidator.core.logs;

public interface LoggerManager<T> {
    void info(String message);

    void info(String message, Object object);

    void error(String message);

    void error(String message, Object object);
}
