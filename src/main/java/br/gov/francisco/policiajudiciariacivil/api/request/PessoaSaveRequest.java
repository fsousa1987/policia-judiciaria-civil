package br.gov.francisco.policiajudiciariacivil.api.request;

import br.gov.francisco.policiajudiciariacivil.api.dto.pessoa.PessoaSaveRequestDto;
import jakarta.validation.Valid;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaSaveRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 6715090912951120085L;

    @Valid
    private PessoaSaveRequestDto pessoa;

}
