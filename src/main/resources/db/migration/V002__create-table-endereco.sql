CREATE TABLE endereco
(
    end_id              INT,
    end_tipo_logradouro VARCHAR(50),
    end_logradouro      VARCHAR(200),
    end_numero          INT,
    end_bairro          VARCHAR(100)
);

ALTER TABLE endereco
    ADD CONSTRAINT pk_endereco PRIMARY KEY (end_id);

CREATE INDEX idx_end_id ON endereco (end_id);