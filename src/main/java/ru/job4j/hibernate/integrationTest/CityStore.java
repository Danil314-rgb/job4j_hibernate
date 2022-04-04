package ru.job4j.hibernate.integrationTest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class CityStore {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public City create(City city) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(city);
        session.getTransaction().commit();
        session.close();
        return city;
    }

    public List<City> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<City> res = session.createQuery("from City").list();
        session.getTransaction();
        session.close();
        return res;
    }

}
