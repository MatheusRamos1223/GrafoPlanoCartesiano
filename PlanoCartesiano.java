/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package planocartesiano;

/**
 *
 * @author Usuario
 */
import java.util.Scanner;

public class PlanoCartesiano {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Scanner scanner = new Scanner(System.in);
  
        GrafoPlano gp = new GrafoPlano();
        PontoNomeado pontoAtual = gp.getOrigem();
        
        
        boolean op = false;
        
        while(op != true){
            
            
            System.out.println("\n\t\t======================PLANO CARTESIANO======================");
            System.out.print("\t\t\t\t Ponto Atual - " + pontoAtual.toString());
            System.out.println("\n\t\t============================================================");
            System.out.print("""
                             \t\t\t1 - Criar Coordenada
                             \t\t\t2 - Deslocar Coordenada
                             \t\t\t3 - Escalar Coordenada
                             \t\t\t4 - Calcular Distancia Entre Coordenadas
                             \t\t\t5 - Trocar Coordenada
                             \t\t\t6 - Exibir Coordenadas
                             \t\t\t7 - Exibir Arestas
                             \t\t\t8 - Deletar Coordenadas
                             \t\t\t9 - Encontrar Rota Mais Curta
                             \t\t\t0 - Sair
                             \t\t\t\t\t: """);
            int escolha = scanner.nextInt();
            scanner.nextLine();
            
            switch(escolha){
                case 1 -> {
                    System.out.println("\n\t\t=====================CRIANDO COORDENADA=====================");
                    System.out.print("\t\tNome dessa Coordenada: ");
                    String nomeXY = scanner.nextLine();
                    
                    System.out.print("\t\t\t\t\tX: ");
                    double x = scanner.nextDouble();
                    
                    System.out.print("\n\t\t\t\t\tY: ");
                    double y = scanner.nextDouble();
                    scanner.nextLine();
                    
                    /*
                    Esse objeto representa um ponto com nome + coordenadas.
                    */
                    
                    PontoNomeado novo = new PontoNomeado(nomeXY, x, y);
                    gp.adicionarPonto(novo);
                    
                    
                    pontoAtual = novo;
                    
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                }
                
                case 2 -> {
                    System.out.println("\n\t\t=========================DESLOCANDO=========================");
                    
                    System.out.print("\tDelta de X: ");
                    double newLocalX = scanner.nextDouble();

                    System.out.print("\n\tDelta de Y: ");
                    double newLocalY = scanner.nextDouble();
                    scanner.nextLine(); // limpa buffer aqui
                    /*
                    desloc() soma os valores informados às coordenadas atuais.
                     */
                    pontoAtual.desloc(newLocalX, newLocalY);
                    
                    
                    System.out.println("\nCoordenadas Deslocadas - Pressione Enter para continuar...");
                    scanner.nextLine();
                }
                
                case 3 -> {
                    System.out.println("\n\t\t=========================ESCALANDO==========================");
                    
                    System.out.print("\t\tTem certeza que deseja escalonar os Pontos? [S/N]: ");
                    String certeza = scanner.nextLine().toUpperCase();

                    if(certeza.equalsIgnoreCase("SIM") || certeza.equalsIgnoreCase("S")){

                        System.out.print("\n\t\tFator de Escala: ");
                        double fator = scanner.nextDouble();
                        scanner.nextLine(); //limpa Buffer

                        pontoAtual.escale(fator);
                    }else{
                       System.out.println("\nAção cancelada.");
                        
                    }    
                    System.out.println("\nCoordenadas Escaladas! - Pressione Enter para continuar...");
                    scanner.nextLine();
                } 
                    
                case 4 -> { 
                    System.out.println("\n\t\t=====================MEDINDO DISTANCIAS=====================");
                    System.out.println("\t\tCordenada Atual: " + pontoAtual.getNome() + pontoAtual.toString());
                    System.out.print("\t\tDigite o Nome da Coordenada de Destino: ");
                    String buscaDest = scanner.nextLine();
                    /*
                    Busca o ponto dentro do repositório pelo nome.
                    */
                    
                    PontoNomeado destEncontrado = gp.buscarPonto(buscaDest);
                    
                    if(destEncontrado != null){
                        
                        double distance = pontoAtual.distance(destEncontrado);
                        
                        System.out.printf("\tA Distancia do Ponto Atual: " + pontoAtual.getNome() +  pontoAtual.toString() +
                                " -X- Ao destino: " + destEncontrado.getNome() + destEncontrado.toString() +
                                " Eh de: " + distance);
                        
                    }else{
                        System.out.print("\tErro! " + buscaDest + "Nao Encontrado!");
                    }
                    
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                }
                    
                case 5 -> {
                    System.out.println("\n\t\t=====================TROCANDO COORDENADAS=====================");
                    System.out.println("\t\tCOORDENADAS ATUAIS: " + pontoAtual.toString());
                    for(PontoNomeado pLista : gp.listarPontos()){
                        pLista.exibirPontos();
                    }
                    System.out.print("\tPara Qual Coordenada Deseja Trocar?(Digite o Nome): ");
                    String busca = scanner.nextLine();
                    
                    
                    PontoNomeado encontrado = gp.buscarPonto(busca);
                    
                    if(encontrado != null){
                        pontoAtual = encontrado;
                        
                        System.out.println("\tSucesso! Coordenadas Trocadas Para: " + pontoAtual.toString());
                    }else{
                        System.out.print("\tErro! " + busca + "Nao Encontrado!");
                    }
                    
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                }
                    
                case 6 -> {
                    System.out.println("\n\t\t====================EXIBINDO COORDENADAS====================");
                    for(PontoNomeado pLista : gp.listarPontos()){
                        pLista.exibirPontos();
                    }
                    
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                }
                
                case 7-> {
                    System.out.println("\n\t\t======================EXIBINDO ARESTAS======================");
                    for(Aresta aLista : gp.listarArestas()){
                        aLista.exibirArestas();
                    }
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                }
                    
                case 8 -> {
                    System.out.println("\n\t\t====================DELETANDO COORDENADAS====================");
                    System.out.print("\t\tQual Coordenada Deseja Deletar?(Digite o Nome): ");
                    String buscaDel = scanner.nextLine();
                    
                    
                    PontoNomeado removido = gp.deletarPonto(buscaDel);
                    if(removido != null){
                        if(removido == pontoAtual){//caso o cara apague  o ponto atual
                            pontoAtual = gp.getOrigem();
                        }
                        System.out.println("\t\tCoordenada: " +  removido.toString() + " Deletada com Sucesso!");
                    }else{
                        System.out.println("\t\tErro! Coordenada: " + buscaDel +" Nao Existe ou Nao Deletavel");
                    }
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                }
                case 9 -> {
                    System.out.println("\n\t\t====================GRAFO PARA ROTAS====================");
                    System.out.print("\t\t Ponto de Origem: ");
                    String entradaOrigem = scanner.nextLine();
                    
                    PontoNomeado buscaOrigem = gp.buscarPonto(entradaOrigem);
                    
                    System.out.print("\n\t\tPonto de Destino: ");
                    String entradaDestino = scanner.nextLine();
                    
                    PontoNomeado buscaDestino = gp.buscarPonto(entradaDestino);
                    
                    System.out.print(gp.disjtrka(buscaOrigem,buscaDestino));
                    
                    System.out.println("\nPressione Enter para continuar...");
                    scanner.nextLine();
                }
                    
                    
                    
                case 0 -> {
                    System.out.println("Encerrando...");
                    op = true;
                }
                    
                default -> System.out.println("Opção Invalida! Tente Novamente!"); 
                    
                    
                    
            }       
            
        }
    }
    
}
