package ru.job4j.hibernate.book;

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

            Book book1 = Book.of("Игрок");
            Book book2 = Book.of("Сияние");
            Book book3 = Book.of("Оно");
            Book book4 = Book.of("Наша история");

            Author author1 = Author.of("Достоевский");
            author1.getBooks().add(book1);

            Author author2 = Author.of("Кинг");
            author2.getBooks().add(book2);
            author2.getBooks().add(book3);

            Author author3 = Author.of("Я");
            author3.getBooks().add(book4);

            Author author4 = Author.of("Ты");
            author4.getBooks().add(book4);

            session.persist(author1);
            session.persist(author2);
            session.persist(author3);
            session.persist(author4);

            Author author = session.get(Author.class, 2);
            session.remove(author);

            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
