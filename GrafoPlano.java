package planocartesiano;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuario
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Optional;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;


public class GrafoPlano {
    private ArrayList<PontoNomeado> listaPontos;
    private ArrayList<Aresta> listaArestas;
    private ArrayList<PontoNomeado> pontosProxRaio;
    private final PontoOrigem origem;
    
    public GrafoPlano(){
        this.listaPontos = new ArrayList<>();
        this.listaArestas = new ArrayList<>();
        this.origem = new PontoOrigem();
        this.listaPontos.add(this.origem);
       
    }
    /*==============================================
                        PONTOS
     ===============================================*/
    
    public PontoOrigem getOrigem(){return origem;}
    
    public List<PontoNomeado> listarPontos(){
        return new ArrayList<>(listaPontos);
    }
     public void adicionarPonto(PontoNomeado pn){
        listaPontos.add(pn);
        gerarConexaoPonto(pn);
        reconstruirGrafo();
    }
     
         public PontoNomeado buscarPonto(String nomeBusca){
        
        for(PontoNomeado pn : listaPontos){
            if(pn.getNome().equalsIgnoreCase(nomeBusca)){
                return pn;
            }
        }
        return null;
    }
    
    public PontoNomeado deletarPonto(String nomeDel){
        Iterator<PontoNomeado> it = listaPontos.iterator(); 
        while(it.hasNext()){
            PontoNomeado pn = it.next();
            if(pn == origem){
                continue;
            }
            if(pn.getNome().equalsIgnoreCase(nomeDel)){        
                for(PontoNomeado p : obterVizinhos(pn)){
                    deletarArestas(pn,p);
                }
                it.remove();
                reconstruirGrafo();
                
                return pn;
            }
                     
        }

        return null;
    }
    /*==============================================
                        ARESTAS
     ===============================================*/
    public List<Aresta> listarArestas(){
        return new ArrayList<>(listaArestas);
    }
    public void adicionarAresta(Aresta a){
        listaArestas.add(a);
    }
    
    public void removerAresta(Aresta a){
        listaArestas.remove(a);
    }
   
    public Aresta conectar(PontoNomeado p1, PontoNomeado p2){
        if(p1 == p2){
            return null;
        }
        for(Aresta a : listaArestas){
            if(a.contem(p1)){
                if(a.getOutro(p1)== p2){
                    return null;
                } 
            }
        }
        Aresta novaAresta = new Aresta(p1, p2);
        adicionarAresta(novaAresta);
        return novaAresta;
       
    }
    
    public Aresta deletarArestas(PontoNomeado p1, PontoNomeado p2){
        if(p1 == p2){
            return null;
        }
        Iterator<Aresta> it = listaArestas.iterator();
        while(it.hasNext()){
            Aresta a = it.next();
            if(a.contem(p1)){
                if(a.getOutro(p1)== p2){
                    it.remove();
                    return a;
                } 
            }
        }
        return null;
    }
    
/*==============================================
            FUNÇÕES AUXILIARES DO GRAFO
 ===============================================*/
    
    
    public List<PontoNomeado> obterVizinhos(PontoNomeado ponto){
        List<PontoNomeado> vizinhos = new ArrayList<>();
        for(Aresta a : listaArestas){
            if(a.contem(ponto)){ 
                vizinhos.add(a.getOutro(ponto));     
            }
        }
        
        return vizinhos;
    }
    
    public Optional<PontoNomeado> buscarMaisProxRaio(PontoNomeado ponto, double r){
        //“qual é o ponto mais próximo dentro do raio?”(conecta com o apenas um, o mais perto, já que objetivo é calcular a rota mais curta)
        List<PontoNomeado> pontosProxRaio = new ArrayList<>();
        for(PontoNomeado p : listaPontos){
            if (p == ponto) continue;
            if(ponto.distance(p) <= r){
                pontosProxRaio.add(p);
                
            }
        }
        
        Optional<PontoNomeado> pMaisProx = pontosProxRaio.stream()
        .min(Comparator.comparingDouble(p -> p.distance(ponto)));//pensa no sacrificio que foi pra ussar isso

        return pMaisProx;
             
    }
    
    public double controleMedia(){
         double operadorMedia;
        if(listaArestas.size() <= 3){//Caso tenha poucas arestas: Usar Média Normal
            operadorMedia = listaArestas.stream()
                .mapToDouble(Aresta::getPeso)//pega o peso
                .average()// faz a média com o maior e menor 
                .orElse(0.0);// se a lista estiver vazia retorna zero
            
        }else{//Média sem outliers
            operadorMedia = listaArestas.stream()
                .mapToDouble(Aresta::getPeso)//pega o peso
                .sorted()//organiza do menor para o maior
                .skip(1)//pula o primeiro valor(menor)
                .limit(listaArestas.size() -2)//pula o ultimo valor (maior)
                .average()// faz a média com o maior e menor pulados
                .orElse(0.0);// se a lista estiver vazia retorna zero
           
        } 
        
        
        return operadorMedia;
    }
    public Aresta gerarConexaoPonto(PontoNomeado p1){
 
        double rInicial = controleMedia();
        Optional<PontoNomeado> candidato = buscarMaisProxRaio(p1, rInicial);
        
        if(listaArestas.isEmpty()){//Caso Plano Cartesiano Vazio
            return conectar(p1,origem);
        }    
       
        if(candidato.isPresent()){//Caso em que não é necessario expandir Raio
            PontoNomeado p2 = candidato.get();
            return conectar(p1,p2);
        }
        if(!candidato.isPresent()){//Caso em que é necessario expandir Raio
            while(!candidato.isPresent()){
            rInicial = rInicial + controleMedia();
            candidato = buscarMaisProxRaio(p1,rInicial);
            }
           PontoNomeado p2 = candidato.get();
           return conectar(p1,p2);
        }
        return null;    
               
        }
    
    
    
    public Aresta reconstruirGrafo(){
        if(listaArestas.isEmpty()){
            return null;
        }
        double mediaPassada = controleMedia();
        listaArestas.clear();
        Optional<PontoNomeado> candidato = buscarMaisProxRaio(origem, mediaPassada);
        //Caso Especial de reconstrução de grafo para garantir Origem
        if(candidato.isPresent()){
            PontoNomeado p2 = candidato.get();
            conectar(origem,p2);
        }
        if(!candidato.isPresent()){
            while(!candidato.isPresent()){  
            mediaPassada = mediaPassada + mediaPassada;
            candidato = buscarMaisProxRaio(origem,mediaPassada);
            }
            PontoNomeado p2 = candidato.get();
            conectar(origem,p2);
            }
            for(PontoNomeado p : listaPontos){
                if(p == origem){
                    continue;
                }
                gerarConexaoPonto(p);
                }
        return null;
    }
            
/*==============================================
                    DISJTRKA
 ===============================================*/    
    public String disjtrka(PontoNomeado entradaOrigem, PontoNomeado entradaDestino){
        HashMap<PontoNomeado, Double> distancias = new HashMap<>();
        HashMap<PontoNomeado, PontoNomeado> anterior = new HashMap<>();
        HashSet<PontoNomeado> visitados = new HashSet<>();
        for(PontoNomeado p : listaPontos){
            distancias.put(p,Double.POSITIVE_INFINITY);
            anterior.put(p,null);
        }
            
        distancias.put(entradaOrigem,0.0);
            
        PriorityQueue<PontoNomeado> pq = new PriorityQueue<>(Comparator.comparingDouble(distancias::get));
            
        pq.add(entradaOrigem);

        while(!pq.isEmpty()){
            PontoNomeado atual = pq.poll();
            if (visitados.contains(atual)) {
                continue;
            }

            visitados.add(atual);

            if (atual == entradaDestino) {
                break;
            }
            for(PontoNomeado vizinho : obterVizinhos(atual)){
                double novaDistancia = distancias.get(atual) + atual.distance(vizinho);

                if(novaDistancia < distancias.get(vizinho)){
                    distancias.put(vizinho, novaDistancia);
                    anterior.put(vizinho, atual);
                    pq.add(vizinho);

                }
            }


        }

        ArrayList<PontoNomeado> caminho = new ArrayList<>();
        PontoNomeado atual = entradaDestino;


        if(distancias.get(entradaDestino) == Double.POSITIVE_INFINITY){
            return null;

        }
        while(atual != null){
            caminho.add(atual);
            atual = anterior.get(atual);
        }
        Collections.reverse(caminho);
        
        String resultado = caminho.stream()
        .map(PontoNomeado::toString) // Extrai o nome de cada objeto
        .collect(Collectors.joining(" -> "));
       
        return  "\t\t" + resultado;

    }
        
}
        
    
    


