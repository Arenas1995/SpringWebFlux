package com.spring.boot.webflux;

import com.spring.boot.webflux.documents.CategoriaDocument;
import com.spring.boot.webflux.documents.ProductoDocument;
import com.spring.boot.webflux.service.CategoriaService;
import com.spring.boot.webflux.service.ProductoService;
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

	private ProductoService productoService;

	private CategoriaService categoriaService;

	private ReactiveMongoTemplate reactiveMongoTemplate;

	private static final Logger LOG = LoggerFactory.getLogger(WebfluxApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		reactiveMongoTemplate.dropCollection("producto").subscribe();
		reactiveMongoTemplate.dropCollection("categoria").subscribe();

		CategoriaDocument categoriaDocument1 = new CategoriaDocument(null, "Tecnologia1");
		CategoriaDocument categoriaDocument2 = new CategoriaDocument(null, "Tecnologia2");
		CategoriaDocument categoriaDocument3 = new CategoriaDocument(null, "Tecnologia3");
		CategoriaDocument categoriaDocument4 = new CategoriaDocument(null, "Tecnologia4");

		Flux.just(categoriaDocument1, categoriaDocument2, categoriaDocument3, categoriaDocument4)
				.flatMap(categoriaService::save)
				.doOnNext(categoria -> LOG.info("Se inserto la categoria: {}", categoria))
				.thenMany(
						Flux.just(new ProductoDocument(null, "TV", 10000.0, LocalDate.now(),
												categoriaDocument1),
										new ProductoDocument(null, "PC", 8000.0, LocalDate.now(),
												categoriaDocument2),
										new ProductoDocument(null, "MOUSE", 1000.0, LocalDate.now(),
												categoriaDocument3),
										new ProductoDocument(null, "TECLADO", 2000.0, LocalDate.now(),
												categoriaDocument4))
								.flatMap(producto -> productoService.save(producto))
								.doOnNext(producto -> LOG.info("Se inserto el producto: {}", producto))
				)
				.subscribe();



	}
}
