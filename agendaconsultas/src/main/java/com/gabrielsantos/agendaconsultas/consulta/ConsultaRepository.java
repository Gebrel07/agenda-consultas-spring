package com.gabrielsantos.agendaconsultas.consulta;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByStatus(StatusConsulta status);

    List<Consulta> findByPacienteId(Long pacienteId);

    List<Consulta> findByProfissionalId(Long profissionalId);

    List<Consulta> findByData(LocalDate data);

    List<Consulta> findByDataBetween(LocalDate startDate, LocalDate endDate);

    List<Consulta> findAllByOrderByDataDescHoraInicioAsc();
}
