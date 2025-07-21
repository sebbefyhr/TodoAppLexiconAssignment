package org.fyr.Sequencers;


//TODO -- Se över så att jag inte har missat något
public class TodoItemIdSequencer {
    private int currentId;
    private static TodoItemIdSequencer todoItemIdSequencer;

    private TodoItemIdSequencer(){}

    public int nextId(){
        return ++currentId;
    }
    public int getCurrentId() {
        return currentId;
    }

    public void setCurrentId(int currentId) {
        currentId = currentId;
    }

    public static TodoItemIdSequencer getInstance(){
        if(todoItemIdSequencer == null){
            todoItemIdSequencer = new TodoItemIdSequencer();
        }
        return todoItemIdSequencer;
    }

}
