create table authors
(
    id   serial primary key,
    name text
);

create table books
(
    id   serial primary key,
    name text
);

create table authors_books
(
    author_id int,
    books_id  int

);