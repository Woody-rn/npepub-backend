package ru.nikitin.npepubbackend.service;

import org.springframework.web.multipart.MultipartFile;
import ru.nikitin.npepubbackend.entity.Resume;

import java.io.IOException;
import java.util.List;

public interface ResumeService {
    List<Resume> getAll();
    Resume upload(MultipartFile file) throws IOException;
    Resume activate(Long id);
    void delete(Long id);
    Resume getActive();
}