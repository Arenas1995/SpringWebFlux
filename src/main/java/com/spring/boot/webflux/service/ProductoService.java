package com.spring.boot.webflux.service;

import com.spring.boot.webflux.documents.ProductoDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoService {

    Flux<ProductoDocument> findAll();

    Mono<ProductoDocument> findById(String id);

    Mono<ProductoDocument> save(ProductoDocument productoDocument);

    Mono<Void> delete(ProductoDocument productoDocument);
}
