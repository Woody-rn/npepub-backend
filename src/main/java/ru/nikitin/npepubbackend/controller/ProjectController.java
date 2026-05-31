package ru.nikitin.npepubbackend.controller;

import org.springframework.web.bind.annotation.*;
import ru.nikitin.npepubbackend.entity.Project;
import ru.nikitin.npepubbackend.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping("/projects")
    public List<Project> getProjects() {
        return service.getAll();
    }
}