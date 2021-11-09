package src;
import java.util.*;

public class Malha{
    List<Posicao> objPos=new ArrayList<Posicao>();
    private Posicao tamMax;

    public Malha(int x, int y){
        tamMax = new Posicao(x,y);
    }
    public void setTamX(int x){
        this.tamMax.setPosicaoX(x);
    }
    public void setTamY(int y){
        this.tamMax.setPosicaoY(y);
    }
    public int getTamX(){
        return this.tamMax.getPosicaoX();
    }
    public int getTamY(){
        return this.tamMax.getPosicaoY();
    }
    public void posicaoValida(int x, int y){
        if(x >= 0 && x <= this.tamMax.getPosicaoX() && y >= 0 && y <= this.tamMax.getPosicaoY())
            return;
        throw new IllegalArgumentException("Valores fora dos limites da malha!");
    }
    public void imprimirListaDeObjetos(){
        for(Posicao pos : this.objPos)
            System.out.println("x: " + pos.getPosicaoX() + " y: " + pos.getPosicaoY() + '\n');
    }
    public void verificarPosicaoValida(Posicao newObject){
        for(Posicao pos : this.objPos){
            if(pos.getPosicaoX()==newObject.getPosicaoX() && pos.getPosicaoY()==newObject.getPosicaoY())
                throw new IllegalArgumentException("A sonda colidiu na posicao " + newObject.getPosicaoX() + ' ' + newObject.getPosicaoX());
        }
    }
    public void addNovoObjt(Posicao novoObjeto){
        try{
            this.verificarPosicaoValida(novoObjeto);
        }catch(Exception e){
            throw e;
        }

        this.objPos.add(novoObjeto);
    }
}
