package src;
import java.util.*;

public class malha{
    List<posicao> objPos=new ArrayList<posicao>();
    private posicao tamMax;

    public malha(int x, int y){
        tamMax = new posicao(x,y);
    }
    public void setxSize(int x){
        this.tamMax.setxPosition(x);
    }
    public void setySize(int y){
        this.tamMax.setyPosition(y);
    }
    public int getxSize(){
        return this.tamMax.getxPosition();
    }
    public int getySize(){
        return this.tamMax.getyPosition();
    }
    public boolean posicaoValida(int x, int y){
        return x >= 0 && x <= this.tamMax.getxPosition() && y >= 0 && y <= this.tamMax.getxPosition();
    }
    public void printObjectList(){
        for(posicao pos : this.objPos)
            System.out.println("x: " + pos.getxPosition() + " y: " + pos.getyPosition() + '\n');
    }
    public boolean addNewObject(posicao newObject){
        for(posicao pos : this.objPos){
            if(pos.getxPosition()==newObject.getxPosition() && pos.getyPosition()==newObject.getyPosition())
                return false;
        }
        return this.objPos.add(newObject);
    }
}
