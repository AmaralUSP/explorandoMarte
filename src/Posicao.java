package src;

public class Posicao{
    private int[] posAtual = new int[2];

    public Posicao(int x, int y){
        this.posAtual[0] = x;
        this.posAtual[1] = y;
    }
    public void setPosicaoX(int x){
        this.posAtual[0] = x;
    }
    public void setPosicaoY(int y){
        this.posAtual[1] = y;
    }
    public int getPosicaoX(){
        return this.posAtual[0];
    }
    public int getPosicaoY(){
        return this.posAtual[1];
    }
}
