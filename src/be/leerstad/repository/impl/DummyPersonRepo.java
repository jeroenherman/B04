package be.leerstad.repository.impl;

import be.leerstad.business.Person;
import be.leerstad.repository.IPersonRepository;

import java.util.*;

public class DummyPersonRepo implements IPersonRepository {
    private Map<Integer, Person> myPersons;

    public DummyPersonRepo() {
        myPersons = new HashMap<>();
    }

    @Override
    public boolean add(Person aggregate) {
        myPersons.put(aggregate.getId(), aggregate);
        return myPersons.containsKey(aggregate.getId());
    }

    @Override
    public boolean remove(Person aggregate) {
        myPersons.remove(aggregate.getId());
        return !(myPersons.containsKey(aggregate.getId()));
    }

    @Override
    public Person getByID(int id) {
        return myPersons.get(id);
    }

    @Override
    public List<Person> getAll() {
        // DO NOT OPEN THE GATES OF MORDOR!!
        // use Collections.unmodifiableXXX to disable bypassing the add/remove methods
        return Collections.unmodifiableList(new ArrayList<>(myPersons.values()));
    }

}

