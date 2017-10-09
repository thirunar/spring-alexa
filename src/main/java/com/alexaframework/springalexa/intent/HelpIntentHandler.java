package com.alexaframework.springalexa.intent;

import com.alexaframework.springalexa.config.AlexaConfiguration;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class HelpIntentHandler implements IntentHandler {

    private static final String INTENT_NAME = "AMAZON.HelpIntent";
    private static final String HELP_MESSAGE = "Help message not defined";

    @Autowired
    private AlexaConfiguration configuration;

    @Override
    public boolean canHandle(String name) {
        return INTENT_NAME.equals(name);
    }

    @Override
    public SpeechletResponse handle(Session session, Intent intent) {
        return getTellSpeechletResponse(configuration.
                getProperty("intent.help.message", HELP_MESSAGE));

    }
}
