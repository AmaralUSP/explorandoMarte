package src;

public class malha{
    private posicao[] shipPos;
    private int xSize;
    private int ySize;

    public malha(int x, int y, int numShip){
        setxSize(x);
        setySize(y);
        this.shipPos = new posicao[numShip];
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

}
