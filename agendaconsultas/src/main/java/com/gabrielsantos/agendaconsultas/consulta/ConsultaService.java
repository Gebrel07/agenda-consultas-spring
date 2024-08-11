package com.gabrielsantos.agendaconsultas.consulta;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ConsultaService  {

    private final ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public Consulta saveConsulta(Consulta consulta) {
        consulta.validateTimes();
        return consultaRepository.save(consulta);
    }

    public List<Consulta> findAllConsultas() {
        return consultaRepository.findAllByOrderByDataDescHoraInicioAsc();
    }

    public Optional<Consulta> findConsultaById(Long id) {
        return consultaRepository.findById(id);
    }

    public List<Consulta> findConsultasByStatus(StatusConsulta status) {
        return consultaRepository.findByStatus(status);
    }

    public List<Consulta> findConsultasByPacienteId(Long pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId);
    }

    public List<Consulta> findConsultasByProfissionalId(Long profissionalId) {
        return consultaRepository.findByProfissionalId(profissionalId);
    }

    public List<Consulta> findConsultasByData(LocalDate data) {
        return consultaRepository.findByData(data);
    }

    public List<Consulta> findConsultasBetweenDates(LocalDate startDate, LocalDate endDate) {
        return consultaRepository.findByDataBetween(startDate, endDate);
    }

    public void deleteConsulta(Long id) {
        consultaRepository.deleteById(id);
    }
}
