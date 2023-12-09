package br.gov.francisco.policiajudiciariacivil.domain.repository;

import br.gov.francisco.policiajudiciariacivil.domain.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Integer> {
}
