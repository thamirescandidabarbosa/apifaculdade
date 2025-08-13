package com.apifaculdade.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apifaculdade.model.Aluno;
import com.apifaculdade.model.Curso;
import com.apifaculdade.repository.AlunoRepository;
import com.apifaculdade.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class AlunoService {
    private static final Logger log = LoggerFactory.getLogger(AlunoService.class);
    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public AlunoService(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    @Transactional
    public Aluno salvar(String nome, Long cursoId) {
        log.info("Salvando aluno: {} para cursoId {}", nome, cursoId);
        Optional<Curso> cursoOpt = cursoRepository.findById(cursoId);
        if (cursoOpt.isEmpty()) {
            log.warn("Curso não encontrado para id: {}", cursoId);
            throw new IllegalArgumentException("Curso não encontrado");
        }
        Aluno aluno = new Aluno(nome, cursoOpt.get());
        return alunoRepository.save(aluno);
    }
}
