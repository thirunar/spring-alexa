package com.alexaframework.springalexa.intent;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SimpleCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class StaticIntentHandler {

    @Autowired
    private IntentMetaDataParser parser;

    public SpeechletResponse handle(Session session, Intent intent) throws IOException {
        List<IntentMetaData> metaDataList = parser.parse();

        return metaDataList.stream()
                .filter(metaData -> metaData.getName().equals(intent.getName()))
                .findFirst()
                .map(intentMetaData -> getTellSpeechletResponse(intentMetaData.getResponse()))
                .orElse(null);
    }

    private SpeechletResponse getTellSpeechletResponse(String speechText) {
        SimpleCard card = new SimpleCard();
        card.setContent(speechText);
        card.setTitle("Session");

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }

}
