package br.gov.francisco.policiajudiciariacivil.domain.repository;

import br.gov.francisco.policiajudiciariacivil.domain.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer> {
}
