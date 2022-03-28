create table j_brand
(
    id   serial primary key,
    name varchar(2000)
);

create table j_model
(
    id   serial primary key,
    name varchar(2000)
);

create table j_brand_j_model
(
    brand_id int,
    model_id int
);