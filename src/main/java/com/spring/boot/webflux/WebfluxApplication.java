package com.spring.boot.webflux;

import com.spring.boot.webflux.Documents.ProductoDocument;
import com.spring.boot.webflux.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.time.LocalDate;


@SpringBootApplication
@AllArgsConstructor
public class WebfluxApplication implements CommandLineRunner {

	private ProductoRepository productoRepository;

	private ReactiveMongoTemplate reactiveMongoTemplate;

	private static final Logger LOG = LoggerFactory.getLogger(WebfluxApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		reactiveMongoTemplate.dropCollection("producto").subscribe();

		Flux.just(new ProductoDocument(null, "TV", 10000.0, LocalDate.now()),
				new ProductoDocument(null, "PC", 8000.0, LocalDate.now()),
				new ProductoDocument(null, "MOUSE", 1000.0, LocalDate.now()),
				new ProductoDocument(null, "TECLADO", 2000.0, LocalDate.now()))
				.flatMap(producto -> productoRepository.save(producto))
				.subscribe(producto -> LOG.info("Se inserto el producto: {}", producto));
	}
}
