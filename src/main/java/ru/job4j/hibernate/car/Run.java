package ru.job4j.hibernate.car;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Run {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata()
                    .buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Brand brand = Brand.of("Lana");
            session.save(brand);

            Model model1 = Model.of("2107");
            model1.addBrand(session.load(Brand.class, 1));
            session.save(model1);

            Model model2 = Model.of("2111");
            model2.addBrand(session.load(Brand.class, 1));
            session.save(model2);

            Model model3 = Model.of("2111");
            model3.addBrand(session.load(Brand.class, 1));
            session.save(model3);

            Model model4 = Model.of("2110");
            model4.addBrand(session.load(Brand.class, 1));
            session.save(model4);

            Model model5 = Model.of("2114");
            model5.addBrand(session.load(Brand.class, 1));
            session.save(model5);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
