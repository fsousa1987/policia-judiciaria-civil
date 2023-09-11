package br.gov.francisco.policiajudiciariacivil.controller;

import br.gov.francisco.policiajudiciariacivil.response.PessoaResponse;
import br.gov.francisco.policiajudiciariacivil.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PessoaResponse>> findAll() {
        List<PessoaResponse> pessoas = pessoaService.findAll();

        return ResponseEntity.ok().body(pessoas);
    }

}
