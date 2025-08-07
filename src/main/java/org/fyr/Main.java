package org.fyr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.fyr.DAO.AppUserDaoCollection;
import org.fyr.DAO.PersonDaoCollection;
import org.fyr.DAO.TodoItemDaoCollection;
import org.fyr.DAO.TodoItemTaskDAOCollection;
import org.fyr.Sequencers.PersonIdSequencer;
import org.fyr.Sequencers.Sequencer;
import org.fyr.Sequencers.TodoItemIdSequencer;
import org.fyr.Sequencers.TodoItemTaskIdSequencer;

import java.io.*;
import java.util.*;

public class Main {

    static {
        //TODO -- Behöver jag ha kvar Appuser filen? För det är inga värden som skrivs till den.

        AppUserDaoCollection.getInstance().loadAppusers();

        PersonDaoCollection.getInstance().loadPersons();
        TodoItemDaoCollection.getInstance().loadTodoItems();
        TodoItemTaskDAOCollection.getInstance().loadTodoItemTasks();
        loadIDSequencers();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        /**
         * No menu has been implemented as I never saw this in the requirements,
         *
         * Questions for simon.
         * First question is  the first line in the static block in the main class.
         *
         * The second question is in the "loadIDSequencers" method after the main method in the Main class.
         */



        saveAllData();
        saveIdSequencers();
    }

    private static void saveAllData() {

        AppUserDaoCollection.getInstance().saveAppusers();
        PersonDaoCollection.getInstance().savePersons();
        TodoItemDaoCollection.getInstance().saveTodoItems();
        TodoItemTaskDAOCollection.getInstance().saveTodoItemTasks();

    }

    private static void loadIDSequencers() {

        File file = new File("src/main/resources/protocols/SequencerValues.json");

        if (file.length() <= 0) return;

        ObjectMapper mapper = new ObjectMapper();
        HashMap<Sequencer, Integer> map;


        try (FileReader reader = new FileReader(file)) {

            /**
             * Fråga till Simon, om det här är en bra lösning, eller om jag borde spara det som strängar,
             * och sen läsa in det som strängar och sen mappa?
             * Jag tyclker det här fungerar, och jag tror det fungerar ju fler man har,
             * så länge man lägger till dom i klassen: SequencerKeyDeserializer.class
             *
             * save Sequencers as strings, using classnames,
             * and then read the values as strings and parsing to values to respective class???
             *
             * or use keydeserializer method?
             */

            SimpleModule simpMod = new SimpleModule();

            simpMod.addKeyDeserializer(Sequencer.class, new SequencerKeyDeserializer());

            mapper.registerModule(simpMod);
            map = mapper.readValue(reader, new TypeReference<HashMap<Sequencer, Integer>>() {});


        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }


        for (Map.Entry<Sequencer, Integer> entry : map.entrySet()) {


            if(entry.getKey() instanceof PersonIdSequencer){
                System.out.println("PersonIdSequencer");
                entry.getKey().setCurrentId(entry.getValue());
            }
            else if(entry.getKey() instanceof TodoItemIdSequencer){
                entry.getKey().setCurrentId(entry.getValue());
            }
            else if(entry.getKey() instanceof TodoItemTaskIdSequencer){
                entry.getKey().setCurrentId(entry.getValue());
            }
            else {
                System.out.println("----------  NOT A KNOW VALUE!!!!!!!!!!!!!!!!");
            }

        }


    }

    private static void saveIdSequencers() {

        HashMap<Sequencer, Integer> map = new HashMap<>();

        map.put(PersonIdSequencer.getInstance(), PersonIdSequencer.getInstance().getCurrentId());
        map.put(TodoItemIdSequencer.getInstance(), TodoItemIdSequencer.getInstance().getCurrentId());
        map.put(TodoItemTaskIdSequencer.getInstance(), TodoItemTaskIdSequencer.getInstance().getCurrentId());

        try (FileWriter writer = new FileWriter("src/main/resources/protocols/SequencerValues.json")) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            writer.write(mapper.writeValueAsString(map));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
