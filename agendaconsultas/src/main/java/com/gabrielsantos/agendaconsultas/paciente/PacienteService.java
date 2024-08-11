package com.gabrielsantos.agendaconsultas.paciente;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> findAllPacientesOrderedByName() {
        return pacienteRepository.findAllByOrderByNomeAsc();
    }

    public Optional<Paciente> findPacienteById(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente savePaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public void deletePaciente(Long id) {
        pacienteRepository.deleteById(id);
    }
}
