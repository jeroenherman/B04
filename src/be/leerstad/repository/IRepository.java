package be.leerstad.repository;

public interface IRepository<T> {
    public boolean add(T aggregate);

    public boolean remove(T aggregate);

    public T getByID(int id);

}