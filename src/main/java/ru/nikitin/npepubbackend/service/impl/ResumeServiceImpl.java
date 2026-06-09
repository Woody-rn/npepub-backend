package ru.nikitin.npepubbackend.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.nikitin.npepubbackend.entity.Resume;
import ru.nikitin.npepubbackend.repository.ResumeRepository;
import ru.nikitin.npepubbackend.service.ResumeService;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository repository;
    private final Path uploadDir;

    public ResumeServiceImpl(ResumeRepository repository,
                             @Value("${app.resume.upload-dir:uploads}") String uploadDir) {
        this.repository = repository;
        this.uploadDir = Paths.get(uploadDir);
        try {
            Files.createDirectories(this.uploadDir);
        } catch (IOException e) {
            throw new RuntimeException("Cannot create upload directory", e);
        }
    }

    @Override
    public List<Resume> getAll() {
        return repository.findAll();
    }

    @Override
    public Resume upload(MultipartFile file) throws IOException {
        String originalName = file.getOriginalFilename();
        String storedName = UUID.randomUUID() + ".pdf";

        Files.copy(file.getInputStream(), uploadDir.resolve(storedName), StandardCopyOption.REPLACE_EXISTING);

        Resume resume = Resume.builder()
                .originalName(originalName != null ? originalName : "resume.pdf")
                .storedName(storedName)
                .size(file.getSize())
                .uploadedAt(LocalDateTime.now())
                .active(false)
                .build();

        return repository.save(resume);
    }

    @Override
    public Resume activate(Long id) {
        repository.findAll().forEach(r -> {
            r.setActive(false);
            repository.save(r);
        });

        Resume resume = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found"));
        resume.setActive(true);
        return repository.save(resume);
    }

    @Override
    public void delete(Long id) {
        Resume resume = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found"));
        try {
            Files.deleteIfExists(uploadDir.resolve(resume.getStoredName()));
        } catch (IOException e) {
            // файл уже удалён
        }
        repository.delete(resume);
    }

    @Override
    public Resume getActive() {
        return repository.findByActiveTrue()
                .orElseThrow(() -> new RuntimeException("No active resume"));
    }
}