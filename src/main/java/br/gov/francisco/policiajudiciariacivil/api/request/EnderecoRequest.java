package br.gov.francisco.policiajudiciariacivil.api.request;

import br.gov.francisco.policiajudiciariacivil.api.dto.endereco.EnderecoRequestDto;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 6715090912951120085L;

    private EnderecoRequestDto endereco;

}
