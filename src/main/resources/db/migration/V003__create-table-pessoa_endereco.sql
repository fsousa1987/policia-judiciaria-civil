CREATE TABLE pessoa_endereco
(
    pes_id INT,
    end_id INT
);

-- Adicione a chave primária composta
ALTER TABLE pessoa_endereco
    ADD CONSTRAINT pk_pessoa_endereco PRIMARY KEY (pes_id, end_id);

-- Adicione as chaves estrangeiras
ALTER TABLE pessoa_endereco
    ADD CONSTRAINT fk_pessoa_id FOREIGN KEY (pes_id) REFERENCES pessoa (pes_id);

ALTER TABLE pessoa_endereco
    ADD CONSTRAINT fk_endereco_id FOREIGN KEY (end_id) REFERENCES endereco (end_id);

CREATE INDEX idx_pes_end ON pessoa_endereco (pes_id, end_id);