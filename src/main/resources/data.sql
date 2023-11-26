insert into inspection (car_id, created_at)
values (45, CURRENT_TIMESTAMP);
insert into question (question_text)
values ('ruhsatta eksiklik var mi?');
insert into question (question_text)
values ('aküde eksiklik var mi?');
insert into question (question_text)
values ('mültümedya eksiklik var mi?');
insert into inspection (car_id, created_at)
values (45, CURRENT_TIMESTAMP);
insert into answer (answer, inspection_id, QUESTION_ID, COMMENT)
values (true, 1, 1, 'ariza');
insert into answer (answer, inspection_id, QUESTION_ID, COMMENT)
values (true, 1, 2, 'sıkıntı');
insert into answer (answer, inspection_id, QUESTION_ID)
values (false, 1, 3);
insert into answer (answer, inspection_id, QUESTION_ID, COMMENT)
values (true, 2, 1, 'arizaaaa');
insert into answer (answer, inspection_id, QUESTION_ID, COMMENT)
values (true, 2, 2, 'sıkıntıııı');
insert into answer (answer, inspection_id, QUESTION_ID)
values (false, 2, 3);