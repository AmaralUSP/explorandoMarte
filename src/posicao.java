package src;

public class posicao {
    private int[] currPosition = new int[2];

    public posicao(int x, int y){
        this.currPosition[0] = x;
        this.currPosition[1] = y;
    }
    public void setxPosition(int x){
        this.currPosition[0] = x;
    }
    public void setyPosition(int y){
        this.currPosition[1] = y;
    }
    public int getxPosition(){
        return this.currPosition[0];
    }
    public int getyPosition(){
        return this.currPosition[1];
    }
}
