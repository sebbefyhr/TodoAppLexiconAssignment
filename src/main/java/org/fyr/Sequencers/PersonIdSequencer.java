package org.fyr.Sequencers;

import java.io.Serializable;

public class PersonIdSequencer extends Sequencer {

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
        if(id < 0 ){
            throw new IllegalArgumentException("ID cant be a negative number");
        }
        currentId = id;
    }

    public static PersonIdSequencer getInstance(){
        if (personIdSequencer == null){
            personIdSequencer = new PersonIdSequencer();
        }
        return personIdSequencer;
    }
}
