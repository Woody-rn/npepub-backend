package ru.nikitin.npepubbackend.service.impl;

import org.springframework.stereotype.Service;
import ru.nikitin.npepubbackend.entity.Project;
import ru.nikitin.npepubbackend.repository.ProjectRepository;
import ru.nikitin.npepubbackend.service.ProjectService;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;

    public ProjectServiceImpl(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Project> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Project> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Project create(Project project) {
        return repository.save(project);
    }

    @Override
    public Optional<Project> update(Long id, Project project) {
        return repository.findById(id)
                .map(existing -> {
                    project.setId(id);
                    return repository.save(project);
                });
    }

    @Override
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}