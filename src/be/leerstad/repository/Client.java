package be.leerstad.repository;

import be.leerstad.business.Person;
import be.leerstad.repository.impl.DummyPersonRepo;
import be.leerstad.repository.impl.JDBCPersonRepo;

public class Client {

    public static void main(String[] args) {
        // SWITCH HERE for the concrete implementation
        IPersonRepository myRepo = new JDBCPersonRepo();
        System.out.println("Persons: " + myRepo.getAll());
        Person p = new Person(11, "Karel");
        boolean result = myRepo.add(p);
        System.out.println("Added: " + result);
        System.out.println("Persons: " + myRepo.getAll());
        Person foundPerson = myRepo.getByID(11);
        System.out.println("Found: " + foundPerson.getName());
        Person louis = new Person(111, "Louis");
        try{
            // trying to bypass the interface of the repo BUT this
            // throws, @runtime, a java.lang.UnsupportedOperationException
            // thanks to the Collections.unmodifiableList()
            myRepo.getAll().add(louis);
        }
        catch(UnsupportedOperationException uoe) {
            //THIS is the way to add an aggregate! through the repo
            myRepo.add(louis);

        }
        System.out.println("Persons: " + myRepo.getAll());

        System.out.println("Remove Karel: " + myRepo.remove(p));
    }

}
