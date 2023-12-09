package br.gov.francisco.policiajudiciariacivil.api.request;

import br.gov.francisco.policiajudiciariacivil.api.dto.EnderecoRequestDto;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

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
