/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author nada haninou
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Opération de création (Create)
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // Opération de lecture (Read) - Tous les étudiants
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Opération de lecture (Read) - Un étudiant par ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Opération de mise à jour (Update)
    public Student updateStudent(Student updatedStudent) {
        return studentRepository.save(updatedStudent);
    }

    // Opération de suppression (Delete)
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
