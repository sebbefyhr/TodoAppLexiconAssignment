package org.fyr.Sequencers;

public class TodoItemIdSequencer {
    private static int currentId;
    private static TodoItemIdSequencer todoItemIdSequencer;

    private TodoItemIdSequencer(){}

    public static int getNextId(){
        return currentId++;
    }
    public static int getCurrentId() {
        return currentId;
    }

    public static void setCurrentId(int currentId) {
        TodoItemIdSequencer.currentId = currentId;
    }

    public static TodoItemIdSequencer getInstance(){
        if(todoItemIdSequencer == null){
            todoItemIdSequencer = new TodoItemIdSequencer();
        }
        return todoItemIdSequencer;
    }

}
