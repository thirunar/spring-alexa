package com.alexaframework.springalexa.intent;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import org.springframework.stereotype.Component;

@Component
public interface IntentHandler {

    boolean canHandle(String name);

    SpeechletResponse handle(Session session, Intent intent);

    default SpeechletResponse getAskSpeechletResponse(String speechText, String repromptText) {
        SimpleCard card = new SimpleCard();
        card.setTitle("Session");
        card.setContent(speechText);

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
        repromptSpeech.setText(repromptText);
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptSpeech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    default SpeechletResponse getTellSpeechletResponse(String speechText) {
        SimpleCard card = new SimpleCard();
        card.setTitle("Session");
        card.setContent(speechText);

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }

}
