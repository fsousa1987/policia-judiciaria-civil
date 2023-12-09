package br.gov.francisco.policiajudiciariacivil.api.dto;

import br.gov.francisco.policiajudiciariacivil.api.response.endereco.EnderecoResponse;
import br.gov.francisco.policiajudiciariacivil.domain.enums.Sexo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 4990978000541130061L;

    private Integer id;

    private String nome;

    private LocalDate dataNascimento;

    private Sexo sexo;

    private String nomeMae;

    private String nomePai;

    private List<EnderecoResponse> enderecos;

}
