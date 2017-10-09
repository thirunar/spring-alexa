package com.alexaframework.springalexa.speechlet;

import com.alexaframework.springalexa.intent.IntentHandler;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public abstract class SpringSpeechlet implements Speechlet {

    private List<IntentHandler> intentHandlers;

    @Autowired
    public SpringSpeechlet(IntentHandler... intentHandlers) {
        this.intentHandlers = Arrays.asList(intentHandlers);
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest intentRequest,
                                      Session session) throws SpeechletException {
        Intent intent = intentRequest.getIntent();
        for (IntentHandler handler : intentHandlers) {
            if (handler.canHandle(intent.getName())) {
                return handler.handle(session, intent);
            }
        }
        return null;
    }
}
