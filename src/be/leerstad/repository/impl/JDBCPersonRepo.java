package be.leerstad.repository.impl;

import be.leerstad.business.Person;
import be.leerstad.repository.IPersonRepository;

import java.sql.*;
import java.util.*;


public class JDBCPersonRepo implements IPersonRepository {
    private static String connURL = "jdbc:mysql://172.16.1.11/cvo"; // jdbc:mysql://<host>/<dBaseName>
    private static String user = "cvo";
    private static String password = "123";


    @Override
    public List<Person> getAll() {
        try (Connection connection = DriverManager.getConnection(connURL, user, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from persons");

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Person> result = new ArrayList<>();
            while ( resultSet.next()) {
                Person p = new Person();
                p.setName(resultSet.getString("name"));
                p.setId(resultSet.getInt("id"));
                p.setCountry(resultSet.getString("country"));
                result.add(p);
            }
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean add(Person aggregate) {
        Boolean result = false;

        if (!(contains(aggregate))) {
            try (Connection connection = DriverManager.getConnection(connURL, user, password)) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `persons`(`id`, `country`, `name`) VALUES (?,?,?)");
                preparedStatement.setInt(1, aggregate.getId());
                preparedStatement.setString(2, aggregate.getCountry());
                preparedStatement.setString(3, aggregate.getName());
                Integer updateRows = preparedStatement.executeUpdate();

                if(updateRows >0)
                    result = true;


            } catch (SQLException e) {
                e.printStackTrace();
                return result;
            }
        }
        return result;
    }

    @Override
    public boolean remove(Person aggregate) {
        Boolean result = false;
        if (aggregate == null)
        return false;
        try (Connection connection = DriverManager.getConnection(connURL, user, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `persons` WHERE `id` = ?");
            preparedStatement.setInt(1,aggregate.getId());
            Integer updateRows = preparedStatement.executeUpdate();

            if(updateRows >0)
                result = true;

        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @Override
    public Person getByID(int id) {
        try (Connection connection = DriverManager.getConnection(connURL, user, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT `id`, `country`, `name` FROM `persons` WHERE `id` = ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Person p = new Person();
            while (resultSet.next()) {
                p.setName(resultSet.getString("name"));
                p.setId(resultSet.getInt("id"));
                p.setCountry(resultSet.getString("country"));
            }
            return p;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Boolean contains(Person person) {
        if (person == null)
            return false;
        Person foundPerson = this.getByID(person.getId());

         if (foundPerson.getName() == null) {
             return false;
         }
         else return true;
    }
}
