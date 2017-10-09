package com.alexaframework.springalexa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AlexaConfiguration {

    public static final int INVALID_VALUE = Integer.MIN_VALUE;
    public static final String VALUE_DELIMITER = ",";

    private PropertiesSegregator properties = new PropertiesSegregator();

    private Environment environment;

    @Autowired
    public AlexaConfiguration(Environment environment) {
        this.environment = environment;
    }

    public String getProperty(String name) {
        return getPropertyFromEnvContext(name);
    }

    public String getProperty(String name, String defaultValue) {
        String value = getProperty(name);
        return value == null ? defaultValue : value;
    }

    public int getIntProperty(String name) {
        return getIntProperty(name, INVALID_VALUE);
    }

    public long getLongProperty(String name) {
        return getLongProperty(name, INVALID_VALUE);
    }

    public boolean getBooleanProperty(String name) {
        return getBooleanProperty(name, false);
    }

    public String[] getPropertyValues(String name) {
        String property = getProperty(name);
        if (property == null) {
            return new String[0];
        }
        return property.split(VALUE_DELIMITER);
    }


    public String[] getPropertyValues(String name, String[] defaultValue) {
        String[] propertyValues = getPropertyValues(name);
        return propertyValues.length == 0 ? defaultValue : propertyValues;
    }

    public int getIntProperty(String name, int defaultValue) {
        try {
            return Integer.parseInt(getProperty(name, "" + defaultValue));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public long getLongProperty(String name, long defaultValue) {
        try {
            return Long.parseLong(getProperty(name, "" + defaultValue));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public boolean getBooleanProperty(String name, boolean defaultValue) {
        try {
            return Boolean.parseBoolean(getProperty(name, "" + defaultValue));
        } catch (Exception e) {
            return false;
        }
    }

    public void setProperty(String name, String value) {
        properties.setProperty(name, value);
    }

    public void setPropertyIfAbsent(String name, String value) {
        boolean propertyInEnv = environment != null && environment.getProperty(name) != null;
        boolean propertyInLocal = properties.getProperty(name) != null;
        if (propertyInLocal || propertyInEnv) {
            return;
        }
        setProperty(name, value);
    }


    public void clear() {
        Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            System.clearProperty((String) propertyNames.nextElement());
        }
        properties.clear();
    }


    private String getPropertyFromEnvContext(String name) {
        if (environment == null) {
            return properties.getProperty(name);
        }
        return properties.getProperty(name, environment.getProperty(name));
    }


    @Override
    public String toString() {
        return "MFSConfiguration{" +
                "properties=" + properties +
                ", environment=" + environment +
                '}';
    }


    public void clearProperty(String name) {
        properties.remove(name);
    }

    public int size() {
        return properties.size();
    }

    public boolean isEmpty() {
        return properties.isEmpty();
    }

    public Set<Object> keySet() {
        return properties.keySet();
    }

    public Set<Map.Entry<Object, Object>> entrySet() {
        return properties.entrySet();
    }

    public Collection<Object> values() {
        return properties.values();
    }

    private static class PropertiesSegregator extends Properties {
        private Properties myOwnProperties = new Properties();

        @Override
        public synchronized Object setProperty(String key, String value) {
            myOwnProperties.setProperty(key, value);
            return super.setProperty(key, value);
        }

        @Override
        public synchronized Object remove(Object key) {
            myOwnProperties.remove(key);
            return super.remove(key);
        }

        @Override
        public synchronized void clear() {
            myOwnProperties.clear();
            super.clear();
        }

    }

}
