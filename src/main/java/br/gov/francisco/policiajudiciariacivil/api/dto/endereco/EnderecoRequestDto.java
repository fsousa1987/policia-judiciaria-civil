package br.gov.francisco.policiajudiciariacivil.api.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "o campo tipo de logradouro é obrigatório")
    @EqualsAndHashCode.Include
    private String tipoLogradouro;

    @NotBlank(message = "o campo logradouro é obrigatório")
    @EqualsAndHashCode.Include
    private String logradouro;

    @NotNull(message = "o campo número é obrigatório")
    @EqualsAndHashCode.Include
    private Integer numero;

    @NotBlank(message = "o campo bairro é obrigatório")
    @EqualsAndHashCode.Include
    private String bairro;

}