package br.gov.francisco.policiajudiciariacivil.domain.service.impl;

import br.gov.francisco.policiajudiciariacivil.api.dto.endereco.EnderecoSaveRequestDto;
import br.gov.francisco.policiajudiciariacivil.api.dto.pessoa.PessoaResponseDto;
import br.gov.francisco.policiajudiciariacivil.api.dto.pessoa.PessoaSaveRequestDto;
import br.gov.francisco.policiajudiciariacivil.api.exceptionhandler.exceptions.PessoaNaoEncontradaException;
import br.gov.francisco.policiajudiciariacivil.api.request.PessoaSaveRequest;
import br.gov.francisco.policiajudiciariacivil.api.request.PessoaUpdateRequest;
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
    private final EnderecoRepository enderecoRepository;
    private final ModelMapper pessoaMapper;
    private final ModelMapper enderecoMapper;

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
    public PessoaResponse save(PessoaSaveRequest pessoaSaveRequest) {
        List<EnderecoSaveRequestDto> enderecosDto = pessoaSaveRequest.getPessoa().getEnderecos().stream().toList();
        PessoaSaveRequestDto pessoa = pessoaSaveRequest.getPessoa();
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

    @Override
    @Transactional(readOnly = true)
    public PessoaResponse findById(Integer id) {
        return PessoaResponse
                .builder()
                .pessoa(pessoaRepository
                        .findById(id)
                        .map(pessoaEntity -> pessoaMapper
                                .map(pessoaEntity, PessoaResponseDto.class)
                        )
                        .orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada para o id "
                                .concat(id.toString()))
                        )
                )
                .build();
    }

    @Override
    @Transactional
    public PessoaResponse update(Integer id, PessoaUpdateRequest pessoaUpdateRequest) {
        return null;
    }

}
