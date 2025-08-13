package com.apifaculdade.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {
    public static final Logger LOGGER = LoggerFactory.getLogger(LoggingConfig.class);
}
