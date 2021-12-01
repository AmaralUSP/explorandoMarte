package src;

public class Posicao{
    private int posicaoX;
    private int posicaoY;

    public Posicao(int x, int y){
        this.posicaoX = x;
        this.posicaoY = y;
    }
    public void setPosicaoX(int x){
        this.posicaoX = x;
    }
    public void setPosicaoY(int y){
        this.posicaoY = y;
    }
    public int getPosicaoX(){
        return this.posicaoX;
    }
    public int getPosicaoY(){
        return this.posicaoY;
    }
}
