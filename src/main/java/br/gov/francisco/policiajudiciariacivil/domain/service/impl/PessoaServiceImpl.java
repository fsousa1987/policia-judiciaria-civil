package br.gov.francisco.policiajudiciariacivil.domain.service.impl;

import br.gov.francisco.policiajudiciariacivil.api.dto.endereco.EnderecoSaveRequestDto;
import br.gov.francisco.policiajudiciariacivil.api.dto.endereco.EnderecoUpdateRequestDto;
import br.gov.francisco.policiajudiciariacivil.api.dto.pessoa.PessoaRequestDto;
import br.gov.francisco.policiajudiciariacivil.api.dto.pessoa.PessoaResponseDto;
import br.gov.francisco.policiajudiciariacivil.api.exceptionhandler.exceptions.EnderecoNaoEncontradoException;
import br.gov.francisco.policiajudiciariacivil.api.exceptionhandler.exceptions.FalhaDeDuplicidadeAoAtualizarException;
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

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        PessoaEntity pessoaParaAtualizar = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada para o id "
                        .concat(id.toString())));

        List<EnderecoEntity> enderecosParaAtualizar = pessoaParaAtualizar.getEnderecos();

        List<EnderecoUpdateRequestDto> enderecosUpdateRequest = pessoaUpdateRequest
                .getPessoa()
                .getEnderecos()
                .stream()
                .toList();

        boolean saoIguais = verificarIgualdadeEntreEnderecosDaBaseEnderecosRequest(enderecosParaAtualizar,
                enderecosUpdateRequest);

        if (saoIguais) {
            pessoaMapper.map(pessoaUpdateRequest.getPessoa(), pessoaParaAtualizar);
            PessoaEntity pessoaAtualizada = pessoaRepository.saveAndFlush(pessoaParaAtualizar);
            return construirResponsePessoa(pessoaAtualizada);
        }

        verificarSeEnderecosRequestExistem(enderecosUpdateRequest, enderecosParaAtualizar);
        verificarSeAoAtualizarNaoOcorreraEnderecoDuplicado(enderecosUpdateRequest, enderecosParaAtualizar);

        enderecosUpdateRequest.forEach(enderecoUpdateRequest ->
                enderecosParaAtualizar.stream()
                        .filter(enderecoEncontradoNaBaseDeDados -> Objects
                                .equals(enderecoUpdateRequest.getId(), enderecoEncontradoNaBaseDeDados.getId()))
                        .findFirst()
                        .ifPresent(enderecoEntity -> enderecoMapper.map(enderecoUpdateRequest, enderecoEntity)));

        pessoaMapper.map(pessoaUpdateRequest, pessoaParaAtualizar);
        List<EnderecoEntity> enderecosAtualizados = enderecoRepository.saveAll(enderecosParaAtualizar);
        pessoaParaAtualizar.setEnderecos(enderecosAtualizados);
        PessoaEntity save = pessoaRepository.save(pessoaParaAtualizar);

        return construirResponsePessoa(save);
    }

    private PessoaResponse construirResponsePessoa(PessoaEntity save) {
        return PessoaResponse.builder()
                .pessoa(pessoaMapper.map(save, PessoaResponseDto.class))
                .build();
    }

    private boolean verificarIgualdadeEntreEnderecosDaBaseEnderecosRequest(List<EnderecoEntity> enderecosEncontrados, List<EnderecoUpdateRequestDto> enderecosUpdateRequest) {
        List<EnderecoEntity> enderecoRequestList = enderecosUpdateRequest.stream().map(enderecoRequest -> enderecoMapper.map(enderecoRequest, EnderecoEntity.class)).toList();

        return enderecosEncontrados
                .size() == enderecoRequestList.size() && IntStream.range(0, enderecosEncontrados.size())
                .allMatch(i -> enderecosEncontrados.get(i).equals(enderecoRequestList.get(i)));
    }

    private void verificarSeEnderecosRequestExistem(List<EnderecoUpdateRequestDto> enderecosUpdateRequest,
                                                    List<EnderecoEntity> enderecosEntity) {
        enderecosUpdateRequest
                .forEach(enderecoRequest -> enderecosEntity
                        .stream()
                        .filter(enderecoEntity -> Objects.equals(enderecoRequest.getId(), enderecoEntity.getId()))
                        .findFirst()
                        .orElseThrow(() -> new EnderecoNaoEncontradoException("Não foi possível atualizar os endereços. " +
                                "Verifique se os IDs estão corretos")));
    }

    private void verificarSeAoAtualizarNaoOcorreraEnderecoDuplicado(List<EnderecoUpdateRequestDto> enderecosUpdateRequest,
                                                                    List<EnderecoEntity> enderecosEntity) {

        Set<EnderecoEntity> listaComparativa = new HashSet<>(enderecosEntity);

        Set<EnderecoEntity> collect = enderecosUpdateRequest
                .stream()
                .map(enderecoRequest -> enderecoMapper.map(enderecoRequest, EnderecoEntity.class))
                .collect(Collectors.toSet());

        boolean saoIguais = listaComparativa.equals(collect);

        if (saoIguais) {
            return;
        }

        if (!listaComparativa.addAll(collect)) {
            throw new FalhaDeDuplicidadeAoAtualizarException("Falha ao atualizar o endereço. " +
                    "Já existe um mesmo endereço já cadastrado na base de dados.");
        }
    }

}
