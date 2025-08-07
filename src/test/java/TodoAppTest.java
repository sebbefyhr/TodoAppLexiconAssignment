
import org.fyr.DAO.*;
import org.fyr.Sequencers.PersonIdSequencer;
import org.fyr.Sequencers.TodoItemIdSequencer;
import org.fyr.Sequencers.TodoItemTaskIdSequencer;
import org.fyr.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class TodoAppTest {


    @Test
    void personTest() {

        Person p1 = new Person( "Seb", "Dyr", "sebDyr@lexicon.se", new AppUser("sebzter", "password123", AppRole.ROLE_APP_ADMIN));
        Person p3 = new Person("Pellan", "hymn", "PellanHymn@lexicon.se", new AppUser("bellan_pellan", "guess84", AppRole.ROLE_APP_USER));

        Person per1 = new Person("Simon", "lexicon", "s.lexicon@Lexicon.se");
        Person per2 = new Person("Markus", "Gudmundsen", "m.g@lexicon.se");


        assertTrue(per1.getCredentials() == null);

        AppUser per1Appuser  = new AppUser("Simpa", "lösen", AppRole.ROLE_APP_USER);
        AppUser per2App = new AppUser("Mackan", "password123", AppRole.ROLE_APP_USER);

        per1.setCredentials(per1Appuser);
        per2.setCredentials(per2App);


        assertThrows(NullPointerException.class, () -> p3.setFirstName("     "));
        assertNotSame(per1, per2);
        assertDoesNotThrow(() -> p1.setEmail("ebfyr@lexicon.se"));
        assertThrows(NullPointerException.class, () -> new Person("", "andersson", "benny@gmail.com"));
        assertThrows(NullPointerException.class, () -> p3.setEmail(""));
        assertNotEquals(p1.hashCode(), per2.hashCode());

    }

    @Test
    void appUserTest(){

        AppUser au = new AppUser("seFryzz", "cool1234", AppRole.ROLE_APP_ADMIN);
        AppUser au1 = new AppUser("Appie1!!", "Long___Password****", AppRole.ROLE_APP_USER);
        AppUser au2 = new AppUser("Newbie!!", "short__Passwor**", AppRole.ROLE_APP_USER);

        assertFalse(au.equals(au2));
        assertThrows(NullPointerException.class, () -> au2.setUsername("                "));
        assertThrows(NullPointerException.class, () -> au2.setPassword("                "));
        assertThrows(NullPointerException.class, () -> new AppUser(" ", "pass", AppRole.ROLE_APP_ADMIN));

        assertSame(au1.getRole(), au2.getRole());
        assertNotSame(au.getRole(), au2.getRole());

    }

    @Test
    void todoItemTest() {
        Person p1 = new Person( "Seb", "Dyr", "sebDyr@lexicon.se", new AppUser("sebzter", "password123", AppRole.ROLE_APP_ADMIN));
        Person p2 = new Person( "pelle", "Gyr", "PelleGyr@lexicon.se", new AppUser("pelzzzZ", "coolguy2", AppRole.ROLE_APP_USER));
        Person p3 = new Person("Pellan", "hymn", "PellanHymn@lexicon.se", new AppUser("bellan_pellan", "guess84", AppRole.ROLE_APP_USER));

        TodoItem td1 = new TodoItem( "First TodoItem", "no description", LocalDate.parse("2025-07-05"), false, p2);
        TodoItem td2 = new TodoItem( "Second TodoItem", " description", LocalDate.parse("2025-06-05"), false, p2);
        TodoItem td4 = new TodoItem("Third TodoItem", "no description", LocalDate.parse("2025-06-05"), false, p1);
        TodoItem td5 = new TodoItem("Fourth TodoItem", " description", LocalDate.parse("2025-06-05"), false, p3);
        TodoItem td6 = new TodoItem("Fifth TodoItem", " description", LocalDate.parse("2025-06-05"), false, p3);

        assertThrows(NullPointerException.class, () -> td1.setTitle(""));
        assertThrows(NullPointerException.class, () -> td5.setTitle("           \t"));
        assertThrows(DateTimeParseException.class, () -> td4.setDeadLine(LocalDate.parse("0000-00-00")));

        assertTrue(!td2.isDone());
        td2.setDone(true);
        assertTrue(td2.isDone());

        td6.setDescription("");
        assertTrue(td6.getDescription().isEmpty());

        assertThrows(NullPointerException.class, () -> td4.setDeadLine(null));

    }

    @Test
    void todoTestItemTaskTest() {
        Person p1 = new Person( "Seb", "Dyr", "sebDyr@lexicon.se", new AppUser("sebzter", "password123", AppRole.ROLE_APP_ADMIN));
        Person p2 = new Person( "pelle", "Gyr", "PelleGyr@lexicon.se", new AppUser("pelzzzZ", "coolguy2", AppRole.ROLE_APP_USER));

        TodoItem td1 = new TodoItem( "First TodoItem", "no description", LocalDate.parse("2025-07-05"), false, p2);

        TodoItemTask tdit1 = new TodoItemTask(td1, p1);

        assertTrue(tdit1.isAssigned());
        tdit1.setAssignee(null);
        assertFalse(tdit1.isAssigned());
        tdit1.setAssignee(p1);
        assertTrue(tdit1.isAssigned());

        assertThrows(NullPointerException.class, () -> new TodoItemTask(null, p1));
    }


    @Test
    void sequencerTests(){
        PersonIdSequencer p = PersonIdSequencer.getInstance();
        p.setCurrentId(0);
        p.nextId();
        p.nextId();
        assertEquals(2, p.getCurrentId());
        assertNotNull(p);
        p.setCurrentId(5);
        assertEquals(5, p.getCurrentId());
        assertThrows(IllegalArgumentException.class, () -> p.setCurrentId(-1));

        TodoItemIdSequencer tiis =  TodoItemIdSequencer.getInstance();

        tiis.nextId();
        tiis.nextId();
        assertNotEquals(3, tiis.getCurrentId());
        assertNotNull(tiis);

        assertThrows(IllegalArgumentException.class, () -> tiis.setCurrentId(-1));


        TodoItemTaskIdSequencer tits = TodoItemTaskIdSequencer.getInstance();
        tits.setCurrentId(590);
        assertNotEquals(0, tits.getCurrentId());
        assertNotNull(tits);

        assertThrows(IllegalArgumentException.class, () -> tits.setCurrentId(-1));
    }


    @Test
    void appUserDaoTest(){
        AppUserDaoCollection appie = AppUserDaoCollection.getInstance();

        AppUser au = new AppUser("seFryzz", "cool1234", AppRole.ROLE_APP_ADMIN);
        AppUser au1 = new AppUser("Appie1!!", "Long___Password****", AppRole.ROLE_APP_USER);
        AppUser au2 = new AppUser("Newbie!!", "short__Passwor**", AppRole.ROLE_APP_USER);


        assertTrue(appie.findAll().isEmpty());
        assertSame(au1, appie.persist(au1));
        assertNotNull(appie.persist(au2));
        assertNotNull(appie.persist(au));

        assertEquals(3, appie.findAll().size());
        assertNotNull(appie.findByUsername("seFryzz"));
        assertNull(appie.findByUsername("sebbzter"));

        appie.remove("Newbie!!");
        assertNotEquals(3, appie.findAll().size());
        assertEquals(2, appie.findAll().size());
        assertNull(appie.findByUsername("Newbie!!"));

    }

    @Test
    void personDaoTest(){

        PersonIdSequencer pids = PersonIdSequencer.getInstance();
        pids.setCurrentId(0);

        Person p1 = new Person( "Seb", "Dyr", "sebDyr@lexicon.se", new AppUser("sebzter", "password123", AppRole.ROLE_APP_ADMIN));
        Person p2 = new Person( "pelle", "Gyr", "PelleGyr@lexicon.se", new AppUser("pelzzzZ", "coolguy2", AppRole.ROLE_APP_USER));
        Person p3 = new Person("Pellan", "hymn", "PellanHymn@lexicon.se", new AppUser("bellan_pellan", "guess84", AppRole.ROLE_APP_USER));
        PersonDaoCollection p = PersonDaoCollection.getInstance();

        assertEquals(0, p.findAll().size());

        assertEquals(p1, p.persist(p1));
        assertEquals(p2, p.persist(p2));
        assertEquals(p3, p.persist(p3));

        assertEquals(3, p.findAll().size());

        System.out.println(p.findAll());

        assertNull(p.findById(45));
        assertNotNull(p.findById(2));

        assertNotNull(p.findByEmail("PellanHymn@lexicon.se"));
        assertNull(p.findByEmail("PellanmellanHymn@lexicon.sesss"));

        p.remove(1);

        assertTrue(p.findAll().size() == 2);

        p.remove(3);

        assertTrue(p.findAll().size() == 1);

        p.remove(4);

        assertTrue(p.findAll().size() == 1);


    }

    @Test
    void todoItemDaoTest(){
        TodoItemIdSequencer tiis = TodoItemIdSequencer.getInstance();
        tiis.setCurrentId(0);

        PersonIdSequencer pids = PersonIdSequencer.getInstance();
        pids.setCurrentId(0);

        TodoItemDaoCollection t = TodoItemDaoCollection.getInstance();


        Person p1 = new Person( "Seb", "Dyr", "sebDyr@lexicon.se", new AppUser("sebzter", "password123", AppRole.ROLE_APP_ADMIN));
        Person p2 = new Person( "pelle", "Gyr", "PelleGyr@lexicon.se", new AppUser("pelzzzZ", "coolguy2", AppRole.ROLE_APP_USER));
        Person p3 = new Person("Pellan", "hymn", "PellanHymn@lexicon.se", new AppUser("bellan_pellan", "guess84", AppRole.ROLE_APP_USER));

        TodoItem td1 = new TodoItem( "First TodoItem", "no description", LocalDate.parse("2025-07-05"), false, p2);
        TodoItem td2 = new TodoItem( "Second TodoItem", " description", LocalDate.parse("2025-09-05"), false, p2);
        TodoItem td4 = new TodoItem("Third TodoItem", " description", LocalDate.parse("2025-10-05"), true, p1);
        TodoItem td5 = new TodoItem("Fourth TodoItem", " description", LocalDate.parse("2025-12-05"), false, p3);
        TodoItem td8 = new TodoItem("Seventh TodoItem", " description", LocalDate.parse("2025-05-05"), true, p2);
        TodoItem td9 = new TodoItem("Eigth TodoItem", " description", LocalDate.parse("2025-06-05"), false, p3);

        assertTrue(t.findAll().isEmpty());

        assertEquals(td1, t.persist(td1));
        assertEquals(td2, t.persist(td2));
        assertEquals(td4, t.persist(td4));
        assertEquals(td5, t.persist(td5));
        assertEquals(td8, t.persist(td8));
        assertEquals(td9, t.persist(td9));

        assertEquals(6, t.findAll().size());

        assertNull(t.findById(17));
        assertNotNull(t.findById(6));

        assertEquals(2, t.findAllByDoneStatus(true).size());
        assertEquals(4, t.findAllByDoneStatus(false).size());

        assertTrue(!t.findByTitleContains("Second").isEmpty());
        assertEquals(6, t.findByTitleContains("TodoItem").size());

        assertTrue(!t.findByPersonId(2).isEmpty());
        assertEquals(2, t.findByPersonId(3).size());

        assertTrue(!t.findByDeadlineAfter(LocalDate.now()).isEmpty());
        assertEquals(3, t.findByDeadlineAfter(LocalDate.now()).size());


        assertTrue(!t.findByDeadlineBefore(LocalDate.now()).isEmpty());
        assertEquals(3, t.findByDeadlineBefore(LocalDate.now()).size());

        t.remove(4);
        t.remove(2);
        assertEquals(4, t.findAll().size());


    }

    @Test
    void todoItemTaskDaoTest(){

        PersonIdSequencer pids = PersonIdSequencer.getInstance();
        pids.setCurrentId(0);

        TodoItemTaskIdSequencer tits = TodoItemTaskIdSequencer.getInstance();
        tits.setCurrentId(0);

        TodoItemTaskDAOCollection t = TodoItemTaskDAOCollection.getInstance();

        Person p1 = new Person( "Seb", "Dyr", "sebDyr@lexicon.se", new AppUser("sebzter", "password123", AppRole.ROLE_APP_ADMIN));
        Person p2 = new Person( "pelle", "Gyr", "PelleGyr@lexicon.se", new AppUser("pelzzzZ", "coolguy2", AppRole.ROLE_APP_USER));
        Person p3 = new Person("Pellan", "hymn", "PellanHymn@lexicon.se", new AppUser("bellan_pellan", "guess84", AppRole.ROLE_APP_USER));

        TodoItem td1 = new TodoItem( "First TodoItem", "no description", LocalDate.parse("2025-07-05"), false, p2);
        TodoItem td2 = new TodoItem( "Second TodoItem", " description", LocalDate.parse("2025-09-05"), false, p2);
        TodoItem td4 = new TodoItem("Third TodoItem", " description", LocalDate.parse("2025-10-05"), true, p1);
        TodoItem td5 = new TodoItem("Fourth TodoItem", " description", LocalDate.parse("2025-12-05"), false, p3);

        TodoItemTask tdit1 = new TodoItemTask(td1, p1);
        TodoItemTask tdit2 = new TodoItemTask(td2, p1);
        TodoItemTask tdit4 = new TodoItemTask(td4, null);
        TodoItemTask tdit5 = new TodoItemTask(td5, p2);

        assertEquals(0, t.findAll().size());

        assertEquals(tdit1, t.persist(tdit1));
        assertEquals(tdit2, t.persist(tdit2));
        assertEquals(tdit4, t.persist(tdit4));
        assertEquals(tdit5, t.persist(tdit5));

        assertEquals(4, t.findAll().size());

        assertNotNull(t.findById(3));
        assertNull(t.findById(6));

        assertEquals(3, t.findByAssignedStatus(true).size());
        assertEquals(1, t.findByAssignedStatus(false).size());

        assertNotNull(t.findByPersonId(2));
        assertEquals(1, t.findByPersonId(2).size());

        t.remove(1);
        assertEquals(3, t.findAll().size());
        assertNull(t.findById(1));
    }

}
