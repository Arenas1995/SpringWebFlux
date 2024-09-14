package com.spring.boot.webflux.service.impl;

import com.spring.boot.webflux.documents.CategoriaDocument;
import com.spring.boot.webflux.repository.CategoriaRepository;
import com.spring.boot.webflux.service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public Flux<CategoriaDocument> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Mono<CategoriaDocument> findById(String id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Mono<CategoriaDocument> save(CategoriaDocument categoriaDocument) {
        return categoriaRepository.save(categoriaDocument);
    }

    @Override
    public Mono<Void> delete(CategoriaDocument categoriaDocument) {
        return categoriaRepository.delete(categoriaDocument);
    }
}
