package src;

import java.util.InputMismatchException;

public class Sonda extends Objeto{
    private Posicao posicaoFinal;
    private int direcaoAtual;
    private static int[][] movimentos = {{ 0, 1},
                                        { 1, 0},
                                        { 0,-1},
                                        {-1, 0}};

    public Sonda(){
        super(0, 0);
        this.direcaoAtual = 0;
    }

    public Sonda(int x, int y, char direcaoInicial, Malha m){
        super(x, y);
        try{
            m.posicaoValida(x, y);
            this.diracaoInvalida(direcaoInicial);
        } catch (Exception e){
            throw new InputMismatchException("Informacoes invalidas!");
        }

        this.posicaoFinal = new Posicao(x, y);
        this.direcaoAtual = diracaoParaInt(direcaoInicial);
    }
    public Posicao getPosFinal(){
        return this.posicaoFinal;
    }
    public int getDirecaoAtual(){
        return this.direcaoAtual;
    }
    private int diracaoParaInt(char direcao){
        switch (direcao){
            case 'N':
               return 0;
            case 'E':
                return 1;
            case 'S':
                return 2;
            case 'W':
                return 3;
            case '*':
                return 4;
            default:
                throw new IllegalArgumentException("Direcao invalida!");
        }
    }
    public char intParaDirecao(int direcao){
        switch (direcao){
            case 0:
               return 'N';
            case 1:
                return 'E';
            case 2:
                return 'S';
            case 3:
                return 'W';
            case 4:
                return '*';
            default:
                throw new IllegalArgumentException("Direcao invalida!");
        }
    }
    public void diracaoInvalida(char direcao){
        if(direcao != 'N' && direcao != 'W' && direcao != 'E' && direcao != 'S' && direcao != '*')
            throw new IllegalArgumentException("Direcao invalida!");
    
    }
    public Posicao pousar(String instrucoes, Malha m){
        if(instrucoes.length() == 0){
            return this.posicaoFinal;
        }
        int numInstrucoes = instrucoes.length();
        char instrucaoAtual;

        for(int indice = 0; indice < numInstrucoes; indice++){
            instrucaoAtual = instrucoes.charAt(indice);

            switch (instrucaoAtual){
                case 'L':
                    if(this.direcaoAtual-1 < 0){
                        this.direcaoAtual = 3;
                    } else{
                        this.direcaoAtual = this.direcaoAtual-1;
                    }
                break;
                case 'R':
                    if(this.direcaoAtual+1 > 3){
                        this.direcaoAtual = 0;
                    } else{
                        this.direcaoAtual = this.direcaoAtual+1;
                    }
                break;
                case 'M':
                    this.posicaoFinal.setPosicaoX(this.posicaoFinal.getPosicaoX() + movimentos[this.direcaoAtual][0]);
                    this.posicaoFinal.setPosicaoY(this.posicaoFinal.getPosicaoY() + movimentos[this.direcaoAtual][1]);
                    if(this.posicaoFinal.getPosicaoX() < 0 || this.posicaoFinal.getPosicaoX() > m.getTamX()
                    || this.posicaoFinal.getPosicaoY() < 0 || this.posicaoFinal.getPosicaoY() > m.getTamY()){
                        System.out.println("A sonda saiu do espaco observavel!");
                        return this.posicaoFinal;
                    }
                    try{
                        m.verificarPosicaoValida(this.posicaoFinal);
                    } catch(Exception e){
                        this.posicaoFinal.setPosicaoX(this.posicaoFinal.getPosicaoX() - movimentos[this.direcaoAtual][0]);
                        this.posicaoFinal.setPosicaoY(this.posicaoFinal.getPosicaoY() - movimentos[this.direcaoAtual][1]);
                        System.out.println("As instrucoes fornecidas causariam uma colisao, por este motivo a sonda pousou antes do acidente!");
                        return this.posicaoFinal;
                    }

                break;
                default:
                    System.out.println("Comando invalido, seguindo para proxima instrucao");
            }
        }
        return this.posicaoFinal; 
    }
}
