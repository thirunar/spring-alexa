package com.alexaframework.springalexa.intent;

import com.alexaframework.springalexa.config.AlexaConfiguration;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class IntentMetaDataParser {

    private static final String INTENT_METADATA_JSON = "intent_metadata.json";

    @Autowired
    private AlexaConfiguration configuration;

    @Autowired
    private ResourceLoader resourceLoader;

    public List<IntentMetaData> parse() throws IOException {
        String resourceName = configuration.getProperty("metadata.intent.file", INTENT_METADATA_JSON);

        String withClassPath = String.format("classpath:%s", resourceName);

        Resource resource = resourceLoader.getResource(withClassPath);

        if (resource.exists()) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(resource.getURL(), new TypeReference<List<IntentMetaData>>(){});
        }
        return null;
    }
}
