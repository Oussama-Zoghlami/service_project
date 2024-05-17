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
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/list")
    public String showAllDepartments(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "departments/list";
    }

    @GetMapping("/add")
    public String showAddDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "departments/add";
    }

    @PostMapping("/add")
    public String addDepartment(@ModelAttribute("department") Department department) {
        departmentService.createDepartment(department);
        return "redirect:/departments/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditDepartmentForm(@PathVariable("id") Long id, Model model) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        department.ifPresent(value -> model.addAttribute("department", value));
        return "departments/edit";
    }

    @PostMapping("/edit")
    public String editDepartment(@ModelAttribute("department") Department department) {
        departmentService.updateDepartment(department);
        return "redirect:/departments/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments/list";
    }
}