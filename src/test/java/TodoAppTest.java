
import org.fyr.model.Person;
import org.fyr.model.TodoItem;
import org.fyr.model.TodoItemTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TodoAppTest {

    Person p1 = new Person(1, "Seb", "Dyr", "sebDyr@lexicon.se");
    Person p2 = new Person(2, "pelle", "Gyr", "PelleGyr@lexicon.se");
    Person p3 = new Person(3, "Pellan", "hymn", "PellanHymn@lexicon.se");

    TodoItem td1 = new TodoItem(1, "First TodoItem", "no description", LocalDate.parse("2025-07-05"), false, p2);
    TodoItem td2 = new TodoItem(2, "Second TodoItem", " description", LocalDate.parse("2025-06-05"), false, p2);
    TodoItem td4 = new TodoItem();
    TodoItem td5 = new TodoItem();
    TodoItem td6 = new TodoItem();
    TodoItem td7 = new TodoItem();
    TodoItem td8 = new TodoItem();
    TodoItem td9 = new TodoItem();

    TodoItemTask tdit1 = new TodoItemTask(td1, p1);
    TodoItemTask tdit2 = new TodoItemTask(td2, p1);
    TodoItemTask tdit4 = new TodoItemTask(td4, p2);
    TodoItemTask tdit5 = new TodoItemTask(td5, p2);
    TodoItemTask tdit6 = new TodoItemTask(td6, p2);
    TodoItemTask tdit7 = new TodoItemTask(td7, p3);
    TodoItemTask tdit8 = new TodoItemTask(td8, p3);
    TodoItemTask tdit9 = new TodoItemTask(td9, p3);




    @Test
    void personTest() {
        String corr = p1.getEmail();
        assertDoesNotThrow(() -> p1.setEmail("ebfyr@lexicon.se"));
        //assertDoesNotThrow(NullPointerException.class, () -> p1.setEmail("ebfyr@lexicon.se"));
        assertNotEquals(corr, p1.getEmail());

        assertThrows(NullPointerException.class, () -> new Person(4, "", "andersson", "benny@gmail.com"));

    }

    @Test
    void todoItemTest() {

        assertFalse(td1.isOverdue());
        assertTrue(td2.isOverdue());

        assertThrows(NullPointerException.class, () -> td2.setTitle(""));

    }

    @Test
    void todoTestItemTaskTest() {
        td5.setCreator(p3);
        TodoItemTask task1 = new TodoItemTask(1, td5, p3);
        System.out.println(task1.getSummary());
        //assertThrows(NullPointerException.class, ()-> new TodoItemTask(1, null, p3));
    }

}
