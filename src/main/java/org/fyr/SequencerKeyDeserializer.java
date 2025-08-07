package org.fyr;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import org.fyr.Sequencers.PersonIdSequencer;
import org.fyr.Sequencers.TodoItemIdSequencer;
import org.fyr.Sequencers.TodoItemTaskIdSequencer;

import java.io.IOException;

public class SequencerKeyDeserializer extends KeyDeserializer {
    @Override
    public Object deserializeKey(final String s, DeserializationContext deserializationContext) throws IOException {

        if(s.contains("PersonIdSequencer")){
            return PersonIdSequencer.getInstance();
        }
        else if(s.contains("TodoItemIdSequencer")){
            return TodoItemIdSequencer.getInstance();
        }
        else if(s.contains("TodoItemTaskIdSequencer")){
            return TodoItemTaskIdSequencer.getInstance();
        }
        else{
            return null;
        }

    }
}
