package ru.nikitin.npepubbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nikitin.npepubbackend.entity.Resume;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Optional<Resume> findByActiveTrue();
}