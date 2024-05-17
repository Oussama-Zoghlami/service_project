package com.example.demo;

import jdk.jfr.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/Evenements")
public class EventController {

    private final ProfessorService professorService ;

    public EventController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/addEventForm")
    public String showAddEventForm(Model model) {
        model.addAttribute("event", new Evenement());
        return "addEventForm";
    }

    @PostMapping("/addEvent")
    public String addEvent(Evenement event) {
        professorService.addEvent(event);
        return "redirect:/events";
    }

    @GetMapping("/markPresenceForm")
    public String showMarkPresenceForm() {
        return "markPresenceForm";
    }

    @PostMapping("/markPresence")
    public String marquerPresence(String email, Date jourCours, LocalTime heureCours) {
        professorService.marquerPresence(email, jourCours, heureCours);

        return "redirect:/students";
    }

    @GetMapping("/markReussiteForm")
    public String showMarkReussiteForm() {
        return "markReussiteForm";
    }

    @PostMapping("/markReussite")
    public String marquerReussite(String email, Reussite reussite) {
        professorService.marquerReussite(email, reussite);
        return "redirect:/students";
    }

    @GetMapping("/calculateAbsentisme")
    public String calculateAbsentisme() {
        professorService.calculerPourcentageAbsentisme();
        return "calculateAbsentisme";
    }

    @GetMapping("/calculateReussite")
    public String calculateReussite(String email,Reussite reussite) {
        professorService.marquerReussite(email, reussite);
        return "calculateReussite";
    }
}



