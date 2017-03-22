INSERT INTO resume (uuid, full_name) VALUES
  ('c7db9d45-1614-4be5-9402-d3b59dc2621e',  'First Man'),
  ('820b7f89-1113-4858-9d35-30dcd03cd245',	'Second Man'),
  ('45dab958-f3b1-4b3a-879e-a4a4bbe059a4',	'Third Man');

INSERT INTO contact (type, value, resume_uuid) VALUES
  ('PHONE', '+38 066 777 - 333 - 00', 'c7db9d45-1614-4be5-9402-d3b59dc2621e'),
  ('SKYPE', 'NEW_SKYPE', 'c7db9d45-1614-4be5-9402-d3b59dc2621e'),
  ('MAIL', 'update@u-rise.com', 'c7db9d45-1614-4be5-9402-d3b59dc2621e'),
  ('SKYPE', 'skype_us2', '820b7f89-1113-4858-9d35-30dcd03cd245'),
  ('MAIL', 'email2@mail.ru', '820b7f89-1113-4858-9d35-30dcd03cd245'),
  ('SKYPE', 'us_skype3', '45dab958-f3b1-4b3a-879e-a4a4bbe059a4'),
  ('MAIL', 'email3@mail.ru', '45dab958-f3b1-4b3a-879e-a4a4bbe059a4');

INSERT INTO section (type, resume_uuid, value) VALUES
  ('PERSONAL',	'c7db9d45-1614-4be5-9402-d3b59dc2621e',	'{"CLASSNAME":"com.urise.webapp.model.TextSection","INSTANCE":{"content":"Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."}}'),
  ('OBJECTIVE',	'c7db9d45-1614-4be5-9402-d3b59dc2621e',	'{"CLASSNAME":"com.urise.webapp.model.TextSection","INSTANCE":{"content":"OBJECTIVE TEXT"}}'),
  ('QUALIFICATIONS',	'c7db9d45-1614-4be5-9402-d3b59dc2621e',	'{"CLASSNAME":"com.urise.webapp.model.ListSection","INSTANCE":{"items":["JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2","Version control: Subversion, Git, Mercury, ClearCase, Perforce","DB:PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB)"]}}'),
  ('PERSONAL',	'45dab958-f3b1-4b3a-879e-a4a4bbe059a4',	'{"CLASSNAME":"com.urise.webapp.model.TextSection","INSTANCE":{"content":"Personal data 3"}}'),
  ('EXPERIENCE',	'45dab958-f3b1-4b3a-879e-a4a4bbe059a4',	'{"CLASSNAME":"com.urise.webapp.model.OrganizationSection","INSTANCE":{"organizations":[{"homePage":{"name":"Place work number 1","url":"http://URL3.com"},"positions":[{"startDate":{"year":2015,"month":12,"day":1},"endDate":{"year":3000,"month":1,"day":1},"title":"Аспирантура","description":"Прогрммист C/C++"}]}]}}'),
  ('PERSONAL',	'820b7f89-1113-4858-9d35-30dcd03cd245',	'{"CLASSNAME":"com.urise.webapp.model.TextSection","INSTANCE":{"content":"Personal data 2"}}');
