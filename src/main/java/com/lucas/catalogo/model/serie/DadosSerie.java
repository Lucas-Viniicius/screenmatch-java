package com.lucas.catalogo.model.serie;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String titulo, 
                        @JsonAlias("totalSeasons") Integer temporadas, 
                        @JsonAlias("Genre") String genero,
                        @JsonAlias("Plot") String descricao, 
                        @JsonAlias("Year") String anoDeLancamento){

    public DadosSerie{

        if (descricao == null || descricao.equalsIgnoreCase("N/A")){
            descricao = "Indisponível";
        }

        if (anoDeLancamento == null || anoDeLancamento.equalsIgnoreCase("N/A")){
            anoDeLancamento = "Indisponível";
        }
        
        if (temporadas == null){
            temporadas = 0;
        }
        
        if (titulo == null || titulo.equalsIgnoreCase("N/A")){
            titulo = "Indisponível";
        }   
        
        if(genero == null || genero.equalsIgnoreCase("N/A")){
            genero = "Genero não disponível";
        }
    }

    @Override
    public String toString(){
        return "\n************************"+
        "\n  INFORMAÇÕES DA SÉRIE\n"+
        "************************\n"+
        "\nTitulo: "+titulo+
        "\nTemporadas: "+temporadas+
        "\nGenero: "+genero+
        "\nAno de lançamento: "+anoDeLancamento+
        "\nDescrição: "+descricao+"\n";
    }
}
