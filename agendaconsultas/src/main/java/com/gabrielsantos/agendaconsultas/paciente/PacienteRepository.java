package com.gabrielsantos.agendaconsultas.paciente;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, Long> {
    List<Paciente> findAllByOrderByNomeAsc();
}
