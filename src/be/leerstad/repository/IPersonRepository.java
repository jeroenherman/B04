package be.leerstad.repository;

import be.leerstad.business.Person;

import java.util.List;

public interface IPersonRepository extends IRepository<Person> {
    public List<Person> getAll();
}
