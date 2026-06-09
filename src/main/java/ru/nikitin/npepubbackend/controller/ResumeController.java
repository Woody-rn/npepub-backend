package ru.nikitin.npepubbackend.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.nikitin.npepubbackend.entity.Resume;
import ru.nikitin.npepubbackend.service.ResumeService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ResumeController {

    private final ResumeService service;

    public ResumeController(ResumeService service) {
        this.service = service;
    }

    // Публичное скачивание активного резюме
    @GetMapping("/resume")
    public ResponseEntity<Resource> downloadActive() {
        Resume active = service.getActive();
        Resource resource = new FileSystemResource("uploads/" + active.getStoredName());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"nikitin.pdf\"")
                .body(resource);
    }

    // Админка: список всех резюме
    @GetMapping("/admin/resumes")
    public List<Resume> getAll() {
        return service.getAll();
    }

    // Админка: загрузить новое
    @PostMapping("/admin/resumes")
    public Resume upload(@RequestParam("file") MultipartFile file) throws IOException {
        return service.upload(file);
    }

    // Админка: сделать активным
    @PutMapping("/admin/resumes/{id}/activate")
    public Resume activate(@PathVariable Long id) {
        return service.activate(id);
    }

    // Админка: удалить
    @DeleteMapping("/admin/resumes/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}