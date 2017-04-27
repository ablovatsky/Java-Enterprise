
/* USER_PROFILE Table */
INSERT INTO PROFILE(type) VALUES ('USER');
INSERT INTO PROFILE(type) VALUES ('ADMIN');
INSERT INTO PROFILE(type) VALUES ('DBA');

/* Subdivision Table */
INSERT INTO avectis.subdivision (name) VALUES ('Administrators');
INSERT INTO avectis.subdivision (name) VALUES ('АВС');
INSERT INTO avectis.subdivision (name) VALUES ('ГАП');
INSERT INTO avectis.subdivision (name) VALUES ('ГИП');
INSERT INTO avectis.subdivision (name) VALUES ('ИИ');
INSERT INTO avectis.subdivision (name) VALUES ('ПКиСД');
INSERT INTO avectis.subdivision (name) VALUES ('ПКО');
INSERT INTO avectis.subdivision (name) VALUES ('ПО');
INSERT INTO avectis.subdivision (name) VALUES ('ПрКСиЭ');
INSERT INTO avectis.subdivision (name) VALUES ('СБ');
INSERT INTO avectis.subdivision (name) VALUES ('СБЭ');
INSERT INTO avectis.subdivision (name) VALUES ('СУП');
INSERT INTO avectis.subdivision (name) VALUES ('ЭМиПНР');

/* WORKER Table */
INSERT INTO WORKER(sso_id, password, first_name, last_name, patronymic, email, subdivision_id)
VALUES ('admin','$2a$04$psdZWu3EpehkjnL0cERrhOFE8EtBIUh4apzAXVSv4j9EoA3HKENcS', 'Admin','Admin','admin','admin@admin.com', (SELECT id FROM subdivision WHERE name LIKE 'Administrators'));
INSERT INTO avectis.worker (sso_id, password, first_name, last_name, patronymic, email, subdivision_id)
VALUES ('vTsvirko', '$2a$10$uI8IXy9nslN7uPYOST5UcOyTKeVf.FlCcs9A6DLOEewDQFPP3O/Zq', 'Виталий', 'Цвирко', 'Леонидович', 'vTsvirko@gmail.com', (SELECT id FROM subdivision WHERE name LIKE 'ПО'));
INSERT INTO avectis.worker (sso_id, password, first_name, last_name, patronymic, email, subdivision_id)
VALUES ('iPalcheuski', '$2a$10$nBdly8nZgIa6TPeNtYZToucJ3NptuU5JqTIKBMyxRH6JAFLbg4mUi', 'Иван', 'Пальчевский', 'Иванович', 'iPalcheuski@avectis.by', (SELECT id FROM subdivision WHERE name LIKE 'ПО'));
INSERT INTO avectis.worker (sso_id, password, first_name, last_name, patronymic, email, subdivision_id)
VALUES ('vBukmyrza', '$2a$10$HWisccumpS72oblbuexJzOELwvd4/Zf3554r3NYX.0ncezo13iWKy', 'Вадим', 'Букмырза', 'Ремусович', 'vBukmyrza@avectis.by', (SELECT id FROM subdivision WHERE name LIKE 'ПО'));
INSERT INTO avectis.worker (sso_id, password, first_name, last_name, patronymic, email, subdivision_id)
VALUES ('dBasalai', '$2a$10$etXB9D7yrpSGiH45ZokfB.moC20JT41LdrKeP0auBD4oZNOFUUScu', 'Дмитрий', 'Басалай', 'Васильевич', 'dBasalai@avectis.by', (SELECT id FROM subdivision WHERE name LIKE 'ПО'));

/* Populate JOIN Table */
INSERT INTO WORKER_TO_PROFILE (worker_id, worker_profile_id)
  SELECT worker.id, profile.id FROM worker worker, profile profile
  where worker.sso_id='admin' and profile.type='ADMIN';

INSERT INTO WORKER_TO_PROFILE (worker_id, worker_profile_id)
  SELECT worker.id, profile.id FROM worker worker, profile profile
  where worker.sso_id='vTsvirko' and profile.type='USER';

INSERT INTO WORKER_TO_PROFILE (worker_id, worker_profile_id)
  SELECT worker.id, profile.id FROM worker worker, profile profile
  where worker.sso_id='iPalcheuski' and profile.type='USER';

INSERT INTO WORKER_TO_PROFILE (worker_id, worker_profile_id)
  SELECT worker.id, profile.id FROM worker worker, profile profile
  where worker.sso_id='vBukmyrza' and profile.type='USER';

INSERT INTO WORKER_TO_PROFILE (worker_id, worker_profile_id)
  SELECT worker.id, profile.id FROM worker worker, profile profile
  where worker.sso_id='dBasalai' and profile.type='USER';


/* CONTRACTS_STATE Table */
INSERT INTO CONTRACTS_STATE(type) VALUES ('Открыт');
INSERT INTO CONTRACTS_STATE(type) VALUES ('Закрыт');
INSERT INTO CONTRACTS_STATE(type) VALUES ('Приостановлен');