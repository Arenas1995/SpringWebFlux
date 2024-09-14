package com.spring.boot.webflux.service.impl;

import com.spring.boot.webflux.documents.CategoriaDocument;
import com.spring.boot.webflux.documents.ProductoDocument;
import com.spring.boot.webflux.repository.CategoriaRepository;
import com.spring.boot.webflux.repository.ProductoRepository;
import com.spring.boot.webflux.service.ProductoService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private ProductoRepository productoRepository;

    private CategoriaRepository categoriaRepository;

    private static final Logger LOG = LoggerFactory.getLogger(ProductoServiceImpl.class);

    @Override
    public Flux<ProductoDocument> findAll() {

        Flux<ProductoDocument> productoDocumentFlux = productoRepository.findAll();
        productoDocumentFlux.subscribe(producto -> LOG.info(producto.toString()));

        return productoDocumentFlux;
    }

    @Override
    public Mono<ProductoDocument> findById(String id) {
        return productoRepository.findById(id);
    }

    @Override
    public Mono<ProductoDocument> save(ProductoDocument productoDocument) {

        Mono<CategoriaDocument> categoriaDocumentMono = categoriaRepository
                .findById(productoDocument.getCategoriaDocument().getId());

        return categoriaDocumentMono.flatMap(categoria -> {
            productoDocument.setFecha(LocalDate.now());
            productoDocument.setCategoriaDocument(categoria);
            return productoRepository.save(productoDocument);
        });

    }

    @Override
    public Mono<Void> delete(ProductoDocument productoDocument) {
        return productoRepository.delete(productoDocument);
    }
}
