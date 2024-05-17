/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final EventRepo eventRepo ;
    private final StudentRepository studentRepository;


    @Autowired
    public ProfessorService(ProfessorRepository professorRepository, EventRepo eventRepo, StudentRepository studentRepository) {
        this.professorRepository = professorRepository;
        this.eventRepo = eventRepo;
        this.studentRepository = studentRepository;
    }

    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    public Optional<Professor> getProfessorById(Long id) {
        return professorRepository.findById(id);
    }

    public void createProfessor(Professor professor) {
        professorRepository.save(professor);
    }

    public void updateProfessor(Professor professor) {
        professorRepository.save(professor);
    }

    public void deleteProfessor(Long id) {
        professorRepository.deleteById(id);
    }


    public Evenement addEvent(Evenement event) {
        return eventRepo.save(event);
    }


    public List<Evenement> getAllEvenement() {
        return eventRepo.findAll();
    }



    public void marquerPresence(String email, Date jourCours, LocalTime heureCours) {

        Student student = studentRepository.findByEmail(email);

        if (student != null) {

            Evenement evenement = eventRepo.findByStudentAndJourCoursAndHeureCours(student, jourCours, heureCours);


            if (evenement != null) {
                evenement.setAbscentisme(Abscentisme.PRESENT);
                eventRepo.save(evenement);
            }
        }
    }


    public void marquerReussite(String email,Reussite reussite) {
        Student student = studentRepository.findByEmail(email);

        student.setReussite(reussite);
        studentRepository.save(student);
    }


    public double calculerPourcentageAbsentisme() {

        List<Evenement> evenements = eventRepo.findAll();

        long nombreAbsent = evenements.stream()
                .filter(e -> e.getAbscentisme() == Abscentisme.ABSCENT)
                .count();


        double pourcentageAbsentisme = (double) nombreAbsent / evenements.size() * 100;

        return pourcentageAbsentisme;
    }


    public double calculerPourcentageReussite() {

        List<Student> students = studentRepository.findAll();


        long nombreReussite = students.stream()
                .filter(e -> e.getReussite() == Reussite.REUSSI)
                .count();


        double pourcentageReussite = (double) nombreReussite / students.size() * 100;

        return pourcentageReussite;
    }

}