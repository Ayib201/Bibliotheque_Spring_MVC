package com.groupeisi.bibliotheque.controllers;

import com.groupeisi.bibliotheque.dto.auteur.AuteurCreateDto;
import com.groupeisi.bibliotheque.dto.auteur.AuteurDetailDto;
import com.groupeisi.bibliotheque.services.auteur.IAuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/auteurs")
public class AuteurController  {
    private static final String REDIRECT_AUTEURS = "redirect:/auteurs";
    private final IAuteurService auteurService;

    @Autowired
    public AuteurController(IAuteurService auteurService) {
        this.auteurService = auteurService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("auteurs", auteurService.getAll());
        return "auteur/list";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Long id, Model model) {
        Optional<AuteurDetailDto> auteur = auteurService.get(id);
        if (auteur.isEmpty()) {
            return REDIRECT_AUTEURS;
        }
        model.addAttribute("auteur", auteur.get());
        return "auteur/detail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("auteurCreateDto", new AuteurCreateDto());
        return "auteur/create";
    }

    @PostMapping
    public String save(@ModelAttribute AuteurCreateDto createDto) {
        auteurService.save(createDto);
        return REDIRECT_AUTEURS;
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Optional<AuteurDetailDto> auteur = auteurService.get(id);
        if (auteur.isEmpty()) {
            return REDIRECT_AUTEURS;
        }
        model.addAttribute("auteur", auteur.get());
        return "auteur/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute AuteurCreateDto createDto) {
        auteurService.update(createDto);
        return REDIRECT_AUTEURS;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        auteurService.delete(id);
        return REDIRECT_AUTEURS;
    }
}
