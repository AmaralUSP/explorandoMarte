package src;

public class objeto {
    private posicao position;

    public objeto(int x, int y){
        position = new posicao(x,y);
    }
    public void setPositon(int x, int y){
        this.position.setxPosition(x);
        this.position.setyPosition(y);
    }
    public posicao getposition(){
        return this.position;
    }
}
