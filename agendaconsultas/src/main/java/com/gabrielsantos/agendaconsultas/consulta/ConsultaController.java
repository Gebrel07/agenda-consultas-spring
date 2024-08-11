package com.gabrielsantos.agendaconsultas.consulta;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gabrielsantos.agendaconsultas.paciente.Paciente;
import com.gabrielsantos.agendaconsultas.paciente.PacienteService;
import com.gabrielsantos.agendaconsultas.profissionalsaude.ProfissionalSaude;
import com.gabrielsantos.agendaconsultas.profissionalsaude.ProfissionalSaudeService;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;
    private final PacienteService pacienteService;
    private final ProfissionalSaudeService profissionalSaudeService;

    public ConsultaController(ConsultaService consultaService, PacienteService pacienteService,
            ProfissionalSaudeService profissionalSaudeService) {
        this.consultaService = consultaService;
        this.pacienteService = pacienteService;
        this.profissionalSaudeService = profissionalSaudeService;
    }

    @GetMapping
    public String listConsultas(Model model) {
        List<Consulta> consultas = consultaService.findAllConsultas();
        model.addAttribute("consultas", consultas);
        return "consulta/list";
    }

    @GetMapping("/{id}")
    public String getConsulta(@PathVariable Long id, Model model) {
        Optional<Consulta> consultaOpt = consultaService.findConsultaById(id);
        if (consultaOpt.isEmpty()) {
            return "redirect:/consultas";
        }
        String deleteUrl = String.format("/consultas/%d/delete", consultaOpt.get().getId());
        List<StatusConsultaDTO> listaStatus = Arrays.stream(StatusConsulta.values()).map(StatusConsultaDTO::new)
                .collect(Collectors.toList());
        List<Paciente> pacientes = pacienteService.findAllPacientesOrderedByName();
        List<ProfissionalSaude> profissionais = profissionalSaudeService.findAllProfissionaisOrderedByName();
        model.addAttribute("consulta", consultaOpt.get());
        model.addAttribute("formAction", "/consultas");
        model.addAttribute("deleteUrl", deleteUrl);
        model.addAttribute("listaStatus", listaStatus);
        model.addAttribute("pacientes", pacientes);
        model.addAttribute("profissionais", profissionais);
        return "consulta/detail";
    }

    @GetMapping("/new")
    public String createConsultaForm(Model model) {
        List<Paciente> pacientes = pacienteService.findAllPacientesOrderedByName();
        List<ProfissionalSaude> profissionais = profissionalSaudeService.findAllProfissionaisOrderedByName();
        List<StatusConsultaDTO> listaStatus = Arrays.stream(StatusConsulta.values()).map(StatusConsultaDTO::new)
                .collect(Collectors.toList());
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("formAction", "/consultas");
        model.addAttribute("listaStatus", listaStatus);
        model.addAttribute("pacientes", pacientes);
        model.addAttribute("profissionais", profissionais);
        return "consulta/create";
    }

    @PostMapping
    public String saveConsulta(@ModelAttribute Consulta consulta, RedirectAttributes redirectAttributes) {
        try {
            consulta.validateTimes();
            consultaService.saveConsulta(consulta);
            redirectAttributes.addFlashAttribute("successMessage", "Consulta salva com sucesso!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("dangerMessage", e.getMessage());
        }
        return "redirect:consultas/" + consulta.getId();
    }

    @PostMapping("/{id}/delete")
    public String deleteConsulta(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            consultaService.deleteConsulta(id);
            redirectAttributes.addFlashAttribute("successMessage", "Consulta excluída com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao excluir a consulta.");
        }
        return "redirect:/consultas";
    }
}
