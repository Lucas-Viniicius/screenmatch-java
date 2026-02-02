package com.lucas.catalogo.model.serie;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio( @JsonAlias("Title") String titulo,
                             @JsonAlias("Episode") Integer episodio,
                             @JsonAlias("ImdbRating") Integer avaliacao,
                             @JsonAlias("Released") String anoDeLancamento
                            ) {
    
    public DadosEpisodio{

        if (anoDeLancamento == null || anoDeLancamento.equalsIgnoreCase("N/A")){
            anoDeLancamento = "Indisponível";
        }
        if (avaliacao == null){
            avaliacao = 0;
        }
    }

    @Override
    public String toString(){
        return "\n************************"+
        "\n  INFORMAÇÕES DA EPISÓDIO\n"+
        "************************\n"+
        "\nTitulo: "+titulo+
        "Episódio: "+episodio+
        "Avaliação: "+avaliacao+
        "Ano de lançamento: "+anoDeLancamento+"/n";
    }
}
