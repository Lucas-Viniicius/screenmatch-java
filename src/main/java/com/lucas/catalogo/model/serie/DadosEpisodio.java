package com.lucas.catalogo.model.serie;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio( @JsonAlias("Title") String titulo,
                             @JsonAlias("Episode") Integer episodio,
                             @JsonAlias("imdbRating") String avaliacao,
                             @JsonAlias("Released") String anoDeLancamento
                            ) {
    
    public DadosEpisodio{

        if (anoDeLancamento == null || anoDeLancamento.equalsIgnoreCase("N/A")){
            anoDeLancamento = "Indisponível";
        }
        if (avaliacao == null || avaliacao.equals("N/A")){
            avaliacao = "0.0";
        }

        if (titulo == null || titulo.equalsIgnoreCase("N/A")){
            titulo = "Indisponível";
        }

        if (episodio == null){
            episodio = 0;
        }
    }

    @Override
    public String toString(){
        return "\n***************************"+
        "\n  INFORMAÇÕES DA EPISÓDIO\n"+
        "***************************\n"+
        "\nTitulo: "+titulo+
        "\nEpisódio: "+episodio+
        "\nAvaliação: "+avaliacao+
        "\nAno de lançamento: "+anoDeLancamento+"\n";
    }
}
