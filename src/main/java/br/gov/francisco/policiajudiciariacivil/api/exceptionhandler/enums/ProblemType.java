package br.gov.francisco.policiajudiciariacivil.api.exceptionhandler.enums;

import lombok.Getter;

@Getter
public enum ProblemType {

    RECURSO_NAO_ENCONTRADO("Recurso não encontrado"),
    DADOS_INVALIDOS("Dados inválidos"),
    EXCLUSAO_INVALIDA("Exclusão inválida");

    private final String title;

    ProblemType(String title) {
        this.title = title;
    }

}
