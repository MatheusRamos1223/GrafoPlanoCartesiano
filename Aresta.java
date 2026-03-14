/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planocartesiano;

/**
 *
 * @author Usuario
 */
public class Aresta {
    private PontoNomeado p1;
    private PontoNomeado p2;
    private double peso;
    
   
    
    public Aresta(PontoNomeado p1, PontoNomeado p2){
            this.p1 = p1;
            this.p2 = p2;
            this.peso = p1.distance(p2); 
    } 
    
    public Aresta(){
        this.peso = 0;
    }
    
    public PontoNomeado getP1(){
        return p1;
    }
    
    public PontoNomeado getP2(){
        return p2;
    }
    
    public double getPeso(){
        return peso;
    }
    
     public boolean contem(PontoNomeado ponto) {
        return ponto == p1 || ponto == p2; 
    }
    
    public PontoNomeado getOutro(PontoNomeado ponto) {
        if(ponto == p1){
            return p2;
        }else if(ponto == p2){
            return p1;
        }
        return null; 
    }
    
    public void exibirArestas(){
        System.out.println("\t\t\t\t" + toString()); 
        System.out.println("\n\t\t============================================================");
    }
    
    @Override
    public String toString() {
        return p1.getNome() + " <-> " + p2.getNome() + " | peso: " + peso;
    }
   }

