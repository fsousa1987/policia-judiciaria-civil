package br.gov.francisco.policiajudiciariacivil.api.dto.endereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EnderecoSaveRequestDto implements Serializable {

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

    @NotNull(message = "o uf não pode ser nulo")
    @Valid
    private UnidadeRequestDto uf;

}
