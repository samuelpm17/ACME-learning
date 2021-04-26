insert into roles (name) values ('ROLE_INSTRUCTOR');
insert into roles (name) values ('ROLE_STUDENT');

insert into users (username, password, role_id) values ('samuel.pena', '$2a$10$c2TEaTRn5N/pJlcRgNo0g.M9/ArAA0dzqIBzosR84gZlefOyTqGqq', 1);
insert into users (username, password, role_id) values ('andres.perez', '$2a$10$c2TEaTRn5N/pJlcRgNo0g.M9/ArAA0dzqIBzosR84gZlefOyTqGqq', 2);
insert into users (username, password, role_id) values ('adriana.jaramillo', '$2a$10$c2TEaTRn5N/pJlcRgNo0g.M9/ArAA0dzqIBzosR84gZlefOyTqGqq', 1);

insert into users (username, password, role_id) values ('carlos.alvarez', '$2a$10$c2TEaTRn5N/pJlcRgNo0g.M9/ArAA0dzqIBzosR84gZlefOyTqGqq', 2);
insert into users (username, password, role_id) values ('juan.perez', '$2a$10$c2TEaTRn5N/pJlcRgNo0g.M9/ArAA0dzqIBzosR84gZlefOyTqGqq', 2);
insert into users (username, password, role_id) values ('luisa.perez', '$2a$10$c2TEaTRn5N/pJlcRgNo0g.M9/ArAA0dzqIBzosR84gZlefOyTqGqq', 2);
insert into users (username, password, role_id) values ('mauricio.alvarez', '$2a$10$c2TEaTRn5N/pJlcRgNo0g.M9/ArAA0dzqIBzosR84gZlefOyTqGqq', 2);
insert into users (username, password, role_id) values ('maria.gomez', '$2a$10$c2TEaTRn5N/pJlcRgNo0g.M9/ArAA0dzqIBzosR84gZlefOyTqGqq', 2);
insert into users (username, password, role_id) values ('liliana.torres', '$2a$10$c2TEaTRn5N/pJlcRgNo0g.M9/ArAA0dzqIBzosR84gZlefOyTqGqq', 2);

insert into people (name, lastname, email, user_id) values ('Samuel', 'Peña', 'samuel.pena@mail.com', 1);
insert into people (name, lastname, email, user_id) values ('Andres', 'Perez', 'andres.perez@mail.com', 2);
insert into people (name, lastname, email, user_id) values ('Adriana', 'Jaramillo', 'adriana.jaramillo@mail.com', 3);

insert into people (name, lastname, email, user_id) values ('Carlos', 'Alvarez', 'carlos.alvarez@mail.com', 4);
insert into people (name, lastname, email, user_id) values ('Juan', 'Perez', 'juan.perez@mail.com', 5);
insert into people (name, lastname, email, user_id) values ('Luisa', 'Perez', 'luisa.perez@mail.com', 6);
insert into people (name, lastname, email, user_id) values ('Mauricio', 'Alvarez', 'mauricio.alvarez@mail.com', 7);
insert into people (name, lastname, email, user_id) values ('Maria', 'Gomez', 'maria.gomez@mail.com', 8);
insert into people (name, lastname, email, user_id) values ('Liliana', 'Torres', 'liliana.torres@mail.com', 9);

insert into instructors (salary, person_id) values (5000, 1);
insert into instructors (salary, person_id) values (3000, 3);

insert into students (parent_phone, parent_name, person_id) values ('4444444', 'Juan Perez', 2);
insert into students (parent_phone, parent_name, person_id) values ('3333333','Julian',4);
insert into students (parent_phone, parent_name, person_id) values ('5222200','Monica',5);
insert into students (parent_phone, parent_name, person_id) values ('6662255','Luis',6);
insert into students (parent_phone, parent_name, person_id) values ('4440000','Hugo',7);
insert into students (parent_phone, parent_name, person_id) values ('6548583','Estrella',8);
insert into students (parent_phone, parent_name, person_id) values ('5441212','Sofia',9);

insert into courses (name,description,started,active) values ('Math','Just Math',true,true);
insert into courses (name,description,started,active) values ('Programming','The best course',true,true);
insert into courses (name,description,started,active) values ('Painting','Its manga time!',true,true);

insert into instructors_courses (instructor_id, courses_id) values (1, 1);
insert into instructors_courses (instructor_id, courses_id) values (1, 2);
insert into instructors_courses (instructor_id, courses_id) values (2, 3);