package org.fyr;

import org.fyr.model.Person;
import org.fyr.model.TodoItem;
import org.fyr.model.TodoItemTask;

public class Main {
    public static void main(String[] args) {

        Person p1 = new Person("Seb", "FYr");
        Person p2 = new Person("Nisse", "Fyr");
        Person p3 = new Person("Elin", "Fyr");

        TodoItem td1 = new TodoItem();
        TodoItem td2 = new TodoItem();
        TodoItem td3 = new TodoItem();
        TodoItem td4 = new TodoItem();
        TodoItem td5 = new TodoItem();
        TodoItem td6 = new TodoItem();
        TodoItem td7 = new TodoItem();
        TodoItem td8 = new TodoItem();
        TodoItem td9 = new TodoItem();


        TodoItemTask tdit1 = new TodoItemTask(td1, p1);
        TodoItemTask tdit2 = new TodoItemTask(td2, p1);
        TodoItemTask tdit3 = new TodoItemTask(td3, p1);
        TodoItemTask tdit4 = new TodoItemTask(td4, p2);
        TodoItemTask tdit5 = new TodoItemTask(td5, p2);
        TodoItemTask tdit6 = new TodoItemTask(td6, p2);
        TodoItemTask tdit7 = new TodoItemTask(td7, p3);
        TodoItemTask tdit8 = new TodoItemTask(td8, p3);
        TodoItemTask tdit9 = new TodoItemTask(td9, p3);

        TodoItemTask t1 = new TodoItemTask();
        TodoItemTask t2 = new TodoItemTask();
        TodoItemTask t3 = new TodoItemTask();

        System.out.println(tdit1.isAssigned());
        System.out.println(tdit4.isAssigned());
        System.out.println(tdit5.isAssigned());
        System.out.println(tdit8.isAssigned());
        System.out.println(tdit2.isAssigned());

        System.out.println(t1.isAssigned());
        System.out.println(t2.isAssigned());
        System.out.println(t3.isAssigned());
        t1.setAssignee(p1);
        t2.setAssignee(p1);
        t3.setAssignee(p3);

        System.out.println(t1.isAssigned());
        System.out.println(t2.isAssigned());
        System.out.println(t3.isAssigned());
    }
}