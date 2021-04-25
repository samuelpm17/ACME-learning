insert into roles (name) values ('ROLE_INSTRUCTOR');
insert into roles (name) values ('ROLE_STUDENT');

insert into users (username, password, role_id) values ('samuel.pena', '$2a$10$c2TEaTRn5N/pJlcRgNo0g.M9/ArAA0dzqIBzosR84gZlefOyTqGqq', 1);
insert into users (username, password, role_id) values ('andres.perez', '$2a$10$c2TEaTRn5N/pJlcRgNo0g.M9/ArAA0dzqIBzosR84gZlefOyTqGqq', 2);
insert into users (username, password, role_id) values ('adriana.jaramillo', '$2a$10$c2TEaTRn5N/pJlcRgNo0g.M9/ArAA0dzqIBzosR84gZlefOyTqGqq', 2);

insert into people (name, lastname, email, user_id) values ('Samuel', 'Peña', 'samuel.pena@mail.com', 1);
insert into people (name, lastname, email, user_id) values ('Andres', 'Perez', 'andres.perez@mail.com', 2);
insert into people (name, lastname, email, user_id) values ('Adriana', 'Jaramillo', 'adriana.jaramillo@mail.com', 3);

insert into instructors (salary, person_id) values (5000, 1);

insert into students (parent_phone, parent_name, person_id) values ('4444444', 'Juan Perez', 2);
insert into students (parent_phone, parent_name, person_id) values ('3003334455', 'German Jaramillo', 3);

insert into courses (name,description,started) values ('Math','Just Math',true);
insert into courses (name,description,started) values ('Programming','The best course',true);
insert into courses (name,description,started) values ('Painting','Its manga time!',true);

insert into instructors_courses (instructor_person_id, courses_id ) values (1, 1);
insert into instructors_courses (instructor_person_id, courses_id ) values (1, 2);
insert into instructors_courses (instructor_person_id, courses_id ) values (1, 3);
