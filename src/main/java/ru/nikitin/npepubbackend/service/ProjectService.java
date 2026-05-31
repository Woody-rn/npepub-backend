package ru.nikitin.npepubbackend.service;

import ru.nikitin.npepubbackend.entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<Project> getAll();
    Optional<Project> getById(Long id);
    Project create(Project project);
    Optional<Project> update(Long id, Project project);
    boolean delete(Long id);
}