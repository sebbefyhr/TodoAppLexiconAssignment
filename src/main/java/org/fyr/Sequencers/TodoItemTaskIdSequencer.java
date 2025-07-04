package org.fyr.Sequencers;

//TODO -- Se över så att jag inte har missat något
public class TodoItemTaskIdSequencer {

    private static int currentId;
    private static TodoItemTaskIdSequencer TodoItemTaskIdSequencer;

    private TodoItemTaskIdSequencer(){}

    public static int nextId(){
        return currentId++;
    }

    public static int getCurrentId(){
        return currentId;
    }
    public static void setCurrentId(int id){
        currentId = id;
    }

    public static TodoItemTaskIdSequencer getInstance(){
        if(TodoItemTaskIdSequencer == null){
            TodoItemTaskIdSequencer = new TodoItemTaskIdSequencer();
        }
        return TodoItemTaskIdSequencer;
    }

}
