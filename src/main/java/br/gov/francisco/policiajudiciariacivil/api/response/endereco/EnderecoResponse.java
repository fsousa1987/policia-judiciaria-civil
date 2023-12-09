package br.gov.francisco.policiajudiciariacivil.api.response.endereco;

import br.gov.francisco.policiajudiciariacivil.api.dto.EnderecoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -4901523878702190555L;

    private EnderecoResponseDto endereco;

}
