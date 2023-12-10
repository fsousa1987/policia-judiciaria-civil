package br.gov.francisco.policiajudiciariacivil.api.resource;

import br.gov.francisco.policiajudiciariacivil.api.request.PessoaSaveRequest;
import br.gov.francisco.policiajudiciariacivil.api.request.PessoaUpdateRequest;
import br.gov.francisco.policiajudiciariacivil.api.response.pessoa.PessoaResponse;
import br.gov.francisco.policiajudiciariacivil.api.response.pessoa.PessoaResponseList;
import br.gov.francisco.policiajudiciariacivil.domain.service.PessoaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/pessoas")
@RequiredArgsConstructor
public class PessoaResource {

    private final PessoaService pessoaService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaResponseList> findAll() {
        PessoaResponseList pessoas = pessoaService.findAll();
        return ResponseEntity.ok().body(pessoas);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaResponse> save(@RequestBody @Valid PessoaSaveRequest pessoaSaveRequest) {
        PessoaResponse pessoa = pessoaService.save(pessoaSaveRequest);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pessoa.getPessoa().getId())
                .toUri();
        return ResponseEntity.created(uri).body(pessoa);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaResponse> findById(@PathVariable Integer id) {
        PessoaResponse pessoa = pessoaService.findById(id);
        return ResponseEntity.ok().body(pessoa);
    }

    @PutMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaResponse> update(@PathVariable Integer id,
                                                 @RequestBody @Valid PessoaUpdateRequest pessoaUpdateRequest) {
        PessoaResponse pessoa = pessoaService.update(id, pessoaUpdateRequest);
        return ResponseEntity.ok().body(pessoa);
    }

}
