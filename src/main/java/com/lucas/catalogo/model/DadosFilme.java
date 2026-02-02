package com.lucas.catalogo.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosFilme( @JsonAlias("title") String titulo, 
                        @JsonAlias("plot") String descricao, 
                        @JsonAlias("Actores") String atores,
                        @JsonAlias("Language") String linguasDisponiveis,
                        @JsonAlias("Runtime") String duracao, 
                        @JsonAlias("Year") String anoDeLancamento) {
    
}
