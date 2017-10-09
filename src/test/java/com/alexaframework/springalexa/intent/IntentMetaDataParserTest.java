package com.alexaframework.springalexa.intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IntentMetaDataParserTest.Config.class)
public class IntentMetaDataParserTest {

    @Autowired
    private IntentMetaDataParser parser;

    @Test
    public void shouldReturnListOfIntentMetaDataBasedOnTheJson() throws Exception {

        List<IntentMetaData> metaDataList = parser.parse();

        IntentMetaData helpIntent = IntentMetaData.builder()
                .name("AMAZON.HelpIntent").response("How can I help you")
                .build();
        IntentMetaData cancelIntent = IntentMetaData.builder()
                .name("AMAZON.CancelIntent").response("Thank you for using the skill")
                .build();
        assertTrue(metaDataList.containsAll(Arrays.asList(helpIntent, cancelIntent)));
    }

    @Configuration
    @ImportResource
    @ComponentScan(basePackages = "com.alexaframework.springalexa")
    public static class Config {

    }

}