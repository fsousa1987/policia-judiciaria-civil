package br.gov.francisco.policiajudiciariacivil.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Data
public class EnderecoResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String tipoLogradouro;

    private String logradouro;

    private Integer numero;

    private String bairro;
}
