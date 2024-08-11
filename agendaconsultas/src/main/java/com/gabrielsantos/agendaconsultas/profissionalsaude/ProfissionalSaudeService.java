package com.gabrielsantos.agendaconsultas.profissionalsaude;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ProfissionalSaudeService {

    private final ProfissionalSaudeRepository profissionalSaudeRepository;

    public ProfissionalSaudeService(ProfissionalSaudeRepository profissionalSaudeRepository) {
        this.profissionalSaudeRepository = profissionalSaudeRepository;
    }

    public List<ProfissionalSaude> findAllProfissionaisOrderedByName() {
        return profissionalSaudeRepository.findAllByOrderByNomeAsc();
    }

    public Optional<ProfissionalSaude> findProfissionalById(Long id) {
        return profissionalSaudeRepository.findById(id);
    }

    public ProfissionalSaude saveProfissional(ProfissionalSaude profissionalSaude) {
        return profissionalSaudeRepository.save(profissionalSaude);
    }

    public void deleteProfissional(Long id) {
        profissionalSaudeRepository.deleteById(id);
    }
}
