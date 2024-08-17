package com.gabrielsantos.agendaconsultas.profissionalsaude;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalSaudeRepository extends CrudRepository<ProfissionalSaude, Long> {
    List<ProfissionalSaude> findAllByOrderByNomeAsc();
}
