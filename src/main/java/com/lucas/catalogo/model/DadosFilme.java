package com.lucas.catalogo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosFilme( @JsonAlias("Title") String titulo, 
                        @JsonAlias("Plot") String descricao, 
                        @JsonAlias("Actors") String atores,
                        @JsonAlias("Language") String linguasDisponiveis,
                        @JsonAlias("Runtime") String duracao, 
                        @JsonAlias("Year") String anoDeLancamento) {

    public DadosFilme{

        if (atores == null || atores.equalsIgnoreCase("N/A")){
            atores = "Não encontrado";
        }

        if (descricao == null || descricao.equalsIgnoreCase("N/A")){
            descricao ="Indisponivel";
        }
    }

    @Override
    public String toString(){
        return "\n************************"+
        "\n  INFORMAÇÕES DA FILME\n"+
        "************************\n"+
        "\nNome: "+titulo+
        "\nDuração: "+duracao+
        "\nAno de Lançamento: "+anoDeLancamento+
        "\nDescrição: "+descricao+
        "\nPrincipais Atores: "+atores+
        "\nLinguagem: "+linguasDisponiveis+"\n";
    }
}
