package br.gov.francisco.policiajudiciariacivil.api.dto.pessoa;

import br.gov.francisco.policiajudiciariacivil.api.dto.endereco.EnderecoSaveRequestDto;
import br.gov.francisco.policiajudiciariacivil.core.validation.EnumValidator;
import br.gov.francisco.policiajudiciariacivil.domain.enums.Sexo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
public class PessoaRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 4990978000541130061L;

    @NotBlank(message = "o campo nome é obrigatório")
    private String nome;

    @NotNull(message = "o campo data de nascimento é obrigatório")
    private LocalDate dataNascimento;

    @EnumValidator(enumClassName = Sexo.class)
    private String sexo;

    @NotBlank(message = "o campo nome da mãe é obrigatório")
    private String nomeMae;

    @NotBlank(message = "o campo nome do pai é obrigatório")
    private String nomePai;

    @NotNull(message = "a lista de endereços não pode ser nula")
    @NotEmpty(message = "a lista de endereços não pode estar vazia")
    @Valid
    private Set<EnderecoSaveRequestDto> enderecos;

}
