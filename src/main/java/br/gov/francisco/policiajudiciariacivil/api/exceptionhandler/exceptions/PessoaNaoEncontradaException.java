package br.gov.francisco.policiajudiciariacivil.api.exceptionhandler.exceptions;

public class PessoaNaoEncontradaException extends RuntimeException {

    public PessoaNaoEncontradaException(String message) {
        super(message);
    }

}
