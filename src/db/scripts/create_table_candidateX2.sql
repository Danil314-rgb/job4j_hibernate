drop table candidates;
drop table dbPosts;
drop table posts;
drop table dbPosts_posts;

create table dbPosts
(
    id   serial primary key,
    name text
);
create table candidates
(
    id              serial primary key,
    name            text,
    experience      text,
    salary          DOUBLE PRECISION,
    databasepost_id int references dbPosts (id)
);
create table posts
(
    id          serial primary key,
    name        text,
    description text
);
create table dbposts_posts
(
    databasepost_id int,
    posts_id        int
);

select*
from dbPosts
select*
from candidates
select*
from posts
select*
from dbPosts_posts