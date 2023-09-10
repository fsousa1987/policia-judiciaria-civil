CREATE TABLE pessoa
(
    pes_id              INT,
    pes_nome            VARCHAR(200),
    pes_data_nascimento DATE,
    pes_sexo            VARCHAR(9),
    pes_mae             VARCHAR(200),
    pes_pai             VARCHAR(200)
);

ALTER TABLE pessoa
    ADD CONSTRAINT pk_pessoa PRIMARY kEY (pes_id);

CREATE SEQUENCE pessoa_seq
    START 1
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE INDEX idx_pes_id ON pessoa (pes_id);