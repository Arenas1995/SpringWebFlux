package com.spring.boot.webflux.controller;

import com.spring.boot.webflux.documents.ProductoDocument;
import com.spring.boot.webflux.service.ProductoService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/producto")
@AllArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    private static final Logger LOG = LoggerFactory.getLogger(ProductoController.class);

    @GetMapping("/listar")
    private Flux<ProductoDocument>  listarProductos() {
        return productoService.findAll();
    }

    @GetMapping("/listar/{id}")
    private Mono<ProductoDocument>  listarProductosById(@PathVariable String id) {

        /*Flux<ProductoDocument> productoDocumentFlux = productoService.findAll();
        Mono<ProductoDocument> productoDocumentMonoFilter = productoDocumentFlux
                .filter(productoDocument -> productoDocument.getId().equals(id))
                .next()
                .doOnNext(productoDocument -> LOG.info(productoDocument.toString()));*/

        return productoService.findById(id);
    }

    @GetMapping("/listar-data-driver")
    private Flux<ProductoDocument>  listarProductosDataDriver() {
        return productoService.findAll()
                .delayElements(Duration.ofSeconds(2));
    }

    @PostMapping("/guardar")
    private Mono<ProductoDocument>  guardarProducto(@RequestBody ProductoDocument productoDocument) {
        return productoService.save(productoDocument);
    }

}
