package com.apifaculdade.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apifaculdade.dto.AlunoDTO;
import com.apifaculdade.model.Aluno;
import com.apifaculdade.model.Curso;
import com.apifaculdade.repository.AlunoRepository;
import com.apifaculdade.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RestController
@RequestMapping(value = "/api/v1/alunos")
public class AlunoController {
    private static final Logger log = LoggerFactory.getLogger(AlunoController.class);
    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public AlunoController(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public Page<AlunoDTO> listarAlunos(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        log.info("Listando alunos com paginação: {}", pageable);
        return alunoRepository.findAll(pageable).map(a -> new AlunoDTO(a.getId(), a.getNome()));
    }

    @PostMapping
    public ResponseEntity<?> criarAluno(@Valid @RequestBody AlunoDTO alunoDTO, @RequestParam Long cursoId) {
        log.info("Criando aluno: {} para cursoId {}", alunoDTO.getNome(), cursoId);
        Optional<Curso> cursoOpt = cursoRepository.findById(cursoId);
        if (cursoOpt.isEmpty()) {
            log.warn("Curso não encontrado para id: {}", cursoId);
            return ResponseEntity.badRequest().body("Curso não encontrado");
        }
        Aluno aluno = new Aluno(alunoDTO.getNome(), cursoOpt.get());
        Aluno salvo = alunoRepository.save(aluno);
        log.info("Aluno criado com id: {}", salvo.getId());
        return ResponseEntity.ok(new AlunoDTO(salvo.getId(), salvo.getNome()));
    }
}
