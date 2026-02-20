package com.lucas.catalogo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosFilme( @JsonAlias("Title") String titulo,
                        @JsonAlias("Genre") String genero,
                        @JsonAlias("Plot") String descricao, 
                        @JsonAlias("Actors") String atores,
                        @JsonAlias("Language") String linguasDisponiveis,
                        @JsonAlias("Runtime") String duracao, 
                        @JsonAlias("Year") String anoDeLancamento) {

    public DadosFilme{

        if (atores == null || atores.equalsIgnoreCase("N/A")) {
            atores = "Não encontrado";
        }

        if (descricao == null || descricao.equalsIgnoreCase("N/A")) {
            descricao = "Indisponível";
        }

        if (linguasDisponiveis == null || linguasDisponiveis.equalsIgnoreCase("N/A")) {
            linguasDisponiveis = "Não informado";
        }

        if (duracao == null || duracao.equalsIgnoreCase("N/A")) {
            duracao = "Duração desconhecida";
        }

        if (anoDeLancamento == null || anoDeLancamento.equalsIgnoreCase("N/A")) {
            anoDeLancamento = "Ano não informado";
        }

        if (titulo == null || titulo.equalsIgnoreCase("N/A")) {
            titulo = "Título não disponível";
        }

        if(genero == null || genero.equalsIgnoreCase("N/A")){
            genero = "Genero não disponível";
        }
    }

    @Override
    public String toString(){
        return "\n************************"+
        "\n  INFORMAÇÕES DO FILME\n"+
        "************************\n"+
        "\nNome: "+titulo+
        "\nGenero: "+genero+
        "\nDuração: "+duracao+
        "\nAno de Lançamento: "+anoDeLancamento+
        "\nDescrição: "+descricao+
        "\nPrincipais Atores: "+atores+
        "\nLinguagem: "+linguasDisponiveis+"\n";
    }
}
