CREATE TABLE resume
(
  uuid CHAR(36) PRIMARY KEY NOT NULL,
  full_name TEXT NOT NULL
);

CREATE TABLE contact
(
  id SERIAL,
  resume_uuid CHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
  type TEXT NOT NULL,
  value TEXT NOT NULL
);

CREATE UNIQUE INDEX contact_uuid_type_index ON contact (resume_uuid, type);

CREATE TABLE section
(
  id INTEGER DEFAULT nextval('section_id_seq'::regclass) PRIMARY KEY NOT NULL,
  type TEXT NOT NULL,
  resume_uuid CHAR(36) NOT NULL,
  CONSTRAINT section_resume_uuid_fk FOREIGN KEY (resume_uuid) REFERENCES resume (uuid)
);
CREATE UNIQUE INDEX section_type_uuid_index ON section (type, resume_uuid);

CREATE TABLE text_section
(
  id INTEGER DEFAULT nextval('text_section_id_seq'::regclass) PRIMARY KEY NOT NULL,
  content TEXT NOT NULL,
  section_id INTEGER NOT NULL,
  CONSTRAINT text_section_id_fk FOREIGN KEY (section_id) REFERENCES section (id)
);
CREATE UNIQUE INDEX text_section_section_id_uindex ON text_section (section_id);