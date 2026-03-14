/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planocartesiano;

/**
 *
 * @author Usuario
 */
public class PontoOrigem extends PontoNomeado{
    
    public PontoOrigem() {
        super("Origem", 0, 0);
}
    
    @Override
    public void setX(double x){
        System.out.println("\n\tOrigem Nao Pode Ser Alterada!");
    }
    
    @Override
    public void setY(double y){
        System.out.println("\n\tOrigem Nao Pode Ser Alterada!");
    }
    @Override
    public void setXY(double x, double y){
        System.out.println("\n\tOrigem Nao Pode Ser Alterada!");
    }
    
    @Override
    public void setNome(String nome){
        System.out.println("\n\tOrigem Nao Pode Ser Alterada!");
    }
    
    @Override
    public void desloc(double dX, double dY){
       System.out.println("\n\tOrigem Nao Pode Ser Alterada!");
    }
    
    @Override
    public void escale(double factor){
        System.out.println("\n\tOrigem Nao Pode Ser Alterada!"); 
    }
}
