package org.fyr.Sequencers;

public class TodoItemTaskIdSequencer {

    private static int currentId;
    private static TodoItemIdSequencer todoItemIdSequencer;

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

    public static TodoItemIdSequencer getInstance(){
        if(todoItemIdSequencer == null){
            todoItemIdSequencer = new TodoItemIdSequencer();
        }
        return todoItemIdSequencer;
    }

}
