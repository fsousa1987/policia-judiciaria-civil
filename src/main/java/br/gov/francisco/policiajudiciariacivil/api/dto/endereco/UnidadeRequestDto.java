package br.gov.francisco.policiajudiciariacivil.api.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UnidadeRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6834013300318695922L;

    @NotBlank(message = "o campo cidade é obrigatório")
    private String cidade;

    @NotBlank(message = "o campo estado é obrigatório")
    private String estado;

}
