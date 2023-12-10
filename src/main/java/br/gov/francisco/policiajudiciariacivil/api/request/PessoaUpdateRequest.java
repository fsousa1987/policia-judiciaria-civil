package br.gov.francisco.policiajudiciariacivil.api.request;

import br.gov.francisco.policiajudiciariacivil.api.dto.pessoa.PessoaUpdateRequestDto;
import jakarta.validation.Valid;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaUpdateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -7859238393756051553L;

    @Valid
    private PessoaUpdateRequestDto pessoa;

}
