package ru.nikitin.npepubbackend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.nikitin.npepubbackend.config.SecurityConfig;
import ru.nikitin.npepubbackend.entity.Project;
import ru.nikitin.npepubbackend.security.JwtAuthFilter;
import ru.nikitin.npepubbackend.security.JwtProvider;
import ru.nikitin.npepubbackend.service.ProjectService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
@Import({SecurityConfig.class, JwtAuthFilter.class, JwtProvider.class})
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProjectService projectService;

    @Test
    void shouldReturnProjectsList() throws Exception {
        var project = Project.builder()
                .id(1L)
                .title("Test")
                .description("Desc")
                .technologies(List.of("Java"))
                .build();

        when(projectService.getAll()).thenReturn(List.of(project));

        mockMvc.perform(get("/api/v1/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test"));
    }
}