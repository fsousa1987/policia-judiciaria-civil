package br.gov.francisco.policiajudiciariacivil.api.request;

import br.gov.francisco.policiajudiciariacivil.api.dto.endereco.EnderecoSaveRequestDto;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoSaveRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 6715090912951120085L;

    private EnderecoSaveRequestDto endereco;

}
