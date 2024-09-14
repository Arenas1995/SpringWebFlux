package com.spring.boot.webflux.repository;

import com.spring.boot.webflux.documents.CategoriaDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoriaRepository extends ReactiveMongoRepository<CategoriaDocument, String> {
}
