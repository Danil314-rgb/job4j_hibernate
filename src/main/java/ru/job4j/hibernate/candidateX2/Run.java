package ru.job4j.hibernate.candidateX2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Run {

    public static void main(String[] args) {
        Candidate res = null;

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Post post1 = Post.of("Java", "1 year");
            Post post2 = Post.of("C#", "12 years");
            Post post3 = Post.of("PHP", "2 years");
            session.persist(post1);
            session.persist(post2);
            session.persist(post3);

            DataBasePost db = DataBasePost.of("Job");
            db.addPost(post1);
            db.addPost(post2);
            db.addPost(post3);
            session.persist(db);

            Candidate candidate = Candidate.of("Егор","Нет опыта", 400.98, db);
            session.persist(candidate);

            res = session.createQuery(
                            "select can from Candidate can" +
                                    " where can.id = :cId", Candidate.class
                    ).setParameter("cId", 1)
                    .uniqueResult();
            System.out.println(res);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
