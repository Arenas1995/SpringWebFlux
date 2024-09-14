package com.spring.boot.webflux.Controller;

import com.spring.boot.webflux.Documents.ProductoDocument;
import com.spring.boot.webflux.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/producto")
@AllArgsConstructor
public class ProductoController {

    private final ProductoRepository productoRepository;

    private static final Logger LOG = LoggerFactory.getLogger(ProductoController.class);

    @GetMapping("/listar")
    private Flux<ProductoDocument>  listarProductos() {

        Flux<ProductoDocument> productoDocumentFlux = productoRepository.findAll();

        productoDocumentFlux.subscribe(producto -> LOG.info(producto.toString()));
        return productoDocumentFlux;
    }

    @GetMapping("/listar/{id}")
    private Mono<ProductoDocument>  listarProductosById(@PathVariable String id) {

        //Mono<ProductoDocument> productoDocumentMono = productoRepository.findById(id);

        Flux<ProductoDocument> productoDocumentFlux = productoRepository.findAll();
        Mono<ProductoDocument> productoDocumentMonoFilter = productoDocumentFlux
                .filter(productoDocument -> productoDocument.getId().equals(id))
                .next()
                .doOnNext(productoDocument -> LOG.info(productoDocument.toString()));

        return productoDocumentMonoFilter;
    }

    @GetMapping("/listar-data-driver")
    private Flux<ProductoDocument>  listarProductosDataDriver() {

        Flux<ProductoDocument> productoDocumentFlux = productoRepository.findAll()
                .delayElements(Duration.ofSeconds(2));

        productoDocumentFlux.subscribe(producto -> LOG.info(producto.toString()));
        return productoDocumentFlux;
    }

}
