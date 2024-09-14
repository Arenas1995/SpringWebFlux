package com.spring.boot.webflux.repository;

import com.spring.boot.webflux.documents.ProductoDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductoRepository extends ReactiveMongoRepository<ProductoDocument, String> {
}
