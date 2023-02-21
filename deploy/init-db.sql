DROP DATABASE IF EXISTS students_learning;

CREATE DATABASE students_learning;

CREATE TABLE IF NOT EXISTS public.student
(
    id bigint NOT NULL,
    name varchar(255) NOT NULL,
    email varchar(255),
    CONSTRAINT student_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.student
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.mark
(
    id bigint NOT NULL,
    discipline varchar(255) NOT NULL,
    value double precision,
    CONSTRAINT mark_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.mark
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.student_mark
(
    student_id bigint NOT NULL,
    mark_id bigint NOT NULL,
    CONSTRAINT student_fkey FOREIGN KEY (student_id)
        REFERENCES public.student (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT mark_fkey FOREIGN KEY (mark_id)
        REFERENCES public.mark (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT smpk PRIMARY KEY(student_id, mark_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.student_mark
    OWNER to postgres;