package br.gov.francisco.policiajudiciariacivil.service;

import br.gov.francisco.policiajudiciariacivil.entity.Pessoa;
import br.gov.francisco.policiajudiciariacivil.mapper.PessoaMapper;
import br.gov.francisco.policiajudiciariacivil.repository.PessoaRepository;
import br.gov.francisco.policiajudiciariacivil.response.PessoaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper;

    @Transactional
    public List<PessoaResponse> findAll() {
        List<Pessoa> pessoas = pessoaRepository.findAll();

        return pessoaMapper.toPessoaResponse(pessoas);
    }

}
