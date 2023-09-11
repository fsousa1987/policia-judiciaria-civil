package br.gov.francisco.policiajudiciariacivil.mapper;

import br.gov.francisco.policiajudiciariacivil.entity.Pessoa;
import br.gov.francisco.policiajudiciariacivil.response.PessoaResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

    List<PessoaResponse> toPessoaResponse(List<Pessoa> pessoas);

}
