package br.gov.francisco.policiajudiciariacivil.api.response.endereco;

import br.gov.francisco.policiajudiciariacivil.api.dto.endereco.EnderecoResponseDto;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class EnderecoResponseList implements Serializable {

    @Serial
    private static final long serialVersionUID = 1871504900658662826L;

    private List<EnderecoResponseDto> enderecos;

}
