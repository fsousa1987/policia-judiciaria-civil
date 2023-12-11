package br.gov.francisco.policiajudiciariacivil.domain.service.impl;

import br.gov.francisco.policiajudiciariacivil.api.dto.endereco.EnderecoSaveRequestDto;
import br.gov.francisco.policiajudiciariacivil.api.dto.endereco.EnderecoUpdateRequestDto;
import br.gov.francisco.policiajudiciariacivil.api.dto.pessoa.PessoaRequestDto;
import br.gov.francisco.policiajudiciariacivil.api.dto.pessoa.PessoaResponseDto;
import br.gov.francisco.policiajudiciariacivil.api.exceptionhandler.exceptions.EnderecoNaoEncontradoException;
import br.gov.francisco.policiajudiciariacivil.api.exceptionhandler.exceptions.PessoaNaoEncontradaException;
import br.gov.francisco.policiajudiciariacivil.api.request.PessoaRequest;
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
import java.util.Objects;

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
    public PessoaResponse save(PessoaRequest pessoaRequest) {
        List<EnderecoSaveRequestDto> enderecosDto = pessoaRequest.getPessoa().getEnderecos().stream().toList();
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
        PessoaEntity pessoaEncontrada = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada para o id "
                        .concat(id.toString())));

        List<EnderecoEntity> enderecosEncontrados = pessoaEncontrada.getEnderecos();

        List<EnderecoUpdateRequestDto> enderecosUpdateRequest = pessoaUpdateRequest
                .getPessoa()
                .getEnderecos()
                .stream()
                .toList();

        verificarEnderecoExiste(enderecosUpdateRequest, enderecosEncontrados);

        List<EnderecoEntity> enderecos = enderecosUpdateRequest
                .stream()
                .map(enderecoRequest -> enderecoMapper.map(enderecoRequest, EnderecoEntity.class))
                .toList();

        enderecos.forEach(endereco ->
                enderecosEncontrados.stream()
                        .filter(enderecoEntity -> Objects.equals(endereco.getId(), enderecoEntity.getId()))
                        .findFirst()
                        .ifPresent(enderecoEntity -> enderecoMapper.map(endereco, enderecoEntity)));

        pessoaMapper.map(pessoaUpdateRequest, pessoaEncontrada);
        enderecos = enderecoRepository.saveAll(enderecosEncontrados);
        pessoaEncontrada.setEnderecos(enderecos);
        PessoaEntity save = pessoaRepository.save(pessoaEncontrada);

        return PessoaResponse.builder()
                .pessoa(pessoaMapper.map(save, PessoaResponseDto.class))
                .build();
    }

    private void verificarEnderecoExiste(List<EnderecoUpdateRequestDto> enderecosUpdateRequest,
                                         List<EnderecoEntity> enderecosEntity) {
        enderecosUpdateRequest
                .forEach(enderecoRequest -> enderecosEntity
                        .stream()
                        .filter(enderecoEntity -> Objects.equals(enderecoRequest.getId(), enderecoEntity.getId()))
                        .findFirst()
                        .orElseThrow(() -> new EnderecoNaoEncontradoException("Não foi possível atualizar os endereços. " +
                                "Verifique se os IDs estão corretos")));
    }

}
