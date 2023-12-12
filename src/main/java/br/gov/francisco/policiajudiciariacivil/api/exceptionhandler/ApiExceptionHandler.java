package br.gov.francisco.policiajudiciariacivil.api.exceptionhandler;

import br.gov.francisco.policiajudiciariacivil.api.exceptionhandler.enums.ProblemType;
import br.gov.francisco.policiajudiciariacivil.api.exceptionhandler.exceptions.EnderecoNaoEncontradoException;
import br.gov.francisco.policiajudiciariacivil.api.exceptionhandler.exceptions.FalhaDeDuplicidadeAoAtualizarOuSalvarNovoEnderecoException;
import br.gov.francisco.policiajudiciariacivil.api.exceptionhandler.exceptions.PessoaNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {

        return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            @NonNull HttpMessageNotReadableException ex, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {

        var problemType = ProblemType.DADOS_INVALIDOS;
        var detail = "O campo data de nascimento está inválido";
        var problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(PessoaNaoEncontradaException.class)
    public ResponseEntity<?> handlePessoaNaoEncontradaException(PessoaNaoEncontradaException ex, WebRequest request) {

        var status = HttpStatus.NOT_FOUND;
        var problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        var detail = ex.getMessage();

        var problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EnderecoNaoEncontradoException.class)
    public ResponseEntity<?> handleEnderecoNaoEncontradoException(EnderecoNaoEncontradoException ex, WebRequest request) {

        var status = HttpStatus.NOT_FOUND;
        var problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        var detail = ex.getMessage();

        var problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(FalhaDeDuplicidadeAoAtualizarOuSalvarNovoEnderecoException.class)
    public ResponseEntity<?> handleFalhaDeDuplicidadeAoAtualizarException(FalhaDeDuplicidadeAoAtualizarOuSalvarNovoEnderecoException ex,
                                                                          WebRequest request) {

        var status = HttpStatus.CONFLICT;
        var problemType = ProblemType.DADOS_INVALIDOS;
        var detail = ex.getMessage();

        var problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatusCode status, ProblemType problemType, String detail) {
        return Problem.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .title(problemType.getTitle())
                .detail(detail);
    }

    private ResponseEntity<Object> handleValidationInternal(
            Exception ex, BindingResult bindingResult, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var problemType = ProblemType.DADOS_INVALIDOS;
        var detail = "Um ou mais campos estão inválidos. Preencha corretamente e tente outra vez";

        var problemObjects = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    var message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                    var name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }

                    return Problem.Object.builder()
                            .name(name)
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());

        var problem = createProblemBuilder(status, problemType, detail)
                .objects(problemObjects)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

}
