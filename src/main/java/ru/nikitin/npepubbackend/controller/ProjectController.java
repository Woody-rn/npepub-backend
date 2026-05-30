package ru.nikitin.npepubbackend.controller;

import org.springframework.web.bind.annotation.*;
import ru.nikitin.npepubbackend.entity.Project;
import ru.nikitin.npepubbackend.repository.ProjectRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProjectController {

    private final ProjectRepository repository;

    public ProjectController(ProjectRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/projects")
    public List<Project> getProjects() {
        return repository.findAll();
    }
}