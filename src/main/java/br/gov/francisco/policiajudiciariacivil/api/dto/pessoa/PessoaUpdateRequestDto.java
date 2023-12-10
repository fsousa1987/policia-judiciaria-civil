package br.gov.francisco.policiajudiciariacivil.api.dto.pessoa;

import br.gov.francisco.policiajudiciariacivil.core.validation.EnumValidator;
import br.gov.francisco.policiajudiciariacivil.domain.enums.Sexo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaUpdateRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 8778038335868212554L;

    @NotBlank(message = "o campo nome é obrigatório")
    private String nome;

    @NotNull(message = "o campo data de nascimento é obrigatório")
    private LocalDate dataNascimento;

    @EnumValidator(enumClassName = Sexo.class)
    private Sexo sexo;

    @NotBlank(message = "o campo nome da mãe é obrigatório")
    private String nomeMae;

    @NotBlank(message = "o campo nome do pai é obrigatório")
    private String nomePai;

}
