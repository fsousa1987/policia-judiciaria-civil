package br.gov.francisco.policiajudiciariacivil.api.request;

import br.gov.francisco.policiajudiciariacivil.api.dto.endereco.EnderecoSaveRequestDto;
import jakarta.validation.Valid;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoSaveRequestList implements Serializable {

    @Serial
    private static final long serialVersionUID = 6715090912951120085L;

    @Valid
    private Set<EnderecoSaveRequestDto> enderecos;

}
