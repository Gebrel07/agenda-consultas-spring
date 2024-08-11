package com.gabrielsantos.agendaconsultas.paciente;

import java.util.List;
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

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public String listPaciente(Model model) {
        List<Paciente> pacientes = pacienteService.findAllPacientesOrderedByName();
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
        pacienteService.savePaciente(paciente);
        redirectAttributes.addFlashAttribute("successMessage", "Paciente criado com sucesso!");
        return "redirect:/pacientes";
    }

    @GetMapping("/{id}")
    public String getPaciente(@PathVariable Long id, Model model) {
        Optional<Paciente> paciente = pacienteService.findPacienteById(id);
        if (paciente.isEmpty()) {
            return "error/404";
        }
        model.addAttribute("paciente", paciente.get());
        return "paciente/editar";
    }

    @PostMapping("/{id}")
    public String updatePaciente(@ModelAttribute Paciente paciente,
            RedirectAttributes redirectAttributes) {
        pacienteService.savePaciente(paciente);
        redirectAttributes.addFlashAttribute("successMessage", "Paciente atualizado com sucesso!");
        return "redirect:/pacientes/" + paciente.getId();
    }

    @PostMapping("/{id}/deletar")
    public String deletePaciente(@ModelAttribute Paciente paciente,
            RedirectAttributes redirectAttributes) {
        pacienteService.deletePaciente(paciente.getId());
        redirectAttributes.addFlashAttribute("dangerMessage", "Paciente excluído com sucesso!");
        return "redirect:/pacientes";
    }
}
