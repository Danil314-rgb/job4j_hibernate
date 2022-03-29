package ru.job4j.hibernate.candidate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class Run {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Candidate candidate1 = Candidate.of("Данил","Нет опыта", 500.45);
            Candidate candidate2 = Candidate.of("Егор","Нет опыта", 400.98);
            Candidate candidate3 = Candidate.of("Виктор","Опыт 2 года", 1500);
            Candidate candidate4 = Candidate.of("Игорь","Опыт 5 лет", 5000);

            session.persist(candidate1);
            session.persist(candidate2);
            session.persist(candidate3);
            session.persist(candidate4);

            Query queryAll = session.createQuery("from Candidate");
            for (var item : queryAll.list()) {
                System.out.println(item);
            }

            Query queryById = session.createQuery("from Candidate c where c.id = :fId");
            queryById.setParameter("fId", 2);
            System.out.println(queryById.uniqueResult());

            Query queryByName = session.createQuery("from Candidate c where c.name = :fName");
            queryByName.setParameter("fName", "Игорь");
            System.out.println(queryByName.uniqueResult());

            Query queryUpdate = session.createQuery(
                    "update Candidate c set c.experience = :newExp, c.salary = :newSal where c.id = : fId");
            queryUpdate.setParameter("newExp", "Опыт 1 год");
            queryUpdate.setParameter("newSal", 1000.00);
            queryUpdate.setParameter("fId", 1);
            queryUpdate.executeUpdate();

            session.createQuery("delete from Candidate where id = :fId")
                    .setParameter("fId", 2)
                    .executeUpdate();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
