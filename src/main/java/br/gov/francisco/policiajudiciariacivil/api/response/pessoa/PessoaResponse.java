package br.gov.francisco.policiajudiciariacivil.api.response.pessoa;

import br.gov.francisco.policiajudiciariacivil.api.dto.PessoaResponseDto;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 2594233831974576305L;

    private PessoaResponseDto pessoa;

}
