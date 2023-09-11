package br.gov.francisco.policiajudiciariacivil.response;

import br.gov.francisco.policiajudiciariacivil.entity.Endereco;
import br.gov.francisco.policiajudiciariacivil.enums.Sexo;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Builder
@Data
public class PessoaResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private Sexo sexo;
    private String nomeMae;
    private String nomePai;
    private Set<Endereco> enderecos;

}
