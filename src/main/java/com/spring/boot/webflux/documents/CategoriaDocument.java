package com.spring.boot.webflux.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categoria")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoriaDocument {

    @Id
    private String id;

    private String nombre;
}
