package br.gov.francisco.policiajudiciariacivil.api.dto.pessoa;

import br.gov.francisco.policiajudiciariacivil.domain.enums.Sexo;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PessoaResponseUpdateDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -1885806832630103845L;

    private Integer id;

    private String nome;

    private LocalDate dataNascimento;

    private Sexo sexo;

    private String nomeMae;

    private String nomePai;

}
