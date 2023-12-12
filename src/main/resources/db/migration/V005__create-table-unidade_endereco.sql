CREATE TABLE unidade_endereco
(
    unid_id INT,
    end_id  INT
);

-- Adicione a chave primária composta
ALTER TABLE unidade_endereco
    ADD CONSTRAINT pk_unidade_endereco PRIMARY KEY (unid_id, end_id);

-- Adicione as chaves estrangeiras
ALTER TABLE unidade_endereco
    ADD CONSTRAINT fk_unidade_id FOREIGN KEY (unid_id) REFERENCES unidade (unid_id);

ALTER TABLE unidade_endereco
    ADD CONSTRAINT fk_endereco_id FOREIGN KEY (end_id) REFERENCES endereco (end_id);

CREATE INDEX idx_uni_end ON unidade_endereco (unid_id, end_id);