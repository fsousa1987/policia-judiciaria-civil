package br.gov.francisco.policiajudiciariacivil.api.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EnderecoRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -8494310009863090414L;

    @EqualsAndHashCode.Include
    private String tipoLogradouro;

    @EqualsAndHashCode.Include
    private String logradouro;

    @EqualsAndHashCode.Include
    private Integer numero;

    @EqualsAndHashCode.Include
    private String bairro;

}
