package org.fyr.Sequencers;

import java.io.Serializable;

//TODO -- Se över så att jag inte har missat något
public class PersonIdSequencer implements Serializable {

    private static int currentId;
    private static PersonIdSequencer personIdSequencer;

    private PersonIdSequencer(){}

    public int nextId(){
        return ++currentId;
    }

    public static int getCurrentId(){
        return PersonIdSequencer.getCurrentId();
    }

    public static void setCurrentId(int id){
        PersonIdSequencer.currentId = id;
    }

    public static PersonIdSequencer getInstance(){
        if (personIdSequencer == null){
            personIdSequencer = new PersonIdSequencer();
        }
        return personIdSequencer;
    }
}
