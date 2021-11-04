package src;
import java.util.*;

public class malha{
    List<posicao> objPos=new ArrayList<posicao>();  
    private int xSize;
    private int ySize;

    public malha(int x, int y){
        setxSize(x);
        setySize(y);
    }
    public void setxSize(int x){
        this.xSize = x;
    }
    public void setySize(int y){
        this.ySize = y;
    }
    public int getxSize(){
        return this.xSize;
    }
    public int getySize(){
        return this.ySize;
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
