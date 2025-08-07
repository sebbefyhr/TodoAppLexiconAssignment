package org.fyr.Sequencers;


import java.io.Serializable;

public class TodoItemIdSequencer extends Sequencer {

    private int currentId;
    private static TodoItemIdSequencer todoItemIdSequencer;

    private TodoItemIdSequencer(){}

    public int nextId(){
        return ++currentId;
    }
    public int getCurrentId() {
        return currentId;
    }

    public void setCurrentId(int id) {
        if(id < 0){
            throw new IllegalArgumentException("ID cant be a negative number");
        }
        currentId = id;
    }

    public static TodoItemIdSequencer getInstance(){
        if(todoItemIdSequencer == null){
            todoItemIdSequencer = new TodoItemIdSequencer();
        }
        return todoItemIdSequencer;
    }

}
