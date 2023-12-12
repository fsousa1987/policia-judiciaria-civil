package br.gov.francisco.policiajudiciariacivil.domain.service;

import br.gov.francisco.policiajudiciariacivil.api.request.EnderecoSaveRequestList;
import br.gov.francisco.policiajudiciariacivil.api.request.PessoaRequest;
import br.gov.francisco.policiajudiciariacivil.api.request.PessoaUpdateRequest;
import br.gov.francisco.policiajudiciariacivil.api.response.endereco.EnderecoResponseList;
import br.gov.francisco.policiajudiciariacivil.api.response.pessoa.PessoaResponse;
import br.gov.francisco.policiajudiciariacivil.api.response.pessoa.PessoaResponseList;

public interface PessoaService {

    PessoaResponseList findAll();

    PessoaResponse save(PessoaRequest pessoaRequest);

    PessoaResponse findById(Integer id);

    PessoaResponse update(Integer id, PessoaUpdateRequest pessoaUpdateRequest);

    void delete(Integer id);

    EnderecoResponseList adicionarEnderecos(Integer id, EnderecoSaveRequestList enderecoSaveRequestList);
}
