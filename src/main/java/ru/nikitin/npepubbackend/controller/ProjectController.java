package ru.nikitin.npepubbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikitin.npepubbackend.entity.Project;


import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProjectController {

    @GetMapping("/projects")
    public List<Project> getProjects() {
        return Arrays.asList(
                Project.builder()
                        .id(1L)
                        .title("npepub.ru")
                        .description("Личный сайт-портфолио. Стек: Spring Boot, React, Docker.")
                        .technologies(Arrays.asList("Java", "Spring Boot", "React", "Docker"))
                        .githubUrl("https://github.com/woody-rn/npepub-backend")
                        .liveUrl("https://npepub.ru")
                        .imageUrl("/images/npepub.png")
                        .build(),
                Project.builder()
                        .id(2L)
                        .title("Task Tracker")
                        .description("Система управления задачами с REST API.")
                        .technologies(Arrays.asList("Java", "Spring Boot", "PostgreSQL"))
                        .githubUrl("https://github.com/woody-rn/task-tracker")
                        .liveUrl(null)
                        .imageUrl("/images/tasktracker.png")
                        .build(),
                Project.builder()
                        .id(3L)
                        .title("URL Shortener")
                        .description("Сервис сокращения ссылок со статистикой переходов.")
                        .technologies(Arrays.asList("Java", "Spring Boot", "Redis"))
                        .githubUrl("https://github.com/woody-rn/url-shortener")
                        .liveUrl("https://short.npepub.ru")
                        .imageUrl("/images/shortener.png")
                        .build()
        );
    }
}
