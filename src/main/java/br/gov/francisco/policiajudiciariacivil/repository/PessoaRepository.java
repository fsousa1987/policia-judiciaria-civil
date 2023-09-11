package br.gov.francisco.policiajudiciariacivil.repository;

import br.gov.francisco.policiajudiciariacivil.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
