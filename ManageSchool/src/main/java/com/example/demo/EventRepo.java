package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Date;

@Repository
public interface EventRepo extends JpaRepository <Evenement,Integer> {
    Evenement findByStudentAndJourCoursAndHeureCours(Student student, Date jourCours, LocalTime heureCours);
}
