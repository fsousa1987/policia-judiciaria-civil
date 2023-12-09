package br.gov.francisco.policiajudiciariacivil.api.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 2227255289899879163L;

    private Integer id;

    private String tipoLogradouro;

    private String logradouro;

    private Integer numero;

    private String bairro;

}
