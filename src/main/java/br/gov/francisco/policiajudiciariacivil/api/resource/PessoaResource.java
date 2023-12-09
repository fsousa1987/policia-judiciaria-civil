package br.gov.francisco.policiajudiciariacivil.api.resource;

import br.gov.francisco.policiajudiciariacivil.api.request.PessoaRequest;
import br.gov.francisco.policiajudiciariacivil.api.response.pessoa.PessoaResponse;
import br.gov.francisco.policiajudiciariacivil.api.response.pessoa.PessoaResponseList;
import br.gov.francisco.policiajudiciariacivil.domain.service.PessoaService;
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
    public ResponseEntity<PessoaResponse> save(@RequestBody PessoaRequest pessoaRequest) {
        PessoaResponse pessoa = pessoaService.save(pessoaRequest);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pessoa.getPessoa().getId())
                .toUri();
        return ResponseEntity.created(uri).body(pessoa);
    }

}
