package src;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class Malha{
    List<Sonda> objetoPosicao=new ArrayList<Sonda>();
    private Posicao tamanhoMaximo;

    public Malha(int x, int y){
        tamanhoMaximo = new Posicao(x,y);
    }
    public void setTamX(int x){
        this.tamanhoMaximo.setPosicaoX(x);
    }
    public void setTamY(int y){
        this.tamanhoMaximo.setPosicaoY(y);
    }
    public int getTamX(){
        return this.tamanhoMaximo.getPosicaoX();
    }
    public int getTamY(){
        return this.tamanhoMaximo.getPosicaoY();
    }
    public void posicaoValida(int x, int y){
        if(x >= 0 && x <= this.tamanhoMaximo.getPosicaoX() && y >= 0 && y <= this.tamanhoMaximo.getPosicaoY())
            return;
        throw new IllegalArgumentException("Valores fora dos limites da malha!");
    }
    @Override
    public String toString(){
        int x = this.getTamX();
        int y = this.getTamY();
        String print = new String();
        StringBuilder[] newString = new StringBuilder[y+2];
        
        newString[y+1] = new StringBuilder();
        newString[y+1].append("^y\n");

        for(int index=y; index>0; index--){
            newString[index] = new StringBuilder();
            newString[index].append("|").append(IntStream.range(0, x).mapToObj(i -> " 0").collect(Collectors.joining(""))).append("\n");
        }
        
        newString[0] = new StringBuilder();
        newString[0].append(" ").append(IntStream.range(0, x).mapToObj(i -> " ─").collect(Collectors.joining(""))).append(" >x");
        
        for(Sonda pos : this.objetoPosicao){
            int posicaoX = pos.getPosicao().getPosicaoX();
            int posicaoY = pos.getPosicao().getPosicaoY();
            try{
                this.posicaoValida(posicaoX, posicaoY);
            } catch(Exception e){
                continue;
            }
            newString[posicaoY].setCharAt(posicaoX*2, pos.intParaDirecao(pos.getDirecaoAtual()));
        }

        for(int index=y+1; index>=0; index--){
            print = print + newString[index].toString();
        }

        return print;
    }
    public void verificarPosicaoValida(Posicao newObject){
        for(Sonda pos : this.objetoPosicao){
            if(pos.getPosicao().getPosicaoX()==newObject.getPosicaoX() && pos.getPosicao().getPosicaoY()==newObject.getPosicaoY())
                throw new IllegalArgumentException("A sonda colidiu na posicao " + newObject.getPosicaoX() + ' ' + newObject.getPosicaoY());
        }
    }
    public void addNovoObjt(Posicao novoObjeto, char direcao){
        int x = novoObjeto.getPosicaoX();
        int y = novoObjeto.getPosicaoY();
        
        try{
            this.verificarPosicaoValida(novoObjeto);
        }catch(Exception e){
            throw e;
        }

        this.objetoPosicao.add(new Sonda(x, y, direcao, this));
    }
}
