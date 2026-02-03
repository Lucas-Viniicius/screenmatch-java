package com.lucas.catalogo.principal;

import java.util.Scanner;

import com.lucas.catalogo.model.DadosFilme;
import com.lucas.catalogo.model.serie.DadosSerie;
import com.lucas.catalogo.service.ApiRest;
import com.lucas.catalogo.service.Conversor;

public class MainCatalogo {

    private static Scanner input = new Scanner(System.in);

    private static final String ENDERECO = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=6585022c";

    
    public static void limpaTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    
    public static void main(String[]args){

        ApiRest api = new ApiRest();
        Conversor conversor = new Conversor();

        System.out.println("\n<*><*>*<*><*><*><*><*><*><*><*><*>");
        System.out.println("    BEM-VINDO AO CATALOGO JAVA");
        System.out.println("<*><*>*<*><*><*><*><*><*><*><*><*>\n");
        System.out.println("Digite 1 para pesquisar FILMES");
        System.out.println("Digite 2 para pesquisar SÉRIES");
        System.out.println("Digite 3 para pesquisar informações de EPISÓDIOS");
        System.out.println("Digite 4 para SAIR");
        System.out.print("\nInforme a opção desejada: ");
        int opcaoMenu = input.nextInt();
        input.nextLine();

        switch(opcaoMenu){
            case 1:
                limpaTela();
                System.out.print("Digite o nome do filme: ");
                String filme = input.nextLine();
                String jsonFilme = api.pesquisar(ENDERECO+filme.trim().replace(" ","+")+API_KEY);
                DadosFilme informacoesFilme = conversor.conversor(jsonFilme, DadosFilme.class);
                System.out.println(informacoesFilme.toString());
                break;

            case 2:
                limpaTela();
                System.out.print("Digite o nome da série: ");
                String serie = input.nextLine();
                String jsonSerie = api.pesquisar(ENDERECO+serie.trim().replace(" ", "+")+API_KEY);
                DadosSerie informacoesSerie = conversor.conversor(jsonSerie, DadosSerie.class);
                System.out.println(informacoesSerie.toString());
                break;

        }
    }
}
