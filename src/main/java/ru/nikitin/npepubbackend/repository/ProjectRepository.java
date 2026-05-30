package ru.nikitin.npepubbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nikitin.npepubbackend.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}