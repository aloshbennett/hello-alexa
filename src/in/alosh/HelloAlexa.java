package in.alosh;

import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SimpleCard;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by alosh on 15/6/16.
 */
public class HelloAlexa extends SpeechletRequestStreamHandler{
    private static final Set<String> supportedApplicationIds = new HashSet<String>();
    static {
        supportedApplicationIds.add("amzn1.echo-sdk-ams.app.770275ab-d937-44f1-b20b-e4fbc113c87c");
    }
    public HelloAlexa() {
        super(new Speechlet() {
            @Override
            public void onSessionStarted(SessionStartedRequest sessionStartedRequest, Session session) throws SpeechletException {

            }

            @Override
            public SpeechletResponse onLaunch(LaunchRequest launchRequest, Session session) throws SpeechletException {
                return null;
            }

            @Override
            public SpeechletResponse onIntent(IntentRequest intentRequest, Session session) throws SpeechletException {
                Map<String, Slot> slots = intentRequest.getIntent().getSlots();
                Slot nameSlot = slots.get("Person");
                String speechText = "Hello " + nameSlot.getValue();

                // Create the Simple card content.
                SimpleCard card = new SimpleCard();
                card.setTitle("HelloWorld");
                card.setContent(speechText);

                // Create the plain text output.
                PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
                speech.setText(speechText);

                return SpeechletResponse.newTellResponse(speech, card);
            }

            @Override
            public void onSessionEnded(SessionEndedRequest sessionEndedRequest, Session session) throws SpeechletException {

            }
        }, supportedApplicationIds);
    }
}
