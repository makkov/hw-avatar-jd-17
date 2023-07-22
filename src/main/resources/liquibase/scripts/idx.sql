-- — liquibase formatted sql
-- changeset makkov:1

CREATE INDEX IF NOT EXISTS student_name_idx ON student (name);
CREATE INDEX IF NOT EXISTS  faculty_name_color_idx ON faculty (name, color);