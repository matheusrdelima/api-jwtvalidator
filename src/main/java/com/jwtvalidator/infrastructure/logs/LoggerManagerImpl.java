package com.jwtvalidator.infrastructure.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jwtvalidator.core.logs.LoggerManager;

public class LoggerManagerImpl<T> implements LoggerManager<T> {
    private final Logger logger;

    public LoggerManagerImpl(Class<T> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    @Override
    public void info(String message) {
        this.logger.info(message);
    }

    @Override
    public void info(String message, Object object) {
        this.logger.info(message, object);
    }

    @Override
    public void error(String message) {
        this.logger.error(message);
    }

    @Override
    public void error(String message, Object object) {
        this.logger.error(message, object);
    }
}
