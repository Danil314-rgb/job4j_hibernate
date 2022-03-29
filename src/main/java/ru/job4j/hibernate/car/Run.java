package ru.job4j.hibernate.car;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class Run {

    public static void main(String[] args) {
        List<Brand> list = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata()
                    .buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Brand brand = Brand.of("Lada");
            session.persist(brand);

            Model model1 = Model.of("2107", brand);
            Model model2 = Model.of("2110", brand);
            Model model3 = Model.of("2111", brand);

            session.persist(model1);
            session.persist(model2);
            session.persist(model3);

            list = session.createQuery("from Brand").list();

            for (Brand brands : list) {
                for (Model model : brands.getModels()) {
                    System.out.println(model);
                }
            }

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
