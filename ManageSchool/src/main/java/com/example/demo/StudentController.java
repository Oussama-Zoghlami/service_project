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
/**
 *
 * @author nada haninou
 */
@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Affiche la liste de tous les étudiants
    @GetMapping("/list")
    public String showAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students/list";
    }

    // Affiche le formulaire pour ajouter un nouvel étudiant
    @GetMapping("/add")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/add";
    }

    // Traite la soumission du formulaire d'ajout d'un nouvel étudiant
    @PostMapping("/add")
    public String addStudent(@ModelAttribute("student") Student student) {
        studentService.createStudent(student);
        return "redirect:/students/list";
    }

    // Affiche le formulaire pour mettre à jour les informations d'un étudiant
    @GetMapping("/edit/{id}")
    public String showEditStudentForm(@PathVariable("id") Long id, Model model) {
        Optional<Student> student = studentService.getStudentById(id);
        student.ifPresent(value -> model.addAttribute("student", value));
        return "students/edit";
    }

    // Traite la soumission du formulaire de mise à jour des informations d'un étudiant
    @PostMapping("/edit")
    public String editStudent(@ModelAttribute("student") Student student) {
        studentService.updateStudent(student);
        return "redirect:/students/list";
    }

    // Supprime un étudiant
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students/list";
    }
}
