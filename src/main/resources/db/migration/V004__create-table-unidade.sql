CREATE TABLE unidade
(
    unid_id    INT,
    unid_nome  VARCHAR(200),
    unid_sigla VARCHAR(20)
);

ALTER TABLE unidade
    ADD CONSTRAINT pk_unidade PRIMARY kEY (unid_id);

CREATE SEQUENCE unidade_seq
    START 1
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE INDEX idx_unid_id ON unidade (unid_id);