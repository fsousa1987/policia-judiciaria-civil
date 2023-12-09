package br.gov.francisco.policiajudiciariacivil.domain.service.impl;

import br.gov.francisco.policiajudiciariacivil.api.dto.PessoaResponseDto;
import br.gov.francisco.policiajudiciariacivil.api.response.pessoa.PessoaResponseList;
import br.gov.francisco.policiajudiciariacivil.domain.repository.PessoaRepository;
import br.gov.francisco.policiajudiciariacivil.domain.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;
    private final ModelMapper pessoaMapper;

    @Override
    @Transactional(readOnly = true)
    public PessoaResponseList findAll() {
        return PessoaResponseList
                .builder()
                .pessoas(
                        pessoaRepository
                                .findAll()
                                .stream()
                                .map(pessoaEntity -> pessoaMapper.map(pessoaEntity, PessoaResponseDto.class))
                                .toList()
                )
                .build();
    }

}
