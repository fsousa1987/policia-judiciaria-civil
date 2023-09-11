package br.gov.francisco.policiajudiciariacivil.mapper;

import br.gov.francisco.policiajudiciariacivil.entity.Endereco;
import br.gov.francisco.policiajudiciariacivil.response.EnderecoResponse;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    List<EnderecoResponse> toEnderecoResponse(Set<Endereco> enderecos);

}
