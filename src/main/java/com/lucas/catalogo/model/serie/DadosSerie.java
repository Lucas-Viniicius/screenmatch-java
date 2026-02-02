package com.lucas.catalogo.model.serie;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String titulo, 
                        @JsonAlias("Season") Integer temporadas, 
                        @JsonAlias("Plot") String descricao, 
                        @JsonAlias("Year") String anoDeLancamento){
}
