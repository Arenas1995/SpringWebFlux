package com.spring.boot.webflux.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductoDocument {

    @Id
    private String id;

    private String nombre;

    private Double precio;

    private LocalDate fecha;

    private CategoriaDocument categoriaDocument;
}
