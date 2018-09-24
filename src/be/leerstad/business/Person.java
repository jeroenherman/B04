package be.leerstad.business;
public class Person {
    private int id;
    private String name;
    private String country;

    public Person() {
    }

    public Person(int id, String name, String country) {
        super();
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Person(int id, String name) {
        this.setId(id);
        this.setName(name);
        this.setCountry("BELGIUM");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public String getCountry() {
        return country;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCountry(String country) {this.country = country;}
    @Override
    public String toString() {
        return id + " - " + name;
    }


}
