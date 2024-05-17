/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/list")
    public String showAllProfessors(Model model) {
        List<Professor> professors = professorService.getAllProfessors();
        model.addAttribute("professors", professors);
        return "professors/list";
    }

    @GetMapping("/add")
    public String showAddProfessorForm(Model model) {
        model.addAttribute("professor", new Professor());
        return "professors/add";
    }

    @PostMapping("/add")
    public String addProfessor(@ModelAttribute("professor") Professor professor) {
        professorService.createProfessor(professor);
        return "redirect:/professors/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditProfessorForm(@PathVariable("id") Long id, Model model) {
        Optional<Professor> professor = professorService.getProfessorById(id);
        professor.ifPresent(value -> model.addAttribute("professor", value));
        return "professors/edit";
    }

    @PostMapping("/edit")
    public String editProfessor(@ModelAttribute("professor") Professor professor) {
        professorService.updateProfessor(professor);
        return "redirect:/professors/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProfessor(@PathVariable("id") Long id) {
        professorService.deleteProfessor(id);
        return "redirect:/professors/list";
    }
}