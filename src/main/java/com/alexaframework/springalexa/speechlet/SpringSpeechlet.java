package com.alexaframework.springalexa.speechlet;

import com.alexaframework.springalexa.intent.IntentHandler;
import com.alexaframework.springalexa.intent.StaticIntentHandler;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public abstract class SpringSpeechlet implements Speechlet {

    private final StaticIntentHandler staticIntentHandler;
    private List<IntentHandler> intentHandlers;

    @Autowired
    public SpringSpeechlet(StaticIntentHandler staticIntentHandler,
                           IntentHandler... intentHandlers) {
        this.staticIntentHandler = staticIntentHandler;
        this.intentHandlers = Arrays.asList(intentHandlers);
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest intentRequest,
                                      Session session) throws SpeechletException {
        try {
            Intent intent = intentRequest.getIntent();
            SpeechletResponse response = staticIntentHandler.handle(session, intent);

            if (response != null)
                return response;

            for (IntentHandler handler : intentHandlers) {
                if (handler.canHandle(intent.getName())) {
                    return handler.handle(session, intent);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
