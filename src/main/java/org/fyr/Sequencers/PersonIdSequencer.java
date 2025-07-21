package org.fyr.Sequencers;

import java.io.Serializable;

//TODO -- Se över så att jag inte har missat något
public class PersonIdSequencer implements Serializable {

    private int currentId;
    private static PersonIdSequencer personIdSequencer;

    private PersonIdSequencer(){}

    public int nextId(){
        return ++currentId;
    }

    public int getCurrentId(){
        return currentId;
    }

    public void setCurrentId(int id){
        currentId = id;
    }

    public static PersonIdSequencer getInstance(){
        if (personIdSequencer == null){
            personIdSequencer = new PersonIdSequencer();
        }
        return personIdSequencer;
    }
}
