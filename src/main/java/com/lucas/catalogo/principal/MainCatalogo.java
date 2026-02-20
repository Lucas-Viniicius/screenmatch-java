package com.lucas.catalogo.principal;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.lucas.catalogo.model.DadosFilme;
import com.lucas.catalogo.model.serie.DadosEpisodio;
import com.lucas.catalogo.model.serie.DadosSerie;
import com.lucas.catalogo.model.serie.DadosTemporada;
import com.lucas.catalogo.service.ApiRest;
import com.lucas.catalogo.service.Conversor;

public class MainCatalogo {

    private Scanner input = new Scanner(System.in);

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    private List<DadosTemporada> listaTemporadas = new ArrayList<>();

    
    public static void limpaTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public void bannerEntrada(){
        System.out.println("""

            ╔════════════════════════════════════════════════╗
            ║               CATÁLOGO JAVA 🎥                 ║
            ║        Filmes • Séries • Episódios             ║
            ║                                                ║
            ║   Consumo de API • POO • Streams • JSON        ║
            ╚════════════════════════════════════════════════╝

            """);
    }

    public void bannerEncerramento(){
        limpaTela();
        System.out.println("""

            ------------------------------------------------
                O Catálogo Java agradece a sua escolha.
                Até a próxima! 👋
            ------------------------------------------------

                """);
    }


    public String usarNovamente(){
        while(true){
            System.out.print("\nDeseja voltar ao menu ? (sim/nao) ");
            String opcao = input.nextLine();
            if(!opcao.equalsIgnoreCase("sim") && !opcao.equalsIgnoreCase("nao")){
                System.out.println("***Digite apenas (sim / nao)***");
            }else{
                return opcao;
            }
        }
    }


    public void verificaContinuar(String opcaoContinuar){
        if(opcaoContinuar.equalsIgnoreCase("nao")){
            limpaTela();
            bannerEncerramento();
        }else{
            // continuar = "sim";
            limpaTela();
        }
    }
    

    public void principalCatalogo(){

        ApiRest api = new ApiRest();
        Conversor conversor = new Conversor();

        limpaTela();
        bannerEntrada();
    
        System.out.println("SEJA MUITO BEM-VINDO AO CATÁLOGO JAVA!\n");

        String continuar = "sim";

        while (continuar.equalsIgnoreCase("sim")){

            System.out.println("===================================");
            System.out.println("          MENU PRINCIPAL");
            System.out.println("===================================");
            System.out.println("1️⃣  Pesquisar FILMES");
            System.out.println("2️⃣  Pesquisar SÉRIES");
            System.out.println("3️⃣  Pesquisar EPISÓDIOS");
            System.out.println("4️⃣  Avaliação por TEMPORADA");
            System.out.println("5️⃣  Estatística da série");
            System.out.println("6️⃣  SAIR");
            System.out.println("===================================");

            int opcaoMenu;

            while(true){
                try{
                    System.out.print("\nInforme a opção desejada: ");
                    opcaoMenu = input.nextInt();
                    input.nextLine(); // limpa o buffer
                    break;               
                }catch(InputMismatchException e){
                    System.out.println("***Digite apenas números!***\n");
                    input.nextLine(); // evita que o loop trave (como o nextInt não limpa o buffer, o valor das letras irão ficar lá sempre, criando um loop infinito)
                }                
            }

            switch(opcaoMenu){
                case 1:
                    limpaTela();
                    while(true){
                        System.out.print("Digite o nome do filme: ");
                        String filme = input.nextLine();
                        if (filme.isBlank()){
                            System.out.println("\n***Digite um nome de Filme válido***\n");
                            continue;
                        }
                        try{
                            String jsonFilme = api.pesquisar(ENDERECO+filme.trim().replace(" ","+")+API_KEY);
                            DadosFilme informacoesFilme = conversor.conversor(jsonFilme, DadosFilme.class);
                            System.out.println(informacoesFilme.toString());
                            break;
                        }catch(RuntimeException e){
                            System.out.println("\n***Filme não encontrado***\n");
                            break;
                        }
                    }
                    continuar = usarNovamente();
                    verificaContinuar(continuar);

                    break;
                    
                case 2:
                    limpaTela();
                    while(true){
                        System.out.print("Digite o nome da série: ");
                        String serie = input.nextLine();
                        if(serie.isBlank()){
                            System.out.println("\n***Digite o nome de uma série válida***\n");
                            continue;
                        }
                        try{
                            String jsonSerie = api.pesquisar(ENDERECO+serie.trim().replace(" ", "+")+API_KEY);
                            DadosSerie informacoesSerie = conversor.conversor(jsonSerie, DadosSerie.class);
                            System.out.println(informacoesSerie.toString());     
                            break;                         
                        }catch(RuntimeException e){
                            System.out.println("\n***Série não encontrada***\n");
                            break;
                        }
                    }

                    continuar = usarNovamente();
                    verificaContinuar(continuar);

                    break;
                
                case 3:
                    limpaTela();

                    Integer temporada; 
                    String nomeSerie; 
                    while(true){
                        System.out.print("Digite o nome da série: ");
                        nomeSerie = input.nextLine();
                        if(nomeSerie.isBlank()){ // verifica se a string não esta vazia
                            System.out.println("\n***Digite o nome de uma série válida***\n");
                            continue;
                        }else{
                            break;
                        }
                    }
                    while(true){
                        try{
                            System.out.print("Digite a temporada: ");
                            temporada = input.nextInt();
                            input.nextLine(); // limpa o buffer 
                            break;                   
                        }catch(InputMismatchException e){
                            System.out.println("\n***Digite apenas números***\n");
                            input.nextLine(); // limpa o buffer
                            continue;
                        }                         
                    }                  
                    try{
                        String jsonTemporada = api.pesquisar(ENDERECO + nomeSerie.trim().replace(" ", "+") + "&season=" + temporada + API_KEY);
                        DadosTemporada dadosTemporada = conversor.conversor(jsonTemporada, DadosTemporada.class);

                        while(true){
                            try{
                                System.out.print("Digite o número do episódio: ");
                                Integer numeroEp = input.nextInt();
                                input.nextLine();
                                Optional<DadosEpisodio> episodios = dadosTemporada.listaEpisodios().stream() // crie um fluxo com todos os episódios
                                        .filter(e -> e.episodio().equals(numeroEp)) // só deixe passar os episódios ou o episódio que for igual ao numeroEp
                                        .findFirst(); // pega o primeiro elemento que passou pelo filtro e retorna um Optional

                                        if(episodios.isPresent()){
                                            System.out.println(episodios.get());
                                            break;
                                        }else{
                                            System.out.println("\n***Epoisódio não encontrado***\n");
                                        }                                
                                }catch(InputMismatchException e){
                                    System.out.println("\n***Digite apenas números***\n");
                                    input.nextLine();
                                }
                        }
                        }catch(RuntimeException e){
                            System.out.println("\n***Série não encontrada***\n");
                        }
                    continuar = usarNovamente();
                    verificaContinuar(continuar);

                    break;

                case 4:
                    limpaTela();
                    listaTemporadas.clear(); // limpa a lista

                    String nomeAvaliacao;
                    String jsonAvaliacao;
                    DadosSerie dadosAvaliacao = null;
                    while(true){
                        System.out.print("Digite o nome da série: ");
                        nomeAvaliacao = input.nextLine();
                        if(nomeAvaliacao.isBlank()){
                            System.out.println("\n***Digite o nome de uma série válida***\n");
                            continue;
                        }else{
                            break;
                        }
                    }
                    try{
                        jsonAvaliacao = api.pesquisar( ENDERECO + nomeAvaliacao.trim().replace(" ", "+") + API_KEY);
                        dadosAvaliacao = conversor.conversor(jsonAvaliacao, DadosSerie.class);
                            
                    }catch(RuntimeException e){
                        System.out.println("\n***Série não encontrada***\n");
                        continuar = usarNovamente();
                        verificaContinuar(continuar);
                        break; // sai do case 4
                    }

                    for(int i = 1; i <= dadosAvaliacao.temporadas(); i++){
                        String avaliacao = api.pesquisar(ENDERECO + nomeAvaliacao.trim().replace(" ", "+") + "&season=" + i + API_KEY);
                        DadosTemporada avaliacaoConvertida = conversor.conversor(avaliacao, DadosTemporada.class);
                        listaTemporadas.add(avaliacaoConvertida);
                    }

                    System.out.println(" "); // pula uma linha

                    for (DadosTemporada temporadaEm : listaTemporadas) {

                        double media = temporadaEm.listaEpisodios().stream()
                            .mapToDouble(e -> Double.parseDouble(e.avaliacao())) // converte a avaliação do episódio (String) para double
                            .filter(avaliacao -> avaliacao > 0.0) // mantém apenas avaliações válidas (maiores que 0.0)
                            .average() // calcula a média dos valores que passaram pelo filtro
                            .orElse(0.0); // retorna 0.0 caso não exista nenhuma avaliação válida (Option)

                        System.out.printf("Temporada " +temporadaEm.temporada()+ ": %.1f%n", media);
                    }
                    
                    continuar = usarNovamente();
                    verificaContinuar(continuar);

                    break;
                    
                case 5:
                    limpaTela();
                    listaTemporadas.clear(); // limpa a lista

                    String estSerie;
                    while(true){
                        System.out.print("Digite o nome da série: ");
                        estSerie = input.nextLine(); 
                        if(estSerie.isBlank()){
                            System.out.println("\n***Digite o nome de uma série válida***\n");
                            continue;
                        }else{
                            break;
                        }                     
                    }

                    DadosSerie serieEst = null;
                    try{
                        String jsonEst = api.pesquisar( ENDERECO + estSerie.trim().replace(" ", "+") + API_KEY);
                        serieEst = conversor.conversor(jsonEst, DadosSerie.class); 
                        if(serieEst == null){
                            break;
                        }                   
                    }catch(RuntimeException e){
                        System.out.println("\n***Série não encontrada***\n");
                        continuar = usarNovamente();
                        verificaContinuar(continuar);
                    }

                    for(int i = 1; i <= serieEst.temporadas(); i++){
                        String jsonEstatistica = api.pesquisar(ENDERECO + estSerie.trim().replace(" ", "+") + "&season=" + i + API_KEY);
                        DadosTemporada estatisticaSerie = conversor.conversor(jsonEstatistica, DadosTemporada.class);
                        listaTemporadas.add(estatisticaSerie);
                    }

                    DoubleSummaryStatistics est = listaTemporadas.stream()
                                                .flatMap(t -> t.listaEpisodios().stream()) // pega todos os episódios de todas as temporadas e coloca dentro de um caixa
                                                .filter(e -> !e.avaliacao().equals("N/A")) // filtre todas as avaliações que não sejam N/A (mas não é preciso, pois ja trasformie isso em 0.0 la no record)
                                                .mapToDouble(e -> Double.parseDouble(e.avaliacao())) // trasforme esses episódios.avaliacão() em numeros do tipo double
                                                .filter(avaliacao -> avaliacao > 0.0) // porque não e.avaliacao, isso porque o mapToDouble "trasformou o objeto todo" em um número do tipo double
                                                .summaryStatistics(); // calcula a média, min, max, soma de todas as avaliações, dentre outras coisas

                    limpaTela();
                    System.out.println("ESTATÍSTICA DOS EPISÓDIOS DA SÉRIE\n");
                    System.out.printf("Episódio com a maior nota: %.2f \n", est.getMax());
                    System.out.printf("Episódio com a menor nota: %.2f \n", est.getMin());
                    System.out.printf("Média das notas dos episódios: %.2f \n", est.getAverage());

                    continuar = usarNovamente();
                    verificaContinuar(continuar);

                    break;

                case 6:
                    limpaTela();
                    bannerEncerramento();
                    return;

                default:
                    limpaTela();
                    System.out.println("****Digito iválido!****\n");
            }
        }
    }
}
