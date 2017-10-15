package com.alexaframework.springalexa.intent;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.SimpleCard;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class StaticIntentHandlerTest {

    @InjectMocks
    private StaticIntentHandler handler;

    @Mock
    private IntentMetaDataParser parser;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldReturnResponseBasedOnTheIntentName() throws Exception {
        IntentMetaData helpIntent = IntentMetaData.builder()
                .name("AMAZON.HelpIntent").response("How can I help you")
                .build();
        IntentMetaData cancelIntent = IntentMetaData.builder()
                .name("AMAZON.CancelIntent").response("Thank you for using the skill")
                .build();
        when(parser.parse()).thenReturn(Arrays.asList(helpIntent, cancelIntent));

        Session session = Session.builder().withSessionId("123").build();
        Intent intent = Intent.builder().withName("AMAZON.HelpIntent").build();

        SpeechletResponse response = handler.handle(session, intent);

        assertThat(((SimpleCard) response.getCard()).getContent(), is("How can I help you"));
    }
  
    @Test
    public void shouldReturnNullIfIntentIsNotPresentInStaticList() throws Exception {
        IntentMetaData helpIntent = IntentMetaData.builder()
                .name("AMAZON.HelpIntent").response("How can I help you")
                .build();
        IntentMetaData cancelIntent = IntentMetaData.builder()
                .name("AMAZON.CancelIntent").response("Thank you for using the skill")
                .build();
        when(parser.parse()).thenReturn(Arrays.asList(helpIntent, cancelIntent));

        Session session = Session.builder().withSessionId("123").build();
        Intent intent = Intent.builder().withName("AMAZON.DummyIntent").build();

        SpeechletResponse response = handler.handle(session, intent);

        assertNull(response);
    }

    @Test
    public void shouldReturnNullIfWhenParserReturnsNull() throws Exception {
        when(parser.parse()).thenReturn(null);

        Session session = Session.builder().withSessionId("123").build();
        Intent intent = Intent.builder().withName("AMAZON.DummyIntent").build();

        SpeechletResponse response = handler.handle(session, intent);

        assertNull(response);
    }
}