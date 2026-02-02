package com.lucas.catalogo.model.serie;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosTemporada(@JsonAlias("Season") Integer temporada,
                            @JsonAlias("Episodes") List<DadosEpisodio> listaEpisodios ) {
    
}
