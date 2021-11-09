package src;

public class Objeto {
    private Posicao pos;

    public Objeto(int x, int y){
        pos = new Posicao(x,y);
    }
    public void setPosicao(int x, int y){
        this.pos.setPosicaoX(x);
        this.pos.setPosicaoY(y);
    }
    public Posicao getPosicao(){
        return this.pos;
    }
}
