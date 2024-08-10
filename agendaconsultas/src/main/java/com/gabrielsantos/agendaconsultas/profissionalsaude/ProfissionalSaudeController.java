package com.gabrielsantos.agendaconsultas.profissionalsaude;

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
@RequestMapping("/profissionais")
public class ProfissionalSaudeController {

    private final ProfissionalSaudeRepository profissionalSaudeRepository;

    public ProfissionalSaudeController(ProfissionalSaudeRepository profissionalSaudeRepository) {
        this.profissionalSaudeRepository = profissionalSaudeRepository;
    }

    @GetMapping
    public String listProfissinal(Model model) {
        Iterable<ProfissionalSaude> profissionais = profissionalSaudeRepository.findAll();
        model.addAttribute("profissionais", profissionais);
        return "profissionalsaude/listar";
    }

    @GetMapping("/criar")
    public String createProfissional() {
        return "profissionalsaude/criar";
    }

    @PostMapping("/criar")
    public String createProfissional(@ModelAttribute ProfissionalSaude profissionalSaude,
            RedirectAttributes redirectAttributes) {
        profissionalSaudeRepository.save(profissionalSaude);
        redirectAttributes.addFlashAttribute("successMessage", "Profissional criado com sucesso!");
        return "redirect:/profissionais";
    }

    @GetMapping("/{id}")
    public String getProfissional(@PathVariable Long id, Model model) {
        Optional<ProfissionalSaude> profissionalSaude = profissionalSaudeRepository.findById(id);
        if (profissionalSaude.isEmpty()) {
            return "error/404";
        }
        model.addAttribute("profissional", profissionalSaude.get());
        return "profissionalsaude/editar";
    }

    @PostMapping("/{id}")
    public String updateProfissional(@ModelAttribute ProfissionalSaude profissionalSaude,
            RedirectAttributes redirectAttributes) {
        profissionalSaudeRepository.save(profissionalSaude);
        redirectAttributes.addFlashAttribute("successMessage", "Profissional atualizado com sucesso!");
        return "redirect:/profissionais/" + profissionalSaude.getId();
    }

    @PostMapping("/{id}/deletar")
    public String deleteProfissional(@ModelAttribute ProfissionalSaude profissionalSaude,
            RedirectAttributes redirectAttributes) {
        profissionalSaudeRepository.delete(profissionalSaude);
        redirectAttributes.addFlashAttribute("dangerMessage", "Profissional excluído com sucesso!");
        return "redirect:/profissionais";
    }
}
