package com.lucas.catalogo.principal;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.InputMismatchException;
import java.util.List;
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


    public String confirmacao(String novamente){

        if(novamente.equalsIgnoreCase("sim")){
            return "sim";
        }
        else if(novamente.equalsIgnoreCase("nao")){
            return "nao";
        }else{
            return "diferente";
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
                    System.out.print("Digite o nome do filme: ");
                    String filme = input.nextLine();
                    String jsonFilme = api.pesquisar(ENDERECO+filme.trim().replace(" ","+")+API_KEY);
                    DadosFilme informacoesFilme = conversor.conversor(jsonFilme, DadosFilme.class);
                    System.out.println(informacoesFilme.toString());

                    while(true){
                        System.out.print("\nDeseja ver outro filme ? (sim/nao) ");
                        String opcao = input.nextLine();
                        continuar = confirmacao(opcao);
                        if(!continuar.equalsIgnoreCase("sim") && !continuar.equalsIgnoreCase("nao")){
                            System.out.println("***Digite apenas (SIM / NAO)***");
                        }else{
                            break;
                        }
                    }

                    if(continuar.equalsIgnoreCase("nao")){
                        limpaTela();
                        bannerEncerramento();
                        break;
                    }else{
                        // continuar = "sim";
                        limpaTela();
                    }
                    break;
                    

                case 2:
                    limpaTela();
                    System.out.print("Digite o nome da série: ");
                    String serie = input.nextLine();
                    String jsonSerie = api.pesquisar(ENDERECO+serie.trim().replace(" ", "+")+API_KEY);
                    DadosSerie informacoesSerie = conversor.conversor(jsonSerie, DadosSerie.class);
                    System.out.println(informacoesSerie.toString());

                    while(true){
                        System.out.print("\nDeseja ver outra série ? (sim/nao) ");
                        String opcao = input.nextLine();
                        continuar = confirmacao(opcao);
                        if(!continuar.equalsIgnoreCase("sim") && !continuar.equalsIgnoreCase("nao")){
                            System.out.println("***Digite apenas (SIM / NAO)***");
                        }else{
                            break;
                        }
                    }

                    if(continuar.equalsIgnoreCase("nao")){
                        limpaTela();
                        bannerEncerramento();
                        break;
                    }else{
                        // continuar = "sim";
                        limpaTela();
                    }
                    break;
                
                case 3:
                    limpaTela();
                    System.out.print("Digite o nome da série: ");
                    String nomeSerie = input.nextLine();
                    System.out.print("Digite a temporada: ");
                    Integer temporada = input.nextInt();
                    input.nextLine(); // conssumo enter
                    String jsonTemporada = api.pesquisar(ENDERECO + nomeSerie.trim().replace(" ", "+") + "&season=" + temporada + API_KEY);
                    DadosTemporada dadosTemporada = conversor.conversor(jsonTemporada, DadosTemporada.class);
                    System.out.print("Digite o número do episódio: ");
                    Integer numeroEp = input.nextInt();
                    input.nextLine();
                    
                    List<DadosEpisodio> episodios = dadosTemporada.listaEpisodios();
                    
                    episodios.stream() // crie um fluxo com todos os episódios
                            .filter(e -> e.episodio().equals(numeroEp)) // só deixe passar os episódios ou o episódio que for igual ao numeroEp
                            .forEach(e -> System.out.println(e.toString())); // para cada episódio que passou, mostre os dados com o toString

                    while(true){
                        System.out.print("\nDeseja ver outro episódio ? (sim/nao) ");
                        String opcao = input.nextLine();
                        continuar = confirmacao(opcao);
                        if(!continuar.equalsIgnoreCase("sim") && !continuar.equalsIgnoreCase("nao")){
                            System.out.println("***Digite apenas (SIM / NAO)***");
                        }else{
                            break;
                        }
                    }

                    if(continuar.equalsIgnoreCase("nao")){
                        limpaTela();
                        bannerEncerramento();
                        break;
                    }else{
                        // continuar = "sim";
                        limpaTela();
                    }
                    break;

                case 4:
                    limpaTela();
                    System.out.print("Digite o nome da série: ");
                    String nomeAvaliacao = input.nextLine();
                    String jsonAvaliacao = api.pesquisar( ENDERECO + nomeAvaliacao.trim().replace(" ", "+") + API_KEY);
                    DadosSerie dadosAvaliacao = conversor.conversor(jsonAvaliacao, DadosSerie.class);

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
                    
                    while(true){
                        System.out.print("\nDeseja avaliar outra série ? (sim/nao) ");
                        String opcao = input.nextLine();
                        continuar = confirmacao(opcao);
                        if(!continuar.equalsIgnoreCase("sim") && !continuar.equalsIgnoreCase("nao")){
                            System.out.println("***Digite apenas (SIM / NAO)***");
                        }else{
                            break;
                        }
                    }

                    if(continuar.equalsIgnoreCase("nao")){
                        limpaTela();
                        bannerEncerramento();
                        break;
                    }else{
                        // continuar = "sim";
                        limpaTela();
                    }
                    break;


                case 5:
                    limpaTela();
                    System.out.print("Digite o nome da série: ");
                    String estSerie = input.nextLine();
                    String jsonEst = api.pesquisar( ENDERECO + estSerie.trim().replace(" ", "+") + API_KEY);
                    DadosSerie serieEst = conversor.conversor(jsonEst, DadosSerie.class);

                    for(int i = 1; i<serieEst.temporadas(); i++){
                        String jsonEstatistica = api.pesquisar(ENDERECO + estSerie.trim().replace(" ", "+") + "&season=" + i + API_KEY);
                        DadosTemporada estatisticaSerie = conversor.conversor(jsonEstatistica, DadosTemporada.class);
                        listaTemporadas.add(estatisticaSerie);
                    }

                    DoubleSummaryStatistics est = listaTemporadas.stream()
                                                .flatMap(t -> t.listaEpisodios().stream()) 
                                                .filter(e -> !e.avaliacao().equals("N/A")) 
                                                .mapToDouble(e -> Double.parseDouble(e.avaliacao())) 
                                                .summaryStatistics(); 

                    limpaTela();
                    System.out.printf("Média: %.2f \n", est.getAverage());
                    System.out.printf("Maior nota: %.2f \n", est.getMax());
                    System.out.printf("Menor nota: %.2f \n", est.getMin());

                    while(true){
                        System.out.print("\nDeseja avaliar outra série ? (sim/nao) ");
                        String opcao = input.nextLine();
                        continuar = confirmacao(opcao);
                        if(!continuar.equalsIgnoreCase("sim") && !continuar.equalsIgnoreCase("nao")){
                            System.out.println("***Digite apenas (SIM / NAO)***");
                        }else{
                            break;
                        }
                    }

                    if(continuar.equalsIgnoreCase("nao")){
                        limpaTela();
                        bannerEncerramento();
                        break;
                    }else{
                        // continuar = "sim";
                        limpaTela();
                    }
                    break;
                    
                    
                case 6:
                    limpaTela();
                    bannerEncerramento();

                default:
                    limpaTela();
                    System.out.println("****Digito iválido!****\n");
            }
        }
    }
}
