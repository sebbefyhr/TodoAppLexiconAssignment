package org.fyr.Sequencers;

//TODO -- Se över så att jag inte har missat något
public class TodoItemTaskIdSequencer {

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
        currentId = id;
    }

    public static TodoItemTaskIdSequencer getInstance(){
        if(TodoItemTaskIdSequencer == null){
            TodoItemTaskIdSequencer = new TodoItemTaskIdSequencer();
        }
        return TodoItemTaskIdSequencer;
    }

}
