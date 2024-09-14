package com.spring.boot.webflux.service;

import com.spring.boot.webflux.documents.CategoriaDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoriaService {

    Flux<CategoriaDocument> findAll();

    Mono<CategoriaDocument> findById(String id);

    Mono<CategoriaDocument> save(CategoriaDocument categoriaDocument);

    Mono<Void> delete(CategoriaDocument categoriaDocument);
}
