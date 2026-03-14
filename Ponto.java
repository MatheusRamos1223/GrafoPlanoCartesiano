package planocartesiano;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuario
 */
import java.lang.Math;
public class Ponto {
    
    protected double x;
    protected double y;

   
    public Ponto(){
        this.x = 0;
        this.y = 0;
    }
    
    public Ponto(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public Ponto(Ponto outroPonto){
        x = outroPonto.x;
        y = outroPonto.y;
        
    }
    
    public double getX(){return x;}
    
    public double getY(){return y;}
    
    public void setX(double x){this.x = x;}
    
    public void setY(double y){this.y = y;}
    
    public void setXY(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public void assign(Ponto pt){
        this.x = pt.x;
        this.y = pt.y;
    }
    
    public double deltaX(double vX){
     return vX - this.x;
    }
    
    public double deltaY(double vY){
        return vY - this.y;
    }
    
    public double distance(double posX, double posY){
        
        double dX = deltaX(posX);
        double dY = deltaY(posY);
        
        return Math.sqrt(Math.pow(dX, 2)+ Math.pow(dY, 2));
    }
    
    public void desloc(double dX, double dY){
        this.x += dX;
        this.y += dY;
    }
    
    public void escale(double factor){
        this.x *= factor;
        this.y *= factor; 
    }
    
    public double distance(Ponto pt){return distance(pt.getX(), pt.getY());}/*Com isso posso passar um objeto inteiro
                                                                              no parametro na classe em vez de deltaX e deltaY*/
    
    public double distance(){return distance(0, 0);} //Para calcular a distancia até origem 
    
    @Override
    public String toString(){return "(" + x + "," + y + ")";}
}

