package br.gov.francisco.policiajudiciariacivil.domain.service.impl;

import br.gov.francisco.policiajudiciariacivil.api.dto.EnderecoRequestDto;
import br.gov.francisco.policiajudiciariacivil.api.dto.PessoaRequestDto;
import br.gov.francisco.policiajudiciariacivil.api.dto.PessoaResponseDto;
import br.gov.francisco.policiajudiciariacivil.api.request.PessoaRequest;
import br.gov.francisco.policiajudiciariacivil.api.response.pessoa.PessoaResponse;
import br.gov.francisco.policiajudiciariacivil.api.response.pessoa.PessoaResponseList;
import br.gov.francisco.policiajudiciariacivil.domain.entity.EnderecoEntity;
import br.gov.francisco.policiajudiciariacivil.domain.entity.PessoaEntity;
import br.gov.francisco.policiajudiciariacivil.domain.repository.EnderecoRepository;
import br.gov.francisco.policiajudiciariacivil.domain.repository.PessoaRepository;
import br.gov.francisco.policiajudiciariacivil.domain.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;
    private final ModelMapper pessoaMapper;
    private final ModelMapper enderecoMapper;
    private final EnderecoRepository enderecoRepository;

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

    @Override
    @Transactional
    public PessoaResponse save(PessoaRequest pessoaRequest) {
        List<EnderecoRequestDto> enderecosDto = pessoaRequest.getPessoa().getEnderecos().stream().toList();
        PessoaRequestDto pessoa = pessoaRequest.getPessoa();
        List<EnderecoEntity> enderecosEntity = enderecosDto
                .stream()
                .map(enderecoDto -> enderecoMapper.map(enderecoDto, EnderecoEntity.class))
                .toList();

        enderecosEntity = enderecoRepository.saveAll(enderecosEntity);

        PessoaEntity pessoaEntity = pessoaMapper.map(pessoa, PessoaEntity.class);
        pessoaEntity.setEnderecos(enderecosEntity);

        pessoaEntity = pessoaRepository.save(pessoaEntity);

        return pessoaMapper.map(pessoaEntity, PessoaResponse.class);
    }

}
