package br.gov.francisco.policiajudiciariacivil.api.dto.endereco;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class EnderecoResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 2227255289899879163L;

    private Integer id;

    private String tipoLogradouro;

    private String logradouro;

    private Integer numero;

    private String bairro;

}
