package br.gov.francisco.policiajudiciariacivil.api.response.pessoa;

import br.gov.francisco.policiajudiciariacivil.api.dto.pessoa.PessoaResponseDto;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaResponseList implements Serializable {


    @Serial
    private static final long serialVersionUID = -6095606706196887844L;

    private List<PessoaResponseDto> pessoas;

}
