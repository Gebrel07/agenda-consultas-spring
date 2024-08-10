package com.gabrielsantos.agendaconsultas.paciente;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @GetMapping
    public String listPaciente(Model model) {
        Iterable<Paciente> pacientes = pacienteRepository.findAllByOrderByNomeAsc();
        model.addAttribute("pacientes", pacientes);
        return "paciente/listar";
    }

    @GetMapping("/criar")
    public String createPaciente() {
        return "paciente/criar";
    }

    @PostMapping("/criar")
    public String createPaciente(@ModelAttribute Paciente paciente,
            RedirectAttributes redirectAttributes) {
        pacienteRepository.save(paciente);
        redirectAttributes.addFlashAttribute("successMessage", "Paciente criado com sucesso!");
        return "redirect:/pacientes";
    }

    @GetMapping("/{id}")
    public String getPaciente(@PathVariable Long id, Model model) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isEmpty()) {
            return "error/404";
        }
        model.addAttribute("paciente", paciente.get());
        return "paciente/editar";
    }

    @PostMapping("/{id}")
    public String updatePaciente(@ModelAttribute Paciente paciente,
            RedirectAttributes redirectAttributes) {
        pacienteRepository.save(paciente);
        redirectAttributes.addFlashAttribute("successMessage", "Paciente atualizado com sucesso!");
        return "redirect:/pacientes/" + paciente.getId();
    }

    @PostMapping("/{id}/deletar")
    public String deletePaciente(@ModelAttribute Paciente paciente,
            RedirectAttributes redirectAttributes) {
        pacienteRepository.delete(paciente);
        redirectAttributes.addFlashAttribute("dangerMessage", "Paciente excluído com sucesso!");
        return "redirect:/pacientes";
    }
}
