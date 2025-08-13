package com.apifaculdade.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apifaculdade.dto.AlunoDTO;
import com.apifaculdade.dto.CursoDTO;
import com.apifaculdade.model.Curso;
import com.apifaculdade.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/cursos")
public class CursoController {
    private static final Logger log = LoggerFactory.getLogger(CursoController.class);
    private final CursoRepository cursoRepository;
    public CursoController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public Page<CursoDTO> listarCursos(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        log.info("Listando cursos com paginação: {}", pageable);
        return cursoRepository.findAll(pageable).map(this::toDTO);
    }

    @PostMapping
    public ResponseEntity<CursoDTO> criarCurso(@Valid @RequestBody CursoDTO cursoDTO) {
        log.info("Criando curso: {}", cursoDTO.getNome());
        Curso curso = new Curso();
        curso.setNome(cursoDTO.getNome());
        Curso salvo = cursoRepository.save(curso);
        log.info("Curso criado com id: {}", salvo.getId());
        return ResponseEntity.ok(toDTO(salvo));
    }

    private CursoDTO toDTO(Curso curso) {
        List<AlunoDTO> alunos = curso.getAlunos() != null ? curso.getAlunos().stream()
                .map(a -> new AlunoDTO(a.getId(), a.getNome()))
                .collect(Collectors.toList()) : null;
        return new CursoDTO(curso.getId(), curso.getNome(), alunos);
    }
}
