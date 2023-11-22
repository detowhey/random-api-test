package br.dev.henriquealmeida.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class UrlReader {

    private final Properties properties;
    private static final Logger logger = LogManager.getLogger();

    public UrlReader() {
        this.properties = new Properties();

        try {
            properties.load(UrlReader.class.getClassLoader().getResourceAsStream("services.properties"));
        } catch (IOException exception) {
            logger.info(Arrays.toString(exception.getStackTrace()));
        }
    }

    public String getUrl(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
