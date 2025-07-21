package org.fyr.DAO;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.fyr.model.AppUser;

import java.io.*;
import java.lang.runtime.ObjectMethods;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


//TODO -- • Use the Stream API in implementing DAO classes
public class AppUserDaoCollection implements AppUserDAO<AppUser>, Serializable {

    private static AppUserDaoCollection appUserDaoCollection;

    private ObjectMapper mapper = new ObjectMapper();


    private String file = "src/main/resources/protocols/Appuser.json";

    // TODO -- Göra till Set istället för List???
    private List<AppUser> appUsers;


    private AppUserDaoCollection(){
        appUsers = new ArrayList<>();
    }

    @Override
    public AppUser persist(AppUser appUser) {
        if(appUsers.contains(appUser)){
            return appUser;
        }
        appUsers.add(appUser);

        return appUser;
    }

    @Override
    public AppUser findByUsername(String username) {

        //TODO -- Equals eller equalsIgnoreCase??

         Optional<AppUser> appie  =  appUsers.stream()
                .filter(s -> s.getUsername().equalsIgnoreCase(username))
                .findFirst();

        return appie.get();
    }

    @Override
    public Collection<AppUser> findAll() {

        return appUsers;
    }

    @Override
    public void remove(String username) {
        appUsers.removeIf(s ->
                //TODO -- Equals eller equalsIgnoreCase??
            s.getUsername().equalsIgnoreCase(username));

    }

    public static AppUserDaoCollection getInstance(){
        if(appUserDaoCollection == null){
            appUserDaoCollection = new AppUserDaoCollection();

        }
        return appUserDaoCollection;
    }

    public void loadAppusers(){

        try(FileReader reader = new FileReader(file)){
            appUsers = mapper.readValue(reader, new TypeReference<List<AppUser>>() {});

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAppusers(){

        try(FileWriter writer = new FileWriter(file)){

            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            writer.write(mapper.writeValueAsString(appUsers).toString());

            appUsers.clear();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
