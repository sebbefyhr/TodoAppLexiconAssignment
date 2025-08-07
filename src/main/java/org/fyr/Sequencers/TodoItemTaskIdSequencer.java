package org.fyr.Sequencers;

public class TodoItemTaskIdSequencer extends Sequencer {

    private int currentId;
    private static TodoItemTaskIdSequencer TodoItemTaskIdSequencer;

    private TodoItemTaskIdSequencer(){}

    public int nextId(){
        return ++currentId;
    }

    public  int getCurrentId(){
        return currentId;
    }
    public void setCurrentId(int id){
        if(id < 0){
            throw new IllegalArgumentException("ID cant be a negative number");
        }
        currentId = id;
    }

    public static TodoItemTaskIdSequencer getInstance(){
        if(TodoItemTaskIdSequencer == null){
            TodoItemTaskIdSequencer = new TodoItemTaskIdSequencer();
        }
        return TodoItemTaskIdSequencer;
    }

}
