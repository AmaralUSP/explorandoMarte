package src;

public class Objeto {
    private Posicao posicao;

    public Objeto(int x, int y){
        posicao = new Posicao(x,y);
    }
    public void setPosicao(int x, int y){
        this.posicao.setPosicaoX(x);
        this.posicao.setPosicaoY(y);
    }
    public Posicao getPosicao(){
        return this.posicao;
    }
}
