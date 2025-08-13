package com.apifaculdade.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apifaculdade.model.Curso;
import com.apifaculdade.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    private static final Logger log = LoggerFactory.getLogger(CursoService.class);
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> listarTodos() {
        log.info("Listando todos os cursos");
        return cursoRepository.findAll();
    }

    public Optional<Curso> buscarPorId(Long id) {
        log.info("Buscando curso por id: {}", id);
        return cursoRepository.findById(id);
    }

    @Transactional
    public Curso salvar(Curso curso) {
        log.info("Salvando curso: {}", curso.getNome());
        return cursoRepository.save(curso);
    }
}
