package com.lucas.catalogo.model.serie;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio( @JsonAlias("Title") String titulo,
                             @JsonAlias("Episode") Integer episodio,
                             @JsonAlias("ImdbRating") String avaliacao,
                             @JsonAlias("Released") String anoDeLancamento
                            ) {
}
