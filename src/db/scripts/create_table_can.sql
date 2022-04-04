/*1. select fetch - Пример*/
create table accounts
(
    id       serial primary key,
    username text,
    active   boolean
);
create table students
(
    id         serial primary key,
    name       text,
    age        int,
    city       text,
    account_id int references accounts (id)
);
create table books
(
    id              serial primary key,
    name            text,
    publishingHouse text
);
create table accounts_books
(
    account_id int,
    books_id   int
);