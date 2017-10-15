package com.alexaframework.springalexa.intent;

import com.alexaframework.springalexa.config.AlexaConfiguration;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StopIntentHandler implements IntentHandler {

    private static final String INTENT_NAME = "AMAZON.StopIntent";
    private static final String STOP_MESSAGE = "Thank you for using the skill";

    @Autowired
    private AlexaConfiguration configuration;

    @Override
    public boolean canHandle(String name) {
        return INTENT_NAME.equals(name);
    }

    @Override
    public SpeechletResponse handle(Session session, Intent intent) {
        return getTellSpeechletResponse(configuration.getProperty("intent.stop.message", STOP_MESSAGE));
    }
}
